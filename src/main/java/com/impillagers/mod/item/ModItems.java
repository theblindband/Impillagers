package com.impillagers.mod.item;

import com.impillagers.mod.Impillagers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Impillagers.MOD_ID);

    //Items
    public static final DeferredItem<Item> IMP_POTTERY_SHERD = ITEMS.register("imp_pottery_sherd",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PAINTED_SMITHING_TEMPLATE = ITEMS.register("painted_smithing_template",
            () -> SmithingTemplateItem.createArmorTrimTemplate(ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID, "painted")));

    //This mask needs a better model. Maybe use GeckoLib for that?
    public static final DeferredItem<ArmorItem> FROG_MASK = ITEMS.register("frog_mask",
            () -> new ArmorItem(ModArmorMaterials.FROG_MASK_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
