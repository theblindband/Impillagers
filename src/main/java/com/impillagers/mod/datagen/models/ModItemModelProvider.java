package com.impillagers.mod.datagen.models;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Impillagers.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Generates the item modeles
        basicBlock(ModBlocks.PURPLE_HEART_LOG.asItem());
        basicBlock(ModBlocks.PURPLE_HEART_WOOD.asItem());
        basicBlock(ModBlocks.STRIPPED_PURPLE_HEART_LOG.asItem());
        basicBlock(ModBlocks.STRIPPED_PURPLE_HEART_WOOD.asItem());
        basicBlock(ModBlocks.PURPLE_HEART_PLANKS.asItem());
        basicBlock(ModBlocks.PURPLE_HEART_STAIRS.asItem());
        basicBlock(ModBlocks.PURPLE_HEART_SLAB.asItem());

        basicItem(ModItems.IMP_POTTERY_SHERD.get());
    }

    private void basicBlock(Item item) {
        withExistingParent(name(item), modLoc(ModelProvider.BLOCK_FOLDER + "/" + name(item)));
    }
    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
    private String name(Block block) {
        return key(block).getPath();
    }
    private ResourceLocation key(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }
    private String name(Item item) {
        return key(item).getPath();
    }
}
