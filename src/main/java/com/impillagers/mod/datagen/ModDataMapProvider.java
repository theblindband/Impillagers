package com.impillagers.mod.datagen;

import com.impillagers.mod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
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
        //this.builder(NeoForgeDataMaps.FURNACE_FUELS)
        //        .add(ModItems.IMP_POTTERY_SHERD.getId(), new FurnaceFuel(2400), false);
    }
}
