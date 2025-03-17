package com.impillagers.mod.entity.ai.brain.task;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.LivingTargetCache;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.TaskTriggerer;

import java.util.Optional;
import java.util.function.Predicate;

public class FindNewEnemyTask {

    public static <T extends LivingEntity> Task<LivingEntity> create(
            EntityType<? extends T> type,
            int maxDistance,
            MemoryModuleType<? super T> targetModule,
            Class<T> targetClass
    ) {
        return create(type, maxDistance, e -> true, t -> true, targetModule, targetClass);
    }

    public static <E extends LivingEntity, T extends LivingEntity> Task<E> create(
            EntityType<? extends T> type,
            int maxDistance,
            Predicate<E> entityPredicate,
            Predicate<T> targetPredicate,
            MemoryModuleType<? super T> targetModule,
            Class<T> targetClass
    ) {
        int i = maxDistance * maxDistance;

        Predicate<LivingEntity> predicate = entity ->
                type.equals(entity.getType()) && targetPredicate.test(targetClass.cast(entity));

        return TaskTriggerer.task(
                context -> context.group(
                        context.queryMemoryOptional(targetModule),
                        context.queryMemoryOptional(MemoryModuleType.LOOK_TARGET),
                        context.queryMemoryAbsent(MemoryModuleType.ATTACK_TARGET),
                        context.queryMemoryValue(MemoryModuleType.VISIBLE_MOBS)
                ).apply(context, (targetValue, lookTarget, attackTarget, visibleMobs) -> (world, entity, time) -> {
                    LivingTargetCache livingTargetCache = context.getValue(visibleMobs);
                    if (entityPredicate.test(entity) && livingTargetCache.anyMatch(predicate)) {
                        Optional<LivingEntity> optional = livingTargetCache.findFirst(target ->
                                target.squaredDistanceTo(entity) <= (double) i && predicate.test(target));
                        optional.ifPresent(target -> targetValue.remember(targetClass.cast(target)));
                        return true;
                    } else {
                        return false;
                    }
                })
        );
    }
}
