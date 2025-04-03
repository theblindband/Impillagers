package com.impillagers.mod.mixin;

import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {
    @Shadow @Final private static Logger LOGGER;

    @Redirect(
            method = "onDeath",
            at = @At(
                    value = "INVOKE",
                    remap = false,
                    target = "Lorg/slf4j/Logger;info(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"
            )
    )
    private void redirectOnDeathLog(Logger instance, String s, Object o, Object o1) {
        if (!(((Object)this) instanceof ImpillagerEntity)) {
            LOGGER.info("Villager {} died, message: '{}'", o, o1);
        }
    }
}

