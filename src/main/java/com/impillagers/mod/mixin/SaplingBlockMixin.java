package com.impillagers.mod.mixin;

import com.impillagers.mod.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SaplingBlock.class)
public abstract class SaplingBlockMixin {

    @Shadow
    public abstract void generate(ServerWorld world, BlockPos pos, BlockState state, Random random);

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void onRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        BlockPos belowPos = pos.down();
        BlockState blockBelow = world.getBlockState(belowPos);

        if (blockBelow.isOf(ModBlocks.FERTILE_DIRT) || blockBelow.isOf(ModBlocks.FERTILE_FARMLAND)) {
            if (world.getLightLevel(pos.up()) >= 9 && random.nextInt(3) == 0) {
                this.generate(world, pos, state, random);
            }
            ci.cancel();
        }
    }
}

