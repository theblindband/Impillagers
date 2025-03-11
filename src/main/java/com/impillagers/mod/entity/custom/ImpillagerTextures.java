package com.impillagers.mod.entity.custom;

import com.impillagers.mod.Impillagers;
import net.minecraft.util.Identifier;

import java.util.*;

public class ImpillagerTextures {
    public static final Map<String, Integer> TEXTURE_WEIGHTS = Map.of(
            "grey_impillager", 30,
            "black_impillager", 30,
            "brown_impillager", 30,
            "blue_impillager", 20,
            "green_impillager", 20,
            "red_impillager", 1
    );

    public static final Map<String, Identifier> TEXTURE_MAP = Map.of(
            "grey_impillager", Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/grey_impillager.png"),
            "black_impillager", Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/black_impillager.png"),
            "brown_impillager", Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/brown_impillager.png"),
            "blue_impillager", Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/blue_impillager.png"),
            "green_impillager", Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/green_impillager.png"),
            "red_impillager", Identifier.of(Impillagers.MOD_ID, "textures/entity/impillager/red_impillager.png")
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
