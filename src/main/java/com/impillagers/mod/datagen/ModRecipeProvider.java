package com.impillagers.mod.datagen;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
//TODO: FIX
public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
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

        //offerSmithingTrimRecipe(exporter, ModItems.PAINTED_SMITHING_TEMPLATE, Identifier.of(Impillagers.MOD_ID, "painted"));

        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BLOCK, ModItems.DUNG_BALL);

        offerShapelessRecipe(exporter, Items.PURPLE_DYE, ModBlocks.BELLADONNA, "", 1);

        offerShapelessRecipe(exporter, Items.BROWN_DYE, ModBlocks.SWAMP_REED, "brown_dye", 2);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.SAFE, 1)
                .input(Blocks.TRAPPED_CHEST)
                .input(Items.NETHERITE_INGOT)
                .group("")
                .criterion("has_netherite_ingot", conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter, "safe");

        //Mud Recipes

        offerCrackingRecipe(exporter, ModBlocks.CRACKED_MUD_BRICKS, Blocks.MUD_BRICKS);

        offerStairsRecipe(exporter, ModBlocks.PACKED_MUD_STAIRS, Blocks.PACKED_MUD);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_SLAB, Blocks.PACKED_MUD);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_WALL, Blocks.PACKED_MUD);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_STAIRS, Blocks.PACKED_MUD, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_SLAB, Blocks.PACKED_MUD, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_WALL, Blocks.PACKED_MUD, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICKS, Blocks.PACKED_MUD, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICK_STAIRS, Blocks.PACKED_MUD, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICK_SLAB, Blocks.PACKED_MUD, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICK_WALL, Blocks.PACKED_MUD, 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_MUD_BRICKS, Blocks.MUD_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MUD_BRICKS_PILLAR, Blocks.MUD_BRICKS, 1);

        offerStairsRecipe(exporter, ModBlocks.MOSSY_MUD_BRICKS_STAIRS, ModBlocks.MOSSY_MUD_BRICKS);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_MUD_BRICKS_SLAB, ModBlocks.MOSSY_MUD_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_MUD_BRICKS_WALL, ModBlocks.MOSSY_MUD_BRICKS);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_MUD_BRICKS_STAIRS, ModBlocks.MOSSY_MUD_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_MUD_BRICKS_SLAB, ModBlocks.MOSSY_MUD_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_MUD_BRICKS_WALL, ModBlocks.MOSSY_MUD_BRICKS, 1);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_MUD_BRICKS)
                .input(Blocks.MUD_BRICKS)
                .input(Blocks.VINE)
                .criterion("has_mud_bricks", conditionsFromItem(Blocks.MUD_BRICKS))
                .group("mud_bricks")
                .offerTo(exporter, "mossy_mud_bricks_vine");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_MUD_BRICKS)
                .input(Blocks.MUD_BRICKS)
                .input(Blocks.MOSS_BLOCK)
                .criterion("has_mud_bricks", conditionsFromItem(Blocks.MUD_BRICKS))
                .group("mud_bricks")
                .offerTo(exporter, "mossy_mud_bricks_moss");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_MUD_BRICKS, 1)
                .input('M', Blocks.MUD_BRICK_SLAB)
                .pattern("M")
                .pattern("M")
                .criterion("has_mud_brick_slab", conditionsFromItem(Blocks.MUD_BRICK_SLAB))
                .group("chiseled_mud_bricks")
                .offerTo(exporter, "mud_bricks_slabs_to_chiseled_mud_bricks");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MUD_BRICKS_PILLAR, 1)
                .input('M', Blocks.MUD_BRICKS)
                .pattern("M")
                .pattern("M")
                .criterion("has_mud_brick", conditionsFromItem(Blocks.MUD_BRICKS))
                .group("mud_bricks_pillar")
                .offerTo(exporter, "mud_bricks_to_mud_bricks_pillar");

        //Dung Recipes

        offerCrackingRecipe(exporter, ModBlocks.CRACKED_DUNG_BRICKS, ModBlocks.DUNG_BRICKS);

        offerStairsRecipe(exporter, ModBlocks.PACKED_DUNG_STAIRS, ModBlocks.PACKED_DUNG);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_DUNG_SLAB, ModBlocks.PACKED_DUNG);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_DUNG_WALL, ModBlocks.PACKED_DUNG);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_DUNG_STAIRS, ModBlocks.PACKED_DUNG, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_DUNG_SLAB, ModBlocks.PACKED_DUNG, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_DUNG_WALL, ModBlocks.PACKED_DUNG, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BRICKS, ModBlocks.PACKED_DUNG, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BRICKS_STAIRS, ModBlocks.PACKED_DUNG, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BRICKS_SLAB, ModBlocks.PACKED_DUNG, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BRICKS_WALL, ModBlocks.PACKED_DUNG, 1);

        offerStairsRecipe(exporter, ModBlocks.DUNG_BRICKS_STAIRS, ModBlocks.DUNG_BRICKS);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BRICKS_SLAB, ModBlocks.DUNG_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BRICKS_WALL, ModBlocks.DUNG_BRICKS);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BRICKS_STAIRS, ModBlocks.DUNG_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BRICKS_SLAB, ModBlocks.DUNG_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BRICKS_WALL, ModBlocks.DUNG_BRICKS, 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DUNG_BRICKS, ModBlocks.DUNG_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BRICKS_PILLAR, ModBlocks.DUNG_BRICKS, 1);

        offerStairsRecipe(exporter, ModBlocks.MOSSY_DUNG_BRICKS_STAIRS, ModBlocks.MOSSY_DUNG_BRICKS);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DUNG_BRICKS_SLAB, ModBlocks.MOSSY_DUNG_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DUNG_BRICKS_WALL, ModBlocks.MOSSY_DUNG_BRICKS);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DUNG_BRICKS_STAIRS, ModBlocks.MOSSY_DUNG_BRICKS, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DUNG_BRICKS_SLAB, ModBlocks.MOSSY_DUNG_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DUNG_BRICKS_WALL, ModBlocks.MOSSY_DUNG_BRICKS, 1);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DUNG_BRICKS)
                .input(ModBlocks.DUNG_BRICKS)
                .input(Blocks.VINE)
                .criterion("has_dung_bricks", conditionsFromItem(ModBlocks.DUNG_BRICKS))
                .group("mossy_dung_bricks")
                .offerTo(exporter, "mossy_dung_bricks_vine");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_DUNG_BRICKS)
                .input(ModBlocks.DUNG_BRICKS)
                .input(Blocks.MOSS_BLOCK)
                .criterion("has_dung_bricks", conditionsFromItem(ModBlocks.DUNG_BRICKS))
                .group("mossy_dung_bricks")
                .offerTo(exporter, "mossy_dung_bricks_moss");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_DUNG)
                .input(ModBlocks.DUNG_BLOCK)
                .input(Items.WHEAT)
                .criterion("has_dung_block", conditionsFromItem(ModBlocks.DUNG_BLOCK))
                .group("packed_dung")
                .offerTo(exporter, "packed_dung");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BRICKS, 4)
                .input('D', ModBlocks.PACKED_DUNG)
                .pattern("DD")
                .pattern("DD")
                .criterion("has_dung_block", conditionsFromItem(ModBlocks.PACKED_DUNG))
                .group("dung_bricks")
                .offerTo(exporter, "packed_dung_to_dung_bricks");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DUNG_BRICKS, 1)
                .input('D', ModBlocks.DUNG_BRICKS_SLAB)
                .pattern("D")
                .pattern("D")
                .criterion("has_dung_brick_slab", conditionsFromItem(ModBlocks.DUNG_BRICKS_SLAB))
                .group("dung_bricks")
                .offerTo(exporter, "dung_bricks_slabs_to_chiseled_dung_bricks");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DUNG_BRICKS_PILLAR, 1)
                .input('D', ModBlocks.DUNG_BRICKS)
                .pattern("D")
                .pattern("D")
                .criterion("has_dung_brick", conditionsFromItem(ModBlocks.DUNG_BRICKS))
                .group("mud_bricks_pillar")
                .offerTo(exporter, "dung_bricks_to_dung_bricks_pillar");

        //Fertile Dirt

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FERTILE_DIRT, 4)
                .input('D', ModBlocks.DUNG_BLOCK)
                .input('d', Blocks.DIRT)
                .pattern("Dd")
                .pattern("dD")
                .criterion("has_dung_block", conditionsFromItem(ModBlocks.DUNG_BLOCK))
                .group("fertile_dirt")
                .offerTo(exporter, "fertile_dirt_from_dung_blocks_and_dirt_1");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FERTILE_DIRT, 4)
                .input('D', ModBlocks.DUNG_BLOCK)
                .input('d', Blocks.DIRT)
                .pattern("dD")
                .pattern("Dd")
                .criterion("has_dung_block", conditionsFromItem(ModBlocks.DUNG_BLOCK))
                .group("fertile_dirt")
                .offerTo(exporter, "fertile_dirt_from_dung_blocks_and_dirt_2");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WASTE_BASKET, 1)
                .input('P', ItemTags.PLANKS)
                .input('S', ItemTags.WOODEN_SLABS)
                .pattern("S S")
                .pattern("PSP")
                .criterion("has_planks", conditionsFromItem(ModBlocks.PURPLE_HEART_PLANKS))
                .group("waste_basket")
                .offerTo(exporter, "waste_basket");

        //Undyeing Recipes
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.RED_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.LIME_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, "undyeing_shulker_box");
        offerSingleItemUndyeingRecipe(exporter, Blocks.SHULKER_BOX, Blocks.PINK_SHULKER_BOX, "undyeing_shulker_box");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.TERRACOTTA, 8)
                .input('T', ModItems.TOTEM_OF_UNDYEING)
                .input('#', ModTags.Items.DYED_TERRACOTTA)
                .pattern("###")
                .pattern("#T#")
                .pattern("###")
                .criterion("has_totem_of_undyeing", conditionsFromItem(ModItems.TOTEM_OF_UNDYEING))
                .group("undyeing")
                .offerTo(exporter, "totem_of_undyeing_terracotta");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.GLASS, 8)
                .input('T', ModItems.TOTEM_OF_UNDYEING)
                .input('#', ModTags.Items.STAINED_GLASS)
                .pattern("###")
                .pattern("#T#")
                .pattern("###")
                .criterion("has_totem_of_undyeing", conditionsFromItem(ModItems.TOTEM_OF_UNDYEING))
                .group("undyeing")
                .offerTo(exporter, "totem_of_undyeing_glass");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.GLASS_PANE, 8)
                .input('T', ModItems.TOTEM_OF_UNDYEING)
                .input('#', ModTags.Items.STAINED_GLASS_PANE)
                .pattern("###")
                .pattern("#T#")
                .pattern("###")
                .criterion("has_totem_of_undyeing", conditionsFromItem(ModItems.TOTEM_OF_UNDYEING))
                .group("undyeing")
                .offerTo(exporter, "totem_of_undyeing_glass_pane");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.CANDLE, 8)
                .input('T', ModItems.TOTEM_OF_UNDYEING)
                .input('#', ModTags.Items.DYED_CANDLE)
                .pattern("###")
                .pattern("#T#")
                .pattern("###")
                .criterion("has_totem_of_undyeing", conditionsFromItem(ModItems.TOTEM_OF_UNDYEING))
                .group("undyeing")
                .offerTo(exporter, "totem_of_undyeing_candle");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.SAND, 8)
                .input('T', ModItems.TOTEM_OF_UNDYEING)
                .input('#', ModTags.Items.CONCRETE_POWDER)
                .pattern("###")
                .pattern("#T#")
                .pattern("###")
                .criterion("has_totem_of_undyeing", conditionsFromItem(ModItems.TOTEM_OF_UNDYEING))
                .group("undyeing")
                .offerTo(exporter, "totem_of_undyeing_sand");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.GRAVEL, 8)
                .input('T', ModItems.TOTEM_OF_UNDYEING)
                .input('#', ModTags.Items.CONCRETE_POWDER)
                .pattern("###")
                .pattern("#T#")
                .pattern("###")
                .criterion("has_totem_of_undyeing", conditionsFromItem(ModItems.TOTEM_OF_UNDYEING))
                .group("undyeing")
                .offerTo(exporter, "totem_of_undyeing_gravel");

        offerBoatRecipe(exporter, ModItems.PURPLE_HEART_BOAT, ModBlocks.PURPLE_HEART_PLANKS);
        offerChestBoatRecipe(exporter, ModItems.PURPLE_HEART_CHEST_BOAT, ModItems.PURPLE_HEART_BOAT);
    }

    public static void offerSingleItemUndyeingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input, String group) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output)
                .input(ModItems.TOTEM_OF_UNDYEING)
                .input(input)
                .criterion("has_totem_of_undyeing", conditionsFromItem(ModItems.TOTEM_OF_UNDYEING))
                .group(group)
                .offerTo(exporter, convertBetween(output, input));
    }

    public static void offerStairsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createStairsRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    public static void offerFenceRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createFenceRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    public static void offerFenceGateRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createFenceGateRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    public static void offerDoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createDoorRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    public static void offerTrapdoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createTrapdoorRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    public static void offerSignRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createSignRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }


}