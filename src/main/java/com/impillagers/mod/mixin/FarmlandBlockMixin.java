package com.impillagers.mod.mixin;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.block.custom.FertileFarmlandBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {

    @Shadow
    public static void setToDirt(Entity entity, BlockState state, World world, BlockPos pos) {
    }

    @Redirect(
            method = "onLandedUpon",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/FarmlandBlock;setToDirt(Lnet/minecraft/entity/Entity;Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"
            )
    )
    private void redirectSetToDirt(Entity entity, BlockState state, World world, BlockPos pos) {
        if (state.getBlock() == ModBlocks.FERTILE_FARMLAND) {
            FertileFarmlandBlock.setToFertileDirt(entity, state, world, pos);
        } else {
            setToDirt(entity, state, world, pos);
        }
    }
}
