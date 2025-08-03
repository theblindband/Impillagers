package com.impillagers.mod.mixin;

import com.impillagers.mod.enchantment.DistributionEnchantment;
import com.impillagers.mod.enchantment.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {

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
        if (projectile.getWorld().isClient || projectile.getOwner() == null) return;

        if (!(projectile.getOwner() instanceof LivingEntity owner)) return;

        ItemStack weapon = owner.getMainHandStack();
        int distributionLevel = EnchantmentHelper.getLevel(ModEnchantments.DISTRIBUTION, weapon);
        if (distributionLevel == 0) return;

        DistributionEnchantment enchantment = ModEnchantments.DISTRIBUTION;
        enchantment.onProjectileHit(projectile, owner, impactLocation);
    }
}