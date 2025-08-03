package com.impillagers.mod.enchantment;

import com.impillagers.mod.Impillagers;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final DistributionEnchantment DISTRIBUTION = new DistributionEnchantment();
    public static final FuseEnchantment FUSE = new FuseEnchantment();
    public static final FrogsLegsEnchantment FROGS_LEGS = new FrogsLegsEnchantment();

    public static void registerEnchantments() {
        register("distribution", DISTRIBUTION);
        register("fuse", FUSE);
        register("frogs_legs", FROGS_LEGS);
    }

    private static void register(String path, Enchantment enchantment) {
        Registry.register(Registries.ENCHANTMENT, new Identifier(Impillagers.MOD_ID, path), enchantment);
    }
}