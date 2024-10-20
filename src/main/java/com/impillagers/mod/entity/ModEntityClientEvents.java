package com.impillagers.mod.entity;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.ImpillagerEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Impillagers.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEntityClientEvents {


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {

        event.put(ModEntities.IMPILLAGER.get(), ImpillagerEntity.createAttributes().build());
        event.put(ModEntities.CARNIVOURUS_PLANT.get(), ImpillagerEntity.createAttributes().build());

    }



    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {

    }
}
