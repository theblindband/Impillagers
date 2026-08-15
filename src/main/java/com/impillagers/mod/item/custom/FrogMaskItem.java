package com.impillagers.mod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.impillagers.mod.component.ModDataComponentTypes;
import com.impillagers.mod.effect.ModEffects;
import com.impillagers.mod.item.ModArmorMaterials;
import com.impillagers.mod.util.ModTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
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
                addStatusEffectForMaterial(player, mapStatusEffects);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, List<StatusEffectInstance> mapStatusEffect) {
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
                if (helmet.getMaterial() == material) {
                    updateVillageCoordinates(armorStack, player);
                    return true;
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

}