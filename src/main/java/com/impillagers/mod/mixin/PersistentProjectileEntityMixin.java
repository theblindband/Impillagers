package com.impillagers.mod.mixin;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.enchantment.custom.DistributionEnchantment;
import com.impillagers.mod.util.EnchantRegistryHolder;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {

    @Shadow public abstract ItemStack getWeaponStack();

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        handleProjectileHit(entityHitResult.getPos());
    }

    @Inject(method = "onBlockHit", at = @At("TAIL"))
    private void onBlockHit(BlockHitResult blockHitResult, CallbackInfo ci) {
        handleProjectileHit(blockHitResult.getPos());
    }

    @Unique
    private void handleProjectileHit(Vec3d impactLocation) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        if (projectile.getWorld().isClient) return;

        if (EnchantmentHelper.getLevel(EnchantRegistryHolder.getEntry(Identifier.of(Impillagers.MOD_ID, "distribution")), getWeaponStack()) > 0) {
            DistributionEnchantment.onProjectileHit(projectile, projectile.getOwner(), impactLocation, getWeaponStack());
        }
    }
}