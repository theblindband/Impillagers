package com.impillagers.mod.entity.custom.zombieimpillager;

import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import com.impillagers.mod.entity.custom.impillager.ImpillagerTextures;
import com.impillagers.mod.entity.mob.ZombieImpillagerEntityInterface;
import com.impillagers.mod.util.ImpillagerProfessionHandler;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ZombieImpillagerEntity extends ZombieVillagerEntity implements ZombieImpillagerEntityInterface {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int conversionTimer;
    @Nullable
    private UUID converter;
    @Nullable
    private NbtElement gossipData;
    @Nullable
    private TradeOfferList offerData;
    private int xp;
    public static final TrackedData<String> TEXTURE_KEY = DataTracker.registerData(ZombieImpillagerEntity.class, TrackedDataHandlerRegistry.STRING);

    public ZombieImpillagerEntity(EntityType<? extends ZombieVillagerEntity> entityType, World world) {
        super(entityType, world);

        Set<String> professionKeys = ImpillagerProfessionHandler.getProfessionKeys();
        List<VillagerProfession> validProfessions = professionKeys.stream()
                .map(key -> Registries.VILLAGER_PROFESSION.get(Identifier.of(key)))
                .toList();

        if (!validProfessions.isEmpty()) {
            VillagerProfession selectedProfession = validProfessions.get(this.random.nextInt(validProfessions.size()));
            this.setVillagerData(this.getVillagerData().withProfession(selectedProfession));
        }

        this.experiencePoints = 10;
        if (!world.isClient) {
            this.dataTracker.set(TEXTURE_KEY, ImpillagerTextures.selectRandomTextureKey());
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(TEXTURE_KEY, "");
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        super.onTrackedDataSet(data);
        if (data == TEXTURE_KEY && this.dataTracker.get(TEXTURE_KEY).isEmpty()) {
            this.dataTracker.set(TEXTURE_KEY, ImpillagerTextures.selectRandomTextureKey());
        }
    }

    public Identifier getCustomTexture() {
        String textureKey = this.dataTracker.get(TEXTURE_KEY);
        return ImpillagerTextures.getTextureByKey(textureKey).orElse(Identifier.tryParse("fallback_texture"));
    }

    public void setTextureKey(String textureKey) {
        this.dataTracker.set(TEXTURE_KEY, textureKey);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        String textureKey = this.dataTracker.get(TEXTURE_KEY);
        if (textureKey != null && !textureKey.isEmpty()) {
            nbt.putString("TextureKey", textureKey);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("TextureKey")) {
            this.dataTracker.set(TEXTURE_KEY, nbt.getString("TextureKey"));
        }
    }



    //-------------------------------------Attributes-------------------------------------

    public static DefaultAttributeContainer.Builder createZombieImpillagerAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0)
                .add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        return !effect.equals(StatusEffects.POISON) && super.canHaveStatusEffect(effect);
    }

    //-------------------------------------Tick-------------------------------------

    @Override
    public void tick() {
        if (!this.getWorld().isClient && this.isAlive() && this.isConverting()) {
            int i = this.getConversionRate();
            this.conversionTimer -= i;
            if (this.conversionTimer <= 0) {
                this.finishConversion((ServerWorld)this.getWorld());
            }
        }

        super.tick();
        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    //-------------------------------------Animation-------------------------------------

    private void setupAnimationStates(){
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 160;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }


    private void finishConversion(ServerWorld world) {
        ImpillagerEntity villagerEntity = this.convertTo(ModEntities.IMPILLAGER, false);
        if (villagerEntity != null) {
            for (EquipmentSlot equipmentSlot : this.dropEquipment(
                    stack -> !EnchantmentHelper.hasAnyEnchantmentsWith(stack, EnchantmentEffectComponentTypes.PREVENT_ARMOR_CHANGE)
            )) {
                StackReference stackReference = villagerEntity.getStackReference(equipmentSlot.getEntitySlotId() + 300);
                stackReference.set(this.getEquippedStack(equipmentSlot));
            }

            villagerEntity.setVillagerData(this.getVillagerData());
            if (this.gossipData != null) {
                villagerEntity.readGossipDataNbt(this.gossipData);
            }

            if (this.offerData != null) {
                villagerEntity.setOffers(this.offerData.copy());
            }

            villagerEntity.setExperience(this.xp);

            if (villagerEntity instanceof ImpillagerEntity) {
                villagerEntity.setTextureKey(this.dataTracker.get(ZombieImpillagerEntity.TEXTURE_KEY));
            }

            villagerEntity.initialize(world, world.getLocalDifficulty(villagerEntity.getBlockPos()), SpawnReason.CONVERSION, null);
            villagerEntity.reinitializeBrain(world);
            if (this.converter != null) {
                PlayerEntity playerEntity = world.getPlayerByUuid(this.converter);
                if (playerEntity instanceof ServerPlayerEntity) {
                    Criteria.CURED_ZOMBIE_VILLAGER.trigger((ServerPlayerEntity) playerEntity, this, villagerEntity);
                    world.handleInteraction(EntityInteraction.ZOMBIE_VILLAGER_CURED, playerEntity, villagerEntity);
                }
            }

            villagerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0));
            if (!this.isSilent()) {
                world.syncWorldEvent(null, WorldEvents.ZOMBIE_VILLAGER_CURED, this.getBlockPos(), 0);
            }
        }
    }


    private int getConversionRate() {
        int i = 1;
        if (this.random.nextFloat() < 0.01F) {
            int j = 0;
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            for (int k = (int)this.getX() - 4; k < (int)this.getX() + 4 && j < 14; k++) {
                for (int l = (int)this.getY() - 4; l < (int)this.getY() + 4 && j < 14; l++) {
                    for (int m = (int)this.getZ() - 4; m < (int)this.getZ() + 4 && j < 14; m++) {
                        BlockState blockState = this.getWorld().getBlockState(mutable.set(k, l, m));
                        if (blockState.isOf(Blocks.IRON_BARS) || blockState.getBlock() instanceof BedBlock) {
                            if (this.random.nextFloat() < 0.3F) {
                                i++;
                            }

                            j++;
                        }
                    }
                }
            }
        }

        return i;
    }

}
