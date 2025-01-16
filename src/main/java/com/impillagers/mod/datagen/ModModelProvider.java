package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURPLE_HEART_PLANKS);

        blockStateModelGenerator.registerLog(ModBlocks.PURPLE_HEART_LOG).log(ModBlocks.PURPLE_HEART_LOG).wood(ModBlocks.PURPLE_HEART_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_PURPLE_HEART_LOG).log(ModBlocks.STRIPPED_PURPLE_HEART_LOG).wood(ModBlocks.STRIPPED_PURPLE_HEART_WOOD);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.IMP_POTTERY_SHERD, Models.GENERATED);
    }
}
