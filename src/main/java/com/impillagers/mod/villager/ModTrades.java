package com.impillagers.mod.villager;

import com.impillagers.mod.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

public class ModTrades {

    public static void registerModTrades() {
        //Impillagers.LOGGER.info("Registering Mod Trades for " + Impillagers.MOD_ID);

        //Banker
        //Level 1 - Raw Gold Trades
        TradeOfferHelper.registerVillagerOffers(Banker.BANKER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.RAW_GOLD, 2),
                    new ItemStack(ModItems.GOLD_COIN, 1), 16, 2, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Banker.BANKER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.GOLD_NUGGET, 2),
                    new ItemStack(ModItems.FAKE_GOLD_COIN, 1), 16, 2, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Banker.BANKER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Blocks.RAW_GOLD_BLOCK, 2),
                    new ItemStack(ModItems.GOLD_COIN, 8), 16, 2, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Banker.BANKER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Blocks.RAW_GOLD_BLOCK, 2),
                    new ItemStack(ModItems.FAKE_GOLD_COIN, 8), 16, 2, 0.04f));
        });
        //Level 2 - Gold Ingot Trades
        TradeOfferHelper.registerVillagerOffers(Banker.BANKER, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.GOLD_INGOT, 1),
                    new ItemStack(ModItems.GOLD_COIN, 1), 16, 2, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Banker.BANKER, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.GOLD_INGOT, 1),
                    new ItemStack(ModItems.FAKE_GOLD_COIN, 1), 16, 2, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Banker.BANKER, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Blocks.GOLD_BLOCK, 1),
                    new ItemStack(ModItems.GOLD_COIN, 8), 16, 2, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Banker.BANKER, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.GOLD_BLOCK, 1),
                    new ItemStack(ModItems.FAKE_GOLD_COIN, 8), 16, 2, 0.04f));
        });
        //Level 3 - Gold Armor Trades
        //Level 4 - ???
        //Level 5 - ???

        //Musician
        //Level 1 - Dungeon Discs
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.MUSIC_DISC_CAT, 1),
                    new ItemStack(ModItems.FAKE_GOLD_COIN, 8), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.MUSIC_DISC_CAT, 1),
                    new ItemStack(ModItems.GOLD_COIN, 8), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 16),
                    new ItemStack(Items.MUSIC_DISC_CAT, 1), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.MUSIC_DISC_13, 1),
                    new ItemStack(ModItems.FAKE_GOLD_COIN, 8), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.MUSIC_DISC_13, 1),
                    new ItemStack(ModItems.GOLD_COIN, 8), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 16),
                    new ItemStack(Items.MUSIC_DISC_13, 1), 16, 6, 0.04f));
        });
        //Level 2 - Creeper Discs
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 64),
                    new ItemStack(Items.MUSIC_DISC_11, 1), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 64),
                    new ItemStack(Items.MUSIC_DISC_CHIRP, 1), 16, 6, 0.04f));
        });
        //Level 3 - Structure Discs
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 64),
                    new ItemStack(Items.MUSIC_DISC_CREATOR, 1), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 48),
                    new ItemStack(Items.MUSIC_DISC_CREATOR_MUSIC_BOX, 1), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 32),
                    new ItemStack(Items.MUSIC_DISC_PIGSTEP, 1), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 64),
                    new ItemStack(Items.MUSIC_DISC_RELIC, 1), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 64),
                    new ItemStack(Items.MUSIC_DISC_OTHERSIDE, 1), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 8),
                    new ItemStack(Items.DISC_FRAGMENT_5, 1), 16, 6, 0.04f));
        });
        //Level 4 - Noteblock/Jukebox
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Blocks.NOTE_BLOCK, 4),
                    new ItemStack(ModItems.FAKE_GOLD_COIN, 2), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Blocks.NOTE_BLOCK, 4),
                    new ItemStack(ModItems.GOLD_COIN, 2), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 2),
                    new ItemStack(Blocks.NOTE_BLOCK, 2), 16, 6, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 16),
                    new ItemStack(Blocks.JUKEBOX, 1), 16, 6, 0.04f));
        });
        //Level 5 - Goat Horns
        TradeOfferHelper.registerVillagerOffers(Musician.MUSICIAN, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GOLD_COIN, 16),
                    new ItemStack(Items.GOAT_HORN, 1), 16, 6, 0.04f));
        });

        //Dung Collector
        //Level 1 - Dirt Type Blocks
        //Level 2 - Other Natural Shovel Blocks
        //Level 3 - Mud
        //Level 4 - Dung Balls
        //Level 5 - Dung Blocks

        //Explosives Expert
        //Level 1 - Gunpowder and Sand
        //Level 2 - TNT
        //Level 3 - Creeper Oil
        //Level 4 - Enchantments
        //Level 5 - End Crystals (Might be too strong to buy them from it)
    }
}