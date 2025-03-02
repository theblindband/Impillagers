package com.impillagers.mod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.impillagers.mod.Impillagers;
import com.impillagers.mod.component.ModDataComponentTypes;
import com.impillagers.mod.effect.ModEffects;
import com.impillagers.mod.item.ModArmorMaterials;
import com.impillagers.mod.util.HudOverlayOpacityPayload;
import com.impillagers.mod.util.ModTags;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class FrogMaskItem extends ArmorItem {

    private static final Map<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>>())
                    .put(ModArmorMaterials.FROG_MASK_MATERIAL,
                            List.of(new StatusEffectInstance(ModEffects.CALL_OF_THE_IMPS, 40, 0, false, false))).build();

    public FrogMaskItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()) {
            if(entity instanceof PlayerEntity player) {
                if(hasHelmetOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            RegistryEntry<ArmorMaterial> mapArmorMaterial = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, RegistryEntry<ArmorMaterial> mapArmorMaterial, List<StatusEffectInstance> mapStatusEffect) {
        boolean hasPlayerEffect = mapStatusEffect.stream().allMatch(statusEffectInstance -> player.hasStatusEffect(statusEffectInstance.getEffectType()));

        if(!hasPlayerEffect) {
            for (StatusEffectInstance instance : mapStatusEffect) {
                player.addStatusEffect(new StatusEffectInstance(instance.getEffectType(),
                        instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles()));
            }
        }
    }

    private boolean hasHelmetOn(PlayerEntity player) {
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty();
    }

    private boolean hasCorrectArmorOn(RegistryEntry<ArmorMaterial> material, PlayerEntity player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (armorStack.getItem() instanceof FrogMaskItem) {
                ArmorItem helmet = ((ArmorItem) player.getInventory().getArmorStack(3).getItem());
                if (helmet.getMaterial() == material)
                {
                    updateVillageCoordinates(armorStack, player);
                    float threshold = 45.0f;
                    double angle = isLookingAtVillage(armorStack, player, threshold);
                    if ( angle >= 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void updateVillageCoordinates(ItemStack stack, PlayerEntity player) {
        ServerWorld serverWorld = (ServerWorld) player.getWorld();
        BlockPos villageLocation = serverWorld.locateStructure(ModTags.StructureKeys.IMPILLAGER_VILLAGE, BlockPos.ofFloored(player.getPos()), 20000, false);

        if (villageLocation != null) {
            if (!villageLocation.equals(stack.get(ModDataComponentTypes.COORDINATES))) {
                stack.set(ModDataComponentTypes.COORDINATES, villageLocation);
            }
        }
    }

    private double isLookingAtVillage(ItemStack stack, PlayerEntity player, float threshold) {
        BlockPos village = stack.get((ModDataComponentTypes.COORDINATES));
        if (village != null) {
            Vec3d viewDirection = new Vec3d(player.getRotationVec(1.0F).x, 0, player.getRotationVec(1.0F).z).normalize();
            Vec3d villageDirection = new Vec3d(village.getX() - player.getX(), 0, village.getZ() - player.getZ()).normalize();
            double dotProduct = viewDirection.dotProduct(villageDirection);
            double angle = Math.acos(dotProduct);
            angle = Math.toDegrees(angle);
            float opacity = calculateOpacity(angle, threshold);

            if (!player.getEntityWorld().isClient()) {
                    ServerPlayNetworking.send((ServerPlayerEntity) player, new HudOverlayOpacityPayload(opacity));
                }
            if (angle < threshold) {
                return angle;
            }
        }
        return -1;
    }

    public float calculateOpacity(double angle, double threshold) {
        if (angle >= threshold) {
            return 0f;
        } else {
            double ratio = angle / threshold;
            return (float)(1 - Math.pow(ratio, 2));
        }
    }

}