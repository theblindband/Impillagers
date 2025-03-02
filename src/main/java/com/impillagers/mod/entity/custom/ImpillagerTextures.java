package com.impillagers.mod.entity.custom;

import com.impillagers.mod.Impillagers;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Random;

public class ImpillagerTextures {
    public static final Map<Identifier, Integer> TEXTURE_WEIGHTS = Map.of(
            Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/impillager.png"), 50,
            Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/impillager2.png"), 30,
            Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/impillager3.png"), 20
    );

    public static Identifier selectRandomTexture() {
        int totalWeight = TEXTURE_WEIGHTS.values().stream().mapToInt(Integer::intValue).sum();
        int randomWeight = new Random().nextInt(totalWeight);

        for (Map.Entry<Identifier, Integer> entry : TEXTURE_WEIGHTS.entrySet()) {
            randomWeight -= entry.getValue();
            if (randomWeight < 0) {
                return entry.getKey();
            }
        }
        return TEXTURE_WEIGHTS.keySet().iterator().next(); // Fallback
    }
}
