package com.impillagers.mod.block;

import com.impillagers.mod.block.custom.SinkingMudBlock;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.Impillagers;
import com.impillagers.mod.util.ModWoodTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;
import java.util.Properties;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Impillagers.MOD_ID);

    //Purple Heart Wood Set
    public static final DeferredBlock<SinkingMudBlock> SINKING_MUD = registerBlock("sinking_mud",
            () -> new SinkingMudBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.HARP).strength(0.5F).sound(SoundType.MUD).dynamicShape().isRedstoneConductor(ModBlocks::never)
            ));


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
            () -> new FenceGateBlock(ModWoodTypes.PURPLE_HEART_WOOD,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<DoorBlock> PURPLE_HEART_DOOR = registerBlock("purple_heart_door",
            () -> new DoorBlock(ModWoodTypes.PURPLE_HEART_SET,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()
            ));
    public static final DeferredBlock<TrapDoorBlock> PURPLE_HEART_TRAPDOOR = registerBlock("purple_heart_trapdoor",
            () -> new TrapDoorBlock(ModWoodTypes.PURPLE_HEART_SET,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()
            ));
    public static final DeferredBlock<PressurePlateBlock> PURPLE_HEART_PRESSURE_PLATE = registerBlock("purple_heart_pressure_plate",
            () -> new PressurePlateBlock(ModWoodTypes.PURPLE_HEART_SET,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
            ));
    public static final DeferredBlock<ButtonBlock> PURPLE_HEART_BUTTON = registerBlock("purple_heart_button",
            () -> new ButtonBlock(ModWoodTypes.PURPLE_HEART_SET,30,
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

    public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_HEART = create("purple_heart");
    public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Impillagers.id(name));
    }

    public static final DeferredBlock<SaplingBlock> PURPLE_HEART_SAPLING = registerBlock("purple_heart_sapling",
            () -> new SaplingBlock(
                    new TreeGrower("purple_heart", 0.2f,
                            Optional.empty(), Optional.empty(), Optional.of(PURPLE_HEART),
                            Optional.empty(), Optional.empty(), Optional.empty()),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.GRASS)
                            .noOcclusion()
                            .pushReaction(PushReaction.DESTROY)
            ));
    public static final DeferredBlock<FlowerPotBlock> POTTED_PURPLE_HEART_SAPLING = registerBlock("potted_purple_heart_sapling",
            () -> new FlowerPotBlock(PURPLE_HEART_SAPLING.get(),
                    BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)
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
