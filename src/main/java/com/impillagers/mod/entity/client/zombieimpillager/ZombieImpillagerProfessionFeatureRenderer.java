package com.impillagers.mod.entity.client.zombieimpillager;

import com.impillagers.mod.entity.custom.zombieimpillager.ZombieImpillagerEntity;
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
//TODO: the ints for renderModel are maybe wrong?
@Environment(EnvType.CLIENT)
public class ZombieImpillagerProfessionFeatureRenderer extends FeatureRenderer<ZombieImpillagerEntity, ZombieImpillagerModel<ZombieImpillagerEntity>> {

    public ZombieImpillagerProfessionFeatureRenderer(FeatureRendererContext<ZombieImpillagerEntity, ZombieImpillagerModel<ZombieImpillagerEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider,
                       int light,
                       ZombieImpillagerEntity zombieImpillagerEntity,
                       float limbAngle,
                       float limbDistance,
                       float tickDelta,
                       float animationProgress,
                       float headYaw,
                       float headPitch) {

        VillagerProfession profession = zombieImpillagerEntity.getVillagerData().getProfession();
        Identifier professionId = Registries.VILLAGER_PROFESSION.getId(profession);
        String key = professionId.toString();

        Identifier textureIdentifier = ImpillagerProfessionHandler.getZombieTextureForProfession(key);
        renderModel(this.getContextModel(), textureIdentifier, matrixStack, vertexConsumerProvider, light, zombieImpillagerEntity, 1,1,1);
    }
}
