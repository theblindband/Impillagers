package com.impillagers.mod.item;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup IMPILLAGERS_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Impillagers.MOD_ID, "impillagers_item_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.FROG_MASK))
                    .displayName(Text.translatable("itemgroup.impillagers"))
                    .entries((displayContext, entries) -> {
                        //Purple Heart Woodset
                        entries.add(ModBlocks.PURPLE_HEART_LOG);
                        entries.add(ModBlocks.PURPLE_HEART_WOOD);
                        entries.add(ModBlocks.STRIPPED_PURPLE_HEART_LOG);
                        entries.add(ModBlocks.STRIPPED_PURPLE_HEART_WOOD);
                        entries.add(ModBlocks.PURPLE_HEART_PLANKS);
                        entries.add(ModBlocks.PURPLE_HEART_STAIRS);
                        entries.add(ModBlocks.PURPLE_HEART_SLAB);
                        entries.add(ModBlocks.PURPLE_HEART_FENCE);
                        entries.add(ModBlocks.PURPLE_HEART_FENCE_GATE);
                        entries.add(ModBlocks.PURPLE_HEART_DOOR);
                        entries.add(ModBlocks.PURPLE_HEART_TRAPDOOR);
                        entries.add(ModBlocks.PURPLE_HEART_PRESSURE_PLATE);
                        entries.add(ModBlocks.PURPLE_HEART_BUTTON);
                        entries.add(ModBlocks.PURPLE_HEART_LEAVES);
                        entries.add(ModBlocks.PURPLE_HEART_SAPLING);
                        entries.add(ModItems.PURPLE_HEART_SIGN);
                        entries.add(ModItems.PURPLE_HEART_HANGING_SIGN);

                        //Natural Items
                        entries.add(ModBlocks.SINKING_MUD);
                        entries.add(ModBlocks.BELLADONNA);
                        entries.add(ModBlocks.SWAMP_REED);

                        entries.add(ModBlocks.FIREFLY_BUSH);
                        entries.add(ModItems.FIREFLY_BOTTLE);

                        //Ruin Loot
                        entries.add(ModItems.FROG_MASK);
                        entries.add(ModItems.IMP_POTTERY_SHERD);
                        entries.add(ModItems.COVER_POTTERY_SHERD);

                        //Imp Stuff
                        entries.add(ModItems.IMPILLAGER_SPAWN_EGG);
                        entries.add(ModItems.ZOMBIE_IMPILLAGER_SPAWN_EGG);
                        entries.add(ModBlocks.DUNG_BLOCK);
                        entries.add(ModItems.DUNG_BALL);

                        //Imp Trade Stuff
                        entries.add(ModItems.GOLD_COIN);
                        entries.add(ModItems.FAKE_GOLD_COIN);
                        entries.add(ModItems.PAINTED_SMITHING_TEMPLATE);
                        entries.add(ModItems.TOTEM_OF_UNDYEING);
                        entries.add(Items.ENCHANTED_BOOK);

                        //Vanilla Block Expansions
                        entries.add(ModBlocks.PACKED_MUD_STAIRS);
                        entries.add(ModBlocks.PACKED_MUD_SLAB);
                        entries.add(ModBlocks.PACKED_MUD_WALL);

                    }).build());

    public static void registerModItemGroups() {
        //Impillagers.LOGGER.info("Registering Item Groups for " + Impillagers.MOD_ID);
    }
}