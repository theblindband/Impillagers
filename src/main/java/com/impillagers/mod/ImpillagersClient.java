package com.impillagers.mod;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.entity.client.ImpillagerModel;
import com.impillagers.mod.entity.client.ImpillagerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class ImpillagersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_HEART_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_HEART_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_HEART_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_HEART_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_PURPLE_HEART_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BELLADONNA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BELLADONNA, RenderLayer.getCutout());

        EntityModelLayerRegistry.registerModelLayer(ImpillagerModel.IMPILLAGER, ImpillagerModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.IMPILLAGER, ImpillagerRenderer::new);

    }
}
