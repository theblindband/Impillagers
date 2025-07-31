package com.impillagers.mod.world.tree;

import com.impillagers.mod.world.ModConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class ModSaplingGenerators {
    public static final SaplingGenerator PURPLE_HEART = new SaplingGenerator() {
        @Nullable
        @Override
        protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
            return ModConfiguredFeatures.PURPLE_HEART_KEY;
        }
    };
}