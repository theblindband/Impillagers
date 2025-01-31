package com.impillagers.mod.block.entity;

import com.impillagers.mod.block.init.ModBlockSetType;
import com.impillagers.mod.mixin.SignTypeAccessor;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;

public class ModSignTypes {
    public static final WoodType PURPLE_HEART =
            SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("impillagers_purple_heart", ModBlockSetType.PURPLE_HEART));
}
