package com.impillagers.mod.entity.client.impillager;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class ImpillagerModel extends SinglePartEntityModel<ImpillagerEntity> implements ModelWithArms {

    public static final EntityModelLayer IMPILLAGER = new EntityModelLayer(Identifier.of(Impillagers.MOD_ID, "impillager"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart right_hand;
    private final ModelPart leftArm;
    private final ModelPart left_hand;


    public ImpillagerModel(ModelPart root) {
        this.root = root.getChild(EntityModelPartNames.ROOT);
        this.body = this.root.getChild(EntityModelPartNames.BODY);
        this.head = this.body.getChild(EntityModelPartNames.HEAD);
        this.rightArm = this.body.getChild(EntityModelPartNames.RIGHT_ARM);
        this.right_hand = this.rightArm.getChild(EntityModelPartNames.RIGHT_HAND);
        this.leftArm = this.body.getChild(EntityModelPartNames.LEFT_ARM);
        this.left_hand = this.leftArm.getChild(EntityModelPartNames.LEFT_HAND);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild(EntityModelPartNames.ROOT, ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = root.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 22).cuboid(-3.0F, -4.0F, -2.0F, 6.0F, 8.0F, 4.0F, new Dilation(0.0F))
                .uv(20, 22).cuboid(-3.0F, -4.0F, -2.0F, 6.0F, 8.0F, 4.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, -9.0F, 0.0F));


        body.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -5.0F, -3.0F, 8.0F, 5.0F, 6.0F, new Dilation(0.0F))
                .uv(28, 0).cuboid(-4.0F, -5.0F, -3.0F, 8.0F, 5.0F, 6.0F, new Dilation(0.1F))
                .uv(29, 16).cuboid(-1.0F, -3.0F, -4.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 11).cuboid(-7.0F, -11.0F, 0.0F, 14.0F, 11.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, 0.0F));

        ModelPartData left_arm = body.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(0, 42).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(8, 42).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(3.0F, -3.0F, 0.0F));

        left_arm.addChild(EntityModelPartNames.LEFT_HAND, ModelPartBuilder.create().uv(4, 2).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 5.0F, 0.0F));

        ModelPartData right_arm = body.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(0, 34).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(8, 34).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(-3.0F, -3.0F, 0.0F));

        right_arm.addChild(EntityModelPartNames.RIGHT_HAND, ModelPartBuilder.create().uv(4, 2).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 5.0F, 0.0F));

        body.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(16, 43).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 43).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(1.0F, 4.0F, 0.0F));

        body.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(16, 35).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 35).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(-1.0F, 4.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }


    @Override
    public void setAngles(ImpillagerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        boolean bl = entity.getHeadRollingTimeLeft() > 0;

        this.head.yaw = netHeadYaw * (float) (Math.PI / 180.0);
        this.head.pitch = headPitch * (float) (Math.PI / 180.0);
        if (bl) {
            this.head.roll = 0.3F * MathHelper.sin(1.6F * ageInTicks);
            this.head.pitch = 0.4F;
        } else {
            this.head.roll = 0.0F;
        }

        this.animateMovement(ImpillagerAnimations.WALKING, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, ImpillagerAnimations.IDLE, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setArmAngle(Arm arm, MatrixStack matrices) {
        boolean bl = arm == Arm.RIGHT;
        ModelPart whichArm = bl ? this.rightArm : this.leftArm;
        ModelPart whichHand = bl ? this.right_hand : this.left_hand;
        this.root.rotate(matrices);
        this.body.rotate(matrices);
        whichArm.rotate(matrices);
        whichHand.rotate(matrices);
        this.translateForHand(matrices, bl);
    }

    private void translateForHand(MatrixStack matrices, boolean mainHand) {
        if (mainHand) {
            matrices.translate(0.055, -0.55, 0.055);
        } else {
            matrices.translate(-0.055, -0.55, 0.055);
        }
    }
}