package com.impillagers.mod.mixin;

import com.impillagers.mod.effect.ModEffectClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class FovMixin {
    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void onGetFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> cir) {
        double baseFov = cir.getReturnValue();
        double modifiedFov = ModEffectClient.getAdjustedFOV(baseFov);
        cir.setReturnValue(modifiedFov);
    }
}
