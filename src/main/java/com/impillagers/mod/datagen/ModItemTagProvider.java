package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;
//TODO: FIX
public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.DECORATED_POT_SHERDS)
                .add(ModItems.IMP_POTTERY_SHERD)
                .add(ModItems.COVER_POTTERY_SHERD);

        /*getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.PAINTED_SMITHING_TEMPLATE);*/

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

        getOrCreateTagBuilder(ItemTags.TALL_FLOWERS)
                .add(ModBlocks.SWAMP_REED.asItem());

        getOrCreateTagBuilder(ModTags.Items.FROG_POISONOUS_FOOD)
                .add(ModBlocks.FIREFLY_BUSH.asItem())
                .add((ModBlocks.FIREFLY_BOTTLE.asItem()));

        getOrCreateTagBuilder(ModTags.Items.DYED_TERRACOTTA)
                .add(Blocks.WHITE_TERRACOTTA.asItem())
                .add(Blocks.LIGHT_GRAY_TERRACOTTA.asItem())
                .add(Blocks.GRAY_TERRACOTTA.asItem())
                .add(Blocks.BLACK_TERRACOTTA.asItem())
                .add(Blocks.BROWN_TERRACOTTA.asItem())
                .add(Blocks.RED_TERRACOTTA.asItem())
                .add(Blocks.ORANGE_TERRACOTTA.asItem())
                .add(Blocks.YELLOW_TERRACOTTA.asItem())
                .add(Blocks.LIME_TERRACOTTA.asItem())
                .add(Blocks.GREEN_TERRACOTTA.asItem())
                .add(Blocks.CYAN_TERRACOTTA.asItem())
                .add(Blocks.LIGHT_BLUE_TERRACOTTA.asItem())
                .add(Blocks.BLUE_TERRACOTTA.asItem())
                .add(Blocks.PURPLE_TERRACOTTA.asItem())
                .add(Blocks.MAGENTA_TERRACOTTA.asItem())
                .add(Blocks.PINK_TERRACOTTA.asItem());

        getOrCreateTagBuilder(ModTags.Items.STAINED_GLASS)
                .add(Blocks.WHITE_STAINED_GLASS.asItem())
                .add(Blocks.LIGHT_GRAY_STAINED_GLASS.asItem())
                .add(Blocks.GRAY_STAINED_GLASS.asItem())
                .add(Blocks.BLACK_STAINED_GLASS.asItem())
                .add(Blocks.BROWN_STAINED_GLASS.asItem())
                .add(Blocks.RED_STAINED_GLASS.asItem())
                .add(Blocks.ORANGE_STAINED_GLASS.asItem())
                .add(Blocks.YELLOW_STAINED_GLASS.asItem())
                .add(Blocks.LIME_STAINED_GLASS.asItem())
                .add(Blocks.GREEN_STAINED_GLASS.asItem())
                .add(Blocks.CYAN_STAINED_GLASS.asItem())
                .add(Blocks.LIGHT_BLUE_STAINED_GLASS.asItem())
                .add(Blocks.BLUE_STAINED_GLASS.asItem())
                .add(Blocks.PURPLE_STAINED_GLASS.asItem())
                .add(Blocks.MAGENTA_STAINED_GLASS.asItem())
                .add(Blocks.PINK_STAINED_GLASS.asItem());

        getOrCreateTagBuilder(ModTags.Items.STAINED_GLASS_PANE)
                .add(Blocks.WHITE_STAINED_GLASS_PANE.asItem())
                .add(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE.asItem())
                .add(Blocks.GRAY_STAINED_GLASS_PANE.asItem())
                .add(Blocks.BLACK_STAINED_GLASS_PANE.asItem())
                .add(Blocks.BROWN_STAINED_GLASS_PANE.asItem())
                .add(Blocks.RED_STAINED_GLASS_PANE.asItem())
                .add(Blocks.ORANGE_STAINED_GLASS_PANE.asItem())
                .add(Blocks.YELLOW_STAINED_GLASS_PANE.asItem())
                .add(Blocks.LIME_STAINED_GLASS_PANE.asItem())
                .add(Blocks.GREEN_STAINED_GLASS_PANE.asItem())
                .add(Blocks.CYAN_STAINED_GLASS_PANE.asItem())
                .add(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE.asItem())
                .add(Blocks.BLUE_STAINED_GLASS_PANE.asItem())
                .add(Blocks.PURPLE_STAINED_GLASS_PANE.asItem())
                .add(Blocks.MAGENTA_STAINED_GLASS_PANE.asItem())
                .add(Blocks.PINK_STAINED_GLASS_PANE.asItem());

        getOrCreateTagBuilder(ModTags.Items.DYED_CANDLE)
                .add(Blocks.WHITE_CANDLE.asItem())
                .add(Blocks.LIGHT_GRAY_CANDLE.asItem())
                .add(Blocks.GRAY_CANDLE.asItem())
                .add(Blocks.BLACK_CANDLE.asItem())
                .add(Blocks.BROWN_CANDLE.asItem())
                .add(Blocks.RED_CANDLE.asItem())
                .add(Blocks.ORANGE_CANDLE.asItem())
                .add(Blocks.YELLOW_CANDLE.asItem())
                .add(Blocks.LIME_CANDLE.asItem())
                .add(Blocks.GREEN_CANDLE.asItem())
                .add(Blocks.CYAN_CANDLE.asItem())
                .add(Blocks.LIGHT_BLUE_CANDLE.asItem())
                .add(Blocks.BLUE_CANDLE.asItem())
                .add(Blocks.PURPLE_CANDLE.asItem())
                .add(Blocks.MAGENTA_CANDLE.asItem())
                .add(Blocks.PINK_CANDLE.asItem());

        getOrCreateTagBuilder(ModTags.Items.CONCRETE_POWDER)
                .add(Blocks.WHITE_CONCRETE_POWDER.asItem())
                .add(Blocks.LIGHT_GRAY_CONCRETE_POWDER.asItem())
                .add(Blocks.GRAY_CONCRETE_POWDER.asItem())
                .add(Blocks.BLACK_CONCRETE_POWDER.asItem())
                .add(Blocks.BROWN_CONCRETE_POWDER.asItem())
                .add(Blocks.RED_CONCRETE_POWDER.asItem())
                .add(Blocks.ORANGE_CONCRETE_POWDER.asItem())
                .add(Blocks.YELLOW_CONCRETE_POWDER.asItem())
                .add(Blocks.LIME_CONCRETE_POWDER.asItem())
                .add(Blocks.GREEN_CONCRETE_POWDER.asItem())
                .add(Blocks.CYAN_CONCRETE_POWDER.asItem())
                .add(Blocks.LIGHT_BLUE_CONCRETE_POWDER.asItem())
                .add(Blocks.BLUE_CONCRETE_POWDER.asItem())
                .add(Blocks.PURPLE_CONCRETE_POWDER.asItem())
                .add(Blocks.MAGENTA_CONCRETE_POWDER.asItem())
                .add(Blocks.PINK_CONCRETE_POWDER.asItem());

    }
}
