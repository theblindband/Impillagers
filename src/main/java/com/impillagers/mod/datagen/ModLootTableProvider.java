package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
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
//TODO: FIX
public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
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
        addDrop(ModBlocks.PURPLE_HEART_SIGN, ModItems.PURPLE_HEART_SIGN);
        addDrop(ModBlocks.PURPLE_HEART_WALL_SIGN, ModItems.PURPLE_HEART_SIGN);
        addDrop(ModBlocks.PURPLE_HEART_HANGING_SIGN, ModItems.PURPLE_HEART_HANGING_SIGN);
        addDrop(ModBlocks.PURPLE_HEART_WALL_HANGING_SIGN, ModItems.PURPLE_HEART_HANGING_SIGN);

        addDrop(ModBlocks.SINKING_MUD);
        addDrop(ModBlocks.BELLADONNA);
        addPottedPlantDrops(ModBlocks.POTTED_BELLADONNA);
        addDrop(ModBlocks.FIREFLY_BUSH);

        //addDrop(ModBlocks.DUNG_BLOCK, multipleOreDrops(ModBlocks.DUNG_BLOCK, ModItems.DUNG_BALL, 1, 4));

        addDrop(ModBlocks.PACKED_DUNG);
        addDrop(ModBlocks.PACKED_DUNG_STAIRS);
        addDrop(ModBlocks.PACKED_DUNG_SLAB);
        addDrop(ModBlocks.PACKED_DUNG_WALL);

        addDrop(ModBlocks.DUNG_BRICKS);
        addDrop(ModBlocks.DUNG_BRICKS_STAIRS);
        addDrop(ModBlocks.DUNG_BRICKS_SLAB);
        addDrop(ModBlocks.DUNG_BRICKS_WALL);

        addDrop(ModBlocks.MOSSY_DUNG_BRICKS);
        addDrop(ModBlocks.MOSSY_DUNG_BRICKS_STAIRS);
        addDrop(ModBlocks.MOSSY_DUNG_BRICKS_SLAB);
        addDrop(ModBlocks.MOSSY_DUNG_BRICKS_WALL);

        addDrop(ModBlocks.CRACKED_DUNG_BRICKS);
        addDrop(ModBlocks.CHISELED_DUNG_BRICKS);
        addDrop(ModBlocks.DUNG_BRICKS_PILLAR);

        addDrop(ModBlocks.PACKED_MUD_STAIRS);
        addDrop(ModBlocks.PACKED_MUD_SLAB);
        addDrop(ModBlocks.PACKED_MUD_WALL);

        addDrop(ModBlocks.MOSSY_MUD_BRICKS);
        addDrop(ModBlocks.MOSSY_MUD_BRICKS_STAIRS);
        addDrop(ModBlocks.MOSSY_MUD_BRICKS_SLAB);
        addDrop(ModBlocks.MOSSY_MUD_BRICKS_WALL);

        addDrop(ModBlocks.CRACKED_MUD_BRICKS);
        addDrop(ModBlocks.CHISELED_MUD_BRICKS);
        addDrop(ModBlocks.MUD_BRICKS_PILLAR);

        addDrop(ModBlocks.FERTILE_DIRT);
        addDrop(ModBlocks.FERTILE_FARMLAND, ModBlocks.FERTILE_DIRT);

        addDrop(ModBlocks.FIREFLY_BOTTLE, ModItems.FIREFLY_BOTTLE);

        addDrop(ModBlocks.SAFE);
        addDrop(ModBlocks.WASTE_BASKET);
    }

    /*//Loot Table Builders
    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }*/
}
