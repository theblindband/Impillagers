package com.impillagers.mod.effect;

import com.impillagers.mod.Impillagers;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {


    public static final RegistryEntry<StatusEffect> SMELLY = registerStatusEffect("smelly",
            new SmellyEffect(StatusEffectCategory.NEUTRAL, 0x36ebab));
    public static final RegistryEntry<StatusEffect> CALL_OF_THE_IMPS = registerStatusEffect("call_of_the_imps",
            new CallOfTheImpsEffect(StatusEffectCategory.NEUTRAL, 0x36ebab));

    private  static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Impillagers.MOD_ID, name), statusEffect);
    }

    public static void registerModEffects(){

    }
}
