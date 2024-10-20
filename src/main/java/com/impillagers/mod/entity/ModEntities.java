package com.impillagers.mod.entity;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.CarnivourusPlantEntity;
import com.impillagers.mod.entity.custom.ImpillagerEntity;
import com.impillagers.mod.entity.renderer.ImpillagerRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Impillagers.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<ImpillagerEntity>> IMPILLAGER =
            ENTITY_TYPES.register("impillager", () -> EntityType.Builder.of(ImpillagerEntity::new, MobCategory.CREATURE)
                    .sized(1F, 1F).build("impillager"));
    public static final DeferredHolder<EntityType<?>, EntityType<CarnivourusPlantEntity>> CARNIVOURUS_PLANT =
            ENTITY_TYPES.register("carnivourus_plant", () -> EntityType.Builder.of(CarnivourusPlantEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.95F).build("carnivourus_plant"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}



