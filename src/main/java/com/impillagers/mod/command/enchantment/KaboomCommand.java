// File: KaboomCommand.java
package com.impillagers.mod.command.enchantment;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.command.ModCommandListener;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Set;

public class KaboomCommand implements ModCommandListener.IEffectHandler {

    private int kaboomLevel = 1;

    @Override
    public void handleEffect(ServerCommandSource source, @Nullable Entity victim) {

        if (victim instanceof ArrowEntity) {
            victim = null;
        }
        if (!(source.getEntity() instanceof ArrowEntity arrow)) {
            Impillagers.LOGGER.warn("Kaboom Enchantment: Kaboom was triggered by non arrow/null source");
            return;
        }
        ItemStack bow = findBow(arrow);
        if (bow == null) {
            Impillagers.LOGGER.warn("Kaboom Enchantment: Bow Not Found, Assuming Level 1 Kaboom with no other enchantments");

            arrow.getWorld().createExplosion(arrow.getOwner(), arrow.getX(), arrow.getY(), arrow.getZ(),2.0f, false, World.ExplosionSourceType.NONE);

        } else {
            Impillagers.LOGGER.info("Kaboom Enchantment: Found bow with Kaboom enchantment: {}", bow);
            checkCombos(arrow, bow, arrow.getOwner(), victim);
        }
        arrow.kill();
    }

    private void checkCombos(ArrowEntity arrow, ItemStack bow, Entity owner, Entity victim) {
        float[] powerMapping = {0f, 2f, 3.5f, 6f};

        float radius;
        if (kaboomLevel >= 1 && kaboomLevel <= 3) {
            radius = powerMapping[kaboomLevel];
        } else {
            radius = 2f;
        }

        double centerX, centerY, centerZ;
        // Get a reference to the world for convenience.
        World world = arrow.getWorld();

        if (victim != null) {
            // Determine the block right below the victim.
            // Using a slight offset (0.1) ensures we get the block the entity is standing on.
            BlockPos blockBelow = new BlockPos((int) victim.getX(), (int) (victim.getY() - 0.1), (int) victim.getZ());

            // If the block below is not air, use it as the explosion center (centered within the block).
            if (!world.getBlockState(blockBelow).isAir()) {
                centerX = blockBelow.getX() + 0.5;
                centerY = blockBelow.getY();
                centerZ = blockBelow.getZ() + 0.5;
            } else {
                // Fallback: use the victim's exact location.
                centerX = victim.getX();
                centerY = victim.getY();
                centerZ = victim.getZ();
            }
        } else {
            // If there is no victim, use the arrow's landing block position.
            BlockPos arrowPos = arrow.getBlockPos();
            centerX = arrowPos.getX() + 0.5;
            centerY = arrowPos.getY();
            centerZ = arrowPos.getZ() + 0.5;
        }

        // Create the explosion at the computed center.
        world.createExplosion(
                owner,
                centerX,
                centerY,
                centerZ,
                radius,
                false,
                World.ExplosionSourceType.NONE
        );

        // Process the flame enchantment effects if applicable.
        if (hasEnchantment(bow, "minecraft:flame", 1).hasEnchantment) {
            if (world instanceof ServerWorld serverWorld) {
                // Convert the explosion center to a BlockPos for the fire loops.
                BlockPos centerBlockPos = new BlockPos((int) centerX, (int) centerY, (int) centerZ);
                int intRadius = (int) Math.ceil(radius);

                // Loop through a circular area to set fire to valid positions.
                for (int dx = -intRadius; dx <= intRadius; dx++) {
                    for (int dz = -intRadius; dz <= intRadius; dz++) {
                        if (dx * dx + dz * dz <= radius * radius) {
                            for (int dy = -1; dy <= 1; dy++) {
                                BlockPos targetPos = centerBlockPos.add(dx, dy, dz);
                                // Set fire if the target position is air and the block below isn't air.
                                if (serverWorld.getBlockState(targetPos).isAir() && !serverWorld.getBlockState(targetPos.down()).isAir()) {
                                    serverWorld.setBlockState(targetPos, Blocks.FIRE.getDefaultState(), 3);
                                }
                            }
                        }
                    }
                }

                // Define an area for additional flame effects.
                Box area = new Box(
                        centerX - radius, centerY - radius, centerZ - radius,
                        centerX + radius, centerY + radius, centerZ + radius
                );

                // Set any nearby entities (except the owner) on fire.
                for (Entity entity : serverWorld.getEntitiesByClass(Entity.class, area, (e) -> true)) {
                    entity.setOnFireFor(5);
                }
            }
        }
    }



