package com.impillagers.mod.block;

import com.impillagers.mod.mixin.accessor.WoodTypeAccessor;
import net.minecraft.block.WoodType;

public class ModWoodTypes {

    public static final WoodType PURPLE_HEART =
            WoodTypeAccessor.registerNew(WoodTypeAccessor.newWoodType("impillagers_purple_heart", ModBlockSetTypes.PURPLE_HEART));
}
