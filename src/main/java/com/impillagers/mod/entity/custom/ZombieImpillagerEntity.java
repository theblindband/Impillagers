package com.impillagers.mod.entity.custom;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.mob.ZombieImpillagerEntityInterface;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;

import java.util.List;

public class ZombieImpillagerEntity extends ZombieVillagerEntity implements ZombieImpillagerEntityInterface {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private static final TrackedData<String> CUSTOM_TEXTURE = DataTracker.registerData(ZombieImpillagerEntity.class, TrackedDataHandlerRegistry.STRING);

    public ZombieImpillagerEntity(EntityType<? extends ZombieVillagerEntity> entityType, World world) {
        super(entityType, world);

        List<VillagerProfession> validProfessions = Registries.VILLAGER_PROFESSION
                .streamEntries()
                .map(RegistryEntry::value)
                .filter(profession -> {
                    String namespace = Registries.VILLAGER_PROFESSION.getId(profession).getNamespace();
                    return namespace.equals(Impillagers.MOD_ID) || profession == VillagerProfession.NONE || profession == VillagerProfession.NITWIT;
                })
                .toList();

        if (!validProfessions.isEmpty()) {
            VillagerProfession selectedProfession = validProfessions.get(this.random.nextInt(validProfessions.size()));
            this.setVillagerData(this.getVillagerData().withProfession(selectedProfession));
        }

        this.experiencePoints = 10;
        if (!world.isClient) {
            this.dataTracker.set(CUSTOM_TEXTURE, ImpillagerTextures.selectRandomTexture().toString());
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(CUSTOM_TEXTURE, "");
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        super.onTrackedDataSet(data);
        if (data == CUSTOM_TEXTURE && this.dataTracker.get(CUSTOM_TEXTURE).isEmpty()) {
            this.dataTracker.set(CUSTOM_TEXTURE, ImpillagerTextures.selectRandomTexture().toString());
        }
    }

    public Identifier getCustomTexture() {
        String texturePath = this.dataTracker.get(CUSTOM_TEXTURE);
        return texturePath.isEmpty() ? Identifier.tryParse("fallback_texture") : Identifier.tryParse(texturePath);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        String texture = this.dataTracker.get(CUSTOM_TEXTURE);
        if (texture != null && !texture.isEmpty()) {
            nbt.putString("CustomTexture", texture);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("CustomTexture")) {
            this.dataTracker.set(CUSTOM_TEXTURE, nbt.getString("CustomTexture"));
        }
    }

    //-------------------------------------Attributes-------------------------------------

    public static DefaultAttributeContainer.Builder createZombieImpillagerAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.55)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0)
                .add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    //-------------------------------------Tick-------------------------------------

    @Override
    public void tick() {
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


}
