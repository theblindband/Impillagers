package com.impillagers.mod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModCommands {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    CommandManager.literal("impillagers")
                            .requires(source -> !(source.getEntity() instanceof PlayerEntity))
                            .then(CommandManager.literal("trigger_event_listener")
                                    .then(CommandManager.literal("kaboom_enchantment")
                                            // First branch: when no explicit victim argument is provided
                                            .executes(ctx -> {
                                                // If the command was invoked without a parameter,
                                                // use the executor’s entity if available.
                                                Entity victim = ctx.getSource().getEntity();
                                                ModCommandListener.dispatchEffect("kaboom_enchantment", ctx.getSource(), victim);
                                                return Command.SINGLE_SUCCESS;
                                            })
                                            // Second branch: accept a "victim" entity as parameter
                                            .then(CommandManager.argument("victim", EntityArgumentType.entity())
                                                    .executes(ctx -> {
                                                        Entity victim = EntityArgumentType.getEntity(ctx, "victim");
                                                        ModCommandListener.dispatchEffect("kaboom_enchantment", ctx.getSource(), victim);
                                                        return Command.SINGLE_SUCCESS;
                                                    })
                                            )
                                    )
                            )
            );
        });
    }
}
