package com.impillagers.mod.sounds;

import com.impillagers.mod.Impillagers;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {

    //Firefly Bush Ambient Sounds
    public static final SoundEvent FIREFLY_BUSH = registerSoundEvent("block.firefly_bush");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Impillagers.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {
        //BlindsMusicDiscs.LOGGER.info("Registering Sounds for Blind's Music Discs");
    }
}
