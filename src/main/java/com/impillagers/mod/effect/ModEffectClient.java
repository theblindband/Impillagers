package com.impillagers.mod.effect;

public class ModEffectClient {
    private static float currentOpacity = 0.0f;
    private static double lastAdjustedFOV = 70.0;

    public static void adjustFOVBasedOnOpacity(float opacity) {
        currentOpacity = Math.max(0.0f, Math.min(opacity, 1.0f));
    }

    public static double getAdjustedFOV(double baseFOV, float tickDelta) {
        double minFOV = baseFOV - 40;

        // Define the exponential factor (higher value = steeper zoom near full opacity)
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
}
