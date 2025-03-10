package com.impillagers.mod.entity.client;


import com.google.common.collect.ImmutableMap;
import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.ImpillagerEntity;
import com.impillagers.mod.villager.professions.Banker;
import com.impillagers.mod.villager.professions.DungCollector;
import com.impillagers.mod.villager.professions.Musician;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class ImpillagerProfessionFeatureRenderer extends FeatureRenderer<ImpillagerEntity,ImpillagerModel<ImpillagerEntity>> {

    private static final Map<VillagerProfession, Identifier> PROFESSION_TEXTURES = ImmutableMap.ofEntries(

            Map.entry(VillagerProfession.NONE, Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/professions/unemployed.png")),
            Map.entry(VillagerProfession.NITWIT, Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/professions/nitwit.png")),
            Map.entry(DungCollector.DUNG_COLLECTOR, Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/professions/dung_collector.png")),
            Map.entry(Banker.BANKER, Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/professions/banker.png")),
            Map.entry(Musician.MUSICIAN, Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/professions/musician.png"))
    );

    public ImpillagerProfessionFeatureRenderer(FeatureRendererContext<ImpillagerEntity, ImpillagerModel<ImpillagerEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int light,
            ImpillagerEntity impillagerEntity,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float animationProgress,
            float headYaw,
            float headPitch
    ) {

        VillagerProfession impillagerProfession = impillagerEntity.getVillagerData().getProfession();

            Identifier identifier = (Identifier) PROFESSION_TEXTURES.get(impillagerProfession);
            renderModel(this.getContextModel(), identifier, matrixStack, vertexConsumerProvider, light, impillagerEntity, -1);
    }
}

