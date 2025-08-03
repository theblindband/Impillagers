package com.impillagers.mod.mixin;

import com.impillagers.mod.enchantment.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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

    @Inject(method = "getJumpVelocity", at = @At("RETURN"), cancellable = true)
    private void modifyJumpVelocity(CallbackInfoReturnable<Float> cir) {
        float original = cir.getReturnValue();
        cir.setReturnValue(original + this.getFrogsLegsModifier());
    }
}