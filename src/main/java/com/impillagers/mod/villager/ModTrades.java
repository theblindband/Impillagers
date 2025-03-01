package com.impillagers.mod.villager;

import com.impillagers.mod.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

public class ModTrades {

    public static void registerModTrades() {
        //Impillagers.LOGGER.info("Registering Mod Trades for " + Impillagers.MOD_ID);

        //Banker
        TradeOfferHelper.registerVillagerOffers(Banker.BANKER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.FIREFLY_BOTTLE, 1),
                    new ItemStack(ModItems.GOLD_COIN, 8), 16, 2, 0.04f));
        });

        TradeOfferHelper.registerVillagerOffers(Banker.BANKER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.FIREFLY_BOTTLE, 1),
                    new ItemStack(ModItems.FAKE_GOLD_COIN, 8), 16, 2, 0.04f));
        });

        TradeOfferHelper.registerVillagerOffers(Banker.BANKER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.GOLD_INGOT, 16),
                    new ItemStack(ModItems.GOLD_COIN, 32), 16, 2, 0.04f));
        });
        TradeOfferHelper.registerVillagerOffers(Banker.BANKER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.GOLD_INGOT, 16),
                    new ItemStack(ModItems.FAKE_GOLD_COIN, 64), 16, 2, 0.04f));
        });
    }
}