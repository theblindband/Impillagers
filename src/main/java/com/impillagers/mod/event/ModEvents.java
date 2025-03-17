package com.impillagers.mod.event;

import com.impillagers.mod.mixin.MobEntityAccessor;
import com.impillagers.mod.predicate.SmellyPredicate;
import com.impillagers.mod.util.HudOverlayOpacityPayload;
import com.impillagers.mod.util.ModTags;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.network.ServerPlayerEntity;

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
                }
            }
        });

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            if (!player.getEntityWorld().isClient()) {
                ServerPlayNetworking.send(player, new HudOverlayOpacityPayload(0));
            }
        });

        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            if (!newPlayer.getEntityWorld().isClient()) {
                ServerPlayNetworking.send(newPlayer, new HudOverlayOpacityPayload(0));
            }
        });
    }
}




