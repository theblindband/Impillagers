package com.impillagers.mod.block.entity;

import com.impillagers.mod.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static void registerModBlockEntities(){
        //Impillagers.LOGGER.info("Registering Mod Block Entities for " + Impillagers.MOD_ID);

        BlockEntityType.BRUSHABLE_BLOCK.addSupportedBlock(ModBlocks.SUSPICIOUS_DIRT);
    }
}
