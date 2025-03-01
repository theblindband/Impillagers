package com.impillagers.mod.mixin;

import com.impillagers.mod.Impillagers;
import net.minecraft.entity.ai.brain.task.FindPointOfInterestTask;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import java.util.function.Predicate;

@Mixin(FindPointOfInterestTask.class)
public abstract class PointOfInterestTaskMixin {

        @ModifyVariable(
                method = "create(Ljava/util/function/Predicate;Lnet/minecraft/entity/ai/brain/MemoryModuleType;Lnet/minecraft/entity/ai/brain/MemoryModuleType;ZLjava/util/Optional;)Lnet/minecraft/entity/ai/brain/task/Task;",
                at = @At("HEAD"),
                argsOnly = true,
                index = 0
        )
        private static Predicate<RegistryEntry<PointOfInterestType>> modifyPoiPredicate(
                Predicate<RegistryEntry<PointOfInterestType>> poiPredicate) {
                return poiPredicate.and(entry -> {
                        Identifier poiId = Registries.POINT_OF_INTEREST_TYPE.getId(entry.value());
                        return poiId != null && !Impillagers.MOD_ID.equals(poiId.getNamespace());
                });
        }
}
