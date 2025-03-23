package com.impillagers.mod.command;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.command.enchantment.KaboomCommand;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import org.jetbrains.annotations.Nullable;  // or javax.annotation.Nullable
import java.util.HashMap;
import java.util.Map;

public class ModCommandListener {

    public interface IEffectHandler {
        /**
         * Handle the effect for the given key.
         * @param source the command source
         * @param victim an optional entity parameter (for example, the arrow hit target).
         */
        void handleEffect(ServerCommandSource source, @Nullable Entity victim);
    }

    private static final Map<String, IEffectHandler> handlerRegistry = new HashMap<>();

    public static void registerHandler(String key, IEffectHandler handler) {
        handlerRegistry.put(key, handler);
        Impillagers.LOGGER.info("Registered effect handler for key: {}", key);
    }

    public static void dispatchEffect(String key, ServerCommandSource source, @Nullable Entity victim) {
        IEffectHandler handler = handlerRegistry.get(key);
        if (handler != null) {
            handler.handleEffect(source, victim);
        } else {
            Impillagers.LOGGER.error("No effect handler registered for key: {}", key);
        }
    }

    public static void registerListeners() {
        registerHandler("kaboom_enchantment", new KaboomCommand());
    }
}
