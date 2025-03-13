package com.impillagers.mod.predicate;

import com.impillagers.mod.effect.ModEffects;
import net.minecraft.entity.LivingEntity;

import java.util.function.Predicate;

public class SmellyPredicate implements Predicate<LivingEntity> {

    @Override
    public boolean test(LivingEntity entity) {
        return entity.hasStatusEffect(ModEffects.SMELLY);
    }
}

