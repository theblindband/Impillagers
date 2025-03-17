package com.impillagers.mod.entity.ai.brain.task;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.EntityLookTarget;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.WalkTarget;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.TaskTriggerer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;

import java.util.function.Function;

public class ImpillagerAttackMovementTask {

    public static Task<MobEntity> create(float speed, float minDistance, float maxDistance, float tolerance) {
        return create(entity -> speed, minDistance, maxDistance, tolerance);
    }

    public static Task<MobEntity> create(Function<LivingEntity, Float> speed, float minDistance, float maxDistance, float tolerance) {
        return TaskTriggerer.task(
                context -> context.group(
                                context.queryMemoryOptional(MemoryModuleType.WALK_TARGET),
                                context.queryMemoryOptional(MemoryModuleType.LOOK_TARGET),
                                context.queryMemoryValue(MemoryModuleType.ATTACK_TARGET),
                                context.queryMemoryOptional(MemoryModuleType.VISIBLE_MOBS)

                        )
                        .apply(
                                context,
                                (walkTarget, lookTarget, attackTarget, visibleMobs) -> (world, entity, time) -> {
                                    LivingEntity livingEntity = context.getValue(attackTarget);
                                    double currentDistance = entity.squaredDistanceTo(livingEntity);

                                    Vec3d relativeDirection = livingEntity.getPos().subtract(entity.getPos()).normalize();

                                    Vec3d targetPosition;
                                    if (currentDistance <= (minDistance - tolerance) * (minDistance - tolerance)) {
                                        // Pull back if within minDistance minus tolerance
                                        targetPosition = entity.getPos().subtract(relativeDirection.multiply(minDistance));

                                    } else if (currentDistance >= (maxDistance + tolerance) * (maxDistance + tolerance)) {
                                        // Move closer if further than maxDistance plus tolerance
                                        targetPosition = entity.getPos().add(relativeDirection.multiply(maxDistance));

                                    } else {
                                        // Within distance range with tolerance, no need to move
                                        return true;
                                    }

                                    lookTarget.remember(new EntityLookTarget(livingEntity, true));
                                    walkTarget.remember(new WalkTarget(targetPosition, speed.apply(entity), 0));

                                    return true;
                                }
                        )
        );
    }
}