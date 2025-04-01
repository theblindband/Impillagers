package com.impillagers.mod.command.enchantment;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.command.ModCommandListener;

import com.impillagers.mod.mixin.ArrowEntityAccessor;
import com.impillagers.mod.mixin.PersistentProjectileEntityAccessor;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Set;

public class KaboomCommand implements ModCommandListener.IEffectHandler {

    @Override
    public void handleEffect(ServerCommandSource source) {

        Set<Object2IntMap.Entry<RegistryEntry<Enchantment>>> enchantmentEntries = null;

        Entity potentialProjectile = source.getEntity();
        if (potentialProjectile instanceof PersistentProjectileEntity projectileEntity) {
            if (projectileEntity instanceof ArrowEntity) {
                enchantmentEntries = ((PersistentProjectileEntityAccessor) projectileEntity)
                        .getWeapon()
                        .getEnchantments()
                        .getEnchantmentEntries();
            } else {
                Impillagers.LOGGER.warn("Kaboom Enchantment: Kaboom was triggered by a non-arrow or non-spear projectile named: {}", Objects.requireNonNull(source.getEntity()).getDisplayName());
                return;
            }
        } else {
            Impillagers.LOGGER.warn("Kaboom Enchantment: Kaboom was triggered by a non-projectile named: {}", Objects.requireNonNull(source.getEntity()).getDisplayName());
            return;
        }

        int kaboomLevel = getEnchantmentLevel(enchantmentEntries, "Enchantment Kaboom!");
        float[] powerMapping = {2f, 3.5f, 6f};
        float radius = powerMapping[kaboomLevel - 1];

        checkCombos(projectileEntity, enchantmentEntries, projectileEntity.getOwner(), radius);

        PotionContentsComponent potionContents = null;
        if (projectileEntity instanceof ArrowEntity) {
            potionContents = ((ArrowEntityAccessor) projectileEntity).GetPotionContents();
            if (potionContents.hasEffects()) {
                for (StatusEffectInstance effect : potionContents.getEffects()) {
                    applyLingeringPotion(projectileEntity, potionContents, radius);
                }
            }
            projectileEntity.kill();
        }
    }

    private void checkCombos(
            PersistentProjectileEntity projectileEntity,
            Set<Object2IntMap.Entry<RegistryEntry<Enchantment>>> enchantmentEntries,
            Entity owner,
            float radius
    ) {
        World world = projectileEntity.getWorld();
        Vec3d impactLocation = calculateImpactLocation(projectileEntity);
        double centerX = impactLocation.x;
        double centerY = impactLocation.y;
        double centerZ = impactLocation.z;

        world.createExplosion(
                owner,
                centerX,
                centerY,
                centerZ,
                radius,
                false,
                World.ExplosionSourceType.NONE
        );

        if (getEnchantmentLevel(enchantmentEntries, "Enchantment Flame") > 0) {
            BlockPos centerBlockPos = new BlockPos(
                    (int) Math.round(centerX),
                    (int) Math.round(centerY),
                    (int) Math.round(centerZ)
            );

            int intRadius = (int) Math.ceil(radius);
            for (int dx = -intRadius; dx <= intRadius; dx++) {
                for (int dz = -intRadius; dz <= intRadius; dz++) {
                    if (dx * dx + dz * dz <= radius * radius) {
                        for (int dy = -1; dy <= 1; dy++) {
                            BlockPos targetPos = centerBlockPos.add(dx, dy, dz);
                            if (world.getBlockState(targetPos).isAir() &&
                                    !world.getBlockState(targetPos.down()).isAir()) {
                                world.setBlockState(targetPos, Blocks.FIRE.getDefaultState(), 3);
                            }
                        }
                    }
                }
            }

            Box area = new Box(
                    centerX - radius, centerY - radius, centerZ - radius,
                    centerX + radius, centerY + radius, centerZ + radius
            );

            for (Entity entity : world.getEntitiesByClass(LivingEntity.class, area, e -> true)) {
                entity.setOnFireFor(5);
            }
        }
    }
    public static int getEnchantmentLevel(Set<Object2IntMap.Entry<RegistryEntry<Enchantment>>> enchantmentEntries, String targetId)
    {
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : enchantmentEntries) {
            if (entry.getKey().value().toString().equals(targetId)) {
                return entry.getIntValue();
            }
        }
        return 0;
    }
    private void applyLingeringPotion(PersistentProjectileEntity projectileEntity, PotionContentsComponent potionContents, float radius)  {

        Vec3d impactLocation = calculateImpactLocation(projectileEntity);
        double centerX = impactLocation.x;
        double centerY = impactLocation.y;
        double centerZ = impactLocation.z;

        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(projectileEntity.getWorld(), centerX, centerY, centerZ);
        if (projectileEntity.getOwner() instanceof LivingEntity livingEntity) {
            areaEffectCloudEntity.setOwner(livingEntity);
        }

        areaEffectCloudEntity.setRadius(radius);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
        areaEffectCloudEntity.setPotionContents(potionContents);
        projectileEntity.getWorld().spawnEntity(areaEffectCloudEntity);
    }

    private Vec3d calculateImpactLocation(PersistentProjectileEntity projectileEntity) {
        World world = projectileEntity.getWorld();

        double centerX, centerY, centerZ;
        LivingEntity intersectingEntity = world.getOtherEntities(
                projectileEntity,
                projectileEntity.getBoundingBox(),
                entity -> entity instanceof LivingEntity
        ).stream().map(entity -> (LivingEntity) entity).findFirst().orElse(null);

        if (intersectingEntity != null && intersectingEntity.isAlive()) {
            Vec3d entityPos = intersectingEntity.getPos();
            BlockPos blockPos = intersectingEntity.getBlockPos();

            if (!world.getBlockState(blockPos).isAir()) {
                centerX = Math.round((blockPos.getX() + 0.5) * 2) / 2.0;
                centerY = blockPos.getY();
                centerZ = Math.round((blockPos.getZ() + 0.5) * 2) / 2.0;
            } else {
                centerX = entityPos.getX();
                centerY = entityPos.getY();
                centerZ = entityPos.getZ();
            }
        } else {
            BlockPos arrowPos = projectileEntity.getBlockPos();
            BlockPos solidBlockPos = null;

            for (int i = 1; i <= 5; i++) {
                BlockPos candidate = arrowPos.down(i);
                if (!world.getBlockState(candidate).isAir()) {
                    solidBlockPos = candidate;
                    break;
                }
            }

            if (solidBlockPos != null) {
                centerX = Math.round((solidBlockPos.getX() + 0.5) * 2) / 2.0;
                centerY = solidBlockPos.getY();
                centerZ = Math.round((solidBlockPos.getZ() + 0.5) * 2) / 2.0;
            } else {
                centerX = Math.round((arrowPos.getX() + 0.5) * 2) / 2.0;
                centerY = arrowPos.getY();
                centerZ = Math.round((arrowPos.getZ() + 0.5) * 2) / 2.0;
            }
        }

        return new Vec3d(centerX, centerY, centerZ);
    }
}
