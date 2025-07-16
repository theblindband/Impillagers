package com.impillagers.mod.entity;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.boat.ModBoatEntity;
import com.impillagers.mod.entity.boat.ModChestBoatEntity;
import com.impillagers.mod.entity.custom.dung_golem.DungGolemEntity;
import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import com.impillagers.mod.entity.custom.zombieimpillager.ZombieImpillagerEntity;
import com.impillagers.mod.entity.projectile.thrown.DungBallEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<ImpillagerEntity> IMPILLAGER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Impillagers.MOD_ID, "impillager"),
            EntityType.Builder.create(ImpillagerEntity::new, SpawnGroup.MISC)
                    .dimensions(0.625f, 0.9f).build());

    public static final EntityType<ZombieImpillagerEntity> ZOMBIE_IMPILLAGER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Impillagers.MOD_ID, "zombie_impillager"),
            EntityType.Builder.create(ZombieImpillagerEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.625f, 0.9f).build());

    public static final EntityType<DungGolemEntity> DUNG_GOLEM = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Impillagers.MOD_ID, "dung_golem"),
            EntityType.Builder.create(DungGolemEntity::new, SpawnGroup.MISC)
                    .dimensions(0.625f, 0.9f).build());

    public static final EntityType<DungBallEntity> DUNG_BALL = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Impillagers.MOD_ID, "dung_ball"),
            EntityType.Builder.<DungBallEntity>create(DungBallEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25f, 0.25f).build());

    public static final EntityType<ModBoatEntity> BOAT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Impillagers.MOD_ID, "boat"),
            EntityType.Builder.<ModBoatEntity>create(ModBoatEntity::new, SpawnGroup.MISC)
                    .dimensions(1.375F, 0.5625F).maxTrackingRange(5).trackingTickInterval(3).build());

    public static final EntityType<ModChestBoatEntity> CHEST_BOAT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Impillagers.MOD_ID, "chest_boat"),
            EntityType.Builder.create(ModChestBoatEntity::new, SpawnGroup.MISC)
                    .dimensions(1.375F, 0.5625F).maxTrackingRange(5).trackingTickInterval(3).build());


    public static void registerModEntities(){
        //Impillagers.LOGGER.info("Registering Mod Entities for " + Impillagers.MOD_ID);
    }
}
