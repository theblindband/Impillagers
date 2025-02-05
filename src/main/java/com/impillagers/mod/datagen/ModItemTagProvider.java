package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.DECORATED_POT_SHERDS)
                .add(ModItems.IMP_POTTERY_SHERD)
                .add(ModItems.COVER_POTTERY_SHERD);

        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.PAINTED_SMITHING_TEMPLATE);

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.PURPLE_HEART_LOGS);

        getOrCreateTagBuilder(ModTags.Items.PURPLE_HEART_LOGS)
                .add(ModBlocks.PURPLE_HEART_LOG.asItem())
                .add(ModBlocks.PURPLE_HEART_WOOD.asItem())
                .add(ModBlocks.STRIPPED_PURPLE_HEART_LOG.asItem())
                .add(ModBlocks.STRIPPED_PURPLE_HEART_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.PURPLE_HEART_PLANKS.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.PURPLE_HEART_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.PURPLE_HEART_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.PURPLE_HEART_FENCE.asItem());
        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(ModBlocks.PURPLE_HEART_FENCE_GATE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.PURPLE_HEART_DOOR.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.PURPLE_HEART_TRAPDOOR.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.PURPLE_HEART_PRESSURE_PLATE.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(ModBlocks.PURPLE_HEART_BUTTON.asItem());

        getOrCreateTagBuilder(ItemTags.LEAVES)
                .add(ModBlocks.PURPLE_HEART_LEAVES.asItem());
        getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(ModBlocks.PURPLE_HEART_SAPLING.asItem());

        getOrCreateTagBuilder(ItemTags.SIGNS)
                .add(ModItems.PURPLE_HEART_SIGN);
        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                .add(ModItems.PURPLE_HEART_HANGING_SIGN);

        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS)
                .add(ModBlocks.BELLADONNA.asItem());
    }
}
