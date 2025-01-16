package com.impillagers.mod.block;

import com.impillagers.mod.Impillagers;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static net.minecraft.sound.BlockSoundGroup.WOOD;

public class ModBlocks {

    //Purple Heart Wood Set
    public static final Block PURPLE_HEART_PLANKS  = registerBlock("purple_heart_planks", new Block(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(WOOD)));

    public static final Block PURPLE_HEART_LOG  = registerBlock("purple_heart_log", new PillarBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(WOOD)));
    public static final Block PURPLE_HEART_WOOD  = registerBlock("purple_heart_wood", new PillarBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(WOOD)));
    public static final Block STRIPPED_PURPLE_HEART_LOG  = registerBlock("stripped_purple_heart_log", new PillarBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(WOOD)));
    public static final Block STRIPPED_PURPLE_HEART_WOOD  = registerBlock("stripped_purple_heart_wood", new PillarBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(WOOD)));

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