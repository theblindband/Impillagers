package com.impillagers.mod.entity.custom;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.impillagers.mod.effect.ModEffects;
import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.entity.ai.brain.task.ImpillagerTaskListProvider;
import com.impillagers.mod.entity.mob.Impillager;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.sounds.ModSoundEvents;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.VillagerTaskListProvider;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ImpillagerEntity extends VillagerEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public static final TrackedData<String> TEXTURE_KEY = DataTracker.registerData(ImpillagerEntity.class, TrackedDataHandlerRegistry.STRING);

    public ImpillagerEntity(EntityType<? extends VillagerEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 3;
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

    public static DefaultAttributeContainer.Builder createVillagerAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.75)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0);
    }

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        return effect.equals(StatusEffects.POISON) ? false : super.canHaveStatusEffect(effect);
    }

    //-------------------------------------Tick-------------------------------------

    @Override
    public void tick() {
        super.tick();
        if (this.getHeadRollingTimeLeft() > 0) {
            this.setHeadRollingTimeLeft(this.getHeadRollingTimeLeft() - 1);
        }
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

    //-------------------------------------Brain-------------------------------------

    private static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULES = ImmutableList.of(
            MemoryModuleType.HOME,
            MemoryModuleType.JOB_SITE,
            MemoryModuleType.POTENTIAL_JOB_SITE,
            MemoryModuleType.MEETING_POINT,
            MemoryModuleType.MOBS,
            MemoryModuleType.VISIBLE_MOBS,
            MemoryModuleType.VISIBLE_VILLAGER_BABIES,
            MemoryModuleType.NEAREST_PLAYERS,
            MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM,
            MemoryModuleType.ITEM_PICKUP_COOLDOWN_TICKS,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.INTERACTION_TARGET,
            MemoryModuleType.BREED_TARGET,
            MemoryModuleType.PATH,
            MemoryModuleType.DOORS_TO_CLOSE,
            MemoryModuleType.NEAREST_BED,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.NEAREST_HOSTILE,
            MemoryModuleType.SECONDARY_JOB_SITE,
            MemoryModuleType.HIDING_PLACE,
            MemoryModuleType.HEARD_BELL_TIME,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.LAST_SLEPT,
            MemoryModuleType.LAST_WOKEN,
            MemoryModuleType.LAST_WORKED_AT_POI,
            MemoryModuleType.GOLEM_DETECTED_RECENTLY,
            MemoryModuleType.ATTACK_TARGET,
            MemoryModuleType.ATTACK_COOLING_DOWN,
            MemoryModuleType.AVOID_TARGET
    );
    private static final ImmutableList<SensorType<? extends Sensor<? super VillagerEntity>>> SENSORS = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.NEAREST_PLAYERS,
            SensorType.NEAREST_ITEMS,
            SensorType.NEAREST_BED,
            SensorType.HURT_BY,
            SensorType.VILLAGER_HOSTILES,
            SensorType.VILLAGER_BABIES,
            SensorType.SECONDARY_POIS,
            SensorType.GOLEM_DETECTED
    );

    @Override
    public Brain<VillagerEntity> getBrain() {
        return (Brain<VillagerEntity>)super.getBrain();
    }

    @Override
    protected Brain.Profile<VillagerEntity> createBrainProfile() {
        return Brain.createProfile(MEMORY_MODULES, SENSORS);
    }

    @Override
    protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
        Brain<VillagerEntity> brain = this.createBrainProfile().deserialize(dynamic);
        this.initBrain(brain);
        return brain;
    }

    public void reinitializeBrain(ServerWorld world) {
        Brain<VillagerEntity> brain = this.getBrain();
        brain.stopAllTasks(world, this);
        this.brain = brain.copy();
        this.initBrain(this.getBrain());
    }

    private void initBrain(Brain<VillagerEntity> brain) {
        VillagerProfession villagerProfession = this.getVillagerData().getProfession();
        if (this.isBaby()) {
            brain.setSchedule(Schedule.VILLAGER_BABY);
            brain.setTaskList(Activity.PLAY, VillagerTaskListProvider.createPlayTasks(0.5F));
        } else {
            brain.setSchedule(Schedule.VILLAGER_DEFAULT);
            brain.setTaskList(
                    Activity.WORK,
                    VillagerTaskListProvider.createWorkTasks(villagerProfession, 0.5F),
                    ImmutableSet.of(Pair.of(MemoryModuleType.JOB_SITE, MemoryModuleState.VALUE_PRESENT))
            );
        }

        brain.setTaskList(Activity.CORE, ImpillagerTaskListProvider.createCoreTasks(villagerProfession, 0.5F));
        brain.setTaskList(
                Activity.MEET,
                VillagerTaskListProvider.createMeetTasks(villagerProfession, 0.5F),
                ImmutableSet.of(Pair.of(MemoryModuleType.MEETING_POINT, MemoryModuleState.VALUE_PRESENT))
        );
        brain.setTaskList(Activity.REST, VillagerTaskListProvider.createRestTasks(villagerProfession, 0.5F));
        brain.setTaskList(Activity.IDLE, ImpillagerTaskListProvider.createIdleTasks(villagerProfession, 0.5F));
        brain.setTaskList(Activity.PANIC, VillagerTaskListProvider.createPanicTasks(villagerProfession, 0.5F));
        brain.setTaskList(Activity.PRE_RAID, VillagerTaskListProvider.createPreRaidTasks(villagerProfession, 0.5F));
        brain.setTaskList(Activity.RAID, VillagerTaskListProvider.createRaidTasks(villagerProfession, 0.5F));
        brain.setTaskList(Activity.HIDE, VillagerTaskListProvider.createHideTasks(villagerProfession, 0.5F));
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.doExclusively(Activity.IDLE);
        brain.refreshActivities(this.getWorld().getTimeOfDay(), this.getWorld().getTime());
    }



    //-------------------------------------Sounds-------------------------------------

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isSleeping()) {
            return null;
        } else {
            boolean playerNearby = this.getWorld().getClosestPlayer(this, 5) != null;
            return playerNearby ? ModSoundEvents.IMPILLAGER_PURSUE : ModSoundEvents.IMPILLAGER_AMBIENT;
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSoundEvents.IMPILLAGER_DAMAGE;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.IMPILLAGER_DEATH;
    }

    public static SoundEvent getRangedAttackSound() {
        return ModSoundEvents.IMPILLAGER_ATTACK_RANGED;
    }
    protected SoundEvent getMeleeAttackSound() {
        return ModSoundEvents.IMPILLAGER_ATTACK_MELEE;
    }

    @Override
    public SoundEvent getYesSound() {
        return ModSoundEvents.IMPILLAGER_YES;
    }

    @Override
    protected SoundEvent getTradingSound(boolean sold) {
        return sold ? ModSoundEvents.IMPILLAGER_YES : ModSoundEvents.IMPILLAGER_NO;
    }
    @Override
    public void playCelebrateSound() {
        this.playSound(ModSoundEvents.IMPILLAGER_CELEBRATE);
    }


    //Create Child

    @Override
    public VillagerEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.IMPILLAGER.create(world);
    }

    //-------------------------------------Traits-------------------------------------

    @Override
    public boolean canBeLeashed() {
        return true;
    }

    @Override
    public boolean shouldDropXp() {
        return true;
    }

    @Override
    protected int getXpToDrop() {
        return this.experiencePoints;
    }

    //-------------------------------------Trade with Player-------------------------------------

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(ModItems.IMPILLAGER_SPAWN_EGG) || !this.isAlive() || this.hasCustomer() || this.isSleeping()) {
            return super.interactMob(player, hand);
        } else if (this.isBaby()) {
            this.sayNo();
            return ActionResult.success(this.getWorld().isClient);
        } else {
            if (!this.getWorld().isClient) {
                boolean bl = this.getOffers().isEmpty();
                if (hand == Hand.MAIN_HAND) {
                    if (bl) {
                        this.sayNo();
                    }
                }

                if (bl) {
                    return ActionResult.CONSUME;
                }

                this.beginTradeWith(player);
            }

            return ActionResult.success(this.getWorld().isClient);
        }
    }

    private void sayNo() {
        this.setHeadRollingTimeLeft(20);
        if (!this.getWorld().isClient()) {
            this.playSound(ModSoundEvents.IMPILLAGER_NO);
        }
    }

    private void beginTradeWith(PlayerEntity customer) {
        this.prepareOffersFor(customer);
        this.setCustomer(customer);
        this.sendOffers(customer, this.getDisplayName(), this.getVillagerData().getLevel());
    }

    private void prepareOffersFor(PlayerEntity player) {
        int i = this.getReputation(player);
        if (i != 0) {
            for (TradeOffer tradeOffer : this.getOffers()) {
                tradeOffer.increaseSpecialPrice(-MathHelper.floor((float) i * tradeOffer.getPriceMultiplier()));
            }
        }
        if (player.hasStatusEffect(ModEffects.SMELLY)) {
            StatusEffectInstance statusEffectInstance = player.getStatusEffect(ModEffects.SMELLY);
            assert statusEffectInstance != null;

            for (TradeOffer tradeOffer : ((VillagerEntity) (Object) this).getOffers()) {
                double d = 0.3 + 0.0625;
                int k = (int) Math.floor(d * tradeOffer.getOriginalFirstBuyItem().getCount());
                tradeOffer.increaseSpecialPrice(-Math.max(k, 2));
            }
        }
        if (player.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE)) {
            StatusEffectInstance statusEffectInstance = player.getStatusEffect(ModEffects.SMELLY);
            assert statusEffectInstance != null;

            for (TradeOffer tradeOffer : ((VillagerEntity) (Object) this).getOffers()) {
                double d = 0.3 + 0.0625;
                int k = (int) Math.floor(d * tradeOffer.getOriginalFirstBuyItem().getCount());
                tradeOffer.increaseSpecialPrice(+Math.max(k, 5));
            }
        }
    }

    //-------------------------------------Attack-------------------------------------

    @Override
    public boolean damage(DamageSource source, float amount) {
        boolean bl = super.damage(source, amount);
        if (this.getWorld().isClient) {
            return false;
        } else {
            if (bl && source.getAttacker() instanceof LivingEntity) {
                ImpillagerTaskListProvider.onAttacked(this, (LivingEntity)source.getAttacker());
            }
            return bl;
        }
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (!(target instanceof LivingEntity)) {
            return false;
        } else {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
            this.playSound(getMeleeAttackSound());
            return Impillager.tryAttack(this, (LivingEntity)target);
        }
    }
}
