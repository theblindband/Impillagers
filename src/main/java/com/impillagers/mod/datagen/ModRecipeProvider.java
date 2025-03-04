package com.impillagers.mod.datagen;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerBarkBlockRecipe(exporter, ModBlocks.PURPLE_HEART_LOG.asItem(), ModBlocks.PURPLE_HEART_WOOD);
        offerBarkBlockRecipe(exporter, ModBlocks.STRIPPED_PURPLE_HEART_LOG.asItem(), ModBlocks.STRIPPED_PURPLE_HEART_WOOD);

        offerPlanksRecipe(exporter, ModBlocks.PURPLE_HEART_PLANKS.asItem(), ModTags.Items.PURPLE_HEART_LOGS, 4);

        offerStairsRecipe(exporter, ModBlocks.PURPLE_HEART_STAIRS, ModBlocks.PURPLE_HEART_PLANKS);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPLE_HEART_SLAB, ModBlocks.PURPLE_HEART_PLANKS);
        offerFenceRecipe(exporter, ModBlocks.PURPLE_HEART_FENCE, ModBlocks.PURPLE_HEART_PLANKS);
        offerFenceGateRecipe(exporter, ModBlocks.PURPLE_HEART_FENCE_GATE, ModBlocks.PURPLE_HEART_PLANKS);

        offerDoorRecipe(exporter, ModBlocks.PURPLE_HEART_DOOR, ModBlocks.PURPLE_HEART_PLANKS);
        offerTrapdoorRecipe(exporter, ModBlocks.PURPLE_HEART_TRAPDOOR, ModBlocks.PURPLE_HEART_PLANKS);
        offerPressurePlateRecipe(exporter, ModBlocks.PURPLE_HEART_PRESSURE_PLATE, ModBlocks.PURPLE_HEART_PLANKS);
        offerShapelessRecipe(exporter, ModBlocks.PURPLE_HEART_BUTTON, ModBlocks.PURPLE_HEART_PLANKS, "purple_heart_button", 1);

        offerSignRecipe(exporter, ModItems.PURPLE_HEART_SIGN, ModBlocks.PURPLE_HEART_PLANKS);
        offerHangingSignRecipe(exporter, ModItems.PURPLE_HEART_HANGING_SIGN, ModBlocks.STRIPPED_PURPLE_HEART_LOG);

        offerSmithingTrimRecipe(exporter, ModItems.PAINTED_SMITHING_TEMPLATE, Identifier.of(Impillagers.MOD_ID, "painted"));

        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BLOCK, ModItems.DUNG_BALL);

        offerShapelessRecipe(exporter, Items.PURPLE_DYE, ModBlocks.BELLADONNA, "", 1);

        offerStairsRecipe(exporter, ModBlocks.PACKED_MUD_STAIRS, Blocks.PACKED_MUD);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_SLAB, Blocks.PACKED_MUD);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_WALL, Blocks.PACKED_MUD);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_STAIRS, Blocks.PACKED_MUD, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_SLAB, Blocks.PACKED_MUD, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_WALL, Blocks.PACKED_MUD, 1);

        offerShapelessRecipe(exporter, Items.BROWN_DYE, ModBlocks.SWAMP_REED, "brown_dye", 2);

        //Undyeing Recipes
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.GRAY_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.BLACK_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.BROWN_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.RED_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.ORANGE_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.YELLOW_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.LIME_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.GREEN_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.CYAN_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.BLUE_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.PURPLE_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, "undyeing_terracotta");
        offerUndyeingRecipe(exporter, Blocks.TERRACOTTA, Blocks.PINK_TERRACOTTA, "undyeing_terracotta");

        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.WHITE_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.LIGHT_GRAY_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.GRAY_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.BLACK_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.BROWN_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.RED_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.ORANGE_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.YELLOW_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.LIME_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.GREEN_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.CYAN_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.LIGHT_BLUE_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.BLUE_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.PURPLE_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.MAGENTA_STAINED_GLASS, "undyeing_glass");
        offerUndyeingRecipe(exporter, Blocks.GLASS, Blocks.PINK_STAINED_GLASS, "undyeing_glass");

        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.WHITE_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.LIGHT_GRAY_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.GRAY_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.BLACK_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.BROWN_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.RED_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.ORANGE_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.YELLOW_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.LIME_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.GREEN_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.CYAN_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.LIGHT_BLUE_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.BLUE_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.PURPLE_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.MAGENTA_STAINED_GLASS_PANE, "undyeing_glass_pane");
        offerUndyeingRecipe(exporter, Blocks.GLASS_PANE, Blocks.PINK_STAINED_GLASS_PANE, "undyeing_glass_pane");

        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.RED_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.LIME_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, "undyeing_shulker_box");
        offerUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.PINK_SHULKER_BOX, "undyeing_shulker_box");

        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.WHITE_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.LIGHT_GRAY_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.GRAY_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.BLACK_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.BROWN_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.RED_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.ORANGE_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.YELLOW_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.LIME_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.GREEN_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.CYAN_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.LIGHT_BLUE_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.BLUE_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.PURPLE_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.MAGENTA_CANDLE, "undyeing_candle");
        offerUndyeingRecipe(exporter, Blocks.CANDLE, Blocks.PINK_CANDLE, "undyeing_candle");
    }

    public static void offerUndyeingRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, String group) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output)
                .input(ModItems.TOTEM_OF_UNDYEING)
                .input(input)
                .criterion("has_totem_of_undyeing", conditionsFromItem(ModItems.TOTEM_OF_UNDYEING))
                .group(group)
                .offerTo(exporter, convertBetween(output, input));
    }

    public static void offerStairsRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        createStairsRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    public static void offerFenceRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        createFenceRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    public static void offerFenceGateRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        createFenceGateRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    public static void offerDoorRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        createDoorRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }
    public static void offerTrapdoorRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        createTrapdoorRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }
    public static void offerSignRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        createSignRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }
}
