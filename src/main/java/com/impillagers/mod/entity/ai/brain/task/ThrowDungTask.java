package com.impillagers.mod.entity.ai.brain.task;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.impillagers.mod.entity.projectile.thrown.DungBallEntity;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.mob.BreezeEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.projectile.BreezeWindChargeEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Unit;
import net.minecraft.util.math.Vec3d;

public class ThrowDungTask extends MultiTickTask<VillagerEntity> {
    private static final int MIN_SQUARED_RANGE = 4;
    private static final int MAX_SQUARED_RANGE = 256;
    private static final int BASE_PROJECTILE_DIVERGENCY = 5;
    private static final int PROJECTILE_DIVERGENCY_DIFFICULTY_MODIFIER = 4;
    private static final float PROJECTILE_SPEED = 0.7F;
    private static final int SHOOT_CHARGING_EXPIRY = Math.round(15.0F);
    private static final int RECOVER_EXPIRY = Math.round(4.0F);
    private static final int SHOOT_COOLDOWN_EXPIRY = Math.round(10.0F);

    @VisibleForTesting
    public ThrowDungTask() {
        super(
                ImmutableMap.of(
                        MemoryModuleType.ATTACK_TARGET,
                        MemoryModuleState.VALUE_PRESENT,
                        MemoryModuleType.BREEZE_SHOOT_COOLDOWN,
                        MemoryModuleState.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_SHOOT_CHARGING,
                        MemoryModuleState.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_SHOOT_RECOVER,
                        MemoryModuleState.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_SHOOT,
                        MemoryModuleState.VALUE_PRESENT,
                        MemoryModuleType.WALK_TARGET,
                        MemoryModuleState.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_JUMP_TARGET,
                        MemoryModuleState.VALUE_ABSENT
                ),
                SHOOT_CHARGING_EXPIRY + 1 + RECOVER_EXPIRY
        );
    }

    protected boolean shouldRun(ServerWorld serverWorld, VillagerEntity impillagerEntity) {
        return impillagerEntity.getPose() != EntityPose.STANDING
                ? false
                : (Boolean)impillagerEntity.getBrain()
                .getOptionalRegisteredMemory(MemoryModuleType.ATTACK_TARGET)
                .map(target -> isTargetWithinRange(impillagerEntity, target))
                .map(withinRange -> {
                    if (!withinRange) {
                        impillagerEntity.getBrain().forget(MemoryModuleType.BREEZE_SHOOT);
                    }

                    return withinRange;
                })
                .orElse(false);
    }

    protected boolean shouldKeepRunning(ServerWorld serverWorld, VillagerEntity impillagerEntity, long l) {
        return impillagerEntity.getBrain().hasMemoryModule(MemoryModuleType.ATTACK_TARGET) && impillagerEntity.getBrain().hasMemoryModule(MemoryModuleType.BREEZE_SHOOT);
    }

    protected void run(ServerWorld serverWorld, VillagerEntity impillagerEntity, long l) {
        impillagerEntity.getBrain().getOptionalRegisteredMemory(MemoryModuleType.ATTACK_TARGET).ifPresent(target -> impillagerEntity.setPose(EntityPose.SHOOTING));
        impillagerEntity.getBrain().remember(MemoryModuleType.BREEZE_SHOOT_CHARGING, Unit.INSTANCE, (long)SHOOT_CHARGING_EXPIRY);
        impillagerEntity.playSound(SoundEvents.ENTITY_BREEZE_INHALE, 1.0F, 1.0F);
    }

    protected void finishRunning(ServerWorld serverWorld, VillagerEntity impillagerEntity, long l) {
        if (impillagerEntity.getPose() == EntityPose.SHOOTING) {
            impillagerEntity.setPose(EntityPose.STANDING);
        }

        impillagerEntity.getBrain().remember(MemoryModuleType.BREEZE_SHOOT_COOLDOWN, Unit.INSTANCE, (long)SHOOT_COOLDOWN_EXPIRY);
        impillagerEntity.getBrain().forget(MemoryModuleType.BREEZE_SHOOT);
    }

    protected void keepRunning(ServerWorld serverWorld, VillagerEntity impillagerEntity, long l) {
        Brain<VillagerEntity> brain = impillagerEntity.getBrain();
        LivingEntity livingEntity = (LivingEntity)brain.getOptionalRegisteredMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
        if (livingEntity != null) {
            impillagerEntity.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, livingEntity.getPos());
            if (!brain.getOptionalRegisteredMemory(MemoryModuleType.BREEZE_SHOOT_CHARGING).isPresent()
                    && !brain.getOptionalRegisteredMemory(MemoryModuleType.BREEZE_SHOOT_RECOVER).isPresent()) {
                brain.remember(MemoryModuleType.BREEZE_SHOOT_RECOVER, Unit.INSTANCE, (long)RECOVER_EXPIRY);
                if (isFacingTarget(impillagerEntity, livingEntity)) {
                    double d = livingEntity.getX() - impillagerEntity.getX();
                    double e = livingEntity.getBodyY(livingEntity.hasVehicle() ? 0.8 : 0.3) - impillagerEntity.getBodyY(0.5);
                    double f = livingEntity.getZ() - impillagerEntity.getZ();
                    DungBallEntity  dungBallEntity = new DungBallEntity(serverWorld, impillagerEntity);
                    impillagerEntity.playSound(SoundEvents.ENTITY_BREEZE_SHOOT, 1.5F, 1.0F);
                    dungBallEntity.setVelocity(d, e, f, 0.7F, (float)(5 - serverWorld.getDifficulty().getId() * 4));
                    serverWorld.spawnEntity(dungBallEntity);
                }
            }
        }
    }

    @VisibleForTesting
    public static boolean isFacingTarget(VillagerEntity impillagerEntity, LivingEntity target) {
        Vec3d vec3d = impillagerEntity.getRotationVec(1.0F);
        Vec3d vec3d2 = target.getPos().subtract(impillagerEntity.getPos()).normalize();
        return vec3d.dotProduct(vec3d2) > 0.5;
    }

    private static boolean isTargetWithinRange(VillagerEntity impillagerEntity, LivingEntity target) {
        double d = impillagerEntity.getPos().squaredDistanceTo(target.getPos());
        return d > 4.0 && d < 256.0;
    }
}

