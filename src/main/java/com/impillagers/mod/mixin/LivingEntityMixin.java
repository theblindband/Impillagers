package com.impillagers.mod.mixin;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.util.EnchantRegistryHolder;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @ModifyReturnValue(method = "computeFallDamage", at = @At("RETURN"))
    private int modifyFallDamage(int damage) {
        LivingEntity entity = (LivingEntity) (Object) this;
        World world = entity.getWorld();

        if (world.isClient || damage <= 0) return damage;

        ItemStack boots = entity.getEquippedStack(EquipmentSlot.FEET);
        if (boots.isEmpty()) return damage;
        int level = EnchantmentHelper.getLevel(EnchantRegistryHolder.getEntry(Identifier.of(Impillagers.MOD_ID, "lead_falling")), boots);
        if (level <= 0) return damage;

        float power = Math.min(10.0f, damage / 4.0f);
        world.createExplosion(entity, entity.getX(), entity.getY(), entity.getZ(), power, World.ExplosionSourceType.MOB);

        return damage;
    }
}