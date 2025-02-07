package com.impillagers.mod.entity;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.ImpillagerEntity;
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

    public static final EntityType<DungBallEntity> DUNG_BALL = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Impillagers.MOD_ID, "dung_ball"),
            EntityType.Builder.<DungBallEntity>create(DungBallEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25f, 0.25f).build());


    public static void registerModEntities(){
        //Impillagers.LOGGER.info("Registering Mod Entities for " + Impillagers.MOD_ID);
    }
}
