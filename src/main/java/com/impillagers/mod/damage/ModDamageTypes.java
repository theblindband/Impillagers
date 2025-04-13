package com.impillagers.mod.damage;

import com.impillagers.mod.Impillagers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModDamageTypes {

    public static final RegistryKey<DamageType> STRONG_STUFF_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Impillagers.MOD_ID, "strong_stuff"));

    public static DamageSource getStrongStuff(LivingEntity livingEntity) {
        return new DamageSource(livingEntity.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(STRONG_STUFF_DAMAGE));
    }

    public static void registerDamageTypes() {
        //Impillagers.LOGGER.info("Registering Damage Types for " + Impillagers.MOD_ID);
    }
}
