package com.impillagers.mod.mixin;

import com.impillagers.mod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SugarCaneBlock.class)
public abstract class SugarCaneBlockMixin extends Block {

    public SugarCaneBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void onRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (!world.isAir(pos.up())) {
            return;
        }

        int i = 1;
        while (world.getBlockState(pos.down(i)).isOf(this)) {
            i++;
        }

        BlockPos basePos = pos.down(i);
        BlockState baseState = world.getBlockState(basePos);

        boolean isFertile = baseState.isOf(ModBlocks.FERTILE_DIRT) || baseState.isOf(ModBlocks.FERTILE_FARMLAND);
        int maxHeight = isFertile ? 5 : 3;

        if (i >= maxHeight) {
            return;
        }

        if (isFertile) {
            int age = state.get(SugarCaneBlock.AGE);
            int newAge = age + 2;
            if (newAge >= 15) {
                world.setBlockState(pos.up(), this.getDefaultState());
                world.setBlockState(pos, state.with(SugarCaneBlock.AGE, 0), Block.NO_REDRAW);
            } else {
                world.setBlockState(pos, state.with(SugarCaneBlock.AGE, newAge), Block.NO_REDRAW);
            }
            ci.cancel();
        }
    }
}