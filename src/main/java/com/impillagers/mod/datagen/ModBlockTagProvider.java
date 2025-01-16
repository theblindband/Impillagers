package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.PURPLE_HEART_PLANKS);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.PURPLE_HEART_LOGS);

        getOrCreateTagBuilder(ModTags.Blocks.PURPLE_HEART_LOGS)
                .add(ModBlocks.PURPLE_HEART_LOG)
                .add(ModBlocks.PURPLE_HEART_WOOD)
                .add(ModBlocks.STRIPPED_PURPLE_HEART_LOG)
                .add(ModBlocks.STRIPPED_PURPLE_HEART_WOOD);
    }
}
