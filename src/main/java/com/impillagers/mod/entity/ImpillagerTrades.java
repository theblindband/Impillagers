package com.impillagers.mod.entity;

import com.google.common.collect.ImmutableMap;
import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.Optional;

import static com.impillagers.mod.entity.ImpillagerTrades.ItemsForEmeralds.toIntMap;

public class ImpillagerTrades {
    public static final Int2ObjectMap<VillagerTrades.ItemListing[]> IMPILLAGER_TRADES = toIntMap(
            ImmutableMap.of(
                    1,
                    new VillagerTrades.ItemListing[]{
                            //Items for Emeralds
                            new ImpillagerTrades.ItemsForEmeralds(Items.SLIME_BALL, 2, 2, 10, 1),
                            new ImpillagerTrades.ItemsForEmeralds(Items.QUARTZ, 5, 2, 15, 1),
                            new ImpillagerTrades.ItemsForEmeralds(Blocks.CRIMSON_FUNGUS, 10, 1, 1, 1),
                            new ImpillagerTrades.ItemsForEmeralds(Blocks.WARPED_FUNGUS, 10, 1, 1, 1),
                            new ImpillagerTrades.ItemsForEmeralds(ModBlocks.SINKING_MUD.toStack(), 1, 32, 999, 1),
                            new ImpillagerTrades.ItemsForEmeralds(ModBlocks.BELLADONNA.toStack(), 1, 16, 999, 1),
                            new ImpillagerTrades.ItemsForEmeralds(ModBlocks.PURPLE_HEART_SAPLING.toStack(), 1, 16, 999, 1),
                            new ImpillagerTrades.ItemsForEmeralds(Blocks.BROWN_MUSHROOM, 1, 1, 8, 1),
                            new ImpillagerTrades.ItemsForEmeralds(Blocks.RED_MUSHROOM, 1, 1, 8, 1),
                            //Emeralds for Items
                            new ImpillagerTrades.EmeraldForItems(Blocks.PUMPKIN, 6, 4, 1),
                            new ImpillagerTrades.EmeraldForItems(Items.GOLD_INGOT, 4, 4, 1, 1),
                            new ImpillagerTrades.EmeraldForItems(Items.BEETROOT, 3, 4, 1, 1),
                            new ImpillagerTrades.EmeraldForItems(Items.STRING, 1, 4, 1, 1),
                            new ImpillagerTrades.EmeraldForItems(Items.STRING, 1, 4, 1, 1)
                    },
                    2,
                    new VillagerTrades.ItemListing[]{
                            //Rare Trades
                            new ImpillagerTrades.ItemsForEmeralds(Blocks.WARPED_NYLIUM, 32, 1, 5, 1),
                            new ImpillagerTrades.ItemsForEmeralds(Blocks.CRIMSON_NYLIUM, 32, 1, 5, 1),
                            new ImpillagerTrades.ItemsForEmeralds(Blocks.WITHER_SKELETON_SKULL, 64, 1, 2, 1),
                            new ImpillagerTrades.ItemsForEmeralds(ModItems.PAINTED_SMITHING_TEMPLATE.toStack(), 8, 1, 4, 1)
                    }
            )
    );

    static class EmeraldForItems implements VillagerTrades.ItemListing {
        private final ItemCost itemStack;
        private final int maxUses;
        private final int villagerXp;
        private final int emeraldAmount;
        private final float priceMultiplier;

        public EmeraldForItems(ItemLike item, int cost, int maxUses, int villagerXp) {
            this(item, cost, maxUses, villagerXp, 1);
        }

        public EmeraldForItems(ItemLike item, int cost, int maxUses, int villagerXp, int emeraldAmount) {
            this(new ItemCost(item.asItem(), cost), maxUses, villagerXp, emeraldAmount);
        }

        public EmeraldForItems(ItemCost itemStack, int maxUses, int villagerXp, int emeraldAmount) {
            this.itemStack = itemStack;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.emeraldAmount = emeraldAmount;
            this.priceMultiplier = 0.05F;
        }

        @Override
        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            return new MerchantOffer(this.itemStack, new ItemStack(Items.EMERALD, this.emeraldAmount), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsForEmeralds implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;
        private final Optional<ResourceKey<EnchantmentProvider>> enchantmentProvider;

        public ItemsForEmeralds(Block block, int emeraldCost, int numberOfItems, int maxUses, int villagerXp) {
            this(new ItemStack(block), emeraldCost, numberOfItems, maxUses, villagerXp);
        }

        public ItemsForEmeralds(Item item, int emeraldCost, int numberOfItems, int villagerXp) {
            this(new ItemStack(item), emeraldCost, numberOfItems, 12, villagerXp);
        }

        public ItemsForEmeralds(Item item, int emeraldCost, int numberOfItems, int maxUses, int villagerXp) {
            this(new ItemStack(item), emeraldCost, numberOfItems, maxUses, villagerXp);
        }

        public ItemsForEmeralds(ItemStack itemStack, int emeraldCost, int numberOfItems, int maxUses, int villagerXp) {
            this(itemStack, emeraldCost, numberOfItems, maxUses, villagerXp, 0.05F);
        }

        public ItemsForEmeralds(Item item, int emeraldCost, int numberOfItems, int maxUses, int villagerXp, float priceMultiplier) {
            this(new ItemStack(item), emeraldCost, numberOfItems, maxUses, villagerXp, priceMultiplier);
        }

        public ItemsForEmeralds(
                Item item, int emeraldCost, int numberOfItems, int maxUses, int villagerXp, float priceMultiplier, ResourceKey<EnchantmentProvider> enchantmentProvider
        ) {
            this(new ItemStack(item), emeraldCost, numberOfItems, maxUses, villagerXp, priceMultiplier, Optional.of(enchantmentProvider));
        }

        public ItemsForEmeralds(ItemStack itemStack, int emeraldCost, int numberOfItems, int maxUses, int villagerXp, float priceMultiplier) {
            this(itemStack, emeraldCost, numberOfItems, maxUses, villagerXp, priceMultiplier, Optional.empty());
        }

        public ItemsForEmeralds(
                ItemStack itemStack,
                int emeraldCost,
                int numberOfItems,
                int maxUses,
                int villagerXp,
                float priceMultiplier,
                Optional<ResourceKey<EnchantmentProvider>> enchantmentProvider
        ) {
            this.itemStack = itemStack;
            this.emeraldCost = emeraldCost;
            this.itemStack.setCount(numberOfItems);
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = priceMultiplier;
            this.enchantmentProvider = enchantmentProvider;
        }

        @Override
        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            ItemStack itemstack = this.itemStack.copy();
            Level level = trader.level();
            this.enchantmentProvider
                    .ifPresent(
                            p_348340_ -> EnchantmentHelper.enchantItemFromProvider(
                                    itemstack,
                                    level.registryAccess(),
                                    (ResourceKey<EnchantmentProvider>)p_348340_,
                                    level.getCurrentDifficultyAt(trader.blockPosition()),
                                    random
                            )
                    );
            return new MerchantOffer(new ItemCost(Items.EMERALD, this.emeraldCost), itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }

        static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> map) {
            return new Int2ObjectOpenHashMap<>(map);
        }
    }
}
