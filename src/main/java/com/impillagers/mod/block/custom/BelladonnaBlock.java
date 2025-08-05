package com.impillagers.mod.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class BelladonnaBlock extends FlowerBlock {

    public BelladonnaBlock(StatusEffect suspiciousStewEffect, int effectDuration, AbstractBlock.Settings settings) {
        super(suspiciousStewEffect, effectDuration, settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOpaqueFullCube(world, pos);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && world.getDifficulty() != Difficulty.PEACEFUL) {
            if (entity instanceof LivingEntity livingEntity && !livingEntity.isInvulnerableTo(world.getDamageSources().wither()) && !livingEntity.hasStatusEffect(StatusEffects.POISON)) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 40, 1));
            }
        }
    }
}