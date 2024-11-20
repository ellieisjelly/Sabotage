package me.ellieis.Sabotage.game.custom.blocks;

import me.ellieis.Sabotage.Sabotage;
import me.ellieis.Sabotage.game.phase.SabotageActive;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.entity.SignText;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BaseTesterSign  {
    public static void onUse(World world, PlayerEntity player, BlockPos pos) {
        if (!world.isClient()) {
            ServerPlayerEntity plr = (ServerPlayerEntity) player;

            for (SabotageActive game : Sabotage.activeGames) {
                if (game.getWorld().equals(world)) {
                    if (!game.testEntity(plr, pos.toCenterPos())) {
                        plr.sendMessage(Text.translatable("sabotage.tester.fail").formatted(Formatting.YELLOW));
                    }
                    break;
                }
            }
        }
    }

    public static void onPlaced(World world, BlockPos pos) {
        if (!world.isClient()) {
            SignBlockEntity be = (SignBlockEntity) world.getBlockEntity(pos);
            Text[] text = {Text.literal("Click this"), Text.literal("sign to"), Text.literal("start the"), Text.literal("test")};
            be.setText(new SignText(text, text, DyeColor.RED, true), true);
        }
    }
}
