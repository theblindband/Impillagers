package com.impillagers.mod.screen;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.screen.custom.SafeScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<SafeScreenHandler> SAFE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Impillagers.MOD_ID, "safe_screen_handler"),
                    new ExtendedScreenHandlerType<>(SafeScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerModScreenHandlers() {
        //Impillagers.LOGGER.info("Registering Screen Handlers for " + Impillagers.MOD_ID);
    }
}