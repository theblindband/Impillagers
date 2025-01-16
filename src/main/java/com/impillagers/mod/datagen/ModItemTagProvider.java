package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.PURPLE_HEART_PLANKS.asItem());

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.PURPLE_HEART_LOGS);

        getOrCreateTagBuilder(ModTags.Items.PURPLE_HEART_LOGS)
                .add(ModBlocks.PURPLE_HEART_LOG.asItem())
                .add(ModBlocks.PURPLE_HEART_WOOD.asItem())
                .add(ModBlocks.STRIPPED_PURPLE_HEART_LOG.asItem())
                .add(ModBlocks.STRIPPED_PURPLE_HEART_WOOD.asItem());
    }
}
