package com.impillagers.mod.mixin.accessor;

import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.projectile.ArrowEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ArrowEntity.class)
public interface ArrowEntityAccessor {
    @Invoker("getPotionContents")
    PotionContentsComponent GetPotionContents();
}