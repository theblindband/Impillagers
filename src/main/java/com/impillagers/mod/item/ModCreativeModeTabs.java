package com.impillagers.mod.item;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.decoration.PaintingVariants;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Impillagers.MOD_ID);

    //Creates the creative item tab
    public static final Supplier<CreativeModeTab> IMPILLAGERS_TAB = CREATIVE_MODE_TAB.register("impillagers_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.PURPLE_HEART_PLANKS))
                    .title(Component.translatable("creativetab.impillagers"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //The below order is the order they will appear in the creative menu
                        output.accept(ModBlocks.PURPLE_HEART_LOG);
                        output.accept(ModBlocks.PURPLE_HEART_WOOD);
                        output.accept(ModBlocks.STRIPPED_PURPLE_HEART_LOG);
                        output.accept(ModBlocks.STRIPPED_PURPLE_HEART_WOOD);
                        output.accept(ModBlocks.PURPLE_HEART_PLANKS);
                        output.accept(ModBlocks.PURPLE_HEART_STAIRS);
                        output.accept(ModBlocks.PURPLE_HEART_SLAB);
                        output.accept(ModBlocks.PURPLE_HEART_FENCE);
                        output.accept(ModBlocks.PURPLE_HEART_FENCE_GATE);
                        output.accept(ModBlocks.PURPLE_HEART_DOOR);
                        output.accept(ModBlocks.PURPLE_HEART_TRAPDOOR);
                        output.accept(ModBlocks.PURPLE_HEART_PRESSURE_PLATE);
                        output.accept(ModBlocks.PURPLE_HEART_BUTTON);
                        output.accept(ModBlocks.PURPLE_HEART_LEAVES);
                        output.accept(ModBlocks.PURPLE_HEART_SAPLING);

                        output.accept(ModBlocks.BELLADONNA);

                        output.accept(ModBlocks.SINKING_MUD);

                        output.accept(ModItems.FROG_MASK);
                        output.accept(ModItems.IMP_POTTERY_SHERD);
                        output.accept(ModItems.PAINTED_SMITHING_TEMPLATE);

                        output.accept((ItemLike) ModItems.IMPILLAGER_SPAWN_EGG);
                        output.accept((ItemLike) ModItems.CARNIVOURUS_PLANT_SPAWN_EGG);

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
