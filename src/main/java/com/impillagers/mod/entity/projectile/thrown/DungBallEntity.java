package com.impillagers.mod.entity.projectile.thrown;

import com.impillagers.mod.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
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
        ((LivingEntity)entity).addStatusEffect(new StatusEffectInstance(ModEffects.SMELLY, 2400, 0), this);
    }
}
