package com.impillagers.mod.entity.models;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.CarnivourusPlantEntity;
import com.impillagers.mod.entity.custom.ImpillagerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CarnivourusPlantModel extends GeoModel<CarnivourusPlantEntity> {
    //This model is using placeholders while waiting for the model files
    @Override
    public ResourceLocation getModelResource(CarnivourusPlantEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID,"geo/entity/carnivourus_plant.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CarnivourusPlantEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID,"textures/entity/carnivourus_plant.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CarnivourusPlantEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID,"animations/entity/carnivourus_plant.animation.json");
    }
}
