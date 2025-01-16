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
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.IMP_POTTERY_SHERD))
                    .displayName(Text.translatable("itemgroup.impillagers"))
                    .entries((displayContext, entries) -> {
                        //Items
                        entries.add(ModItems.IMP_POTTERY_SHERD);
                        //Purple Heart Woodset
                        //Logs
                        entries.add(ModBlocks.PURPLE_HEART_LOG);
                        entries.add(ModBlocks.PURPLE_HEART_WOOD);
                        entries.add(ModBlocks.STRIPPED_PURPLE_HEART_LOG);
                        entries.add(ModBlocks.STRIPPED_PURPLE_HEART_WOOD);
                        //Planks
                        entries.add(ModBlocks.PURPLE_HEART_PLANKS);
                        //Plank Variants
                        entries.add(ModBlocks.PURPLE_HEART_STAIRS);
                        entries.add(ModBlocks.PURPLE_HEART_SLAB);
                        entries.add(ModBlocks.PURPLE_HEART_FENCE);
                        entries.add(ModBlocks.PURPLE_HEART_FENCE_GATE);
                        //Redstone Items
                        entries.add(ModBlocks.PURPLE_HEART_DOOR);
                        entries.add(ModBlocks.PURPLE_HEART_TRAPDOOR);
                        entries.add(ModBlocks.PURPLE_HEART_PRESSURE_PLATE);
                        entries.add(ModBlocks.PURPLE_HEART_BUTTON);
                        //Leaves and Sapling
                        entries.add(ModBlocks.PURPLE_HEART_LEAVES);

                        //Other Blocks
                        entries.add(ModBlocks.SINKING_MUD);

                    }).build());

    public static void registerItemGroups() {
        Impillagers.LOGGER.info("Registering Item Groups for " + Impillagers.MOD_ID);
    }
}