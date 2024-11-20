package me.ellieis.Sabotage.game.custom.blocks;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import xyz.nucleoid.packettweaker.PacketContext;

public class TesterWool extends Block implements PolymerBlock {
    private final Block virtualBlock;
    public TesterWool(Settings settings, Block virtualBlock) {
        super(settings);
        this.virtualBlock = virtualBlock;
    }
    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return this.virtualBlock.getStateWithProperties(state);
    }
}
