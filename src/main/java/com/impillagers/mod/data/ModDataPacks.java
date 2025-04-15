package com.impillagers.mod.data;

import com.impillagers.mod.Impillagers;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ModDataPacks {

    public static void registerDataPacks() {
        Impillagers.LOGGER.info("Registering Data Packs for Impillagers");

        // The datapack folder is at: resources/resourcepacks/datapacks/
        // requiredModId is optional, set to null for no conditional registering
        registerBuiltinDatapack("compat_terralith", true, "terralith");
        registerBuiltinDatapack("compat_clifftree", true, "mr_clifftree");
    }

    private static final ModContainer modContainer = FabricLoader.getInstance()
            .getModContainer(Impillagers.MOD_ID)
            .orElseThrow(() -> new IllegalStateException("Mod container not found for " + Impillagers.MOD_ID));


    public static void registerBuiltinDatapack(String datapackName, boolean autoEnable, @Nullable String requiredModId) {
        String fullPath = "datapacks/" + datapackName;
        boolean shouldAutoEnable = autoEnable;

        if (requiredModId != null) {
            if (FabricLoader.getInstance().isModLoaded(requiredModId)) {
                if (autoEnable) {
                    Impillagers.LOGGER.info("{} DETECTED - Registering datapack '{}' and auto-enabling it",
                            requiredModId.toUpperCase(), datapackName);
                } else {
                    Impillagers.LOGGER.info("{} DETECTED - Registering datapack '{}' But not auto-enabling it",
                            requiredModId.toUpperCase(), datapackName);
                }
            } else {
                Impillagers.LOGGER.info("Registering datapack '{}' But not auto-enabling it",
                        datapackName);
                shouldAutoEnable = false;
            }
        } else {
            Impillagers.LOGGER.info("Registering datapack '{}' with auto-enable set to {}",
                    datapackName, autoEnable);
        }

        ResourcePackActivationType activationType = shouldAutoEnable
                ? ResourcePackActivationType.DEFAULT_ENABLED
                : ResourcePackActivationType.NORMAL;

        Identifier datapackId = Identifier.of(Impillagers.MOD_ID, fullPath);
        ResourceManagerHelper.registerBuiltinResourcePack(datapackId, modContainer, activationType);
    }
}
