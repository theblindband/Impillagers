package com.impillagers.mod.mixin;

import com.impillagers.mod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(CropBlock.class)
public abstract class CropBlockMixin {

    @Shadow
    public abstract int getAge(BlockState state);

    @Shadow
    public abstract int getMaxAge();

    @Shadow
    public abstract BlockState withAge(int age);

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void modifyNaturalGrowth(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {

        if (world.getBlockState(pos.down()).isOf(ModBlocks.FERTILE_FARMLAND)) {
            if (world.getBaseLightLevel(pos, 0) >= 9) {
                int currentAge = this.getAge(state);
                if (currentAge < this.getMaxAge()) {
                    float moisture = 14.0F;
                    if (random.nextInt((int)(25.0F / moisture) + 1) == 0) {
                        world.setBlockState(pos, this.withAge(currentAge + 1), Block.NOTIFY_LISTENERS);
                    }
                }
            }
            ci.cancel();
        }
    }

    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    private void allowFertileFarmland(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (floor.isOf(Blocks.FARMLAND) || floor.isOf(ModBlocks.FERTILE_FARMLAND)) {
            cir.setReturnValue(true);
        }
    }
}