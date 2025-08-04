package com.impillagers.mod.sounds;

import com.impillagers.mod.Impillagers;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {

    //Firefly Bush Ambient Sounds
    public static final SoundEvent FIREFLY_BUSH = registerSoundEvent("block.firefly_bush");

    public static final SoundEvent FIREFLY_BOTTLE_OPEN = registerSoundEvent("block.firefly_bottle.open");
    public static final SoundEvent FIREFLY_BOTTLE_CLOSE = registerSoundEvent("block.firefly_bottle.close");

    public static final SoundEvent SAFE_LOCK = registerSoundEvent("block.safe.lock");
    public static final SoundEvent SAFE_UNLOCK = registerSoundEvent("block.safe.unlock");

    public static final SoundEvent IMPILLAGER_DAMAGE = registerSoundEvent("entity.impillager.damage");
    public static final SoundEvent IMPILLAGER_DEATH = registerSoundEvent("entity.impillager.death");
    public static final SoundEvent IMPILLAGER_NO = registerSoundEvent("entity.impillager.no");
    public static final SoundEvent IMPILLAGER_YES = registerSoundEvent("entity.impillager.yes");
    public static final SoundEvent IMPILLAGER_AMBIENT = registerSoundEvent("entity.impillager.ambient");
    public static final SoundEvent IMPILLAGER_PURSUE = registerSoundEvent("entity.impillager.pursue");
    public static final SoundEvent IMPILLAGER_CELEBRATE = registerSoundEvent("entity.impillager.celebrate");
    public static final SoundEvent IMPILLAGER_ATTACK_MELEE = registerSoundEvent("entity.impillager.attack.melee");
    public static final SoundEvent IMPILLAGER_ATTACK_RANGED = registerSoundEvent("entity.impillager.attack.ranged");

    public static final SoundEvent IMPILLAGER_WORK_BANKER = registerSoundEvent("entity.impillager.work.banker");
    public static final SoundEvent IMPILLAGER_WORK_MUSICIAN = registerSoundEvent("entity.impillager.work.musician");




    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Impillagers.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {
        //Impillagers.LOGGER.info("Registering Sounds for Impillagers");
    }
}
