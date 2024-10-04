package com.impillagers.mod.datagen.models;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.minecraft.client.renderer.RenderType.CUTOUT;

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
        woodSet(ModBlocks.PURPLE_HEART_LOG, ModBlocks.PURPLE_HEART_WOOD, ModBlocks.PURPLE_HEART_PLANKS, ModBlocks.STRIPPED_PURPLE_HEART_LOG, ModBlocks.STRIPPED_PURPLE_HEART_WOOD, ModBlocks.PURPLE_HEART_STAIRS, ModBlocks.PURPLE_HEART_SLAB, ModBlocks.PURPLE_HEART_FENCE, ModBlocks.PURPLE_HEART_FENCE_GATE,
                        ModBlocks.PURPLE_HEART_DOOR, true, ModBlocks.PURPLE_HEART_TRAPDOOR, true, ModBlocks.PURPLE_HEART_PRESSURE_PLATE, ModBlocks.PURPLE_HEART_BUTTON);
        simpleBlock(ModBlocks.PURPLE_HEART_LEAVES.get());
    }

    //Method for generating the wood set
    private void woodSet(DeferredBlock<RotatedPillarBlock> log, DeferredBlock<RotatedPillarBlock> wood, DeferredBlock<Block> planks, DeferredBlock<RotatedPillarBlock> strippedLog, DeferredBlock<RotatedPillarBlock> strippedWood, DeferredBlock<StairBlock> stairs, DeferredBlock<SlabBlock> slab, DeferredBlock<FenceBlock> fence, DeferredBlock<FenceGateBlock> fenceGate, DeferredBlock<DoorBlock> door, boolean cutoutDoor, DeferredBlock<TrapDoorBlock> trapdoor, boolean cutoutTrapdoor, DeferredBlock<PressurePlateBlock> pressurePlate, DeferredBlock<ButtonBlock> button/*, Block sign, Block wallSign, Block hangingSign, Block wallHangingSign*/) {
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
        //Fence and Fence Gate
        fenceBlock(fence.get(), blockTexture(planks.get()));
        fenceGateBlock(fenceGate.get(), blockTexture(planks.get()));
        //Door and Trapdoor
        if (cutoutDoor) {
            doorBlockWithRenderType(door.get(), blockTexture(door.get()).withSuffix("_bottom"), blockTexture(door.get()).withSuffix("_top"), "cutout");
        } else {
            doorBlock(door.get(), blockTexture(door.get()).withSuffix("_bottom"), blockTexture(door.get()).withSuffix("_top"));
        }
        if (cutoutTrapdoor) {
            trapdoorBlockWithRenderType(trapdoor.get(), blockTexture(trapdoor.get()), true, "cutout");
        } else {
            trapdoorBlock(trapdoor.get(), blockTexture(trapdoor.get()), true);
        }
        pressurePlateBlock(pressurePlate.get(), blockTexture(planks.get()));
        buttonBlock(button.get(), blockTexture(planks.get()));
        //The below methods will be uncommented when the blocks for them are added.
        /*
        simpleSign(sign, wallSign, blockTexture(planks));
        simpleSign(hangingSign, wallHangingSign, blockTexture(planks));
        */
    }
}
