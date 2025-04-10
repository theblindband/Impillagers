package com.impillagers.mod.entity.client.dung_golem;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.dung_golem.DungGolemEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DungGolemRenderer extends MobEntityRenderer<DungGolemEntity, DungGolemModel<DungGolemEntity>> {
    public DungGolemRenderer(EntityRendererFactory.Context context) {
        super(context, new DungGolemModel<>(context.getPart(DungGolemModel.DUNG_GOLEM)), 0.75f);
    }

    @Override
    public Identifier getTexture(DungGolemEntity entity) {
        return Identifier.of(Impillagers.MOD_ID, "textures/entity/dung_golem/dung_golem.png");
    }

    @Override
    public void render(DungGolemEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    protected float getShadowRadius(DungGolemEntity dungGolemEntity) {
        return dungGolemEntity.isBaby() ? 0.4F * 0.5F : 0.4F;
    }
}