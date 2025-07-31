package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;
//TODO: FIX
public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.PURPLE_HEART_LOGS);

        getOrCreateTagBuilder(ModTags.Blocks.PURPLE_HEART_LOGS)
                .add(ModBlocks.PURPLE_HEART_LOG)
                .add(ModBlocks.PURPLE_HEART_WOOD)
                .add(ModBlocks.STRIPPED_PURPLE_HEART_LOG)
                .add(ModBlocks.STRIPPED_PURPLE_HEART_WOOD);

        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.PURPLE_HEART_PLANKS);



        //Stairs

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.PURPLE_HEART_STAIRS);
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.PURPLE_HEART_SLAB);
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.PURPLE_HEART_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.PURPLE_HEART_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.PACKED_MUD_STAIRS)
                .add(ModBlocks.PACKED_DUNG_STAIRS)
                .add(ModBlocks.MOSSY_MUD_BRICKS_STAIRS)
                .add(ModBlocks.MOSSY_DUNG_BRICKS_STAIRS)
                .add(ModBlocks.DUNG_BRICKS_STAIRS);


        //Slabs

        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.PACKED_MUD_SLAB)
                .add(ModBlocks.PACKED_DUNG_SLAB)
                .add(ModBlocks.MOSSY_MUD_BRICKS_SLAB)
                .add(ModBlocks.MOSSY_DUNG_BRICKS_SLAB)
                .add(ModBlocks.DUNG_BRICKS_SLAB);

        //Walls
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.PACKED_MUD_WALL)
                .add(ModBlocks.PACKED_DUNG_WALL)
                .add(ModBlocks.MOSSY_MUD_BRICKS_WALL)
                .add(ModBlocks.MOSSY_DUNG_BRICKS_WALL)
                .add(ModBlocks.DUNG_BRICKS_WALL);



        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.PURPLE_HEART_DOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.PURPLE_HEART_TRAPDOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.PURPLE_HEART_PRESSURE_PLATE);
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.PURPLE_HEART_BUTTON);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.PURPLE_HEART_LEAVES);
        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.PURPLE_HEART_SAPLING);

        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.PURPLE_HEART_SIGN);
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(ModBlocks.PURPLE_HEART_WALL_SIGN);
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.PURPLE_HEART_HANGING_SIGN);
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.PURPLE_HEART_WALL_HANGING_SIGN);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.SINKING_MUD)
                .add(ModBlocks.DUNG_BLOCK)
                .add(ModBlocks.SUSPICIOUS_DIRT)
                .add(ModBlocks.FERTILE_DIRT)
                .add(ModBlocks.FERTILE_FARMLAND);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.PACKED_MUD_WALL)
                .add(ModBlocks.PACKED_MUD_SLAB)
                .add(ModBlocks.PACKED_MUD_STAIRS)
                .add(ModBlocks.CRACKED_MUD_BRICKS)
                .add(ModBlocks.MOSSY_MUD_BRICKS)
                .add(ModBlocks.MOSSY_MUD_BRICKS_WALL)
                .add(ModBlocks.MOSSY_MUD_BRICKS_SLAB)
                .add(ModBlocks.MOSSY_MUD_BRICKS_STAIRS)
                .add(ModBlocks.CHISELED_MUD_BRICKS)
                .add(ModBlocks.MUD_BRICKS_PILLAR)

                .add(ModBlocks.PACKED_DUNG)
                .add(ModBlocks.PACKED_DUNG_WALL)
                .add(ModBlocks.PACKED_DUNG_SLAB)
                .add(ModBlocks.PACKED_DUNG_STAIRS)
                .add(ModBlocks.DUNG_BRICKS)
                .add(ModBlocks.DUNG_BRICKS_WALL)
                .add(ModBlocks.DUNG_BRICKS_SLAB)
                .add(ModBlocks.DUNG_BRICKS_STAIRS)
                .add(ModBlocks.MOSSY_DUNG_BRICKS)
                .add(ModBlocks.MOSSY_DUNG_BRICKS_WALL)
                .add(ModBlocks.MOSSY_DUNG_BRICKS_SLAB)
                .add(ModBlocks.MOSSY_DUNG_BRICKS_STAIRS)
                .add(ModBlocks.CRACKED_DUNG_BRICKS)
                .add(ModBlocks.CHISELED_DUNG_BRICKS)
                .add(ModBlocks.DUNG_BRICKS_PILLAR)

                .add(ModBlocks.SAFE);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.WASTE_BASKET);

        getOrCreateTagBuilder(BlockTags.DIRT)
                .add(ModBlocks.SINKING_MUD)
                .add(ModBlocks.FERTILE_DIRT);

        getOrCreateTagBuilder(BlockTags.BIG_DRIPLEAF_PLACEABLE)
                .add(ModBlocks.FERTILE_FARMLAND);

        /*getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
                .add(ModBlocks.BELLADONNA);
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_BELLADONNA);*/

        getOrCreateTagBuilder(BlockTags.TALL_FLOWERS)
                .add(ModBlocks.SWAMP_REED);
    }
}
