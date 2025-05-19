package com.impillagers.mod.villager;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.villager.professions.ModProfessions;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.village.VillagerProfession;

public class ModTrades {

    public static void registerModTrades() {

        // Banker - Level 1: Raw Gold Trades
        registerTrades(ModProfessions.BANKER, 1,
                new TradeData(Items.RAW_GOLD, 2, ModItems.GOLD_COIN, 1, 16, 2, 0.04f),
                new TradeData(Items.GOLD_NUGGET, 2, ModItems.FAKE_GOLD_COIN, 1, 16, 2, 0.04f),
                new TradeData(Blocks.RAW_GOLD_BLOCK, 2, ModItems.GOLD_COIN, 8, 16, 2, 0.04f),
                new TradeData(Blocks.RAW_GOLD_BLOCK, 2, ModItems.FAKE_GOLD_COIN, 8, 16, 2, 0.04f)
        );
        // Banker - Level 2: Gold Ingot Trades
        registerTrades(ModProfessions.BANKER, 2,
                new TradeData(Items.GOLD_INGOT, 1, ModItems.GOLD_COIN, 1, 16, 2, 0.04f),
                new TradeData(Items.GOLD_INGOT, 1, ModItems.FAKE_GOLD_COIN, 1, 16, 2, 0.04f),
                new TradeData(Blocks.GOLD_BLOCK, 1, ModItems.GOLD_COIN, 8, 16, 2, 0.04f),
                new TradeData(Items.GOLD_BLOCK, 1, ModItems.FAKE_GOLD_COIN, 8, 16, 2, 0.04f)
        );
        // Banker - Level 3: Gold Trades (Armor and Tools)
        registerTrades(ModProfessions.BANKER, 3,
                new TradeData(ModItems.GOLD_COIN, 32, Items.GOLDEN_HELMET, 1, 16, 2, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 32, Items.GOLDEN_CHESTPLATE, 1, 16, 2, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 32, Items.GOLDEN_LEGGINGS, 1, 16, 2, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 32, Items.GOLDEN_BOOTS, 1, 16, 2, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 32, Items.GOLDEN_AXE, 1, 16, 2, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 32, Items.GOLDEN_HOE, 1, 16, 2, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 32, Items.GOLDEN_PICKAXE, 1, 16, 2, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 32, Items.GOLDEN_SHOVEL, 1, 16, 2, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 32, Items.GOLDEN_SWORD, 1, 16, 2, 0.04f)
        );
        // Banker - Level 4: Rare Gold Items
        registerTrades(ModProfessions.BANKER, 4,
                new TradeData(ModItems.GOLD_COIN, 48, Blocks.BELL, 1, 16, 2, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 32, Items.GOLDEN_APPLE, 1, 16, 2, 0.04f)
        );
        // Banker - Level 5: Fake Coins to Real Coins
        registerTrades(ModProfessions.BANKER, 5,
                new TradeData(ModItems.FAKE_GOLD_COIN, 16, ModItems.GOLD_COIN, 1, 16, 2, 0.04f)
        );

        // Musician - Level 1: Dungeon Discs
        registerTrades(ModProfessions.MUSICIAN, 1,
                new TradeData(Items.MUSIC_DISC_CAT, 1, ModItems.FAKE_GOLD_COIN, 8, 16, 6, 0.04f),
                new TradeData(Items.MUSIC_DISC_CAT, 1, ModItems.GOLD_COIN, 8, 16, 6, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 16, Items.MUSIC_DISC_CAT, 1, 16, 6, 0.04f),
                new TradeData(Items.MUSIC_DISC_13, 1, ModItems.FAKE_GOLD_COIN, 8, 16, 6, 0.04f),
                new TradeData(Items.MUSIC_DISC_13, 1, ModItems.GOLD_COIN, 8, 16, 6, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 16, Items.MUSIC_DISC_13, 1, 16, 6, 0.04f)
        );
        // Musician - Level 2: Creeper Discs
        registerTrades(ModProfessions.MUSICIAN, 2,
                new TradeData(ModItems.GOLD_COIN, 64, Items.MUSIC_DISC_11, 1, 16, 6, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 64, Items.MUSIC_DISC_CHIRP, 1, 16, 6, 0.04f)
        );
        // Musician - Level 3: Structure Discs
        registerTrades(ModProfessions.MUSICIAN, 3,
                new TradeData(ModItems.GOLD_COIN, 64, Items.MUSIC_DISC_CREATOR, 1, 16, 6, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 48, Items.MUSIC_DISC_CREATOR_MUSIC_BOX, 1, 16, 6, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 32, Items.MUSIC_DISC_PIGSTEP, 1, 16, 6, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 64, Items.MUSIC_DISC_RELIC, 1, 16, 6, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 64, Items.MUSIC_DISC_OTHERSIDE, 1, 16, 6, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 8, Items.DISC_FRAGMENT_5, 1, 16, 6, 0.04f)
        );
        // Musician - Level 4: Noteblock/Jukebox
        registerTrades(ModProfessions.MUSICIAN, 4,
                new TradeData(Blocks.NOTE_BLOCK, 4, ModItems.FAKE_GOLD_COIN, 2, 16, 6, 0.04f),
                new TradeData(Blocks.NOTE_BLOCK, 4, ModItems.GOLD_COIN, 2, 16, 6, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 2, Blocks.NOTE_BLOCK, 2, 16, 6, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 16, Blocks.JUKEBOX, 1, 16, 6, 0.04f)
        );
        // Musician - Level 5: Goat Horns
        registerTrades(ModProfessions.MUSICIAN, 5,
                new TradeData(ModItems.GOLD_COIN, 16, Items.GOAT_HORN, 1, 16, 6, 0.04f)
        );

        // Dung Collector - Level 1: Dirt Type Blocks
        registerTrades(ModProfessions.DUNG_COLLECTOR, 1,
                new TradeData(Blocks.DIRT, 16, ModItems.GOLD_COIN, 1, 16, 6, 0.04f),
                new TradeData(Blocks.COARSE_DIRT, 16, ModItems.GOLD_COIN, 2, 16, 6, 0.04f),
                new TradeData(Blocks.ROOTED_DIRT, 16, ModItems.GOLD_COIN, 3, 16, 6, 0.04f),
                new TradeData(Blocks.DIRT, 16, ModItems.FAKE_GOLD_COIN, 1, 16, 6, 0.04f),
                new TradeData(Blocks.COARSE_DIRT, 16, ModItems.FAKE_GOLD_COIN, 2, 16, 6, 0.04f),
                new TradeData(Blocks.ROOTED_DIRT, 16, ModItems.FAKE_GOLD_COIN, 3, 16, 6, 0.04f)
        );
        // Dung Collector - Level 2: Other Natural Shovel Blocks
        registerTrades(ModProfessions.DUNG_COLLECTOR, 2,
                new TradeData(Blocks.SAND, 32, ModItems.GOLD_COIN, 1, 16, 6, 0.04f),
                new TradeData(Blocks.GRAVEL, 16, ModItems.GOLD_COIN, 1, 16, 6, 0.04f),
                new TradeData(Items.CLAY_BALL, 32, ModItems.GOLD_COIN, 1, 16, 6, 0.04f),
                new TradeData(Blocks.SAND, 32, ModItems.FAKE_GOLD_COIN, 1, 16, 6, 0.04f),
                new TradeData(Blocks.GRAVEL, 16, ModItems.FAKE_GOLD_COIN, 1, 16, 6, 0.04f),
                new TradeData(Items.CLAY_BALL, 32, ModItems.FAKE_GOLD_COIN, 1, 16, 6, 0.04f)
        );
        // Dung Collector - Level 3: Mud
        registerTrades(ModProfessions.DUNG_COLLECTOR, 3,
                new TradeData(ModItems.GOLD_COIN, 8, Blocks.MUD, 8, 16, 6, 0.04f),
                new TradeData(ModItems.GOLD_COIN, 8, ModBlocks.SINKING_MUD, 4, 16, 6, 0.04f)
        );
        // Dung Collector - Level 4: Dung Balls
        registerTrades(ModProfessions.DUNG_COLLECTOR, 4,
                new TradeData(ModItems.GOLD_COIN, 8, ModItems.DUNG_BALL, 6, 16, 6, 0.04f)
        );
        // Dung Collector - Level 5: Dung Blocks
        registerTrades(ModProfessions.DUNG_COLLECTOR, 5,
                new TradeData(ModItems.GOLD_COIN, 16, ModBlocks.DUNG_BLOCK, 8, 16, 6, 0.04f)
        );

        // Explosives Expert - Level 1: Gunpowder and Sand
        registerTrades(ModProfessions.EXPLOSIVES_EXPERT, 1,
                new TradeData(Items.GUNPOWDER, 16, ModItems.GOLD_COIN, 2, 14, 6, 0.04f),
                new TradeData(Blocks.SAND, 16, ModItems.GOLD_COIN, 2, 14, 6, 0.04f),
                new TradeData(Items.GUNPOWDER, 16, ModItems.FAKE_GOLD_COIN, 2, 14, 6, 0.04f),
                new TradeData(Blocks.SAND, 16, ModItems.FAKE_GOLD_COIN, 2, 14, 6, 0.04f)
        );
        // Explosives Expert - Level 2: TNT
        registerTrades(ModProfessions.EXPLOSIVES_EXPERT, 2,
                new TradeData(ModItems.GOLD_COIN, 6, Blocks.TNT, 2, 6, 6, 0.04f)
        );
        // Explosives Expert - Level 3: Creeper Oil
        registerTrades(ModProfessions.EXPLOSIVES_EXPERT, 3,
                new TradeData(ModItems.GOLD_COIN, 10, ModItems.CREEPER_OIL, 1, 3, 6, 0.04f)
        );
        // Explosives Expert - Level 4: Enchantments
        registerTrades(ModProfessions.EXPLOSIVES_EXPERT, 4,
                new TradeData(ModItems.GOLD_COIN, 64, Items.BOOK, 1, 1, 6, 0.04f)
        );
        // Explosives Expert - Level 5: End Crystal
        registerTrades(ModProfessions.EXPLOSIVES_EXPERT, 5,
                new TradeData(ModItems.GOLD_COIN, 16, Items.END_CRYSTAL, 1, 4, 6, 0.04f)
        );

        // Druid - Level 1:
        registerTrades(ModProfessions.DRUID, 1,
                new TradeData(ModItems.GOLD_COIN, 64, ModBlocks.BELLADONNA, 1, 1, 6, 0.04f)
        );

        // Alchemist - Level 1:
        registerTrades(ModProfessions.ALCHEMIST, 1,
                new TradeData(ModItems.GOLD_COIN, 64, Items.GLASS_BOTTLE, 1, 1, 6, 0.04f)
        );

        // Occultist - Level 1:
        registerTrades(ModProfessions.OCCULTIST, 1,
                new TradeData(ModItems.GOLD_COIN, 64, Items.NETHER_WART, 1, 1, 6, 0.04f)
        );

        // Clan Leader - Level 1:
        registerTrades(ModProfessions.CLAN_LEADER, 1,
                new TradeData(ModItems.GOLD_COIN, 64, Items.GOLDEN_APPLE, 1, 1, 6, 0.04f)
        );

        // Guard - Level 1:
        registerTrades(ModProfessions.GUARD, 1,
                new TradeData(ModItems.GOLD_COIN, 64, Items.TRIDENT, 1, 1, 6, 0.04f)
        );
    }

    private static void registerTrades(VillagerProfession profession, int level, TradeData... trades) {
        TradeOfferHelper.registerVillagerOffers(profession, level, factories -> {
            for (TradeData data : trades) {
                factories.add((entity, random) ->
                        new TradeOffer(
                                new TradedItem(data.inputItem, data.inputCount),
                                new ItemStack(data.outputItem, data.outputCount),
                                data.maxUses,
                                data.merchantExperience,
                                data.priceMultiplier
                        )
                );
            }
        });
    }

    private record TradeData(ItemConvertible inputItem, int inputCount, ItemConvertible outputItem, int outputCount, int maxUses, int merchantExperience, float priceMultiplier) {
    }
}
