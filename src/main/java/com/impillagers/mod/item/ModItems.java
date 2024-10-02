package com.impillagers.mod.item;

import com.impillagers.mod.Impillagers;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Impillagers.MOD_ID);

    public static final DeferredItem<Item> IMP_POTTERY_SHERD = ITEMS.register("imp_pottery_sherd",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
