package com.impillagers.mod.world.tree;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator PURPLE_HEART = new SaplingGenerator(Impillagers.MOD_ID + ":purple_heart",
            Optional.empty(), Optional.of(ModConfiguredFeatures.PURPLE_HEART_KEY), Optional.empty());
}
