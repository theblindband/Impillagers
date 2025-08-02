package com.impillagers.mod.mixin;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.effect.ModEffectClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GameRenderer.class)
public class FovMixin {
    @ModifyVariable(method = "getFov", at = @At("STORE"), ordinal = 0)
    private double modifyFov(double fov) {
        Impillagers.LOGGER.info("Original FOV: {}", fov);
        return ModEffectClient.getAdjustedFOV(fov);
    }
}
