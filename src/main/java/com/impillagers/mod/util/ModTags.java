package com.impillagers.mod.util;

import com.impillagers.mod.Impillagers;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> PURPLE_HEART_LOGS = createTag("purple_heart_logs");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Impillagers.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> PURPLE_HEART_LOGS = createTag("purple_heart_logs");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Impillagers.MOD_ID, name));
        }
    }

    public static class EntityTypes {

        public static final TagKey<EntityType<?>> SINKING_MUD_WALKABLE_MOBS = createTag("sinking_mud_walkable_mobs");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Impillagers.MOD_ID, name));
        }
    }
}
