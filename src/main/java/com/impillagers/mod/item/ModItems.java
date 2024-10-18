package com.impillagers.mod.item;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.item.custom.FrogMaskItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Impillagers.MOD_ID);

    //Items
    public static final DeferredItem<Item> IMP_POTTERY_SHERD = ITEMS.register("imp_pottery_sherd",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PAINTED_SMITHING_TEMPLATE = ITEMS.register("painted_smithing_template",
            () -> SmithingTemplateItem.createArmorTrimTemplate(ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID, "painted")));
    public static final DeferredItem<FrogMaskItem> FROG_MASK = ITEMS.register("frog_mask",
            () -> new FrogMaskItem(ModArmorMaterials.FROG_MASK_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));
    public static final Supplier<DeferredSpawnEggItem> IMPILLAGER_SPAWN_EGG = ITEMS.register("impillager_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.IMPILLAGER, 10236982, 13545366, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
