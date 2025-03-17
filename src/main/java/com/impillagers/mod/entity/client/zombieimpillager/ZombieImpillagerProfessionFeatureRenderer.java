package com.impillagers.mod.entity.client.zombieimpillager;


import com.google.common.collect.ImmutableMap;
import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.zombieimpillager.ZombieImpillagerEntity;
import com.impillagers.mod.villager.professions.ModProfessions;
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
public class ZombieImpillagerProfessionFeatureRenderer extends FeatureRenderer<ZombieImpillagerEntity,ZombieImpillagerModel<ZombieImpillagerEntity>> {

    private static final Map<VillagerProfession, Identifier> PROFESSION_TEXTURES = ImmutableMap.ofEntries(

            Map.entry(VillagerProfession.NONE, Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/professions/unemployed.png")),
            Map.entry(VillagerProfession.NITWIT, Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/professions/nitwit.png")),
            Map.entry(ModProfessions.DUNG_COLLECTOR, Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/professions/dung_collector.png")),
            Map.entry(ModProfessions.BANKER, Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/professions/banker.png")),
            Map.entry(ModProfessions.MUSICIAN, Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/professions/musician.png"))
    );

    public ZombieImpillagerProfessionFeatureRenderer(FeatureRendererContext<ZombieImpillagerEntity, ZombieImpillagerModel<ZombieImpillagerEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int light,
            ZombieImpillagerEntity zombieImpillagerEntity,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float animationProgress,
            float headYaw,
            float headPitch
    ) {

        VillagerProfession impillagerProfession = zombieImpillagerEntity.getVillagerData().getProfession();

            Identifier identifier = PROFESSION_TEXTURES.get(impillagerProfession);
            if (identifier != null) {
                renderModel(this.getContextModel(), identifier, matrixStack, vertexConsumerProvider, light, zombieImpillagerEntity, -1);
            }
    }
}

