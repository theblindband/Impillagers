package com.impillagers.mod.entity.models;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.entity.custom.ImpillagerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ImpillagerModel extends GeoModel<ImpillagerEntity> {
    //This model is using placeholders while waiting for the model files
    @Override
    public ResourceLocation getModelResource(ImpillagerEntity animatable) {
        //return ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID,"geo/models/entity/impillager/impillager.geo.json");
        return ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID,"geo/item/armor/frog_mask.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ImpillagerEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID,"textures/entity/impillager/impillager.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ImpillagerEntity animatable) {
        //return ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID,"animations/entity/impillager/impillager.animation.json");
        return ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID,"animations/item/armor/frog_mask.animation.json");
    }
}
