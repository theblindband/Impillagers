package com.impillagers.mod.entity.ai.brain;

import com.google.common.collect.ImmutableMap;
import com.impillagers.mod.villager.professions.ModProfessions;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Schedule;
import net.minecraft.village.VillagerProfession;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public final class ImpillagerSchedules extends Schedule {
    public static final Schedule DEFAULT = register("impillagers:default")
            .withActivity(10, Activity.IDLE)
            .withActivity(2000, Activity.WORK)
            .withActivity(9000, Activity.MEET)
            .withActivity(11000, Activity.IDLE)
            .withActivity(12000, Activity.REST)
            .build();

    /*public static final Schedule DUNG_COLLECTOR = register("impillagers:dung_collector")
            .withActivity(0, Activity.REST)
            .withActivity(12000, Activity.IDLE)
            .withActivity(14000, Activity.WORK)
            .withActivity(20000, Activity.MEET)
            .withActivity(22000, Activity.IDLE)
            .build();
    */
    private static final Map<VillagerProfession, Schedule> PROFESSION_SCHEDULES =
            ImmutableMap.of(
                    //ModProfessions.DUNG_COLLECTOR, DUNG_COLLECTOR
            );

    public static Schedule forProfession(@Nullable VillagerProfession profession) {
        if (profession == null) {
            return DEFAULT;
        }

        Schedule schedule = PROFESSION_SCHEDULES.get(profession);
        return schedule == null ? DEFAULT : schedule;
    }

    public static void registerImpillagerSchedules() {
        //Impillagers.LOGGER.info("Registering Impillager Schedules for " + Impillagers.MOD_ID);
    }
}