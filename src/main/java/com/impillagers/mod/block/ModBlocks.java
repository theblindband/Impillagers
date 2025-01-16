package com.impillagers.mod.block;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.custom.ModSaplingBlock;
import com.impillagers.mod.block.custom.SinkingMudBlock;
import com.impillagers.mod.world.tree.ModSaplingGenerators;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    //Purple Heart Wood Set
    //Logs
    public static final Block PURPLE_HEART_LOG  = registerBlock("purple_heart_log", new PillarBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_WOOD  = registerBlock("purple_heart_wood", new PillarBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_PURPLE_HEART_LOG  = registerBlock("stripped_purple_heart_log", new PillarBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_PURPLE_HEART_WOOD  = registerBlock("stripped_purple_heart_wood", new PillarBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    //Planks and Variants
    public static final Block PURPLE_HEART_PLANKS  = registerBlock("purple_heart_planks", new Block(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_STAIRS  = registerBlock("purple_heart_stairs", new StairsBlock(ModBlocks.PURPLE_HEART_PLANKS.getDefaultState(), AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_SLAB  = registerBlock("purple_heart_slab", new SlabBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_FENCE  = registerBlock("purple_heart_fence", new FenceBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_FENCE_GATE  = registerBlock("purple_heart_fence_gate", new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    //Redstone
    public static final Block PURPLE_HEART_DOOR  = registerBlock("purple_heart_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_TRAPDOOR  = registerBlock("purple_heart_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_PRESSURE_PLATE  = registerBlock("purple_heart_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_BUTTON  = registerBlock("purple_heart_button", new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    //Leaves and Saplings
    public static final Block PURPLE_HEART_LEAVES = registerBlock("purple_heart_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block PURPLE_HEART_SAPLING = registerBlock("purple_heart_sapling",
            new SaplingBlock(ModSaplingGenerators.PURPLE_HEART, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    //Sinking Mud
    public static final Block SINKING_MUD = registerBlock("sinking_mud",
            new SinkingMudBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLACK).strength(0.25F).sounds(BlockSoundGroup.MUD).dynamicBounds()
                    .allowsSpawning(Blocks::never)
                    .solidBlock(Blocks::never)
                    .suffocates(Blocks::never)
                    .blockVision(Blocks::never)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Impillagers.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Impillagers.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Impillagers.LOGGER.info("Registering Mod Blocks for " + Impillagers.MOD_ID);
    }
}