package com.impillagers.mod.mixin;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
//TODO: FIX
@Mixin(PersistentProjectileEntity.class)
public interface PersistentProjectileEntityAccessor {
   /* @Accessor("weapon")
    ItemStack getWeapon();*/
}