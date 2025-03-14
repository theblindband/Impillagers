package com.impillagers.mod.entity.ai.brain.task;

import com.google.common.collect.ImmutableMap;
import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;

public class SpawnGolemTask extends MultiTickTask<VillagerEntity> {
    public SpawnGolemTask() {
        super(ImmutableMap.of());
    }

    protected boolean shouldKeepRunning(ServerWorld serverWorld, VillagerEntity villagerEntity, long l) {
        return villagerEntity.getBrain().hasMemoryModule(MemoryModuleType.ATTACK_TARGET);
    }

    protected void keepRunning(ServerWorld serverWorld, VillagerEntity villagerEntity, long l) {
        if (l % 100L == 0L) {
            if (villagerEntity instanceof ImpillagerEntity impillagerEntity) {
                impillagerEntity.summonGolem(serverWorld, l, 3);
            }
        }
    }

}
