package com.impillagers.mod.entity.client.impillager;

import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import com.impillagers.mod.util.ImpillagerProfessionHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;

@Environment(EnvType.CLIENT)
public class ImpillagerProfessionFeatureRenderer extends FeatureRenderer<ImpillagerEntity, ImpillagerModel<ImpillagerEntity>> {

    public ImpillagerProfessionFeatureRenderer(FeatureRendererContext<ImpillagerEntity, ImpillagerModel<ImpillagerEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider,
                       int light,
                       ImpillagerEntity impillagerEntity,
                       float limbAngle,
                       float limbDistance,
                       float tickDelta,
                       float animationProgress,
                       float headYaw,
                       float headPitch) {

        VillagerProfession profession = impillagerEntity.getVillagerData().getProfession();
        Identifier professionId = Registries.VILLAGER_PROFESSION.getId(profession);
        String key = professionId.toString();

        Identifier textureIdentifier = ImpillagerProfessionHandler.getImpillagerTextureForProfession(key);
        renderModel(this.getContextModel(), textureIdentifier, matrixStack, vertexConsumerProvider, light, impillagerEntity, -1);
    }
}
