package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

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
}
