package com.impillagers.mod.enchantment;

import com.impillagers.mod.Impillagers;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final DistributionEnchantment DISTRIBUTION = new DistributionEnchantment();

    public static void registerEnchantments() {
        Registry.register(Registries.ENCHANTMENT,
                new Identifier(Impillagers.MOD_ID, "distribution"),
                         DISTRIBUTION);
    }
}