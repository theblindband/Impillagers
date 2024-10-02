package com.impillagers.mod.datagen;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Impillagers.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.PLANKS)
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_PLANKS.get()));

        tag(ItemTags.DECORATED_POT_SHERDS)
                .add(ModItems.IMP_POTTERY_SHERD.get());
    }
}
