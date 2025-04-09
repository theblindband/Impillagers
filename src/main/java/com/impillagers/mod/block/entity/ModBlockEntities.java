package com.impillagers.mod.block.entity;

import com.impillagers.mod.Impillagers;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {


    private static <T extends BlockEntityType<?>> T register(String name, T type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Impillagers.MOD_ID, name), type);
    }

    public static void registerModBlockEntities(){
        //Impillagers.LOGGER.info("Registering Mod Block Entities for " + Impillagers.MOD_ID);
    }
}