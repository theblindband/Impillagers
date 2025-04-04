package com.impillagers.mod.entity.client;

import com.impillagers.mod.effect.ModEffectClient;
import com.impillagers.mod.util.OpacityAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;

public class ModHud {
    public static void initializeModHud() {}

    public static void renderCallOfTheImpsOverlay(float opacity) {
        InGameHud inGameHud = MinecraftClient.getInstance().inGameHud;
        if (inGameHud instanceof OpacityAccessor) {
            ((OpacityAccessor) inGameHud).impillagers$setOverlayOpacity(opacity);
        }

        ModEffectClient.updateZoomState(opacity);
    }
}

