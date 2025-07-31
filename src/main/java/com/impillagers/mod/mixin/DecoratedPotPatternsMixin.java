package com.impillagers.mod.mixin;
//TODO: FIX
/*

import com.impillagers.mod.item.sherd.ModSherds;
import net.minecraft.block.DecoratedPotPattern;
import net.minecraft.block.DecoratedPotPatterns;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DecoratedPotPatterns.class)
public class DecoratedPotPatternsMixin {
    @Inject(
            method = "fromSherd",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void imp$getPatternFromSherd(Item sherd, CallbackInfoReturnable<RegistryKey<DecoratedPotPattern>> cir) {
        var map  = ModSherds.getSherdToPattern();
        var key  = map.get(sherd);
        if (key != null) {
            cir.setReturnValue(key);
            cir.cancel();
        }
    }
}
*/
