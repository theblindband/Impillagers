package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

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
        blockStateModelGenerator.registerTintableCross(ModBlocks.PURPLE_HEART_SAPLING, BlockStateModelGenerator.TintType.TINTED);

        blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_PURPLE_HEART_LOG, ModBlocks.PURPLE_HEART_HANGING_SIGN, ModBlocks.PURPLE_HEART_WALL_HANGING_SIGN);



        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SINKING_MUD);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.IMP_POTTERY_SHERD, Models.GENERATED);
        itemModelGenerator.register(ModItems.FROG_MASK, Models.GENERATED);
        itemModelGenerator.register(ModItems.PAINTED_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PURPLE_HEART_SIGN, Models.GENERATED);
    }
}
