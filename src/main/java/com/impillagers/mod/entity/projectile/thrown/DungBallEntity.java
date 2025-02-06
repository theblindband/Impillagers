package com.impillagers.mod.entity.projectile.thrown;

import com.impillagers.mod.effect.ModEffects;
import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class DungBallEntity extends ThrownItemEntity {

    public DungBallEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public DungBallEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.DUNG_BALL, livingEntity, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i = entity instanceof VillagerEntity ? 3 : 1;
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), (float)i);
        ((LivingEntity)entity).addStatusEffect(new StatusEffectInstance(ModEffects.SMELLY, 2400, 0), this);
    }


    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.playSound(SoundEvents.BLOCK_MUD_PLACE, 1 ,1);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.DUNG_BALL;
    }

}

    

