package com.impillagers.mod.item;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.item.custom.DungBallItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item IMP_POTTERY_SHERD = registerItem("imp_pottery_sherd", new Item(new Item.Settings()));
    public static final Item COVER_POTTERY_SHERD = registerItem("cover_pottery_sherd", new Item(new Item.Settings()));

    public static final Item FROG_MASK = registerItem("frog_mask", new Item(new Item.Settings()));

    public static final Item PURPLE_HEART_SIGN = registerItem("purple_heart_sign",
            new SignItem(new Item.Settings().maxCount(16),
                    ModBlocks.PURPLE_HEART_SIGN,ModBlocks.PURPLE_HEART_WALL_SIGN));
    public static final Item PURPLE_HEART_HANGING_SIGN = registerItem("purple_heart_hanging_sign",
            new HangingSignItem(ModBlocks.PURPLE_HEART_HANGING_SIGN,ModBlocks.PURPLE_HEART_WALL_HANGING_SIGN,
                    new Item.Settings().maxCount(16)));

    public static final Item PAINTED_SMITHING_TEMPLATE = registerItem("painted_armor_trim_smithing_template",
            SmithingTemplateItem.of(Identifier.of(Impillagers.MOD_ID, "painted"), FeatureFlags.VANILLA));

    public static final Item DUNG_BALL = registerItem("dung_ball", new DungBallItem(new Item.Settings()));
    public static final Item GOLD_COIN = registerItem("gold_coin", new Item(new Item.Settings()));
    public static final Item FAKE_GOLD_COIN = registerItem("fake_gold_coin", new Item(new Item.Settings()));
    public static final Item IMPILLAGER_SPAWN_EGG = registerItem("impillager_spawn_egg", new SpawnEggItem(ModEntities.IMPILLAGER, 0x995F40, 0xDB635F, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Impillagers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        //Impillagers.LOGGER.info("Registering Mod Items for " + Impillagers.MOD_ID);

    }
}