package com.impillagers.mod.enchantment.custom;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.mixin.ArrowEntityAccessor;
import com.impillagers.mod.util.EnchantRegistryHolder;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class DistributionEnchantment {

    public static void onProjectileHit(PersistentProjectileEntity projectile, Entity owner, Vec3d impactLocation, ItemStack weapon) {
        if (projectile == null || projectile.getWorld().isClient) return;

        int distributionLevel = EnchantmentHelper.getLevel(EnchantRegistryHolder.getEntry(Identifier.of(Impillagers.MOD_ID, "distribution")), weapon);
        if (distributionLevel == 0) return;

        World world = projectile.getWorld();
        float radius = getRadius(distributionLevel);
        Box area = calculateEffectArea(impactLocation.x, impactLocation.y, impactLocation.z, radius);

        // Apply damage to entities in area
        for (LivingEntity entity : world.getEntitiesByClass(LivingEntity.class, area, e -> true)) {
            if (entity != owner) {
                float damage = calculateDamage(weapon);
                entity.damage(projectile.getDamageSources().arrow(projectile, owner), damage);
            }
        }

        // Handle potion effects
        if (projectile instanceof ArrowEntity) {
            List<StatusEffectInstance> effects = getPotionEffects((ArrowEntity) projectile);
            if (!effects.isEmpty()) {
                applyLingeringPotion(projectile, effects, radius, owner, world, impactLocation);
                applyPotionEffectsToEntities(world, area, effects);
            }
        }

        // Handle combo effects
        checkCombos(weapon, radius, impactLocation, world, area);

        // Remove arrow after effect
        if (projectile instanceof ArrowEntity) {
            projectile.kill();
        }
    }

    public void onMeleeHit(LivingEntity attacker, LivingEntity target, int level) {
        if (attacker.getWorld().isClient) return;

        ItemStack weapon = attacker.getMainHandStack();
        Vec3d impactLocation = target.getPos();
        World world = attacker.getWorld();
        float radius = getRadius(level);
        Box area = calculateEffectArea(impactLocation.x, impactLocation.y, impactLocation.z, radius);

        // Apply damage to entities in area
        for (LivingEntity entity : world.getEntitiesByClass(LivingEntity.class, area, e -> true)) {
            if (entity != attacker && entity != target) {
                float damage = calculateDamage(weapon);
                entity.damage(attacker.getDamageSources().mobAttack(attacker), damage);
            }
        }

        // Handle combo effects
        checkCombos(weapon, radius, impactLocation, world, area);
    }

    private static float getRadius(int level) {
        return level == 2 ? 3f : level == 3 ? 4f : 2f;
    }

    private static float calculateDamage(ItemStack weapon) {
        return Math.round((float) (1.5 + (1.25 * (EnchantmentHelper.getLevel(EnchantRegistryHolder.getEntry(Identifier.of("minecraft", "power")), weapon) + EnchantmentHelper.getLevel(EnchantRegistryHolder.getEntry(Identifier.of("minecraft", "sharpness")), weapon) + 1))) * 2) / 2.0f;
    }

    private static List<StatusEffectInstance> getPotionEffects(ArrowEntity arrow) {
        List<StatusEffectInstance> effects = new ArrayList<>();

        // Get potion contents component from arrow
        PotionContentsComponent potionContents = ((ArrowEntityAccessor) arrow).GetPotionContents();

        if (potionContents != null) {
            // Get effects from the potion
            potionContents.getEffects().forEach(effects::add);
        }

        return effects;
    }

    private static void applyLingeringPotion(Entity projectile, List<StatusEffectInstance> effects, float radius, Entity owner, World world, Vec3d impactLocation) {
        AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, impactLocation.x, impactLocation.y, impactLocation.z);
        if (owner instanceof LivingEntity livingOwner) cloud.setOwner(livingOwner);
        cloud.setRadius(radius);
        cloud.setRadiusOnUse(-0.5F);
        cloud.setWaitTime(10);
        cloud.setRadiusGrowth(-cloud.getRadius() / (float) cloud.getDuration());

        for (StatusEffectInstance effect : effects) {
            cloud.addEffect(effect);
        }

        world.spawnEntity(cloud);
        world.playSound(null, BlockPos.ofFloored(impactLocation), SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.BLOCKS, 2.0F, 1.0F);
    }

    private static void applyPotionEffectsToEntities(World world, Box area, List<StatusEffectInstance> effects) {
        for (LivingEntity entity : world.getEntitiesByClass(LivingEntity.class, area, e -> true)) {
            for (StatusEffectInstance effect : effects) {
                entity.addStatusEffect(new StatusEffectInstance(effect));
            }
        }
    }

    private static void checkCombos(ItemStack weapon, float radius, Vec3d impactLocation, World world, Box area) {

        // Fire combo
        if (EnchantmentHelper.getLevel(EnchantRegistryHolder.getEntry(Identifier.of("minecraft", "flame")), weapon) > 0 || EnchantmentHelper.getLevel(EnchantRegistryHolder.getEntry(Identifier.of("minecraft", "fire_aspect")), weapon) > 0) {
            applyFireEffects(radius, impactLocation, world, area);
        }

        // Knockback combo
        int totalKnockback = EnchantmentHelper.getLevel(EnchantRegistryHolder.getEntry(Identifier.of("minecraft", "knockback")), weapon) + EnchantmentHelper.getLevel(EnchantRegistryHolder.getEntry(Identifier.of("minecraft", "punch")), weapon);
        if (totalKnockback > 0) {
            applyKnockbackEffects(totalKnockback, impactLocation, world, area);
        }
    }

    private static void applyFireEffects(float radius, Vec3d impactLocation, World world, Box area) {
        int intRadius = (int) Math.ceil(radius);
        BlockPos centerPos = BlockPos.ofFloored(impactLocation);

        for (int dx = -intRadius; dx <= intRadius; dx++) {
            for (int dz = -intRadius; dz <= intRadius; dz++) {
                if (dx * dx + dz * dz <= radius * radius) {
                    for (int dy = -1; dy <= 1; dy++) {
                        BlockPos targetPos = centerPos.add(dx, dy, dz);
                        if (world.getBlockState(targetPos).isAir() &&
                                !world.getBlockState(targetPos.down()).isAir() &&
                                Math.random() < 0.6) {
                            world.setBlockState(targetPos, Blocks.FIRE.getDefaultState(), 3);
                        }
                    }
                }
            }
        }

        world.playSound(null, centerPos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 2.0F, 1.4F);
        for (LivingEntity entity : world.getEntitiesByClass(LivingEntity.class, area, e -> true)) {
            entity.setOnFireFor(5);
        }
    }

    private static void applyKnockbackEffects(int totalKnockback, Vec3d impactLocation, World world, Box area) {
        for (LivingEntity entity : world.getEntitiesByClass(LivingEntity.class, area, e -> true)) {
            double dx = impactLocation.x - entity.getX();
            double dz = impactLocation.z - entity.getZ();
            double distance = Math.sqrt(dx * dx + dz * dz);
            if (distance < 0.001) distance = 0.001;
            dx /= distance;
            dz /= distance;

            double resistance = entity.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
            double knockbackMultiplier = 1.0 - resistance;
            double baseUpwardBoost = 0.3;

            entity.takeKnockback(totalKnockback, dx, dz);
            entity.addVelocity(0.0, baseUpwardBoost * knockbackMultiplier, 0.0);
        }

        world.playSound(null, BlockPos.ofFloored(impactLocation), SoundEvents.ENTITY_BREEZE_SHOOT, SoundCategory.BLOCKS, 2.0F, 1.0F);
    }

    private static Box calculateEffectArea(double x, double y, double z, float radius) {
        return new Box(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
    }
}