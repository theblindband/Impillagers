package com.impillagers.mod.entity.ai.brain.task;

import com.google.common.annotations.VisibleForTesting;
import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import com.impillagers.mod.entity.projectile.thrown.DungBallEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.task.SingleTickTask;
import net.minecraft.entity.ai.brain.task.TaskTriggerer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ImpillagerAttackTask {
    public static SingleTickTask<MobEntity> create(int meleeCooldown, int throwCooldown, float pitchAdjustment) {
        return TaskTriggerer.task(
                context -> context.group(
                                context.queryMemoryOptional(MemoryModuleType.LOOK_TARGET),
                                context.queryMemoryValue(MemoryModuleType.ATTACK_TARGET),
                                context.queryMemoryAbsent(MemoryModuleType.ATTACK_COOLING_DOWN),
                                context.queryMemoryValue(MemoryModuleType.VISIBLE_MOBS)
                        )
                        .apply(
                                context,
                                (lookTarget, attackTarget, attackCoolingDown, visibleMobs) -> (world, entity, time) -> {
                                    LivingEntity livingEntity = context.getValue(attackTarget);
                                    if (entity.isInAttackRange(livingEntity) && context.getValue(visibleMobs).contains(livingEntity)) {
                                        lookTarget.remember(new EntityLookTarget(livingEntity, true));
                                        entity.swingHand(Hand.MAIN_HAND);
                                        entity.tryAttack(livingEntity);
                                        attackCoolingDown.remember(true, meleeCooldown);
                                        return true;
                                    } else if (!entity.isInAttackRange(livingEntity) && isTargetWithinThrowRange(entity, livingEntity) && context.getValue(visibleMobs).contains(livingEntity)) {
                                        lookTarget.remember(new EntityLookTarget(livingEntity, true));
                                        if (isFacingTarget(entity, livingEntity)) {
                                            double d = livingEntity.getX() - entity.getX();
                                            double e = (livingEntity.getBodyY(livingEntity.hasVehicle() ? 0.8 : 0.3) - entity.getBodyY(0.5)) + pitchAdjustment;
                                            double f = livingEntity.getZ() - entity.getZ();
                                            World serverWorld = entity.getWorld();
                                            DungBallEntity dungBallEntity = new DungBallEntity(entity, world);
                                            entity.playSound((ImpillagerEntity.getRangedAttackSound()) , 1.5F, 1.0F);
                                            dungBallEntity.setVelocity(d, e, f, 0.75F, (float) (5 - serverWorld.getDifficulty().getId() * 4));
                                            serverWorld.spawnEntity(dungBallEntity);
                                            attackCoolingDown.remember(true, throwCooldown);
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                        )
        );
    }

    @VisibleForTesting
    public static boolean isFacingTarget(MobEntity impillagerEntity, LivingEntity target) {
        Vec3d vec3d = impillagerEntity.getRotationVec(1.0F);
        Vec3d vec3d2 = target.getPos().subtract(impillagerEntity.getPos()).normalize();
        return vec3d.dotProduct(vec3d2) > 0.5;
    }

    private static boolean isTargetWithinThrowRange(MobEntity impillagerEntity, LivingEntity target) {
        double d = impillagerEntity.getPos().squaredDistanceTo(target.getPos());
        return d > 4.0 && d < 256.0;
    }
}
