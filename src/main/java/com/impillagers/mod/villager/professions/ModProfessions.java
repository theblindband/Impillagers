package com.impillagers.mod.villager.professions;

import com.google.common.collect.ImmutableSet;
import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.sounds.ModSoundEvents;
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

    public static final VillagerProfession GUARD =
            createProfession("guard", Blocks.DIAMOND_BLOCK, SoundEvents.ITEM_SHIELD_BLOCK);

    public static final VillagerProfession EXPLOSIVES_EXPERT =
            createProfession("explosives_expert", Blocks.TNT, SoundEvents.ENTITY_CREEPER_PRIMED);

    public static final VillagerProfession DRUID =
            createProfession("druid", ModBlocks.BELLADONNA, SoundEvents.ITEM_CROP_PLANT);

    public static final VillagerProfession ALCHEMIST =
            createProfession("alchemist", Blocks.CANDLE, SoundEvents.BLOCK_BREWING_STAND_BREW);

    public static final VillagerProfession OCCULTIST =
            createProfession("occultist", Blocks.REDSTONE_WIRE, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE);

    public static final VillagerProfession CLAN_LEADER =
            createProfession("clan_leader", Blocks.CHERRY_STAIRS, ModSoundEvents.IMPILLAGER_YES);



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
        Impillagers.LOGGER.info("Registering mod professions for " + Impillagers.MOD_ID);
    }
}
