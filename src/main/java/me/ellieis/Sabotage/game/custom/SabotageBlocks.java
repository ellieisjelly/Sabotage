package me.ellieis.Sabotage.game.custom;

import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import me.ellieis.Sabotage.Sabotage;
import me.ellieis.Sabotage.game.custom.blocks.*;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;


public class SabotageBlocks {
    public static final SabotageChest SABOTAGE_CHEST = new SabotageChest(AbstractBlock.Settings.copy(Blocks.CHEST).dropsNothing().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Sabotage.identifier("sabotage_chest"))), Blocks.CHEST);
    public static final TesterWool TESTER_WOOL = new TesterWool(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).dropsNothing().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Sabotage.identifier("tester_wool"))), Blocks.WHITE_WOOL);
    public static final TesterSign TESTER_SIGN = new TesterSign(AbstractBlock.Settings.copy(Blocks.OAK_SIGN).dropsNothing().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Sabotage.identifier("tester_sign"))), Blocks.OAK_SIGN);
    public static final WallTesterSign WALL_TESTER_SIGN = new WallTesterSign(AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Sabotage.identifier("wall_tester_sign"))), Blocks.OAK_WALL_SIGN);

    public static final BlockEntityType<SabotageChestBlockEntity> SABOTAGE_CHEST_ENTITY = FabricBlockEntityTypeBuilder.create(SabotageChestBlockEntity::new, SABOTAGE_CHEST).build();
    public static void register() {
        register("sabotage_chest", SABOTAGE_CHEST);
        register("tester_wool", TESTER_WOOL);
        register("tester_sign", TESTER_SIGN);
        register("wall_tester_sign", WALL_TESTER_SIGN);
        registerBlockEntity("sabotage_chest_block_entity", SABOTAGE_CHEST_ENTITY);

    }

    private static <T extends Block> T register(String id, T block) {
        return Registry.register(Registries.BLOCK, Sabotage.identifier(id), block);
    }
    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String id, BlockEntityType<T> type) {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, Sabotage.identifier(id), type);
        PolymerBlockUtils.registerBlockEntity(SABOTAGE_CHEST_ENTITY);
        return type;
    }
}
