package com.impillagers.mod.entity.ai.brain.task;

import com.impillagers.mod.util.PoiFilterUtil;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.SingleTickTask;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.TaskTriggerer;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.DebugInfoSender;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.poi.PointOfInterestStorage;
import net.minecraft.world.poi.PointOfInterestType;
import org.apache.commons.lang3.mutable.MutableLong;
import org.jetbrains.annotations.Nullable;

public class ImpillagerFindPointOfInterestTask {
	public static final int POI_SORTING_RADIUS = 48;

	public static Task<PathAwareEntity> create(Predicate<RegistryEntry<PointOfInterestType>> poiPredicate, MemoryModuleType<GlobalPos> poiPosModule, MemoryModuleType<GlobalPos> potentialPoiPosModule, boolean onlyRunIfChild) {

		MutableLong mutableLong = new MutableLong(0L);
		Long2ObjectMap<RetryMarker> long2ObjectMap = new Long2ObjectOpenHashMap<>();

		SingleTickTask<PathAwareEntity> singleTickTask = TaskTriggerer.task(
				taskContext -> taskContext.group(taskContext.queryMemoryAbsent(potentialPoiPosModule))
						.apply(taskContext, queryResult -> (world, entity, time) -> {
							if (onlyRunIfChild && entity.isBaby()) {
								return false;
							} else if (mutableLong.getValue() == 0L) {
								mutableLong.setValue(world.getTime() + world.random.nextInt(20));
								return false;
							} else if (world.getTime() < mutableLong.getValue()) {
								return false;
							} else {
								mutableLong.setValue(time + 20L + world.getRandom().nextInt(20));
								PointOfInterestStorage pointOfInterestStorage = world.getPointOfInterestStorage();
								long2ObjectMap.long2ObjectEntrySet().removeIf(entry -> !entry.getValue().isAttempting(time));

								Predicate<BlockPos> predicate2 = pos -> {
									RetryMarker retryMarker = long2ObjectMap.get(pos.asLong());
									if (retryMarker == null) {
										return true;
									} else if (!retryMarker.shouldRetry(time)) {
										return false;
									} else {
										retryMarker.setAttemptTime(time);
										return true;
									}
								};

								Predicate<RegistryEntry<PointOfInterestType>> filteredPoiPredicate = poiPredicate.and(PoiFilterUtil::isPoiValid);

								Set<Pair<RegistryEntry<PointOfInterestType>, BlockPos>> set = pointOfInterestStorage.getSortedTypesAndPositions(filteredPoiPredicate, predicate2, entity.getBlockPos(), POI_SORTING_RADIUS, PointOfInterestStorage.OccupationStatus.HAS_SPACE).limit(5L).collect(Collectors.toSet());

								Path path = findPathToPoi(entity, set);
								if (path != null && path.reachesTarget()) {
									BlockPos blockPos = path.getTarget();
									pointOfInterestStorage.getType(blockPos).ifPresent(poiType -> {
										pointOfInterestStorage.getPosition(poiPredicate, (registryEntry, blockPos2) -> blockPos2.equals(blockPos), blockPos, 1);
										queryResult.remember(GlobalPos.create(world.getRegistryKey(), blockPos));
										long2ObjectMap.clear();
										DebugInfoSender.sendPointOfInterest(world, blockPos);
									});
								} else {
									for (Pair<RegistryEntry<PointOfInterestType>, BlockPos> pair : set) {
										long2ObjectMap.computeIfAbsent(pair.getSecond().asLong(), m -> new RetryMarker(world.random, time));
									}
								}
								return true;
							}
						})
		);

		return potentialPoiPosModule == poiPosModule
				? singleTickTask
				: TaskTriggerer.task(context -> context.group(context.queryMemoryAbsent(poiPosModule)).apply(context, poiPos -> singleTickTask));
	}

	@Nullable
	public static Path findPathToPoi(MobEntity entity, Set<Pair<RegistryEntry<PointOfInterestType>, BlockPos>> pois) {
		if (pois.isEmpty()) {
			return null;
		} else {
			Set<BlockPos> set = new HashSet<>();
			int i = 1;

			for (Pair<RegistryEntry<PointOfInterestType>, BlockPos> pair : pois) {
				i = Math.max(i, pair.getFirst().value().searchDistance());
				set.add(pair.getSecond());
			}

			return entity.getNavigation().findPathTo(set, i);
		}
	}

	static class RetryMarker {
		private static final int ATTEMPT_DURATION = 400;
		private final Random random;
		private long previousAttemptAt;
		private long nextScheduledAttemptAt;
		private int currentDelay;

		RetryMarker(Random random, long time) {
			this.random = random;
			this.setAttemptTime(time);
		}

		public void setAttemptTime(long time) {
			this.previousAttemptAt = time;
			int i = this.currentDelay + this.random.nextInt(40) + 40;
			this.currentDelay = Math.min(i, 400);
			this.nextScheduledAttemptAt = time + this.currentDelay;
		}

		public boolean isAttempting(long time) {
			return time - this.previousAttemptAt < ATTEMPT_DURATION;
		}

		public boolean shouldRetry(long time) {
			return time >= this.nextScheduledAttemptAt;
		}

		public String toString() {
			return "RetryMarker{" + "previousAttemptAt=" + this.previousAttemptAt + ", nextScheduledAttemptAt=" + this.nextScheduledAttemptAt + ", currentDelay=" + this.currentDelay + "}";
		}
	}
}
