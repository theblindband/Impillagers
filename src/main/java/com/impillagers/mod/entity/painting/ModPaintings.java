package com.impillagers.mod.entity.painting;

import com.impillagers.mod.Impillagers;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPaintings {

    public static final PaintingVariant OFFER = registerPainting("offer", new PaintingVariant(16,16));


    private static PaintingVariant registerPainting(String name, PaintingVariant PaintingVariant) {
        return Registry.register(Registries.PAINTING_VARIANT, new Identifier(Impillagers.MOD_ID, name), PaintingVariant);
    }

    public static void registerPaintings() {
        //Impillagers.LOGGER.info("Registering Mod Paintings for " + Impillagers.MOD_ID);
    }
}
