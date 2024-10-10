package com.impillagers.mod.datagen;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }


    @Override
    protected void gather() {
        //This data generator can be used for the following:
            //        COMPOSTABLES, FURNACE_FUELS, MONSTER_ROOM_MOBS, OXIDIZABLES,
            //        PARROT_IMITATIONS, RAID_HERO_GIFTS, VIBRATION_FREQUENCIES, WAXABLES
        //Below is an example for furnace fuels
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModBlocks.BELLADONNA.getId(), new Compostable(0.5f), false);
    }
}
