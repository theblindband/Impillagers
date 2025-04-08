package com.impillagers.mod.mixin;

import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerEntity.class)
public class ImpillagerLightningMixin {

    @Redirect(
            method = "onStruckByLightning",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/ServerWorld;getDifficulty()Lnet/minecraft/world/Difficulty;"
            )
    )
    private Difficulty redirectGetDifficulty(ServerWorld world) {
        if ((Object)this instanceof ImpillagerEntity) {
            return Difficulty.PEACEFUL;
        }
        return world.getDifficulty();
    }
}