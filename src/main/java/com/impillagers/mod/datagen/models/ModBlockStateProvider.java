package com.impillagers.mod.datagen.models;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Impillagers.MOD_ID, exFileHelper);
    }
    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    @Override
    protected void registerStatesAndModels() {
        //Block States
        woodSet(ModBlocks.PURPLE_HEART_LOG, ModBlocks.PURPLE_HEART_WOOD, ModBlocks.PURPLE_HEART_PLANKS, ModBlocks.STRIPPED_PURPLE_HEART_LOG, ModBlocks.STRIPPED_PURPLE_HEART_WOOD, ModBlocks.PURPLE_HEART_STAIRS, ModBlocks.PURPLE_HEART_SLAB);
    }

    //Method for generating the wood set
    private void woodSet(DeferredBlock<RotatedPillarBlock> log, DeferredBlock<RotatedPillarBlock> wood, DeferredBlock<Block> planks, DeferredBlock<RotatedPillarBlock> strippedLog, DeferredBlock<RotatedPillarBlock> strippedWood, DeferredBlock<StairBlock> stairs, DeferredBlock<SlabBlock> slab/*, DoorBlock door, boolean cutoutDoor, TrapDoorBlock trapdoor, boolean cutoutTrapdoor, PressurePlateBlock pressurePlate, ButtonBlock button, FenceBlock fence, FenceGateBlock fenceGate, Block sign, Block wallSign, Block hangingSign, Block wallHangingSign*/) {
        //logBlock generates a pillar block while axisBlock lets you define a texture for each side based on other blocks
        logBlock(log.get());
        axisBlock(wood.get(), blockTexture(log.get()), blockTexture(log.get()));
        //simpleBlock generates a block with the same texture on all sides
        simpleBlock(planks.get());
        logBlock(strippedLog.get());
        axisBlock(strippedWood.get(), blockTexture(strippedLog.get()), blockTexture(strippedLog.get()));
        //Stairs and Slabs
        stairsBlock(stairs.get(), blockTexture(planks.get()));
        slabBlock(slab.get(), blockTexture(planks.get()), blockTexture(planks.get()));
        //The below methods will be uncommented when the blocks for them are added.
        /*
        if (cutoutDoor) {
            doorBlockWithRenderType(door, blockTexture(door).withSuffix("_bottom"), blockTexture(door).withSuffix("_top"), String.valueOf(CUTOUT));
        } else {
            doorBlock(door, blockTexture(door).withSuffix("_bottom"), blockTexture(door).withSuffix("_top"));
        }
        if (cutoutTrapdoor) {
            trapdoorBlockWithRenderType(trapdoor, blockTexture(trapdoor), true, String.valueOf(CUTOUT));
        } else {
            trapdoorBlock(trapdoor, blockTexture(trapdoor), true);
        }
        pressurePlateBlock(pressurePlate, blockTexture(planks));
        buttonBlock(button, blockTexture(planks));
        fenceBlock(fence, blockTexture(planks));
        fenceGateBlock(fenceGate, blockTexture(planks));
        simpleSign(sign, wallSign, blockTexture(planks));
        simpleSign(hangingSign, wallHangingSign, blockTexture(planks));
        */
    }
}