    private ItemStack findBow(ArrowEntity arrow) {
        ItemStack bow = null;
        if (arrow.getOwner() != null) {
            if (arrow.getOwner() instanceof PlayerEntity player) {
                ItemStack mainHand = player.getMainHandStack();
                if (!mainHand.isEmpty() &&
                        mainHand.getItem() instanceof BowItem &&
                        hasEnchantment(mainHand, "impillagers:kaboom", 1).hasEnchantment) {
                    bow = mainHand;
                } else {
                    ItemStack offHand = player.getOffHandStack();
                    if (!offHand.isEmpty() &&
                            offHand.getItem() instanceof BowItem &&
                            hasEnchantment(offHand, "impillagers:kaboom", 1).hasEnchantment) {
                        bow = offHand;
                    } else {
                        PlayerInventory inventory = player.getInventory();
                        for (int slot = 0; slot < 9; slot++) {
                            ItemStack slotStack = inventory.getStack(slot);
                            if (!slotStack.isEmpty() &&
                                    slotStack.getItem() instanceof BowItem &&
                                    hasEnchantment(slotStack, "impillagers:kaboom", 1).hasEnchantment) {
                                bow = slotStack;
                                break;
                            }
                        }
                        if (bow == null) {
                            for (int slot = 9; slot < inventory.size(); slot++) {
                                ItemStack slotStack = inventory.getStack(slot);
                                if (!slotStack.isEmpty() &&
                                        slotStack.getItem() instanceof BowItem &&
                                        hasEnchantment(slotStack, "impillagers:kaboom", 1).hasEnchantment) {
                                    bow = slotStack;
                                    break;
                                }
                            }
                        }
                    }
                }
            } else if (arrow.getOwner() instanceof LivingEntity livingEntity) {
                ItemStack handItem = livingEntity.getMainHandStack();
                if (!handItem.isEmpty() &&
                        handItem.getItem() instanceof BowItem &&
                        hasEnchantment(handItem, "impillagers:kaboom", 1).hasEnchantment) {
                    bow = handItem;
                }
            }
        }
        return bow;
    }

    private record EnchantmentCheckResult(boolean hasEnchantment, int level) {
    }

    private EnchantmentCheckResult hasEnchantment(ItemStack stack, String enchantmentId, int requiredLevel) {
        int levelFound = 0;

        if (!stack.isEmpty()) {
            Set<Object2IntMap.Entry<RegistryEntry<Enchantment>>> entries = stack.getEnchantments().getEnchantmentEntries();
            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : entries) {
                RegistryEntry<Enchantment> enchantmentEntry = entry.getKey();
                int level = entry.getIntValue();
                Optional<RegistryKey<Enchantment>> maybeKey = enchantmentEntry.getKey();
                if (maybeKey.isPresent()) {
                    Identifier foundId = maybeKey.get().getValue();
                    if (foundId.toString().equals(enchantmentId)) {
                        levelFound = level;
                        break;
                    }
                }
            }
        }

        if ("impillagers:kaboom".equals(enchantmentId)) {
            kaboomLevel = levelFound;
        }

        boolean result = levelFound >= requiredLevel;
        return new EnchantmentCheckResult(result, levelFound);
    }

}
