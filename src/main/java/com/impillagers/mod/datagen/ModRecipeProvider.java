package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        //Generates the item recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPLE_HEART_PLANKS.get(), 4)
                .group("planks")
                .requires(ModBlocks.PURPLE_HEART_LOG)
                .unlockedBy("has_purple_heart_logs", has(ModTags.Items.PURPLE_HEART_LOGS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPLE_HEART_STAIRS.get(), 4)
                .group("wooden_stairs")
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', ModBlocks.PURPLE_HEART_PLANKS.get())
                .unlockedBy("has_purple_heart_planks", has(ModBlocks.PURPLE_HEART_PLANKS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPLE_HEART_SLAB.get(), 6)
                .group("wooden_slab")
                .pattern("BBB")
                .define('B', ModBlocks.PURPLE_HEART_PLANKS.get())
                .unlockedBy("has_purple_heart_planks", has(ModBlocks.PURPLE_HEART_PLANKS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PURPLE_HEART_FENCE.get(), 3)
                .group("wooden_fence")
                .pattern("BSB")
                .pattern("BSB")
                .define('B', ModBlocks.PURPLE_HEART_PLANKS.get())
                .define('S', Items.STICK)
                .unlockedBy("has_purple_heart_planks", has(ModBlocks.PURPLE_HEART_PLANKS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.PURPLE_HEART_FENCE_GATE.get(), 1)
                .group("wooden_fence_gate")
                .pattern("SBS")
                .pattern("SBS")
                .define('B', ModBlocks.PURPLE_HEART_PLANKS.get())
                .define('S', Items.STICK)
                .unlockedBy("has_purple_heart_planks", has(ModBlocks.PURPLE_HEART_PLANKS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.PURPLE_HEART_DOOR.get(), 3)
                .group("wooden_door")
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBlocks.PURPLE_HEART_PLANKS.get())
                .unlockedBy("has_purple_heart_planks", has(ModBlocks.PURPLE_HEART_PLANKS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.PURPLE_HEART_TRAPDOOR.get(), 2)
                .group("wooden_trapdoor")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModBlocks.PURPLE_HEART_PLANKS.get())
                .unlockedBy("has_purple_heart_planks", has(ModBlocks.PURPLE_HEART_PLANKS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.PURPLE_HEART_PRESSURE_PLATE.get(), 2)
                .group("wooden_pressure_plate")
                .pattern("BB")
                .define('B', ModBlocks.PURPLE_HEART_PLANKS.get())
                .unlockedBy("has_purple_heart_planks", has(ModBlocks.PURPLE_HEART_PLANKS)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ModBlocks.PURPLE_HEART_BUTTON.get(), 1)
                .group("wooden_button")
                .requires(ModBlocks.PURPLE_HEART_PLANKS)
                .unlockedBy("has_bismuth_block", has(ModBlocks.PURPLE_HEART_PLANKS)).save(recipeOutput);

        //Left some examples here for other recipes
        /*List<ItemLike> BISMUTH_SMELTABLES = List.of(ModItems.RAW_BISMUTH,
                ModBlocks.BISMUTH_ORE, ModBlocks.BISMUTH_DEEPSLATE_ORE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BISMUTH.get(), 9)
                .requires(ModBlocks.BISMUTH_BLOCK)
                .unlockedBy("has_bismuth_block", has(ModBlocks.BISMUTH_BLOCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BISMUTH.get(), 18)
                .requires(ModBlocks.MAGIC_BLOCK)
                .unlockedBy("has_magic_block", has(ModBlocks.MAGIC_BLOCK))
                .save(recipeOutput, "tutorialmod:bismuth_from_magic_block");

        oreSmelting(recipeOutput, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(), 0.25f, 200, "bismuth");
        oreBlasting(recipeOutput, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(), 0.25f, 100, "bismuth");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, TutorialMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }*/
    }
}
