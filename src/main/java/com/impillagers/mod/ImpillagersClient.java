package com.impillagers.mod;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.effect.ModEffectClient;
import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.entity.client.*;
import com.impillagers.mod.entity.client.boat.ModBoatRenderer;
import com.impillagers.mod.entity.client.dung_golem.DungGolemModel;
import com.impillagers.mod.entity.client.dung_golem.DungGolemRenderer;
import com.impillagers.mod.entity.client.impillager.ImpillagerModel;
import com.impillagers.mod.entity.client.impillager.ImpillagerRenderer;
import com.impillagers.mod.entity.client.zombieimpillager.ZombieImpillagerModel;
import com.impillagers.mod.entity.client.zombieimpillager.ZombieImpillagerRenderer;
import com.impillagers.mod.entity.custom.dung_golem.DungGolemEntity;
import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import com.impillagers.mod.entity.custom.zombieimpillager.ZombieImpillagerEntity;
import com.impillagers.mod.particle.ModParticleTypes;
import com.impillagers.mod.particle.custom.FireflyParticle;
import com.impillagers.mod.screen.ModScreenHandlers;
import com.impillagers.mod.screen.custom.SafeScreen;
//import com.impillagers.mod.util.HudOverlayOpacityPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
//TODO: FIX
public class ImpillagersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ModHud.initializeModHud();

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_HEART_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_HEART_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_HEART_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_HEART_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_PURPLE_HEART_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BELLADONNA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BELLADONNA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FIREFLY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SWAMP_REED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FIREFLY_BOTTLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WASTE_BASKET, RenderLayer.getCutout());

        EntityModelLayerRegistry.registerModelLayer(ImpillagerModel.IMPILLAGER, ImpillagerModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ZombieImpillagerModel.ZOMBIE_IMPILLAGER, ZombieImpillagerModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(DungGolemModel.DUNG_GOLEM, DungGolemModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.IMPILLAGER, ImpillagerRenderer::new);
        EntityRendererRegistry.register(ModEntities.ZOMBIE_IMPILLAGER, ZombieImpillagerRenderer::new);
        EntityRendererRegistry.register(ModEntities.DUNG_GOLEM, DungGolemRenderer::new);
        EntityRendererRegistry.register(ModEntities.DUNG_BALL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BOAT, m -> new ModBoatRenderer<>(m, false));
        EntityRendererRegistry.register(ModEntities.CHEST_BOAT, m -> new ModBoatRenderer<>(m, true));

        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.FIREFLY,((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new FireflyParticle(world, x, y, z, spriteProvider)));

        //ClientPlayNetworking.registerGlobalReceiver(HudOverlayOpacityPayload.ID, (payload, context) -> context.client().execute(() -> ModHud.renderCallOfTheImpsOverlay (payload.opacity())));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {ModEffectClient.updateSoundEffects();});

        ClientPlayConnectionEvents.INIT.register((handler, client) -> {
            //Register Attributes on client when joining a server
            FabricDefaultAttributeRegistry.register(ModEntities.IMPILLAGER, ImpillagerEntity.createVillagerAttributes());
            FabricDefaultAttributeRegistry.register(ModEntities.ZOMBIE_IMPILLAGER, ZombieImpillagerEntity.createZombieImpillagerAttributes());
            FabricDefaultAttributeRegistry.register(ModEntities.DUNG_GOLEM, DungGolemEntity.createDungGolemAttributes());
        });

        HandledScreens.register(ModScreenHandlers.SAFE_SCREEN_HANDLER, SafeScreen::new);
    }
}
