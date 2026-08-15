package com.impillagers.mod.block;

import com.impillagers.mod.mixin.accessor.BlockSetTypeAccessor;
import net.minecraft.block.BlockSetType;

public class ModBlockSetTypes {
    public static final BlockSetType PURPLE_HEART = BlockSetTypeAccessor.invokeRegister(new BlockSetType("purple_heart"));

}