package com.impillagers.mod.entity.custom.impillager;

import com.impillagers.mod.Impillagers;
import net.minecraft.util.Identifier;

import java.util.*;

public class ImpillagerTextures {
    public static final Map<String, Integer> TEXTURE_WEIGHTS = Map.of(
            "grey", 30,
            "black", 30,
            "brown", 30,
            "blue", 20,
            "green", 20,
            "red", 1
    );

    public static final Map<String, Identifier> TEXTURE_MAP = Map.of(
            "grey", Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/skin_tones/grey.png"),
            "black", Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/skin_tones/black.png"),
            "brown", Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/skin_tones/brown.png"),
            "blue", Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/skin_tones/blue.png"),
            "green", Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/skin_tones/green.png"),
            "red", Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/skin_tones/red.png")
    );

    public static String selectRandomTextureKey() {
        int totalWeight = TEXTURE_WEIGHTS.values().stream().mapToInt(Integer::intValue).sum();
        int randomWeight = new Random().nextInt(totalWeight);

        for (Map.Entry<String, Integer> entry : TEXTURE_WEIGHTS.entrySet()) {
            randomWeight -= entry.getValue();
            if (randomWeight < 0) {
                return entry.getKey();
            }
        }
        return TEXTURE_WEIGHTS.keySet().iterator().next(); // Fallback
    }

    public static Optional<Identifier> getTextureByKey(String key) {
        return Optional.ofNullable(TEXTURE_MAP.get(key));
    }
}
