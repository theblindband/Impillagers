package com.impillagers.mod.command;

import com.mojang.brigadier.Command;
import net.minecraft.server.command.CommandManager;
import net.minecraft.entity.player.PlayerEntity;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModCommands {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    CommandManager.literal("impillagers")
                            .requires(source -> !(source.getEntity() instanceof PlayerEntity))
                            .then(CommandManager.literal("trigger_event_listener")
                                    .then(CommandManager.literal("distribution_enchantment")
                                            .executes(ctx -> {
                                                ModCommandListener.dispatchEffect("distribution_enchantment", ctx.getSource());
                                                return Command.SINGLE_SUCCESS;
                                            })
                                    )
                            )
            );
        });
    }
}
