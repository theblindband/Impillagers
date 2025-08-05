package com.impillagers.mod.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

public class EnchantRegistryHolder {
    private static DynamicRegistryManager registryManager;

    public static void init(MinecraftServer server) {
        registryManager = server.getRegistryManager();
    }

    public static RegistryEntry<Enchantment> getEntry(Identifier id) {
        var key = RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
        var registry = registryManager.get(RegistryKeys.ENCHANTMENT);
        return registry.getEntry(key).orElseThrow(() -> new IllegalStateException("Missing enchant: " + id));
    }
}
