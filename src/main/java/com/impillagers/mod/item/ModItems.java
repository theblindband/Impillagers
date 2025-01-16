package com.impillagers.mod.item;

import com.impillagers.mod.Impillagers;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item IMP_POTTERY_SHERD = registerItem("imp_pottery_sherd", new Item(new Item.Settings()));
    public static final Item FROG_MASK = registerItem("frog_mask", new Item(new Item.Settings()));

    public static final Item PAINTED_SMITHING_TEMPLATE = registerItem("painted_armor_trim_smithing_template",
            SmithingTemplateItem.of(Identifier.of(Impillagers.MOD_ID, "painted"), FeatureFlags.VANILLA));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Impillagers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Impillagers.LOGGER.info("Registering Mod Items for " + Impillagers.MOD_ID);

    }
}