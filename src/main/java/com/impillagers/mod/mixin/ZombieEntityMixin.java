package com.impillagers.mod.mixin;

import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import com.impillagers.mod.entity.custom.zombieimpillager.ZombieImpillagerEntity;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends HostileEntity {

    // Constructor matching the parent class
    protected ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @WrapMethod(method = "onKilledOther")
    public boolean onKilledOther(ServerWorld world, LivingEntity other, Operation<Boolean> original) {
        if (other instanceof ImpillagerEntity) {
            boolean bln = super.onKilledOther(world, other);
            if ((world.getDifficulty() == Difficulty.NORMAL || world.getDifficulty() == Difficulty.HARD) && other instanceof ImpillagerEntity impillagerEntity) {
                if (world.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
                    return bln;
                }

                ZombieImpillagerEntity zombieImpillagerEntity = impillagerEntity.convertTo(ModEntities.ZOMBIE_IMPILLAGER, false);
                if (zombieImpillagerEntity != null) {
                    zombieImpillagerEntity.initialize(
                            world, world.getLocalDifficulty(zombieImpillagerEntity.getBlockPos()), SpawnReason.CONVERSION, new ZombieEntity.ZombieData(false, true), null
                    );
                    zombieImpillagerEntity.setVillagerData(impillagerEntity.getVillagerData());
                    zombieImpillagerEntity.setGossipData(impillagerEntity.getGossip().serialize(NbtOps.INSTANCE));
                    zombieImpillagerEntity.setOfferData(impillagerEntity.getOffers().toNbt());
                    zombieImpillagerEntity.setXp(impillagerEntity.getExperience());

                    // Transfer texture key using set method
                    zombieImpillagerEntity.setTextureKey(impillagerEntity.getDataTracker().get(ImpillagerEntity.TEXTURE_KEY));

                    if (!this.isSilent()) {
                        world.syncWorldEvent(null, WorldEvents.ZOMBIE_INFECTS_VILLAGER, this.getBlockPos(), 0);
                    }

                    bln = false;
                }
            }
            return bln;
        }
        else return original.call(world, other);
    }
}

