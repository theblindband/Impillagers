package com.impillagers.mod.util;

import com.impillagers.mod.Impillagers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
public class ModTags {
    //Custom Block and Item Tags
    public static class Blocks {
        //Block Tags
        public static final TagKey<Block> PURPLE_HEART_LOGS = createTag("purple_heart_logs");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID, name));
        }
    }
    public static class Items {
        //Item Tags
        public static final TagKey<Item> PURPLE_HEART_LOGS = createTag("purple_heart_logs");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID, name));
        }
    }
    public static class EntityTypes {
        //Entity Type Tags
        public static final TagKey<EntityType<?>> SINKING_MUD_WALKABLE_MOBS = createTag("sinking_mud_walkable_mobs");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID, name));
        }
    }
}
