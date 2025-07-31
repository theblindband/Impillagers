package com.impillagers.mod.item;


import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {

    public static final FoodComponent SPIDER_PIEYE = new FoodComponent.Builder().hunger(8).saturationModifier(0.3F)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 300, 0), 0.5F).build();

}
