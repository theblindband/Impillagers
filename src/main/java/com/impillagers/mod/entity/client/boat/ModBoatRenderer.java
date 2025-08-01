package com.impillagers.mod.entity.client.boat;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.boat.IModBoat;
import com.impillagers.mod.entity.boat.ModBoatEntity;
import com.impillagers.mod.entity.boat.ModChestBoatEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionf;

public class ModBoatRenderer<E extends BoatEntity & IModBoat> extends EntityRenderer<E> {

    private final CompositeEntityModel<BoatEntity> model;

    public ModBoatRenderer(EntityRendererFactory.Context ctx, boolean chest) {
        super(ctx);
        this.shadowRadius = 0.8F;

        EntityModelLayer layer = chest ? EntityModelLayers.createChestBoat(BoatEntity.Type.OAK) : EntityModelLayers.createBoat(     BoatEntity.Type.OAK);

        ModelPart part = ctx.getPart(layer);
        this.model = chest ? new ChestBoatEntityModel(part) : new BoatEntityModel(part);
    }

    @Override
    public void render(E boat, float yaw, float tickDelta, MatrixStack ms, VertexConsumerProvider vcp, int light) {
        ms.push();
        ms.translate(0.0F, 0.375F, 0.0F);
        ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F - yaw));

        float wobbleTicks = boat.getDamageWobbleTicks()    - tickDelta;
        float wobbleStrength = boat.getDamageWobbleStrength() - tickDelta;
        if (wobbleTicks > 0.0F) {
            wobbleStrength = Math.max(wobbleStrength, 0.0F);
            ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.sin(wobbleTicks) * wobbleTicks * wobbleStrength / 10.0F * boat.getDamageWobbleSide()));
        }

        float bubble = boat.interpolateBubbleWobble(tickDelta);
        if (!MathHelper.approximatelyEquals(bubble, 0.0F)) {
            ms.multiply((new Quaternionf()).setAngleAxis(bubble * ((float)Math.PI / 180F), 1.0F, 0.0F, 1.0F));
        }

        ms.scale(-1.0F, -1.0F, 1.0F);
        ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));

        String name = boat.getModBoatType().asString();
        Identifier tex;
        if (boat instanceof ModBoatEntity) {
            tex = Identifier.of(Impillagers.MOD_ID, "textures/entity/boat/" + name + ".png");
        } else if (boat instanceof ModChestBoatEntity) {
            tex = Identifier.of(Impillagers.MOD_ID, "textures/entity/chest_boat/" + name + ".png");
        } else {
            tex = null;
        }

        model.setAngles(boat, tickDelta, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vb = vcp.getBuffer(model.getLayer(tex));
        model.render(ms, vb, light, OverlayTexture.DEFAULT_UV,1,1,1,1);

        if (!boat.isSubmergedInWater() && model instanceof ModelWithWaterPatch) {
            VertexConsumer wb = vcp.getBuffer(RenderLayer.getWaterMask());
            ((ModelWithWaterPatch)model).getWaterPatch().render(ms, wb, light, OverlayTexture.DEFAULT_UV);
        }

        ms.pop();
        super.render(boat, yaw, tickDelta, ms, vcp, light);
    }

    @Override
    public Identifier getTexture(E entity) {
        return null;
    }
}