package com.impillagers.mod.block;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.custom.*;
import com.impillagers.mod.world.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import static net.minecraft.block.Blocks.*;

public class ModBlocks {

    //Purple Heart Blocks
    public static final Block PURPLE_HEART_LOG  = registerBlock("purple_heart_log", new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_WOOD  = registerBlock("purple_heart_wood", new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_PURPLE_HEART_LOG  = registerBlock("stripped_purple_heart_log", new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_PURPLE_HEART_WOOD  = registerBlock("stripped_purple_heart_wood", new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_PLANKS  = registerBlock("purple_heart_planks", new Block(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_STAIRS  = registerBlock("purple_heart_stairs", new StairsBlock(ModBlocks.PURPLE_HEART_PLANKS.getDefaultState(), AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_SLAB  = registerBlock("purple_heart_slab", new SlabBlock(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_FENCE  = registerBlock("purple_heart_fence", new FenceBlock(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_FENCE_GATE  = registerBlock("purple_heart_fence_gate", new FenceGateBlock(ModWoodTypes.PURPLE_HEART, AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_DOOR  = registerBlock("purple_heart_door", new DoorBlock(ModBlockSetTypes.PURPLE_HEART, AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block PURPLE_HEART_TRAPDOOR  = registerBlock("purple_heart_trapdoor", new TrapdoorBlock(ModBlockSetTypes.PURPLE_HEART, AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block PURPLE_HEART_PRESSURE_PLATE  = registerBlock("purple_heart_pressure_plate", new PressurePlateBlock(ModBlockSetTypes.PURPLE_HEART, AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_BUTTON  = registerBlock("purple_heart_button", new ButtonBlock(ModBlockSetTypes.PURPLE_HEART, 30, AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PURPLE_HEART_LEAVES = registerBlock("purple_heart_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block PURPLE_HEART_SAPLING = registerBlock("purple_heart_sapling", new SaplingBlock(ModSaplingGenerators.PURPLE_HEART, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block POTTED_PURPLE_HEART_SAPLING = registerBlockWithoutItem("potted_purple_heart_sapling", new FlowerPotBlock(ModBlocks.PURPLE_HEART_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING)));
    public static final Block PURPLE_HEART_SIGN  = registerBlockWithoutItem("purple_heart_sign", new SignBlock(ModWoodTypes.PURPLE_HEART, AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).noCollision()));
    public static final Block PURPLE_HEART_WALL_SIGN  = registerBlockWithoutItem("purple_heart_wall_sign", new WallSignBlock(ModWoodTypes.PURPLE_HEART, AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).noCollision()));
    public static final Block PURPLE_HEART_HANGING_SIGN  = registerBlockWithoutItem("purple_heart_hanging_sign", new HangingSignBlock(ModWoodTypes.PURPLE_HEART, AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).noCollision()));
    public static final Block PURPLE_HEART_WALL_HANGING_SIGN  = registerBlockWithoutItem("purple_heart_wall_hanging_sign", new WallHangingSignBlock(ModWoodTypes.PURPLE_HEART, AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)));

    //Natural Blocks
    public static final Block SINKING_MUD = registerBlock("sinking_mud", new SinkingMudBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLACK).strength(0.25F).sounds(BlockSoundGroup.MUD).dynamicBounds().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::always)));
    public static final Block BELLADONNA = registerBlock("belladonna", new BelladonnaBlock(SuspiciousStewEffectsComponent.DEFAULT, AbstractBlock.Settings.copy(Blocks.POPPY)));
    public static final Block POTTED_BELLADONNA = registerBlockWithoutItem("potted_belladonna", new FlowerPotBlock(ModBlocks.BELLADONNA, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final Block FIREFLY_BUSH = registerBlock("firefly_bush", new FireflyBushBlock(AbstractBlock.Settings.copy(Blocks.POPPY).luminance(createLightLevelFromLitBlockState(2)).ticksRandomly()));
    public static final Block SWAMP_REED = registerBlock("swamp_reed", new ReedBlock(AbstractBlock.Settings.copy(Blocks.ROSE_BUSH)));

    //Dung Blocks
    public static final Block DUNG_BLOCK = registerBlock("dung_block", new Block(AbstractBlock.Settings.copy(Blocks.MUD)));

    public static final Block PACKED_DUNG = registerBlock("packed_dung", new Block(AbstractBlock.Settings.copy(Blocks.PACKED_MUD)));
    public static final Block PACKED_DUNG_STAIRS = registerBlock("packed_dung_stairs", new StairsBlock(Blocks.PACKED_MUD.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PACKED_MUD)));
    public static final Block PACKED_DUNG_SLAB = registerBlock("packed_dung_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PACKED_MUD)));
    public static final Block PACKED_DUNG_WALL = registerBlock("packed_dung_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PACKED_MUD)));

    public static final Block DUNG_BRICKS = registerBlock("dung_bricks", new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block MOSSY_DUNG_BRICKS = registerBlock("mossy_dung_bricks", new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block CRACKED_DUNG_BRICKS = registerBlock("cracked_dung_bricks", new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block CHISELED_DUNG_BRICKS = registerBlock("chiseled_dung_bricks", new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block DUNG_BRICKS_PILLAR = registerBlock("dung_bricks_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));

    public static final Block DUNG_BRICKS_STAIRS = registerBlock("dung_bricks_stairs", new StairsBlock(Blocks.PACKED_MUD.getDefaultState(), AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block DUNG_BRICKS_SLAB = registerBlock("dung_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block DUNG_BRICKS_WALL = registerBlock("dung_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));

    public static final Block MOSSY_DUNG_BRICKS_STAIRS = registerBlock("mossy_dung_bricks_stairs", new StairsBlock(Blocks.PACKED_MUD.getDefaultState(), AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block MOSSY_DUNG_BRICKS_SLAB = registerBlock("mossy_dung_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block MOSSY_DUNG_BRICKS_WALL = registerBlock("mossy_dung_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));

    //Mud Blocks
    public static final Block PACKED_MUD_STAIRS = registerBlock("packed_mud_stairs", new StairsBlock(Blocks.PACKED_MUD.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PACKED_MUD)));
    public static final Block PACKED_MUD_SLAB = registerBlock("packed_mud_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PACKED_MUD)));
    public static final Block PACKED_MUD_WALL = registerBlock("packed_mud_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PACKED_MUD)));

    public static final Block MOSSY_MUD_BRICKS = registerBlock("mossy_mud_bricks", new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block CRACKED_MUD_BRICKS = registerBlock("cracked_mud_bricks", new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block CHISELED_MUD_BRICKS = registerBlock("chiseled_mud_bricks", new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block MUD_BRICKS_PILLAR = registerBlock("mud_bricks_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));

    public static final Block MOSSY_MUD_BRICKS_STAIRS = registerBlock("mossy_mud_bricks_stairs", new StairsBlock(Blocks.PACKED_MUD.getDefaultState(), AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block MOSSY_MUD_BRICKS_SLAB = registerBlock("mossy_mud_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block MOSSY_MUD_BRICKS_WALL = registerBlock("mossy_mud_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));

    //Dirt Blocks
    public static final Block FERTILE_FARMLAND = registerBlock("fertile_farmland", new FertileFarmlandBlock(AbstractBlock.Settings.copy(FARMLAND).ticksRandomly()));
    public static final Block FERTILE_DIRT = registerBlock("fertile_dirt", new Block(AbstractBlock.Settings.copy(COARSE_DIRT)));

    //Decoration Blocks
    public static final Block FIREFLY_BOTTLE = registerBlockWithoutItem("firefly_bottle", new FireflyBottleBlock(AbstractBlock.Settings.create().strength(0.3F).sounds(BlockSoundGroup.GLASS).luminance(createLightLevelFromLitBlockState(13))));

    //Job Blocks
    public static final Block SAFE = registerBlock("safe", new SafeBlock(AbstractBlock.Settings.copy(IRON_BLOCK)));
    public static final Block WASTE_BASKET = registerBlock("waste_basket", new WasteBasketBlock(AbstractBlock.Settings.copy(OAK_PLANKS)));

    //Brushable Blocks
    public static final Block SUSPICIOUS_DIRT = registerBlock("suspicious_dirt", new NoGravityBrushableBlock(Blocks.DIRT, SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC, SoundEvents.ITEM_BRUSH_BRUSHING_GRAVEL, AbstractBlock.Settings.copy(Blocks.DIRT).pistonBehavior(PistonBehavior.DESTROY).strength(0.25F).sounds(BlockSoundGroup.SUSPICIOUS_GRAVEL)));

    //Register Methods
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Impillagers.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Impillagers.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(Impillagers.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        //Impillagers.LOGGER.info("Registering Mod Blocks for " + Impillagers.MOD_ID);
    }
}