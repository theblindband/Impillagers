package com.impillagers.mod.mixin;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.enchantment.custom.DistributionEnchantment;
import com.impillagers.mod.util.EnchantRegistryHolder;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {

    @Unique
    @Nullable
    private ItemStack distribution$storedWeaponStack = null;

    @Unique
    private int distribution$distributionLevel = 0;

    @Unique
    private boolean distribution$captured = false;

    @Inject(method = "setOwner", at = @At("TAIL"))
    private void onSetOwner(Entity owner, CallbackInfo ci) {
        if (!distribution$captured) {
            captureWeaponStack();
        }
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        handleProjectileHit(entityHitResult.getPos());
    }

    @Inject(method = "onBlockHit", at = @At("TAIL"))
    private void onBlockHit(BlockHitResult blockHitResult, CallbackInfo ci) {
        handleProjectileHit(blockHitResult.getPos());
    }

    @Unique
    private void captureWeaponStack() {
        if (distribution$captured) return;
        distribution$captured = true;

        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        Entity owner = projectile.getOwner();

        Identifier distributionId = Identifier.of(Impillagers.MOD_ID, "distribution");

        // 1. Check owner hands
        if (owner instanceof LivingEntity living) {

            ItemStack main = living.getMainHandStack();
            if (!main.isEmpty()) {
                int lvl = EnchantmentHelper.getLevel(EnchantRegistryHolder.getEntry(distributionId), main);
                if (lvl > 0) {
                    distribution$storedWeaponStack = main;
                    distribution$distributionLevel = lvl;
                }
            }

            if (distribution$distributionLevel == 0) {
                ItemStack off = living.getOffHandStack();
                if (!off.isEmpty()) {
                    int lvl = EnchantmentHelper.getLevel(EnchantRegistryHolder.getEntry(distributionId), off);
                    if (lvl > 0) {
                        distribution$storedWeaponStack = off;
                        distribution$distributionLevel = lvl;
                    }
                }
            }
        }

        // 2. Fallback: projectile item stack
        if (distribution$distributionLevel == 0) {
            ItemStack projStack = projectile.getItemStack();
            if (!projStack.isEmpty()) {
                int lvl = EnchantmentHelper.getLevel(EnchantRegistryHolder.getEntry(distributionId), projStack);
                if (lvl > 0) {
                    distribution$storedWeaponStack = projStack;
                    distribution$distributionLevel = lvl;
                }
            }
        }
    }

    @Unique
    private void handleProjectileHit(Vec3d impactLocation) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        if (projectile.getWorld().isClient) return;

        if (distribution$distributionLevel > 0 && distribution$storedWeaponStack != null) {
            DistributionEnchantment.onProjectileHit(projectile, projectile.getOwner(), impactLocation, distribution$storedWeaponStack);
        }
    }
}
