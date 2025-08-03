package com.impillagers.mod.villager;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.enchantment.ModEnchantments;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.villager.professions.ModProfessions;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

import java.util.function.Supplier;
import java.util.random.RandomGenerator;


public class ModTrades {

    public static void registerModTrades() {

        /* -----BANKER----- */
        // Banker - Level 1: Raw Gold Trades
        registerTrades(ModProfessions.BANKER, 1,
                new TradeData(Items.RAW_GOLD, 16, ModItems.GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.RAW_GOLD, 16, ModItems.FAKE_GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.RAW_GOLD_BLOCK, 2, ModItems.GOLD_COIN, 1, 16, 4, 0.02f),
                new TradeData(Items.RAW_GOLD_BLOCK, 2, ModItems.FAKE_GOLD_COIN, 1, 16, 4, 0.02f)
        );
        // Banker - Level 2: Gold Ingot Trades
        registerTrades(ModProfessions.BANKER, 2,
                new TradeData(Items.GOLD_INGOT, 16, ModItems.GOLD_COIN, 1, 12, 10, 0.02f),
                new TradeData(Items.GOLD_INGOT, 16, ModItems.FAKE_GOLD_COIN, 1, 12, 10, 0.02f),
                new TradeData(Items.GOLD_BLOCK, 2, ModItems.GOLD_COIN, 1, 12, 12, 0.02f),
                new TradeData(Items.GOLD_BLOCK, 2, ModItems.FAKE_GOLD_COIN, 1, 12, 12, 0.02f)
        );
        // Banker - Level 3: Gold Trades (Armor and Tools)
        registerTrades(ModProfessions.BANKER, 3,
                //Armor
                new TradeData(ModItems.GOLD_COIN, 5, Items.GOLDEN_HELMET, 1, 5, 5, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 9, Items.GOLDEN_CHESTPLATE, 1, 5, 5, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 7, Items.GOLDEN_LEGGINGS, 1, 5, 5, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 4, Items.GOLDEN_BOOTS, 1, 5, 5, 0.02f),
                //Tools
                new TradeData(ModItems.GOLD_COIN, 5, Items.GOLDEN_AXE, 1, 5, 5, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 3, Items.GOLDEN_HOE, 1, 5, 5, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 4, Items.GOLDEN_PICKAXE, 1, 5, 5, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 3, Items.GOLDEN_SHOVEL, 1, 5, 5, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 6, Items.GOLDEN_SWORD, 1, 5, 5, 0.02f)
        );
        // Banker - Level 4: Rare Gold Items
        registerTrades(ModProfessions.BANKER, 4,
                new TradeData(ModItems.GOLD_COIN, 36, Blocks.BELL, 1, 12, 15, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 15, Items.GOLDEN_APPLE, 1, 16, 12, 0.02f)
        );
        // Banker - Level 5: Fake Coins to Real Coins
        registerTrades(ModProfessions.BANKER, 5,
                new TradeData(ModItems.FAKE_GOLD_COIN, 8, ModItems.GOLD_COIN, 1, 16, 30, 0.02f)
        );

        /* -----MUSICIAN----- */
        // Musician - Level 1: Dungeon Discs
        registerTrades(ModProfessions.MUSICIAN, 1,
                new TradeData(Items.MUSIC_DISC_CAT, 1, ModItems.GOLD_COIN, 6, 3, 6, 0.02f),
                new TradeData(Items.MUSIC_DISC_CAT, 1, ModItems.FAKE_GOLD_COIN, 6, 3, 6, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 15, Items.MUSIC_DISC_CAT, 1, 3, 6, 0.02f),
                new TradeData(Items.MUSIC_DISC_13, 1, ModItems.GOLD_COIN, 6, 3, 6, 0.02f),
                new TradeData(Items.MUSIC_DISC_13, 1, ModItems.FAKE_GOLD_COIN, 6, 3, 6, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 15, Items.MUSIC_DISC_13, 1, 3, 6, 0.02f)
        );
        // Musician - Level 2: Noteblock/Jukebox
        registerTrades(ModProfessions.MUSICIAN, 2,
                new TradeData(Blocks.NOTE_BLOCK, 4, ModItems.GOLD_COIN, 2, 12, 8, 0.02f),
                new TradeData(Blocks.NOTE_BLOCK, 4, ModItems.FAKE_GOLD_COIN, 2, 12, 8, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 12, Blocks.JUKEBOX, 1, 5, 10, 0.02f)
        );
        // Musician - Level 3: Creeper Discs
        registerTrades(ModProfessions.MUSICIAN, 3,
                new TradeData(ModItems.GOLD_COIN, 24, Items.MUSIC_DISC_BLOCKS, 1, 3, 12, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 24, Items.MUSIC_DISC_CHIRP, 1, 3, 12, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 24, Items.MUSIC_DISC_FAR, 1, 3, 12, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 24, Items.MUSIC_DISC_MALL, 1, 3, 12, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 24, Items.MUSIC_DISC_MELLOHI, 1, 3, 12, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 24, Items.MUSIC_DISC_STAL, 1, 3, 12, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 24, Items.MUSIC_DISC_STRAD, 1, 3, 12, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 24, Items.MUSIC_DISC_WARD, 1, 3, 12, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 24, Items.MUSIC_DISC_11, 1, 3, 12, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 24, Items.MUSIC_DISC_WAIT, 1, 3, 12, 0.02f)
        );
        // Musician - Level 4: Structure Discs
        registerTrades(ModProfessions.MUSICIAN, 4,
                new TradeData(ModItems.GOLD_COIN, 36, Items.MUSIC_DISC_OTHERSIDE, 1, 3, 15, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 4, Items.DISC_FRAGMENT_5, 1, 18, 5, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 36, Items.MUSIC_DISC_PIGSTEP, 1, 3, 20, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 28, Items.MUSIC_DISC_RELIC, 1, 3, 15, 0.02f)
                /*new TradeData(ModItems.GOLD_COIN, 36, Items.MUSIC_DISC_CREATOR, 1, 3, 15, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 28, Items.MUSIC_DISC_CREATOR_MUSIC_BOX, 1, 16, 20, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 28, Items.MUSIC_DISC_PRECIPICE, 1, 16, 20, 0.02f)*/
        );
        // Musician - Level 5: Goat Horns
        registerTrades(ModProfessions.MUSICIAN, 5,
                new TradeData(ModItems.GOLD_COIN, 15, GoatHornItem.getStackForInstrument(Items.GOAT_HORN, Registries.INSTRUMENT.getEntry(RegistryKey.of(RegistryKeys.INSTRUMENT, Identifier.of("minecraft", "admire_goat_horn"))).orElseThrow()), 1, 3, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 15, GoatHornItem.getStackForInstrument(Items.GOAT_HORN, Registries.INSTRUMENT.getEntry(RegistryKey.of(RegistryKeys.INSTRUMENT, Identifier.of("minecraft", "call_goat_horn"))).orElseThrow()), 1, 3, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 15, GoatHornItem.getStackForInstrument(Items.GOAT_HORN, Registries.INSTRUMENT.getEntry(RegistryKey.of(RegistryKeys.INSTRUMENT, Identifier.of("minecraft", "yearn_goat_horn"))).orElseThrow()), 1, 3, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 15, GoatHornItem.getStackForInstrument(Items.GOAT_HORN,Registries.INSTRUMENT.getEntry(RegistryKey.of(RegistryKeys.INSTRUMENT, Identifier.of("minecraft","dream_goat_horn"))).orElseThrow()),1,3,0.02f)
        );

        /* -----DUNG COLLECTOR----- */
        // Dung Collector - Level 1: Dirt Type Blocks
        registerTrades(ModProfessions.DUNG_COLLECTOR, 1,
                new TradeData(Blocks.DIRT, 12, ModItems.GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Blocks.DIRT, 12, ModItems.FAKE_GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Blocks.COARSE_DIRT, 12, ModItems.GOLD_COIN, 2, 16, 2, 0.02f),
                new TradeData(Blocks.COARSE_DIRT, 12, ModItems.FAKE_GOLD_COIN, 2, 16, 2, 0.02f),
                new TradeData(Blocks.ROOTED_DIRT, 12, ModItems.GOLD_COIN, 3, 16, 2, 0.02f),
                new TradeData(Blocks.ROOTED_DIRT, 12, ModItems.FAKE_GOLD_COIN, 3, 16, 2, 0.02f)
        );
        // Dung Collector - Level 2: Other Natural Shovel Blocks
        registerTrades(ModProfessions.DUNG_COLLECTOR, 2,
                new TradeData(Blocks.SAND, 12, ModItems.GOLD_COIN, 1, 16, 10, 0.02f),
                new TradeData(Blocks.SAND, 12, ModItems.FAKE_GOLD_COIN, 1, 16, 10, 0.02f),
                new TradeData(Blocks.GRAVEL, 12, ModItems.GOLD_COIN, 1, 16, 10, 0.02f),
                new TradeData(Blocks.GRAVEL, 12, ModItems.FAKE_GOLD_COIN, 1, 16, 10, 0.02f),
                new TradeData(Items.CLAY_BALL, 24, ModItems.GOLD_COIN, 1, 16, 10, 0.02f),
                new TradeData(Items.CLAY_BALL, 24, ModItems.FAKE_GOLD_COIN, 1, 16, 10, 0.02f)
        );
        // Dung Collector - Level 3: Mud
        registerTrades(ModProfessions.DUNG_COLLECTOR, 3,
                new TradeData(Blocks.MUD, 12, ModItems.GOLD_COIN, 1, 16, 10, 0.02f),
                new TradeData(Blocks.MUD, 12, ModItems.FAKE_GOLD_COIN, 1, 16, 10, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 8, Blocks.MUD, 8, 12, 10, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 8, ModBlocks.SINKING_MUD, 4, 12, 10, 0.02f)
        );
        // Dung Collector - Level 4: Dung Balls
        registerTrades(ModProfessions.DUNG_COLLECTOR, 4,
                new TradeData(ModItems.GOLD_COIN, 4, ModItems.DUNG_BALL, 12, 8, 15, 0.02f)
        );
        // Dung Collector - Level 5: Dung Blocks
        registerTrades(ModProfessions.DUNG_COLLECTOR, 5,
                new TradeData(ModItems.GOLD_COIN, 4, ModBlocks.DUNG_BLOCK, 12, 8, 30, 0.02f)
        );

        /* -----EXPLOSIVES EXPERT----- */
        // Explosives Expert - Level 1: Gunpowder and Sand
        registerTrades(ModProfessions.EXPLOSIVES_EXPERT, 1,
                new TradeData(Items.GUNPOWDER, 15, ModItems.GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.GUNPOWDER, 15, ModItems.FAKE_GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Blocks.SAND, 12, ModItems.GOLD_COIN, 1, 16, 3, 0.02f),
                new TradeData(Blocks.SAND, 12, ModItems.FAKE_GOLD_COIN, 1, 16, 3, 0.02f)
        );
        // Explosives Expert - Level 2: Flint and Steel
        registerTrades(ModProfessions.EXPLOSIVES_EXPERT, 2,
                new TradeData(Items.IRON_INGOT, 15, ModItems.GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.IRON_INGOT, 15, ModItems.FAKE_GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.FLINT, 12, ModItems.GOLD_COIN, 1, 16, 3, 0.02f),
                new TradeData(Items.FLINT, 12, ModItems.FAKE_GOLD_COIN, 1, 16, 3, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 10, Items.FLINT_AND_STEEL, 2, 5, 12, 0.02f)
        );
        // Explosives Expert - Level 3: Creeper Oil and TNT
        registerTrades(ModProfessions.EXPLOSIVES_EXPERT, 3,
                new TradeData(ModItems.GOLD_COIN, 10, Blocks.TNT, 2, 5, 12, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 10, ModItems.CREEPER_OIL, 1, 3, 20, 0.02f)
        );
        // Explosives Expert - Level 4: Enchantments
        registerTrades(ModProfessions.EXPLOSIVES_EXPERT, 4,
                new TradeData(ModItems.GOLD_COIN, 64, () -> Util.make(new ItemStack(Items.ENCHANTED_BOOK), book -> EnchantedBookItem.addEnchantment(book, new EnchantmentLevelEntry(ModEnchantments.DISTRIBUTION, RandomGenerator.getDefault().nextInt(3) + 1))), 1, 20, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 64, () -> Util.make(new ItemStack(Items.ENCHANTED_BOOK), book -> EnchantedBookItem.addEnchantment(book, new EnchantmentLevelEntry(ModEnchantments.FUSE, RandomGenerator.getDefault().nextInt(3) + 1))), 1, 20, 0.02f)
        );
        // Explosives Expert - Level 5: End Crystal
        registerTrades(ModProfessions.EXPLOSIVES_EXPERT, 5,
                new TradeData(ModItems.GOLD_COIN, 36, Items.END_CRYSTAL, 1, 4, 30, 0.02f)
        );

        /* -----DRUID----- */
        // Druid - Level 1: Wheat Seeds + Grass
        registerTrades(ModProfessions.DRUID, 1,
                new TradeData(Items.WHEAT_SEEDS, 15, ModItems.GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.WHEAT_SEEDS, 15, ModItems.FAKE_GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.GRASS, 12, ModItems.GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.GRASS, 12, ModItems.FAKE_GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.TALL_GRASS, 8, ModItems.GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.TALL_GRASS, 8, ModItems.FAKE_GOLD_COIN, 1, 16, 2, 0.02f)
        );
        // Druid - Level 2: Other Seeds
        registerTrades(ModProfessions.DRUID, 2,
                new TradeData(Items.BEETROOT_SEEDS, 15, ModItems.GOLD_COIN, 1, 5, 6, 0.02f),
                new TradeData(Items.BEETROOT_SEEDS, 15, ModItems.FAKE_GOLD_COIN, 1, 5, 6, 0.02f),
                new TradeData(Items.MELON_SEEDS, 15, ModItems.GOLD_COIN, 1, 5, 6, 0.02f),
                new TradeData(Items.MELON_SEEDS, 15, ModItems.FAKE_GOLD_COIN, 1, 5, 6, 0.02f),
                new TradeData(Items.PUMPKIN_SEEDS, 15, ModItems.GOLD_COIN, 1, 5, 6, 0.02f),
                new TradeData(Items.PUMPKIN_SEEDS, 15, ModItems.FAKE_GOLD_COIN, 1, 5, 6, 0.02f)
        );
        // Druid - Level 3: Vines and Ferns
        registerTrades(ModProfessions.DRUID, 3,
                new TradeData(Items.VINE, 12, ModItems.GOLD_COIN, 1, 16, 15, 0.02f),
                new TradeData(Items.VINE, 12, ModItems.FAKE_GOLD_COIN, 1, 16, 15, 0.02f),
                new TradeData(Items.FERN, 10, ModItems.GOLD_COIN, 1, 16, 15, 0.02f),
                new TradeData(Items.FERN, 10, ModItems.FAKE_GOLD_COIN, 1, 16, 15, 0.02f),
                new TradeData(Items.LARGE_FERN, 6, ModItems.GOLD_COIN, 1, 16, 15, 0.02f),
                new TradeData(Items.LARGE_FERN, 6, ModItems.FAKE_GOLD_COIN, 1, 16, 15, 0.02f)
        );
        // Druid - Level 4: Sniffer Seeds
        registerTrades(ModProfessions.DRUID, 4,
                new TradeData(ModItems.GOLD_COIN, 36, Items.TORCHFLOWER_SEEDS, 1, 4, 20, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 36, Items.PITCHER_POD, 1, 4, 20, 0.02f)
        );
        // Druid - Level 5: Venus Fly Trap Seeds
        registerTrades(ModProfessions.DRUID, 5,
                new TradeData(ModItems.GOLD_COIN, 36, Items.WHEAT_SEEDS, 1, 3, 30, 0.02f)
        );

        /* -----ALCHEMIST----- */
        // Alchemist - Level 1: Potion Ingredients
        registerTrades(ModProfessions.ALCHEMIST, 1,
                new TradeData(Items.NETHER_WART, 15, ModItems.GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.NETHER_WART, 15, ModItems.FAKE_GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.GLASS_BOTTLE, 8, ModItems.GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.GLASS_BOTTLE, 8, ModItems.FAKE_GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.SUGAR, 15, ModItems.GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.SUGAR, 15, ModItems.FAKE_GOLD_COIN, 1, 16, 2, 0.02f),
                new TradeData(Items.GLOWSTONE_DUST, 12, ModItems.GOLD_COIN, 1, 16, 4, 0.02f),
                new TradeData(Items.GLOWSTONE_DUST, 12, ModItems.FAKE_GOLD_COIN, 1, 16, 4, 0.02f),
                new TradeData(Items.PHANTOM_MEMBRANE, 8, ModItems.GOLD_COIN, 1, 16, 4, 0.02f),
                new TradeData(Items.PHANTOM_MEMBRANE, 8, ModItems.FAKE_GOLD_COIN, 1, 16, 4, 0.02f)
        );
       /* // Alchemist - Level 2: Low Tier Potions
        registerTrades(ModProfessions.ALCHEMIST, 2,
                new TradeData(ModItems.GOLD_COIN, 18, PotionContentsComponent.createStack(Items.POTION, Potions.STRONG_HEALING).getItem(), 1, 5, 8, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 18, PotionContentsComponent.createStack(Items.SPLASH_POTION, Potions.STRONG_HARMING).getItem(), 1, 5, 8, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 18, PotionContentsComponent.createStack(Items.POTION, Potions.STRONG_LEAPING).getItem(), 1, 5, 8, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 18, PotionContentsComponent.createStack(Items.POTION, Potions.LONG_LEAPING).getItem(), 1, 5, 8, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 18, PotionContentsComponent.createStack(Items.POTION, Potions.LONG_NIGHT_VISION).getItem(), 1, 5, 8, 0.02f)
        );
        // Alchemist - Level 3: Middle Tier Potions
        registerTrades(ModProfessions.ALCHEMIST, 3,
                new TradeData(ModItems.GOLD_COIN, 22, PotionContentsComponent.createStack(Items.POTION, Potions.LONG_SWIFTNESS).getItem(), 1, 5, 15, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 22, PotionContentsComponent.createStack(Items.POTION, Potions.STRONG_SWIFTNESS).getItem(), 1, 5, 15, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 22, PotionContentsComponent.createStack(Items.SPLASH_POTION, Potions.LONG_SLOWNESS).getItem(), 1, 5, 15, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 22, PotionContentsComponent.createStack(Items.SPLASH_POTION, Potions.STRONG_SLOWNESS).getItem(), 1, 5, 15, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 22, PotionContentsComponent.createStack(Items.SPLASH_POTION, Potions.LONG_POISON).getItem(), 1, 5, 15, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 22, PotionContentsComponent.createStack(Items.SPLASH_POTION, Potions.STRONG_POISON).getItem(), 1, 5, 15, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 22, PotionContentsComponent.createStack(Items.SPLASH_POTION, Potions.LONG_SLOW_FALLING).getItem(), 1, 5, 15, 0.02f)
        );
        // Alchemist - Level 4: High Tier Potions
        registerTrades(ModProfessions.ALCHEMIST, 4,
                new TradeData(ModItems.GOLD_COIN, 28, PotionContentsComponent.createStack(Items.POTION, Potions.LONG_INVISIBILITY).getItem(), 1, 4, 20, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 28, PotionContentsComponent.createStack(Items.POTION, Potions.LONG_WATER_BREATHING).getItem(), 1, 4, 20, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 28, PotionContentsComponent.createStack(Items.POTION, Potions.LONG_FIRE_RESISTANCE).getItem(), 1, 4, 20, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 28, PotionContentsComponent.createStack(Items.POTION, Potions.LONG_REGENERATION).getItem(), 1, 4, 20, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 28, PotionContentsComponent.createStack(Items.POTION, Potions.STRONG_REGENERATION).getItem(), 1, 4, 20, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 28, PotionContentsComponent.createStack(Items.SPLASH_POTION, Potions.OOZING).getItem(), 1, 4, 20, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 28, PotionContentsComponent.createStack(Items.SPLASH_POTION, Potions.INFESTED).getItem(), 1, 4, 20, 0.02f),
                new TradeData(ModItems.GOLD_COIN, 28, PotionContentsComponent.createStack(Items.SPLASH_POTION, Potions.WEAVING).getItem(), 1, 4, 20, 0.02f)
        );
        // Alchemist - Level 5: Combo Potions
        registerTrades(ModProfessions.ALCHEMIST, 5,
                new TradeData(ModItems.GOLD_COIN, 36, PotionContentsComponent.createStack(Items.POTION, Potions.LONG_TURTLE_MASTER).getItem(), 1, 3, 30, 0.02f)
        );*/

        /* -----OCCULTIST----- */
        //Theme trades around warping items into something else?
        //E.g. Nether Wart into Warped Nether Wart
        //Might need trades with multiple inputs
        // Occultist - Level 1: ???
        registerTrades(ModProfessions.OCCULTIST, 1,
                new TradeData(ModItems.GOLD_COIN, 64, Items.REDSTONE, 1, 1, 6, 0.02f)
        );

        /* -----GUARD----- */
        // Guard - Level 1: Spear
        registerTrades(ModProfessions.GUARD, 1,
                new TradeData(ModItems.GOLD_COIN, 36, Items.TRIDENT, 1, 1, 6, 0.02f),
                new TradeData(Items.IRON_INGOT, 4, ModItems.GOLD_COIN, 1, 16, 6, 0.02f),
                new TradeData(Items.IRON_INGOT, 4, ModItems.FAKE_GOLD_COIN, 1, 16, 6, 0.02f),
                new TradeData(Items.STICK, 32, ModItems.GOLD_COIN, 1, 16, 6, 0.02f),
                new TradeData(Items.STICK, 32, ModItems.FAKE_GOLD_COIN, 1, 16, 6, 0.02f)
        );
    }

