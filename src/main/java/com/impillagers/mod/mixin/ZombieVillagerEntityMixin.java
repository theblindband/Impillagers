package com.impillagers.mod.mixin;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.mob.ZombieImpillagerEntityInterface;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieVillagerEntity.class)
public abstract class ZombieVillagerEntityMixin extends ZombieEntity {
    public ZombieVillagerEntityMixin(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract VillagerData getVillagerData();

    @Shadow
    public abstract void setVillagerData(VillagerData data);

    @Inject(method = "<init>", at = @At("TAIL"))
    private void filterModProfessions(EntityType<? extends ZombieVillagerEntity> entityType, World world, CallbackInfo ci) {
        if (this instanceof ZombieImpillagerEntityInterface) {
            return;
        }

        VillagerProfession currentProfession = this.getVillagerData().getProfession();

        String namespace = Registries.VILLAGER_PROFESSION.getId(currentProfession).getNamespace();
        if (namespace.equals(Impillagers.MOD_ID)) {
            Registries.VILLAGER_PROFESSION
                    .streamEntries()
                    .map(RegistryEntry::value)
                    .filter(profession -> !Registries.VILLAGER_PROFESSION.getId(profession).getNamespace().equals(Impillagers.MOD_ID))
                    .findAny()
                    .ifPresent(profession -> this.setVillagerData(this.getVillagerData().withProfession(profession)));
        }
    }
}

