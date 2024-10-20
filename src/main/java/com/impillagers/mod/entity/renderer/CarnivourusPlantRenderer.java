package com.impillagers.mod.entity.renderer;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.CarnivourusPlantEntity;
import com.impillagers.mod.entity.custom.ImpillagerEntity;
import com.impillagers.mod.entity.models.CarnivourusPlantModel;
import com.impillagers.mod.entity.models.ImpillagerModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CarnivourusPlantRenderer extends GeoEntityRenderer<CarnivourusPlantEntity> {
    public CarnivourusPlantRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CarnivourusPlantModel());
    }

    @Override
    public ResourceLocation getTextureLocation(CarnivourusPlantEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID,"textures/entity/carnivourus_plant.png");
    }

    @Override
    public void render(CarnivourusPlantEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
