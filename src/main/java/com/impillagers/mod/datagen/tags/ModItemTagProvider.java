package com.impillagers.mod.datagen.tags;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
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
        //Generates the item tags
        tag(ModTags.Items.PURPLE_HEART_LOGS)
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_LOG.get()))
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_WOOD.get()))
                .add(Item.byBlock(ModBlocks.STRIPPED_PURPLE_HEART_LOG.get()))
                .add(Item.byBlock(ModBlocks.STRIPPED_PURPLE_HEART_WOOD.get()));

        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.PURPLE_HEART_LOGS);

        tag(ItemTags.PLANKS)
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_PLANKS.get()));

        tag(ItemTags.WOODEN_STAIRS)
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_STAIRS.get()));

        tag(ItemTags.WOODEN_SLABS)
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_SLAB.get()));

        tag(ItemTags.WOODEN_FENCES)
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_FENCE.get()));

        tag(ItemTags.FENCE_GATES)
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_FENCE_GATE.get()));

        tag(ItemTags.WOODEN_DOORS)
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_DOOR.get()));

        tag(ItemTags.TRAPDOORS)
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_TRAPDOOR.get()));

        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_PRESSURE_PLATE.get()));

        tag(ItemTags.WOODEN_BUTTONS)
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_BUTTON.get()));

        tag(ItemTags.LEAVES)
                .add(Item.byBlock(ModBlocks.PURPLE_HEART_LEAVES.get()));

        tag(ItemTags.DECORATED_POT_SHERDS)
                .add(ModItems.IMP_POTTERY_SHERD.get());
    }
}
