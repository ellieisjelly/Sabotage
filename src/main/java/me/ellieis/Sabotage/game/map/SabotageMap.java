package me.ellieis.Sabotage.game.map;

import me.ellieis.Sabotage.game.config.SabotageConfig;
import me.ellieis.Sabotage.game.custom.blocks.SabotageChest;
import me.ellieis.Sabotage.game.custom.blocks.TesterWool;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import xyz.nucleoid.map_templates.MapTemplate;
import xyz.nucleoid.map_templates.TemplateRegion;
import xyz.nucleoid.plasmid.api.game.GameOpenException;
import xyz.nucleoid.plasmid.api.game.world.generator.TemplateChunkGenerator;
import xyz.nucleoid.plasmid.api.util.PlayerRef;


import java.util.*;

import static me.ellieis.Sabotage.game.custom.SabotageBlocks.SABOTAGE_CHEST;
record ChestInfo(BlockPos pos, Direction direction) { }

public class SabotageMap {
    private final SabotageConfig config;
    private final MapTemplate template;
    private final List<TemplateRegion> spawns;
    private final TemplateRegion testerCloseRegion;
    private final List<BlockPos> testerWools = new ArrayList<>();
    private final List<ChestInfo> chestSpawns = new ArrayList<>();
    private final Map<PlayerRef, Vec3d> playerSpawnPos = new HashMap<>();
    private ServerWorld world;

    public SabotageMap(MapTemplate template, SabotageConfig config) {
        this.config = config;
        this.template = template;
        this.spawns = template.getMetadata().getRegions("spawn").toList();
        this.testerCloseRegion = template.getMetadata().getFirstRegion("tester_close_region");

        if (this.spawns.isEmpty()) {
            throw new GameOpenException(Text.literal("Failed to load spawns, as there aren't any."));
        }

        if (this.testerCloseRegion == null) {
            throw new GameOpenException(Text.literal("Failed to load tester close region, as there isn't any"));
        }

        // generate chest positions from placed SabotageChests
        template.getBounds().forEach(blockPos -> {
            BlockState blockState = template.getBlockState(blockPos);
            Block block = blockState.getBlock();
            if (block instanceof SabotageChest) {
                // note to self: make your positions immutable in forEach loops..
                chestSpawns.add(new ChestInfo(blockPos.toImmutable(), blockState.get(Properties.HORIZONTAL_FACING)));
                template.setBlockState(blockPos, Blocks.AIR.getDefaultState());
            } else if (block instanceof TesterWool) {
                testerWools.add(blockPos.toImmutable());
            }
        });
    }
    public void setWorld(ServerWorld world) {
        this.world = world;
    }

    public void generateChests() {
        Collections.shuffle(chestSpawns);

        // Make sure that the chest count doesn't go over the amount of chest positions
        int chestCount = Math.min(config.chestCount(), chestSpawns.size());
        for (ChestInfo chestInfo : chestSpawns) {
            if (chestCount > 0) {
                chestCount--;
                world.setBlockState(chestInfo.pos(), SABOTAGE_CHEST.getDefaultState().with(Properties.HORIZONTAL_FACING, chestInfo.direction()));
            } else {
                break;
            }
        }
    }

    public List<BlockPos> getTesterWools() {
        return this.testerWools;
    }
    public MapTemplate getTemplate() {
        return this.template;
    }

    public TemplateRegion getTesterCloseRegion() {
        return this.testerCloseRegion;
    }

    public List<TemplateRegion> getSpawns() {
        return this.spawns;
    }

    public Map<PlayerRef, Vec3d> getPlayerSpawns() {
        return this.playerSpawnPos;
    }

    public void spawnPlayer(ServerWorld world, ServerPlayerEntity plr) {
        TemplateRegion spawn = spawns.get(new Random().nextInt(spawns.size()));
        Vec3d pos = spawn.getBounds().centerBottom();
        plr.teleport(world, pos.getX(), pos.getY(), pos.getZ(), new HashSet<>(), spawn.getData().getFloat("Rotation"), 0, true);
        plr.setOnGround(true);
        plr.setVelocity(0,0,0);
        this.playerSpawnPos.put(new PlayerRef(plr.getUuid()), pos);
    }

    public ChunkGenerator asChunkGenerator(MinecraftServer server) {
        return new TemplateChunkGenerator(server, this.template);
    }
}
