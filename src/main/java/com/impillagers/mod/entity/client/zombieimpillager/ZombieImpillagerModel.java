package com.impillagers.mod.entity.client.zombieimpillager;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.zombieimpillager.ZombieImpillagerEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class ZombieImpillagerModel<T extends ZombieImpillagerEntity> extends SinglePartEntityModel<T> {

    public static final EntityModelLayer ZOMBIE_IMPILLAGER = new EntityModelLayer(Identifier.of(Impillagers.MOD_ID, "zombie_impillager"), "main");

    //private final ModelPart root;
    private final ModelPart zombie_impillager;
    private final ModelPart head;
    private final ModelPart body;


    public ZombieImpillagerModel(ModelPart root) {
        this.zombie_impillager = root.getChild("zombie_impillager");
        this.body = this.zombie_impillager.getChild("body");
        this.head = this.body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData zombie_impillager = modelPartData.addChild("zombie_impillager", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = zombie_impillager.addChild("body", ModelPartBuilder.create().uv(0, 22).cuboid(-3.0F, -4.0F, -2.0F, 6.0F, 8.0F, 4.0F, new Dilation(0.0F))
                .uv(20, 22).cuboid(-3.0F, -4.0F, -2.0F, 6.0F, 8.0F, 4.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, -9.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -5.0F, -3.0F, 8.0F, 5.0F, 6.0F, new Dilation(0.0F))
                .uv(28, 0).cuboid(-4.0F, -5.0F, -3.0F, 8.0F, 5.0F, 6.0F, new Dilation(0.1F))
                .uv(29, 16).cuboid(-1.0F, -3.0F, -4.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 11).cuboid(-7.0F, -11.0F, 0.0F, 14.0F, 11.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, 0.0F));

        ModelPartData arms = body.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 9.0F, 0.0F));

        ModelPartData leftArm = arms.addChild("leftArm", ModelPartBuilder.create().uv(0, 42).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(8, 42).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(3.0F, -12.0F, 0.0F));

        ModelPartData rightArm = arms.addChild("rightArm", ModelPartBuilder.create().uv(0, 34).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(8, 34).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(-3.0F, -12.0F, 0.0F));

        ModelPartData legs = body.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 9.0F, 0.0F));

        ModelPartData rightLeg = legs.addChild("rightLeg", ModelPartBuilder.create().uv(16, 35).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 35).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(-1.0F, -5.0F, 0.0F));

        ModelPartData leftLeg = legs.addChild("leftLeg", ModelPartBuilder.create().uv(16, 43).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 43).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(1.0F, -5.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }


    @Override
    public void setAngles(ZombieImpillagerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.head.yaw = netHeadYaw * (float) (Math.PI / 180.0);
        this.head.pitch = headPitch * (float) (Math.PI / 180.0);

        this.animateMovement(ZombieImpillagerAnimations.WALKING, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, ZombieImpillagerAnimations.ZOMBIE_IDLE, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        zombie_impillager.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return zombie_impillager;
    }
}