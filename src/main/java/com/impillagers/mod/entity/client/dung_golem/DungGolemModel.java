package com.impillagers.mod.entity.client.dung_golem;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.dung_golem.DungGolemEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DungGolemModel <T extends DungGolemEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer DUNG_GOLEM = new EntityModelLayer(Identifier.of(Impillagers.MOD_ID, "dung_golem"), "main");
    private final ModelPart dung_golem;
    private final ModelPart body;

    public DungGolemModel(ModelPart root) {
        this.dung_golem = root.getChild("dung_golem");
        this.body = this.dung_golem.getChild("body");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData dung_golem = modelPartData.addChild("dung_golem", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = dung_golem.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -9.0F, -3.0F, 10.0F, 6.0F, 6.0F, new Dilation(0.0F))
                .uv(28, 18).cuboid(-1.0F, -5.0F, -4.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(-8.0F, -13.0F, 0.0F, 16.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData flower = body.addChild("flower", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData stem2_r1 = flower.addChild("stem2_r1", ModelPartBuilder.create().uv(8, 26).cuboid(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.051F, -9.177F, -2.0669F, 0.0F, -1.5708F, -0.7854F));

        ModelPartData stem1_r1 = flower.addChild("stem1_r1", ModelPartBuilder.create().uv(8, 25).cuboid(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.051F, -9.177F, -2.0669F, 0.0F, -1.5708F, 0.7854F));

        ModelPartData head_r1 = flower.addChild("head_r1", ModelPartBuilder.create().uv(28, 22).cuboid(-1.4419F, -1.5301F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.0581F, -9.2699F, -3.075F, 0.0F, 0.0F, 0.9599F));

        ModelPartData legs = dung_golem.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rightLeg = legs.addChild("rightLeg", ModelPartBuilder.create().uv(12, 24).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -3.0F, 0.0F));

        ModelPartData leftLeg = legs.addChild("leftLeg", ModelPartBuilder.create().uv(20, 24).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -3.0F, 0.0F));

        ModelPartData arms = dung_golem.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

        ModelPartData leftArm = arms.addChild("leftArm", ModelPartBuilder.create().uv(12, 18).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 18).cuboid(-0.5F, 2.0F, -1.5F, 3.0F, 4.0F, 3.0F, new Dilation(-0.3F)), ModelTransform.pivot(5.0F, 0.0F, 0.0F));

        ModelPartData rightArm = arms.addChild("rightArm", ModelPartBuilder.create().uv(20, 18).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 25).cuboid(-2.0F, 3.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(-5.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }


    @Override
    public void setAngles(DungGolemEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(DungGolemAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, DungGolemAnimations.IDLE, ageInTicks, 1f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        dung_golem.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return dung_golem;
    }
}
