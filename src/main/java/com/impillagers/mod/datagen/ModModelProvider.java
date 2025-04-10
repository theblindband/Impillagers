package com.impillagers.mod.datagen;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerLog(ModBlocks.PURPLE_HEART_LOG).log(ModBlocks.PURPLE_HEART_LOG).wood(ModBlocks.PURPLE_HEART_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_PURPLE_HEART_LOG).log(ModBlocks.STRIPPED_PURPLE_HEART_LOG).wood(ModBlocks.STRIPPED_PURPLE_HEART_WOOD);

        BlockStateModelGenerator.BlockTexturePool purple_heart_planks_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PURPLE_HEART_PLANKS);

        purple_heart_planks_pool.stairs(ModBlocks.PURPLE_HEART_STAIRS);
        purple_heart_planks_pool.slab(ModBlocks.PURPLE_HEART_SLAB);
        purple_heart_planks_pool.fence(ModBlocks.PURPLE_HEART_FENCE);
        purple_heart_planks_pool.fenceGate(ModBlocks.PURPLE_HEART_FENCE_GATE);

        blockStateModelGenerator.registerDoor(ModBlocks.PURPLE_HEART_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.PURPLE_HEART_TRAPDOOR);
        purple_heart_planks_pool.pressurePlate(ModBlocks.PURPLE_HEART_PRESSURE_PLATE);
        purple_heart_planks_pool.button(ModBlocks.PURPLE_HEART_BUTTON);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURPLE_HEART_LEAVES);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.PURPLE_HEART_SAPLING, ModBlocks.POTTED_PURPLE_HEART_SAPLING, BlockStateModelGenerator.TintType.TINTED);

        blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_PURPLE_HEART_LOG, ModBlocks.PURPLE_HEART_HANGING_SIGN, ModBlocks.PURPLE_HEART_WALL_HANGING_SIGN);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SINKING_MUD);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.BELLADONNA, ModBlocks.POTTED_BELLADONNA, BlockStateModelGenerator.TintType.TINTED);
        blockStateModelGenerator.registerDoubleBlock(ModBlocks.SWAMP_REED, BlockStateModelGenerator.TintType.TINTED);


        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUSPICIOUS_DIRT);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FERTILE_DIRT);


        //Dung Blocks
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DUNG_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_DUNG_BRICKS);
        blockStateModelGenerator.registerLog(ModBlocks.DUNG_BRICKS_PILLAR).log(ModBlocks.DUNG_BRICKS_PILLAR);

        BlockStateModelGenerator.BlockTexturePool packed_dung_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PACKED_DUNG);
        packed_dung_pool.stairs(ModBlocks.PACKED_DUNG_STAIRS);
        packed_dung_pool.slab(ModBlocks.PACKED_DUNG_SLAB);
        packed_dung_pool.wall(ModBlocks.PACKED_DUNG_WALL);

        BlockStateModelGenerator.BlockTexturePool dung_bricks_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DUNG_BRICKS);
        dung_bricks_pool.stairs(ModBlocks.DUNG_BRICKS_STAIRS);
        dung_bricks_pool.slab(ModBlocks.DUNG_BRICKS_SLAB);
        dung_bricks_pool.wall(ModBlocks.DUNG_BRICKS_WALL);

        BlockStateModelGenerator.BlockTexturePool mossy_dung_bricks_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_DUNG_BRICKS);
        mossy_dung_bricks_pool.stairs(ModBlocks.MOSSY_DUNG_BRICKS_STAIRS);
        mossy_dung_bricks_pool.slab(ModBlocks.MOSSY_DUNG_BRICKS_SLAB);
        mossy_dung_bricks_pool.wall(ModBlocks.MOSSY_DUNG_BRICKS_WALL);

        //Mud Blocks
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_MUD_BRICKS);
        blockStateModelGenerator.registerLog(ModBlocks.MUD_BRICKS_PILLAR).log(ModBlocks.MUD_BRICKS_PILLAR);

        BlockStateModelGenerator.BlockTexturePool mossy_mud_bricks_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_MUD_BRICKS);
        mossy_mud_bricks_pool.stairs(ModBlocks.MOSSY_MUD_BRICKS_STAIRS);
        mossy_mud_bricks_pool.slab(ModBlocks.MOSSY_MUD_BRICKS_SLAB);
        mossy_mud_bricks_pool.wall(ModBlocks.MOSSY_MUD_BRICKS_WALL);

        BlockStateModelGenerator.BlockTexturePool packed_mud_pool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PACKED_MUD);

        packed_mud_pool.stairs(ModBlocks.PACKED_MUD_STAIRS);
        packed_mud_pool.slab(ModBlocks.PACKED_MUD_SLAB);
        packed_mud_pool.wall(ModBlocks.PACKED_MUD_WALL);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.IMP_POTTERY_SHERD, Models.GENERATED);
        itemModelGenerator.register(ModItems.COVER_POTTERY_SHERD, Models.GENERATED);
        itemModelGenerator.register(ModItems.FROG_MASK, Models.GENERATED);
        itemModelGenerator.register(ModItems.PAINTED_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PURPLE_HEART_SIGN, Models.GENERATED);

        itemModelGenerator.register(ModItems.IMPILLAGER_SPAWN_EGG, new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(ModItems.ZOMBIE_IMPILLAGER_SPAWN_EGG, new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(ModItems.DUNG_GOLEM_SPAWN_EGG, new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(ModItems.DUNG_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.FAKE_GOLD_COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPIDER_PIEYE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOTEM_OF_UNDYEING, Models.GENERATED);
        itemModelGenerator.register(ModItems.CREEPER_OIL, Models.GENERATED);
    }
}
