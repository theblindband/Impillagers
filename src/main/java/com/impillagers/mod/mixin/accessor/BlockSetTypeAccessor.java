package com.impillagers.mod.mixin.accessor;

import net.minecraft.block.BlockSetType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockSetType.class)
public interface BlockSetTypeAccessor {
    @Invoker("register")
    static BlockSetType invokeRegister(BlockSetType type) {
        throw new AssertionError();
    }
}