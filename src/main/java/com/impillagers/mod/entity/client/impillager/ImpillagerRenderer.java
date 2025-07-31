package com.impillagers.mod.entity.client.impillager;

import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
//TODO: FIX
@Environment(EnvType.CLIENT)
public class ImpillagerRenderer extends MobEntityRenderer<ImpillagerEntity, ImpillagerModel> {

    public ImpillagerRenderer(EntityRendererFactory.Context context) {
        super(context, new ImpillagerModel(context.getPart(ImpillagerModel.IMPILLAGER)), 0.4f);
        this.addFeature(new ImpillagerProfessionFeatureRenderer(this));
        this.addFeature(new HeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()));
    }

    @Override
    public Identifier getTexture(ImpillagerEntity entity) {
        return entity.getCustomTexture();
    }

    @Override
    protected void scale(ImpillagerEntity impillagerEntity, MatrixStack matrixStack, float f) {
        float g = 0.9375F * impillagerEntity.getScaleFactor();
        matrixStack.scale(g, g, g);
    }

    /*@Override
    protected float getShadowRadius(ImpillagerEntity impillagerEntity) {
        float f = super.getShadowRadius(impillagerEntity);
        return impillagerEntity.isBaby() ? f * 0.5F : f;
    }*/

}
