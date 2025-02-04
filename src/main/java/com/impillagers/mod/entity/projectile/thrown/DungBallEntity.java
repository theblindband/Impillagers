package com.impillagers.mod.entity.projectile.thrown;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class DungBallEntity extends SnowballEntity {

    public DungBallEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i = entity instanceof VillagerEntity ? 3 : 1;
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), (float)i);
        ((LivingEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 2400, 3), this);
    }
}
