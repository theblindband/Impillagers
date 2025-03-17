package com.impillagers.mod.mixin;

import com.impillagers.mod.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class SmellyEffectMixin {

	@Inject(method = "prepareOffersFor", at = @At("TAIL"))
	private void addHeroOfTheVillageDiscount(PlayerEntity player, CallbackInfo ci) {
		if (player.hasStatusEffect(ModEffects.SMELLY)) {
			StatusEffectInstance statusEffectInstance = player.getStatusEffect(ModEffects.SMELLY);
			assert statusEffectInstance != null;

			for (TradeOffer tradeOffer : ((VillagerEntity) (Object) this).getOffers()) {
				double d = 0.3 + 0.0625;
				int k = (int) Math.floor(d * tradeOffer.getOriginalFirstBuyItem().getCount());
				tradeOffer.increaseSpecialPrice(+Math.max(k, 5));
			}
		}
	}
}