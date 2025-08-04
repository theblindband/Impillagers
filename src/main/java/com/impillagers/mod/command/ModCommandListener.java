package com.impillagers.mod.command;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.command.enchantment.DistributionCommand;
import net.minecraft.server.command.ServerCommandSource;
import java.util.HashMap;
import java.util.Map;

public class ModCommandListener {

    public interface IEffectHandler {
        void handleEffect(ServerCommandSource source);
    }

    private static final Map<String, IEffectHandler> handlerRegistry = new HashMap<>();

    public static void registerHandler(String key, IEffectHandler handler) {
        handlerRegistry.put(key, handler);
        Impillagers.LOGGER.debug("Registered effect handler for key: {}", key);
    }

    public static void dispatchEffect(String key, ServerCommandSource source) {
        IEffectHandler handler = handlerRegistry.get(key);
        if (handler != null) {
            handler.handleEffect(source);
        } else {
            Impillagers.LOGGER.error("No effect handler registered for key: {}", key);
        }
    }

    public static void registerListeners() {
        registerHandler("distribution_enchantment", new DistributionCommand());
    }
}
