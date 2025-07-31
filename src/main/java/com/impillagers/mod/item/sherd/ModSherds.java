package com.impillagers.mod.item.sherd;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.item.ModItems;
//import net.minecraft.block.DecoratedPotPattern;
import net.minecraft.item.Item;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//TODO: FIX
public final class ModSherds {
    /*private static final List<Sherd> ALL_SHERDS = new ArrayList<>();
    private static final Map<Item, RegistryKey<DecoratedPotPattern>> SHERD_TO_PATTERN = new HashMap<>();

    public static final Sherd IMP_SHERD   = addSherd(id("imp_pottery_pattern"),   ModItems.IMP_POTTERY_SHERD);
    public static final Sherd COVER_SHERD = addSherd(id("cover_pottery_pattern"), ModItems.COVER_POTTERY_SHERD);

    private static Sherd addSherd(Identifier id, Item item) {
        RegistryKey<DecoratedPotPattern> key = RegistryKey.of(RegistryKeys.DECORATED_POT_PATTERN, id);
        SHERD_TO_PATTERN.put(item, key);
        ALL_SHERDS.add(new Sherd(id, item));
        return new Sherd(id, item);
    }

    public static Map<Item, RegistryKey<DecoratedPotPattern>> getSherdToPattern() {
        return SHERD_TO_PATTERN;
    }

    public static void registerSherds() {
        for (Sherd sherd : ALL_SHERDS) {
            String name = sherd.getPatternId().getPath();

            Identifier regId = Identifier.of(Impillagers.MOD_ID, name);

            Registry.register(Registries.DECORATED_POT_PATTERN, regId, new DecoratedPotPattern(sherd.getPatternId()));
        }
    }

    private static Identifier id(String path) {
        return Identifier.of(Impillagers.MOD_ID, path);
    }*/
}
