package com.impillagers.mod.villager.professions;

import com.google.common.collect.ImmutableSet;
import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModProfessions {

    public static final VillagerProfession BANKER =
            createProfession("banker", Blocks.GOLD_BLOCK, SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN);

    public static final VillagerProfession DUNG_COLLECTOR =
            createProfession("dung_collector", ModBlocks.DUNG_BLOCK, SoundEvents.BLOCK_MUD_HIT);

    public static final VillagerProfession MUSICIAN =
            createProfession("musician", Blocks.JUKEBOX, SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN);



    private static VillagerProfession createProfession(String name, Block block, SoundEvent workSound) {
        String poiName = name + "_poi";
        RegistryKey<PointOfInterestType> poiKey = registerPoiKey(poiName);
        PointOfInterestType poi = registerPOI(poiName, block);
        return registerProfession(name, poiKey, workSound);
    }

    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> poiKey,
                                                         SoundEvent sound) {
        return Registry.register(Registries.VILLAGER_PROFESSION,
                Identifier.of(Impillagers.MOD_ID, name),
                new VillagerProfession(name,
                        entry -> entry.matchesKey(poiKey),
                        entry -> entry.matchesKey(poiKey),
                        ImmutableSet.of(),
                        ImmutableSet.of(),
                        sound));
    }

    private static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(Identifier.of(Impillagers.MOD_ID, name), 1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> registerPoiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(Impillagers.MOD_ID, name));
    }

    public static void registerModProfessions() {
        //Impillagers.LOGGER.info("Registering mod professions for " + Impillagers.MOD_ID);
    }
}
