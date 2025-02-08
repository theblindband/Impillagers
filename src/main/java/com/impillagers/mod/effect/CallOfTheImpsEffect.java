package com.impillagers.mod.effect;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.util.ModTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureKeys;

public class CallOfTheImpsEffect extends StatusEffect {

    BlockPos villageLocation = null;
    int DelayCounter = 0;

    public CallOfTheImpsEffect(StatusEffectCategory category, int color) {
        super(category, color);

    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        World world = entity.getWorld();
        if (!world.isClient()) {
            if (DelayCounter == 1) {
                ServerWorld serverWorld = (ServerWorld) entity.getWorld();
                BlockPos villageLocation = null;
                villageLocation = serverWorld.locateStructure(ModTags.StructureKeys.IMPILLAGER_VILLAGE, BlockPos.ofFloored(entity.getPos()), 20000, false);
                Impillagers.LOGGER.info("Tried to locate Impillger Village, it returned " + villageLocation);
                Impillagers.LOGGER.info("Tried to locate Impillger Village, it returned " + villageLocation);
                Impillagers.LOGGER.info("Tried to locate Impillger Village, it returned " + villageLocation);
                Impillagers.LOGGER.info("Tried to locate Impillger Village, it returned " + villageLocation);
                return true;
            }
        }
        DelayCounter++;
        Impillagers.LOGGER.info("Impillger Village Delay Counter is at " + DelayCounter + " and the Current Village is " + villageLocation);
        if (DelayCounter == 1200) {DelayCounter = 0;}
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}

