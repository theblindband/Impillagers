package com.impillagers.mod.mixin;

import com.impillagers.mod.enchantment.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Unique
    private float getFrogsLegsModifier() {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity instanceof PlayerEntity) {
            ItemStack legArmor = entity.getEquippedStack(net.minecraft.entity.EquipmentSlot.LEGS);
            if (!legArmor.isEmpty()) {
                int level = EnchantmentHelper.getLevel(ModEnchantments.FROGS_LEGS, legArmor);
                return level > 0 ? 0.21F * level : 0.0F;
            }
        }
        return 0.0F;
    }

    @Unique
    private int getLeadFallingLevel() {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity instanceof PlayerEntity) {
            ItemStack boots = entity.getEquippedStack(EquipmentSlot.FEET);
            if (!boots.isEmpty()) {
                return EnchantmentHelper.getLevel(ModEnchantments.LEAD_FALLING, boots);
            }
        }
        return 0;
    }

    @Inject(method = "getJumpVelocity", at = @At("RETURN"), cancellable = true)
    private void modifyJumpVelocity(CallbackInfoReturnable<Float> cir) {
        float original = cir.getReturnValue();
        cir.setReturnValue(original + this.getFrogsLegsModifier());
    }

    @ModifyVariable(method = "travel", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private double modifyGravity(double gravity) {
        int level = this.getLeadFallingLevel();
        if (level > 0) {
            return switch (level) {
                case 2 -> 0.24;
                case 3 -> 0.32;
                case 4 -> 0.40;
                default -> 0.16;
            };
        }
        return gravity;
    }
}