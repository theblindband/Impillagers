package com.impillagers.mod.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TotemofUndyeingItem extends Item {
    public TotemofUndyeingItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return stack.copy();
    }
}
