package com.impillagers.mod.particle;

import com.impillagers.mod.Impillagers;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticleTypes {

    public static final SimpleParticleType FIREFLY = FabricParticleTypes.simple();

    static {
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Impillagers.MOD_ID,"firefly"), FIREFLY);
    }

    public static void registerModParticles() {
        Impillagers.LOGGER.info("Registering Mod Particles for " + Impillagers.MOD_ID);
    }
}
