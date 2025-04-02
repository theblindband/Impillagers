package com.impillagers.mod.util;

import com.impillagers.mod.Impillagers;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> PURPLE_HEART_LOGS = createTag("purple_heart_logs");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Impillagers.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> PURPLE_HEART_LOGS = createTag("purple_heart_logs");
        public static final TagKey<Item> FROG_POISONOUS_FOOD = createTag("frog_poisonous_food");

        public static final TagKey<Item> DYED_TERRACOTTA = createTag("dyed_terracotta");
        public static final TagKey<Item> STAINED_GLASS = createTag("stained_glass");
        public static final TagKey<Item> STAINED_GLASS_PANE = createTag("stained_glass_pane");
        public static final TagKey<Item> DYED_CANDLE = createTag("dyed_candle");
        public static final TagKey<Item> CONCRETE_POWDER = createTag("concrete_powder");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Impillagers.MOD_ID, name));
        }
    }

    public static class EntityTypes {

        public static final TagKey<EntityType<?>> SINKING_MUD_WALKABLE_MOBS = createTag("sinking_mud_walkable_mobs");
        public static final TagKey<EntityType<?>> IGNORE_SMELLY = createTag("ignore_smelly");
        public static final TagKey<EntityType<?>> DISTRIBUTION_ENCHANTABLE = createTag("distribution_enchantable");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Impillagers.MOD_ID, name));
        }
    }

    public static class StructureKeys {
        public static final TagKey<Structure> IMPILLAGER_VILLAGE = createTag("impillager_village");

        private static TagKey<Structure> createTag(String name) {
            return TagKey.of(RegistryKeys.STRUCTURE, Identifier.of(Impillagers.MOD_ID, name));
        }
    }
}
