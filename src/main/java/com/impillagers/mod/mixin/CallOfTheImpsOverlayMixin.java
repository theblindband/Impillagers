package com.impillagers.mod.mixin;


import com.impillagers.mod.Impillagers;
import com.impillagers.mod.util.OpacityAccessor;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(InGameHud.class)
public abstract class CallOfTheImpsOverlayMixin implements OpacityAccessor {

	@Unique
	private static final Identifier OVERLAY = Identifier.of(Impillagers.MOD_ID, "textures/misc/looking_at_village_overlay.png");

	@Unique
	private static float overlayOpacity = 0.0F;

	@Override
	public void impillagers$setOverlayOpacity(float opacity) {overlayOpacity = opacity;}

	@Inject(method = "renderMiscOverlays", at = @At("HEAD"))
	private void injectCustomOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo info) {
		float opacity = overlayOpacity;

		if (opacity > 0.0F) {
			((InGameHudAccessor) this).invokeRenderOverlay(context, OVERLAY, opacity);
			Impillagers.LOGGER.debug("Rendering overlay with opacity: {}", Optional.of(opacity));
		}
	}
}