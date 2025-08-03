package com.impillagers.mod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FuseEnchantment extends Enchantment {

    public FuseEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return super.isAcceptableItem(stack) || stack.isOf(Items.BOW) || stack.isOf(Items.CROSSBOW);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.INFINITY && other != ModEnchantments.DISTRIBUTION;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }

    public void onProjectileHit(PersistentProjectileEntity projectile, LivingEntity owner, Vec3d impactLocation) {
        if (projectile == null || projectile.getWorld().isClient) return;

        ItemStack weapon = owner.getMainHandStack();
        int fuseLevel = EnchantmentHelper.getLevel(this, weapon);
        if (fuseLevel == 0) return;

        World world = projectile.getWorld();

        // Spawn primed TNT at impact location
        TntEntity tnt = new TntEntity(world, impactLocation.x, impactLocation.y, impactLocation.z, owner);
        world.spawnEntity(tnt);

        // Play priming sound
        world.playSound(null, impactLocation.x, impactLocation.y, impactLocation.z,
                SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 5.0F, 1.0F);

        // Remove projectile
        projectile.discard();
    }
}