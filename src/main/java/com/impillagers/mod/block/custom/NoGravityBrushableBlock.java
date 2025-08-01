package com.impillagers.mod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrushableBlock;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class NoGravityBrushableBlock extends BrushableBlock {
    public NoGravityBrushableBlock(Block baseBlock, SoundEvent brushingSound, SoundEvent brushingCompleteSound, Settings settings) {
        super(baseBlock, settings, brushingCompleteSound, brushingSound);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBlockEntity(pos) instanceof BrushableBlockEntity brushableBlockEntity) {
            brushableBlockEntity.scheduledTick();
        }
    }
}