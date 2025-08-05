package com.impillagers.mod.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

import java.util.List;

public class BelladonnaBlock extends FlowerBlock {
    public static final MapCodec<BelladonnaBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    STEW_EFFECT_CODEC.forGetter(FlowerBlock::getStewEffects),
                    createSettingsCodec()
            ).apply(instance, BelladonnaBlock::create)
    );

    @Override
    public MapCodec<BelladonnaBlock> getCodec() {
        return CODEC;
    }

    private static BelladonnaBlock create(SuspiciousStewEffectsComponent effects, AbstractBlock.Settings settings) {
        return new BelladonnaBlock(effects, settings);
    }

    public BelladonnaBlock(SuspiciousStewEffectsComponent effects, AbstractBlock.Settings settings) {
        super(effects, settings);
    }

    private static SuspiciousStewEffectsComponent createStewEffectList() {
        List<SuspiciousStewEffectsComponent.StewEffect> effectsList = List.of(
                new SuspiciousStewEffectsComponent.StewEffect(StatusEffects.POISON, 360),
                new SuspiciousStewEffectsComponent.StewEffect(StatusEffects.NAUSEA, 360)
        );
        return new SuspiciousStewEffectsComponent(effectsList);
    }

    @Override
    public SuspiciousStewEffectsComponent getStewEffects() {
        return createStewEffectList();
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && world.getDifficulty() != Difficulty.PEACEFUL) {
            if (entity instanceof LivingEntity livingEntity && !livingEntity.isInvulnerableTo(world.getDamageSources().wither()) && !livingEntity.hasStatusEffect(StatusEffects.POISON)) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 40, 1));
            }
        }
    }
}

