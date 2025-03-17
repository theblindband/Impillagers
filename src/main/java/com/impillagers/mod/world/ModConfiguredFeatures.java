package com.impillagers.mod.world;

import com.google.common.collect.ImmutableList;
import com.impillagers.mod.Impillagers;
import com.impillagers.mod.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunk.*;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> PURPLE_HEART_KEY = registerKey("purple_heart");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, PURPLE_HEART_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.PURPLE_HEART_LOG),
                new LargeOakTrunkPlacer(
                        7, //Height of Main Trunk
                        3, //Minimum height of leaves
                        5  //??
                ),


                BlockStateProvider.of(ModBlocks.PURPLE_HEART_LEAVES),
                new CherryFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(1), ConstantIntProvider.create(4),
                        0.05F, 0.25F, 0.6F, 1.0F),

                new TwoLayersFeatureSize(1, 0, 2))

                .decorators(ImmutableList.of(
                        new LeavesVineTreeDecorator(0.125F),
                        new TrunkVineTreeDecorator()
                ))

                .build());
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Impillagers.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
