package com.impillagers.mod.util;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.registry.Registries;

public class PoiFilterUtil {
    public static boolean isPoiValid(RegistryEntry<PointOfInterestType> entry) {
        Identifier poiId = Registries.POINT_OF_INTEREST_TYPE.getId(entry.value());
        return poiId != null && ImpillagerProfessionHandler.getValidPoiSet().contains(poiId.toString());
    }
}
