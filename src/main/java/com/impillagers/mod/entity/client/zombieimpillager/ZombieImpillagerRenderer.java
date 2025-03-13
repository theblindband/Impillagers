package com.impillagers.mod.entity.client.zombieimpillager;

import com.impillagers.mod.entity.custom.zombieimpillager.ZombieImpillagerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ZombieImpillagerRenderer extends MobEntityRenderer<ZombieImpillagerEntity, ZombieImpillagerModel<ZombieImpillagerEntity>> {

    public ZombieImpillagerRenderer(EntityRendererFactory.Context context) {
        super(context, new ZombieImpillagerModel<>(context.getPart(ZombieImpillagerModel.ZOMBIE_IMPILLAGER)), 0.4f);
        this.addFeature(new ZombieImpillagerProfessionFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(ZombieImpillagerEntity entity) {
        return entity.getCustomTexture();
    }

    @Override
    protected void scale(ZombieImpillagerEntity zombieImpillagerEntity, MatrixStack matrixStack, float f) {
        float g = 0.9375F * zombieImpillagerEntity.getScaleFactor();
        matrixStack.scale(g, g, g);
    }

    @Override
    protected float getShadowRadius(ZombieImpillagerEntity zombieImpillagerEntity) {
        float f = super.getShadowRadius(zombieImpillagerEntity);
        return zombieImpillagerEntity.isBaby() ? f * 0.5F : f;
    }
}
