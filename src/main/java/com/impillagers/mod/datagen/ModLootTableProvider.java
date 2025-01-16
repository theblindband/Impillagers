package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.PURPLE_HEART_LOG);
        addDrop(ModBlocks.PURPLE_HEART_WOOD);
        addDrop(ModBlocks.STRIPPED_PURPLE_HEART_LOG);
        addDrop(ModBlocks.STRIPPED_PURPLE_HEART_WOOD);

        addDrop(ModBlocks.PURPLE_HEART_PLANKS);

        addDrop(ModBlocks.PURPLE_HEART_STAIRS);
        addDrop(ModBlocks.PURPLE_HEART_SLAB);
        addDrop(ModBlocks.PURPLE_HEART_FENCE);
        addDrop(ModBlocks.PURPLE_HEART_FENCE_GATE);

        addDrop(ModBlocks.PURPLE_HEART_DOOR, doorDrops(ModBlocks.PURPLE_HEART_DOOR));
        addDrop(ModBlocks.PURPLE_HEART_TRAPDOOR);
        addDrop(ModBlocks.PURPLE_HEART_PRESSURE_PLATE);
        addDrop(ModBlocks.PURPLE_HEART_BUTTON);

        addDrop(ModBlocks.PURPLE_HEART_SAPLING);

        addDrop(ModBlocks.SINKING_MUD);
    }

    //Loot Table Builders
    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
