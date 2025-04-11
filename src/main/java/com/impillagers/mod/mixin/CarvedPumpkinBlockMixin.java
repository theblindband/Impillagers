package com.impillagers.mod.mixin;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.entity.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CarvedPumpkinBlock.class)
public class CarvedPumpkinBlockMixin {

    @Inject(method = "trySpawnEntity", at = @At("TAIL"))
    private void injectTrySpawnEntity(World world, BlockPos pos, CallbackInfo ci) {
        if (!(world.getBlockState(pos).isOf(Blocks.CARVED_PUMPKIN) ||
                world.getBlockState(pos).isOf(Blocks.JACK_O_LANTERN))) {
            return;
        }
        if (!world.getBlockState(pos.down()).isOf(ModBlocks.DUNG_BLOCK)) {
            return;
        }

        {
            BlockState pumpkinState = world.getBlockState(pos);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
            world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(pumpkinState));
        }

        {
            BlockPos dungPos = pos.down();
            BlockState dungState = world.getBlockState(dungPos);
            world.setBlockState(dungPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
            world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, dungPos, Block.getRawIdFromState(dungState));
        }

        Entity dungGolem = ModEntities.DUNG_GOLEM.create(world);
        if (dungGolem != null) {
            dungGolem.refreshPositionAndAngles(pos.getX() + 0.5, pos.down().getY(), pos.getZ() + 0.5, 0.0F, 0.0F);
            world.spawnEntity(dungGolem);
        }
    }


    @Inject(method = "canDispense", at = @At("RETURN"), cancellable = true)
    private void injectCanDispense(WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos.down()).isOf(ModBlocks.DUNG_BLOCK)) {
            cir.setReturnValue(true);
        }
    }
}
