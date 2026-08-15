package com.impillagers.mod.event;

import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import com.impillagers.mod.mixin.accessor.MobEntityAccessor;
import com.impillagers.mod.predicate.SmellyPredicate;
import com.impillagers.mod.util.ModTags;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;

public class ModEvents {

    public static void registerModEvents() {

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (!world.isClient) {
                if (entity instanceof MobEntity mobEntity) {
                    if (!entity.getType().isIn(ModTags.EntityTypes.IGNORE_SMELLY)) {
                        if (mobEntity instanceof PathAwareEntity pathAwareEntity) {
                            ((MobEntityAccessor) mobEntity).getGoalSelector().add(3, new FleeEntityGoal<>(pathAwareEntity, LivingEntity.class, 6.0F, 1.0, 1.5, new SmellyPredicate()));
                        }
                    }

                    if (entity.getClass() == VillagerEntity.class || entity.getClass() == WanderingTraderEntity.class) {
                        PathAwareEntity pathAwareEntity = (PathAwareEntity) mobEntity;
                        ((MobEntityAccessor) mobEntity).getGoalSelector().add(3, new FleeEntityGoal<>(pathAwareEntity, ImpillagerEntity.class, 25.0F, 1.0, 1.2, target -> target instanceof ImpillagerEntity));
                    }
                }
            }
        });
    }
}




