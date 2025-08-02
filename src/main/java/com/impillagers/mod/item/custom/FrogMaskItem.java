package com.impillagers.mod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.impillagers.mod.effect.ModEffects;
import com.impillagers.mod.item.ModArmorMaterials;
import com.impillagers.mod.util.HudOverlayOpacityPayload;
import com.impillagers.mod.util.ModTags;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class FrogMaskItem extends ArmorItem {

    public FrogMaskItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    private static final Map<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>>())
                    .put(RegistryEntry.of(ModArmorMaterials.FROG_MASK),
                            List.of(new StatusEffectInstance(ModEffects.CALL_OF_THE_IMPS.value(), 40, 0, false, false))).build();

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {
                if (hasHelmetOn(player)) {
                    evaluateArmorEffects(player);
                } else {
                    ServerPlayNetworking.send((ServerPlayerEntity) player, HudOverlayOpacityPayload.PACKET_ID, new HudOverlayOpacityPayload(0F).write(PacketByteBufs.create()));
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if (shouldGiveEffect(player)) {
                addStatusEffectForMaterial(player, mapStatusEffects);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, List<StatusEffectInstance> mapStatusEffect) {
        boolean hasPlayerEffect = mapStatusEffect.stream().allMatch(statusEffectInstance -> player.hasStatusEffect(statusEffectInstance.getEffectType()));

        if (!hasPlayerEffect) {
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

    private boolean shouldGiveEffect(PlayerEntity player) {
        ItemStack helmet = player.getInventory().getArmorStack(3);
        if (helmet.getItem() instanceof FrogMaskItem) {
            updateVillageCoordinates(helmet, player);
            return isLookingAtVillage(helmet, player, 45f);
        }
        return false;
    }

    private void updateVillageCoordinates(ItemStack stack, PlayerEntity player) {
        ServerWorld serverWorld = (ServerWorld) player.getWorld();
        BlockPos villageLocation = serverWorld.locateStructure(ModTags.StructureKeys.IMPILLAGER_VILLAGE, BlockPos.ofFloored(player.getPos()), 20000, false);

        if (villageLocation != null) {
            BlockPos currentVillage = getVillageCoordinates(stack);
            if (!villageLocation.equals(currentVillage)) {
                setVillageCoordinates(stack, villageLocation);
            }
        }
    }

    private boolean isLookingAtVillage(ItemStack stack, PlayerEntity player, float threshold) {
        BlockPos village = getVillageCoordinates(stack);
        if (village != null) {
            Vec3d viewDirection = new Vec3d(player.getRotationVec(1.0F).x, 0, player.getRotationVec(1.0F).z).normalize();
            Vec3d villageDirection = new Vec3d(village.getX() - player.getX(), 0, village.getZ() - player.getZ()).normalize();
            double dotProduct = viewDirection.dotProduct(villageDirection);
            double angle = Math.acos(dotProduct);
            angle = Math.toDegrees(angle);
            float opacity = calculateOpacity(angle, threshold);

            if (!player.getEntityWorld().isClient()) {
                ServerPlayNetworking.send((ServerPlayerEntity) player, HudOverlayOpacityPayload.PACKET_ID, new HudOverlayOpacityPayload(opacity).write(PacketByteBufs.create()));
            }
            return angle < threshold;
        }
        return false;
    }

    private BlockPos getVillageCoordinates(ItemStack stack) {
        if (stack.hasNbt() && stack.getNbt() != null && stack.getNbt().contains("VillageCoordinates")) {
            return NbtHelper.toBlockPos(stack.getNbt().getCompound("VillageCoordinates"));
        }
        return null;
    }

    private void setVillageCoordinates(ItemStack stack, BlockPos pos) {
        stack.getOrCreateNbt().put("VillageCoordinates", NbtHelper.fromBlockPos(pos));
    }

    public float calculateOpacity(double angle, double threshold) {
        if (angle >= threshold) {
            return 0f;
        } else {
            double ratio = angle / threshold;
            return (float) (1 - Math.pow(ratio, 2));
        }
    }
}