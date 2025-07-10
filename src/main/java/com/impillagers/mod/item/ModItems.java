package com.impillagers.mod.item;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.entity.boat.ModBoats;
import com.impillagers.mod.item.custom.CreeperOilItem;
import com.impillagers.mod.item.custom.DungBallItem;
import com.impillagers.mod.item.custom.FrogMaskItem;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    //Purple Heart Items
    public static final Item PURPLE_HEART_SIGN = registerItem("purple_heart_sign", new SignItem(new Item.Settings().maxCount(16), ModBlocks.PURPLE_HEART_SIGN,ModBlocks.PURPLE_HEART_WALL_SIGN));
    public static final Item PURPLE_HEART_HANGING_SIGN = registerItem("purple_heart_hanging_sign", new HangingSignItem(ModBlocks.PURPLE_HEART_HANGING_SIGN,ModBlocks.PURPLE_HEART_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));

    //Ruin Loot
    public static final Item FROG_MASK = registerItem("frog_mask", new
            FrogMaskItem(ModArmorMaterials.FROG_MASK_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item IMP_POTTERY_SHERD = registerItem("imp_pottery_sherd", new Item(new Item.Settings()));
    public static final Item COVER_POTTERY_SHERD = registerItem("cover_pottery_sherd", new Item(new Item.Settings()));

    //Imp Stuff
    public static final Item IMPILLAGER_SPAWN_EGG = registerItem("impillager_spawn_egg", new SpawnEggItem(ModEntities.IMPILLAGER, 0x995F40, 0xDB635F, new Item.Settings()));
    public static final Item ZOMBIE_IMPILLAGER_SPAWN_EGG = registerItem("zombie_impillager_spawn_egg", new SpawnEggItem(ModEntities.ZOMBIE_IMPILLAGER, 0x995F40, 7969893, new Item.Settings()));
    public static final Item DUNG_GOLEM_SPAWN_EGG = registerItem("dung_golem_spawn_egg", new SpawnEggItem(ModEntities.DUNG_GOLEM, 0x674b42, 0xbc8261, new Item.Settings()));
    public static final Item GOLD_COIN = registerItem("gold_coin", new Item(new Item.Settings()));
    public static final Item FAKE_GOLD_COIN = registerItem("fake_gold_coin", new Item(new Item.Settings()));
    public static final Item FIREFLY_BOTTLE = registerItem("firefly_bottle", new BlockItem(ModBlocks.FIREFLY_BOTTLE, new Item.Settings().maxCount(16)));
    public static final Item PAINTED_SMITHING_TEMPLATE = registerItem("painted_armor_trim_smithing_template", SmithingTemplateItem.of(Identifier.of(Impillagers.MOD_ID, "painted"), FeatureFlags.VANILLA));
    public static final Item DUNG_BALL = registerItem("dung_ball", new DungBallItem(new Item.Settings()));
    public static final Item SPIDER_PIEYE = registerItem("spider_pieye", new Item(new Item.Settings().food(ModFoodComponents.SPIDER_PIEYE)));

    public static final Item CREEPER_OIL = registerItem("creeper_oil", new CreeperOilItem(new Item.Settings()));


                                                                                                                                                        //This bit should make the item stay in crafting, not working for some reason
    public static final Item TOTEM_OF_UNDYEING = registerItem("totem_of_undyeing", new Item(new Item.Settings().maxCount(16).rarity(Rarity.UNCOMMON).recipeRemainder(ModItems.TOTEM_OF_UNDYEING)));


    //Boats
    public static Item PURPLE_HEART_BOAT;
    public static Item PURPLE_HEART_CHEST_BOAT;

    static {
        if (!Impillagers.NEOFORGE){
            PURPLE_HEART_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.PURPLE_HEART_BOAT_ID, ModBoats.PURPLE_HEART_BOAT_KEY, false);
            PURPLE_HEART_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.PURPLE_HEART_CHEST_BOAT_ID, ModBoats.PURPLE_HEART_BOAT_KEY, true);
        }
    }

    //Register Methods
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Impillagers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        //Impillagers.LOGGER.info("Registering Mod Items for " + Impillagers.MOD_ID);
    }
}