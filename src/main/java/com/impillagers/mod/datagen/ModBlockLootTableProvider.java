package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        //Block Drops
        dropSelf(ModBlocks.PURPLE_HEART_LOG.get());
        dropSelf(ModBlocks.PURPLE_HEART_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_PURPLE_HEART_LOG.get());
        dropSelf(ModBlocks.STRIPPED_PURPLE_HEART_WOOD.get());
        dropSelf(ModBlocks.PURPLE_HEART_PLANKS.get());
        dropSelf(ModBlocks.PURPLE_HEART_STAIRS.get());
        dropSelf(ModBlocks.PURPLE_HEART_SLAB.get());
        dropSelf(ModBlocks.PURPLE_HEART_FENCE.get());
        dropSelf(ModBlocks.PURPLE_HEART_FENCE_GATE.get());
        add(ModBlocks.PURPLE_HEART_DOOR.get(),
                block -> createDoorTable(ModBlocks.PURPLE_HEART_DOOR.get()));
        dropSelf(ModBlocks.PURPLE_HEART_TRAPDOOR.get());
        dropSelf(ModBlocks.PURPLE_HEART_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.PURPLE_HEART_BUTTON.get());
        dropSelf(ModBlocks.PURPLE_HEART_SAPLING.get());
        add(ModBlocks.PURPLE_HEART_LEAVES.get(),
               block -> createLeavesDrops(ModBlocks.PURPLE_HEART_LEAVES.get(), ModBlocks.PURPLE_HEART_SAPLING.get(), 5));
        add(ModBlocks.POTTED_PURPLE_HEART_SAPLING.get(),
                block -> createPotFlowerItemTable(ModBlocks.PURPLE_HEART_SAPLING.get()));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
