package com.impillagers.mod.mixin;

import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import com.impillagers.mod.entity.custom.zombieimpillager.ZombieImpillagerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends HostileEntity {

    // Constructor matching the parent class
    protected ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author
     * Minico
     * @reason
     * This is a copy of the original zombie conversion code, this is adding a check for if a villagerEntity is actually an ImpillagerEntity and doing its own branch because of the result.
     * I did an override otherwise I would need multiple line replacements and the hope is that since I am leaving the vanilla system intact, just wrapped by the impillager system it shouldn't break compatibility.
     */
    @Overwrite
    public boolean onKilledOther(ServerWorld world, LivingEntity other) {
        boolean bl = super.onKilledOther(world, other);

        if (other instanceof ImpillagerEntity) {

            // Handle ImpillagerEntity to ZombieImpillagerEntity conversion
            if ((world.getDifficulty() == Difficulty.NORMAL || world.getDifficulty() == Difficulty.HARD) && other instanceof ImpillagerEntity impillagerEntity) {
                if (world.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
                    return bl;
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

                    bl = false;
                }
            }
        }



        // Handle standard villager to zombie villager conversion
        else if ((world.getDifficulty() == Difficulty.NORMAL || world.getDifficulty() == Difficulty.HARD) && other instanceof VillagerEntity villagerEntity) {

            if (world.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
                return bl;
            }

            ZombieVillagerEntity zombieVillagerEntity = villagerEntity.convertTo(EntityType.ZOMBIE_VILLAGER, false);
            if (zombieVillagerEntity != null) {
                zombieVillagerEntity.initialize(
                        world, world.getLocalDifficulty(zombieVillagerEntity.getBlockPos()), SpawnReason.CONVERSION, new ZombieEntity.ZombieData(false, true), null
                );
                zombieVillagerEntity.setVillagerData(villagerEntity.getVillagerData());
                zombieVillagerEntity.setGossipData(villagerEntity.getGossip().serialize(NbtOps.INSTANCE));
                zombieVillagerEntity.setOfferData(villagerEntity.getOffers().toNbt());
                zombieVillagerEntity.setXp(villagerEntity.getExperience());
                if (!this.isSilent()) {
                    world.syncWorldEvent(null, WorldEvents.ZOMBIE_INFECTS_VILLAGER, this.getBlockPos(), 0);
                }

                bl = false;
            }
        }
        return bl;
    }
}

