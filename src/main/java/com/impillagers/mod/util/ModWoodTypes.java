package com.impillagers.mod.util;

import com.impillagers.mod.Impillagers;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {

    public static final BlockSetType PURPLE_HEART_SET = new BlockSetType(Impillagers.MOD_ID + ":purple_heart");
    public static WoodType PURPLE_HEART_WOOD = WoodType.register(new WoodType(Impillagers.MOD_ID + ":purple_heart", PURPLE_HEART_SET));
}
