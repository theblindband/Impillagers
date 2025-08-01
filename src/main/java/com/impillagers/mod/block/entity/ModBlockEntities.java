package com.impillagers.mod.block.entity;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<WasteBasketBlockEntity> WASTE_BASKET_BLOCK_ENTITY = registerBlockEntity("waste_basket_block_entity", WasteBasketBlockEntity::new, ModBlocks.WASTE_BASKET);
    public static final BlockEntityType<SafeBlockEntity> SAFE_BLOCK_ENTITY = registerBlockEntity("safe_block_entity", SafeBlockEntity::new, ModBlocks.SAFE);

    // For suspicious dirt, you need to create a brushable block entity
    public static final BlockEntityType<BrushableBlockEntity> SUSPICIOUS_DIRT = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Impillagers.MOD_ID, "suspicious_dirt"),
            BlockEntityType.Builder.create(BrushableBlockEntity::new, ModBlocks.SUSPICIOUS_DIRT).build(null)
    );

    public static void registerModBlockEntities() {
        // This ensures the class is loaded and all static fields are initialized
        //Impillagers.LOGGER.info("Registering Mod Block Entities for " + Impillagers.MOD_ID);
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, BlockEntityType.BlockEntityFactory<? extends T> factory, Block... validBlocks) {
        Identifier id = Identifier.of(Impillagers.MOD_ID, name);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, BlockEntityType.Builder.<T>create(factory, validBlocks).build(null));
    }
}