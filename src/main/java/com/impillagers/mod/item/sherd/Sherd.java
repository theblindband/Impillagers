package com.impillagers.mod.item.sherd;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class Sherd {
    private final Identifier patternId;
    private final Item sherdItem;

    public Sherd(Identifier patternId, Item sherdItem) {
        this.patternId = patternId;
        this.sherdItem = sherdItem;
    }

    public Identifier getPatternId() {
        return patternId;
    }

    public Item getSherdItem() {
        return sherdItem;
    }
}