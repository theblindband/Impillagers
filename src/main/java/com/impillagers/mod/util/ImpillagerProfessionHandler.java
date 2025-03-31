package com.impillagers.mod.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.impillagers.mod.Impillagers;
import net.minecraft.util.Identifier;

import java.io.InputStreamReader;
import java.util.*;

public class ImpillagerProfessionHandler {
    private static final String CONFIG_PATH = "/data/impillagers/entity/impillager/impillager_professions.json";
    private static final Set<String> VALID_POI_SET = new HashSet<>();

    private static final Map<String, ProfessionTexturePair> PROFESSION_TEXTURES = new HashMap<>();

    private static final Identifier DEFAULT_FALLBACK_TEXTURE;

    public record ProfessionTexturePair(Identifier impillager, Identifier zombie) {
    }

    static {
        try (InputStreamReader reader = new InputStreamReader(
                Objects.requireNonNull(ImpillagerProfessionHandler.class.getResourceAsStream(CONFIG_PATH)))) {

            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            if (jsonObject.has("poi_blocks") && jsonObject.get("poi_blocks").isJsonArray()) {
                JsonArray poiArray = jsonObject.getAsJsonArray("poi_blocks");
                poiArray.forEach(element -> {
                    if (element.isJsonPrimitive()) {
                        String poiString = element.getAsString();
                        try {
                            Identifier poiId = Identifier.of(poiString);
                            VALID_POI_SET.add(poiId.toString());
                        } catch (Exception e) {
                            Impillagers.LOGGER.error("Invalid POI identifier detected in JSON: {}", poiString);
                        }
                    }
                });
            } else {
                Impillagers.LOGGER.error("Warning: 'poi_blocks' is missing or not an array in " + CONFIG_PATH);
            }

            if (jsonObject.has("profession_textures") && jsonObject.get("profession_textures").isJsonObject()) {
                JsonObject textures = jsonObject.getAsJsonObject("profession_textures");
                for (Map.Entry<String, com.google.gson.JsonElement> entry : textures.entrySet()) {
                    String key = entry.getKey();
                    try {
                        Identifier.of(key);
                    } catch (Exception e) {
                        Impillagers.LOGGER.error("Invalid profession key detected in JSON: {}", key);
                        continue;
                    }
                    if (!entry.getValue().isJsonObject()) {
                        Impillagers.LOGGER.error("Expected an object for profession key {} but found: {}", key, entry.getValue());
                        continue;
                    }
                    JsonObject texturePairObj = entry.getValue().getAsJsonObject();

                    Identifier impTexture;
                    Identifier zombieTexture;

                    if (texturePairObj.has("impillager") && texturePairObj.get("impillager").isJsonPrimitive()) {
                        String impStr = texturePairObj.get("impillager").getAsString();
                        try {
                            impTexture = Identifier.of(impStr);
                        } catch (Exception e) {
                            Impillagers.LOGGER.error("Invalid impillager texture identifier for key {}: {}. Falling back to nitwit texture.", key, impStr);
                            impTexture = null;
                        }
                    } else {
                        Impillagers.LOGGER.error("Missing 'impillager' texture for key {}. Falling back to nitwit texture.", key);
                        impTexture = null;
                    }

                    if (texturePairObj.has("zombieimpillager") && texturePairObj.get("zombieimpillager").isJsonPrimitive()) {
                        String zomStr = texturePairObj.get("zombieimpillager").getAsString();
                        try {
                            zombieTexture = Identifier.of(zomStr);
                        } catch (Exception e) {
                            Impillagers.LOGGER.error("Invalid zombieimpillager texture identifier for key {}: {}. Falling back to nitwit texture.", key, zomStr);
                            zombieTexture = null;
                        }
                    } else {
                        Impillagers.LOGGER.error("Missing 'zombieimpillager' texture for key {}. Falling back to nitwit texture.", key);
                        zombieTexture = null;
                    }
                    PROFESSION_TEXTURES.put(key, new ProfessionTexturePair(impTexture, zombieTexture));
                }
            } else {
                Impillagers.LOGGER.error("Warning: 'profession_textures' is missing or not an object in " + CONFIG_PATH);
            }
        } catch (Exception e) {
            Impillagers.LOGGER.error("Error reading configuration from " + CONFIG_PATH, e);
        }

        ProfessionTexturePair nitwitPair = PROFESSION_TEXTURES.get("minecraft:nitwit");
        if (nitwitPair != null && nitwitPair.impillager != null) {
            DEFAULT_FALLBACK_TEXTURE = nitwitPair.impillager;
        } else {
            DEFAULT_FALLBACK_TEXTURE = Identifier.of("impillagers", "textures/entity/impillager/professions/nitwit.png");
            Impillagers.LOGGER.error("Cannot find valid nitwit texture in JSON, falling back to hard-coded default: {}", DEFAULT_FALLBACK_TEXTURE);
        }
    }

    public static Set<String> getValidPoiSet() {
        return VALID_POI_SET;
    }

    public static Identifier getImpillagerTextureForProfession(String professionKey) {
        ProfessionTexturePair pair = PROFESSION_TEXTURES.get(professionKey);
        if (pair == null || pair.impillager == null) {
            pair = PROFESSION_TEXTURES.get("minecraft:nitwit");
            if (pair == null || pair.impillager == null) {
                return DEFAULT_FALLBACK_TEXTURE;
            }
            return pair.impillager;
        }
        return pair.impillager;
    }

    public static Identifier getZombieTextureForProfession(String professionKey) {
        ProfessionTexturePair pair = PROFESSION_TEXTURES.get(professionKey);
        if (pair == null || pair.zombie == null) {
            pair = PROFESSION_TEXTURES.get("minecraft:nitwit");
            if (pair == null || pair.zombie == null) {
                return DEFAULT_FALLBACK_TEXTURE;
            }
            return pair.zombie;
        }
        return pair.zombie;
    }

    public static Set<String> getProfessionKeys() {
        return java.util.Collections.unmodifiableSet(PROFESSION_TEXTURES.keySet());
    }
}
