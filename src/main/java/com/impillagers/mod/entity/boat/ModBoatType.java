package com.impillagers.mod.entity.boat;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

public enum ModBoatType implements StringIdentifiable {
    PURPLE_HEART("purple_heart");

    private final String name;
    private static final IntFunction<ModBoatType> BY_ID = ValueLists.createIdToValueFunction((ToIntFunction<ModBoatType>) Enum::ordinal, values(), ValueLists.OutOfBoundsHandling.ZERO);

    ModBoatType(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public static ModBoatType byId(int id) {
        return BY_ID.apply(id);
    }
}