    private static void registerTrades(VillagerProfession prof, int level, TradeData... trades) {
        TradeOfferHelper.registerVillagerOffers(prof, level, factories -> {
            for (TradeData t : trades) {
                factories.add((entity, random) -> {

                    ItemStack first = new ItemStack(t.input1(), t.count1());

                    ItemStack second = (t.count2() > 0 && t.input2() != Items.AIR) ? new ItemStack(t.input2(), t.count2()) : ItemStack.EMPTY;

                    ItemStack result = t.outputStackSupplier().get();

                    return new TradeOffer(first, second, result, t.maxUses(), t.merchantExperience(), t.priceMultiplier());
                });
            }
        });
    }

    public record TradeData(ItemConvertible input1, int count1, ItemConvertible input2, int count2, Supplier<ItemStack> outputStackSupplier, int maxUses, int merchantExperience, float priceMultiplier
    ) {
        public TradeData(ItemConvertible in1, int c1, ItemConvertible outItem, int outCount, int uses, int xp, float priceMult) {
            this(in1, c1, Items.AIR, 0, () -> new ItemStack(outItem, outCount), uses, xp, priceMult);
        }

        public TradeData(ItemConvertible in1, int c1, ItemConvertible in2, int c2, ItemConvertible outItem, int outCount, int uses, int xp, float priceMult) {
            this(in1, c1, in2, c2, () -> new ItemStack(outItem, outCount), uses, xp, priceMult);
        }

        public TradeData(ItemConvertible in1, int c1, Supplier<ItemStack> outputSupplier, int uses, int xp, float priceMult) {
            this(in1, c1, Items.AIR, 0, outputSupplier, uses, xp, priceMult);
        }

        public TradeData(ItemConvertible in1, int c1, ItemStack outStack, int uses, int xp, float priceMult) {
            this(in1, c1, Items.AIR, 0, outStack::copy, uses, xp, priceMult);
        }
    }
}
