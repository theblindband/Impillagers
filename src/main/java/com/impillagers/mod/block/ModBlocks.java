package com.impillagers.mod.block;


import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.Impillagers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Properties;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Impillagers.MOD_ID);

    //Purple Heart Wood Set
    public static final DeferredBlock<RotatedPillarBlock> PURPLE_HEART_LOG = registerBlock("purple_heart_log",
            () -> new RotatedPillarBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<RotatedPillarBlock> PURPLE_HEART_WOOD = registerBlock("purple_heart_wood",
            () -> new RotatedPillarBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_PURPLE_HEART_LOG = registerBlock("stripped_purple_heart_log",
            () -> new RotatedPillarBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_PURPLE_HEART_WOOD = registerBlock("stripped_purple_heart_wood",
            () -> new RotatedPillarBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<Block> PURPLE_HEART_PLANKS = registerBlock("purple_heart_planks",
            () -> new Block(
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<StairBlock> PURPLE_HEART_STAIRS = registerBlock("purple_heart_stairs",
            () -> new StairBlock(ModBlocks.PURPLE_HEART_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<SlabBlock> PURPLE_HEART_SLAB = registerBlock("purple_heart_slab",
            () -> new SlabBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<FenceBlock> PURPLE_HEART_FENCE = registerBlock("purple_heart_fence",
            () -> new FenceBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<FenceGateBlock> PURPLE_HEART_FENCE_GATE = registerBlock("purple_heart_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<DoorBlock> PURPLE_HEART_DOOR = registerBlock("purple_heart_door",
            () -> new DoorBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()
            ));
    public static final DeferredBlock<TrapDoorBlock> PURPLE_HEART_TRAPDOOR = registerBlock("purple_heart_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()
            ));
    public static final DeferredBlock<PressurePlateBlock> PURPLE_HEART_PRESSURE_PLATE = registerBlock("purple_heart_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<ButtonBlock> PURPLE_HEART_BUTTON = registerBlock("purple_heart_button",
            () -> new ButtonBlock(BlockSetType.OAK,30,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<LeavesBlock> PURPLE_HEART_LEAVES = registerBlock("purple_heart_leaves",
            () -> new LeavesBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .strength(0.2F)
                            .randomTicks()
                            .sound(SoundType.GRASS)
                            .noOcclusion()
                            .isValidSpawn(Blocks::ocelotOrParrot)
                            .isSuffocating(ModBlocks::never)
                            .isViewBlocking(ModBlocks::never)
                            .ignitedByLava()
                            .pushReaction(PushReaction.DESTROY)
                            .isRedstoneConductor(ModBlocks::never)
            ));
    /*
    Sapling
    Sign
    Hanging Sign
    Boat
    Chest Boat
     */

    //Register Methods
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }
}
