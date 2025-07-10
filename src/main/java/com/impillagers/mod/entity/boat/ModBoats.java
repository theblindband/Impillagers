package com.impillagers.mod.entity.boat;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.item.ModItems;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModBoats {

    public static final Identifier PURPLE_HEART_BOAT_ID = Identifier.of(Impillagers.MOD_ID, "purple_heart_boat");
    public static final Identifier PURPLE_HEART_CHEST_BOAT_ID = Identifier.of(Impillagers.MOD_ID, "purple_heart_chest_boat");

    public static final RegistryKey<TerraformBoatType> PURPLE_HEART_BOAT_KEY = TerraformBoatTypeRegistry.createKey(PURPLE_HEART_BOAT_ID);

    public static void registerBoats() {
        TerraformBoatType purpleHeartBoat = new TerraformBoatType.Builder().item(ModItems.PURPLE_HEART_BOAT).chestItem(ModItems.PURPLE_HEART_CHEST_BOAT).planks(ModBlocks.PURPLE_HEART_PLANKS.asItem()).build();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, PURPLE_HEART_BOAT_KEY, purpleHeartBoat);
    }
}
