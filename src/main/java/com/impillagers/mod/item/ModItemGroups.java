package com.impillagers.mod.item;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup PINK_GARNET_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Impillagers.MOD_ID, "pink_garnet_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PINK_GARNET))
                    .displayName(Text.translatable("itemgroup.impillagers"))
                    .entries((displayContext, entries) -> {

                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModBlocks.PINK_GARNET_BLOCK);

                    }).build());

    public static void registerItemGroups() {
        Impillagers.LOGGER.info("Registering Item Groups for " + Impillagers.MOD_ID);
    }
}