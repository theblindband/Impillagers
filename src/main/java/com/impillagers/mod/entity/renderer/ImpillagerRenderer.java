package com.impillagers.mod.entity.renderer;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.ImpillagerEntity;
import com.impillagers.mod.entity.models.ImpillagerModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ImpillagerRenderer extends GeoEntityRenderer<ImpillagerEntity> {
    public ImpillagerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ImpillagerModel());
    }

    @Override
    public ResourceLocation getTextureLocation(ImpillagerEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID,"textures/entity/impillager/impillager.png");
    }

    @Override
    public void render(ImpillagerEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
