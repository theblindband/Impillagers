package com.impillagers.mod.effect;

import com.impillagers.mod.sounds.ModSoundEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.Random;

public class ModEffectClient {
    private static float currentOpacity = 0.0f;
    private static double lastAdjustedFOV = 70.0;

    public static void adjustFOVBasedOnOpacity(float opacity) {
        currentOpacity = Math.max(0.0f, Math.min(opacity, 1.0f));
    }

    public static double getAdjustedFOV(double baseFOV) {
        double minFOV = baseFOV - 40;
        double exponent = 3.0;
        double scaledOpacity = Math.pow(currentOpacity, exponent);
        double targetFOV = baseFOV - (scaledOpacity * (baseFOV - minFOV));
        double smoothingFactor = 0.75;
        lastAdjustedFOV = lerp(smoothingFactor, lastAdjustedFOV, targetFOV);
        return lastAdjustedFOV;
    }

    private static double lerp(double alpha, double start, double end) {
        return start + alpha * (end - start);
    }

    private static final int NUM_FIREFLY_CHANNELS = 4;
    private static final int NUM_SECONDARY_CHANNELS = 4;
    private static final float FIREFLY_MAX_VOLUME = 1.2f;
    private static final float FROG_MAX_VOLUME = 1.2f;
    private static final float IMP_MAX_VOLUME = 0.6f;

    private static final int[] fireflyCooldowns = new int[NUM_FIREFLY_CHANNELS];
    private static final float[] fireflyVolumeOffsets = new float[NUM_FIREFLY_CHANNELS];

    private static final int[] secondaryCooldowns = new int[NUM_SECONDARY_CHANNELS];
    private static final float[] secondaryVolumeOffsets = new float[NUM_SECONDARY_CHANNELS];

    private static final Random random = new Random();

    private static final SoundEvent[] FIREFLY_SOUNDS = new SoundEvent[] {
            ModSoundEvents.FIREFLY_BUSH
    };
    private static final SoundEvent[] FROG_SOUNDS = new SoundEvent[] {
            SoundEvents.ENTITY_FROG_AMBIENT,
            SoundEvents.ENTITY_FROG_EAT,
            SoundEvents.ENTITY_FROG_TONGUE
    };
    private static final SoundEvent[] IMPILLAGER_SOUNDS = new SoundEvent[] {
            ModSoundEvents.IMPILLAGER_AMBIENT,
            ModSoundEvents.IMPILLAGER_ATTACK_RANGED,
            ModSoundEvents.IMPILLAGER_CELEBRATE
    };

    static {
        for (int i = 0; i < NUM_FIREFLY_CHANNELS; i++) {
            fireflyVolumeOffsets[i] = 0.8f + random.nextFloat() * 0.4f;
            fireflyCooldowns[i] = 0;
        }
        for (int i = 0; i < NUM_SECONDARY_CHANNELS; i++) {
            secondaryVolumeOffsets[i] = 0.8f + random.nextFloat() * 0.4f;
            secondaryCooldowns[i] = 0;
        }
    }

    public static void updateSoundEffects() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;

        for (int i = 0; i < NUM_FIREFLY_CHANNELS; i++) {
            if (currentOpacity > 0 && fireflyCooldowns[i] <= 0) {
                float volume = currentOpacity * fireflyVolumeOffsets[i] * FIREFLY_MAX_VOLUME;
                playSound(client, FIREFLY_SOUNDS[0], volume);
                fireflyCooldowns[i] = 40 + random.nextInt(61);
            } else {
                fireflyCooldowns[i]--;
            }
        }

        for (int i = 0; i < NUM_SECONDARY_CHANNELS; i++) {
            if (currentOpacity > 0 && secondaryCooldowns[i] <= 0) {
                float frogChance = 0.3f + (1.0f - currentOpacity) * 0.7f;
                double chance = random.nextDouble();
                SoundEvent chosenSound;
                float channelMaxVolume;
                if (chance < frogChance) {
                    chosenSound = FROG_SOUNDS[random.nextInt(FROG_SOUNDS.length)];
                    channelMaxVolume = FROG_MAX_VOLUME;
                } else {
                    chosenSound = IMPILLAGER_SOUNDS[random.nextInt(IMPILLAGER_SOUNDS.length)];
                    channelMaxVolume = IMP_MAX_VOLUME;
                }
                float volume = currentOpacity * secondaryVolumeOffsets[i] * channelMaxVolume;
                playSound(client, chosenSound, volume);
                secondaryCooldowns[i] = 60 + random.nextInt(121);
            } else {
                secondaryCooldowns[i]--;
            }
        }
    }

    private static void playSound(MinecraftClient client, SoundEvent sound, float volume) {
        client.getSoundManager().play(PositionedSoundInstance.master(sound, 1.0F, volume));
    }
}
