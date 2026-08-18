package com.impillagers.mod.entity.ai.brain.task;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.LivingTargetCache;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.TaskTriggerer;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public class FindNewEnemyTask {

    public static Task<LivingEntity> create(Collection<EntityType<?>> enemyTypes, int maxDistance, MemoryModuleType<? super LivingEntity> targetModule) {
        return create(enemyTypes, maxDistance, targetModule, entity -> true);
    }

    public static Task<LivingEntity> create(Collection<EntityType<?>> enemyTypes, int maxDistance, MemoryModuleType<? super LivingEntity> targetModule, Predicate<LivingEntity> targetPredicate) {
        int i = maxDistance * maxDistance;
        Predicate<LivingEntity> typePredicate = entity -> enemyTypes.contains(entity.getType());

        return TaskTriggerer.task(
                context -> context.group(context.queryMemoryOptional(targetModule), context.queryMemoryOptional(MemoryModuleType.LOOK_TARGET), context.queryMemoryAbsent(MemoryModuleType.ATTACK_TARGET), context.queryMemoryValue(MemoryModuleType.VISIBLE_MOBS)
                ).apply(context, (targetValue, lookTarget, attackTarget, visibleMobs) -> (world, entity, time) -> {
                    LivingTargetCache livingTargetCache = context.getValue(visibleMobs);
                    Optional<LivingEntity> optional = livingTargetCache.findFirst(target -> target.squaredDistanceTo(entity) <= (double) i && typePredicate.test(target) && targetPredicate.test(target));
                    optional.ifPresent(targetValue::remember);
                    return optional.isPresent();
                })
        );
    }
}