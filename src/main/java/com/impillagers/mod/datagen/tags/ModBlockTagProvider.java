package com.impillagers.mod.datagen.tags;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Impillagers.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //Generates the files for block tags
        tag(ModTags.Blocks.PURPLE_HEART_LOGS)
                .add(ModBlocks.PURPLE_HEART_LOG.get())
                .add(ModBlocks.PURPLE_HEART_WOOD.get())
                .add(ModBlocks.STRIPPED_PURPLE_HEART_LOG.get())
                .add(ModBlocks.STRIPPED_PURPLE_HEART_WOOD.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.PURPLE_HEART_LOGS);

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.PURPLE_HEART_LOG.get())
                .add(ModBlocks.PURPLE_HEART_WOOD.get())
                .add(ModBlocks.STRIPPED_PURPLE_HEART_LOG.get())
                .add(ModBlocks.STRIPPED_PURPLE_HEART_WOOD.get())
                .add(ModBlocks.PURPLE_HEART_PLANKS.get())
                .add(ModBlocks.PURPLE_HEART_STAIRS.get())
                .add(ModBlocks.PURPLE_HEART_SLAB.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.PURPLE_HEART_PLANKS.get());

        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.PURPLE_HEART_STAIRS.get());

        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.PURPLE_HEART_SLAB.get());

    }
}