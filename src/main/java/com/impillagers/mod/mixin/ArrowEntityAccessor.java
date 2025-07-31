package com.impillagers.mod.mixin;

import net.minecraft.entity.projectile.ArrowEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
//TODO: FIX
@Mixin(ArrowEntity.class)
public interface ArrowEntityAccessor {
   /* @Invoker("getPotionContents")
    PotionContentsComponent GetPotionContents();*/
}