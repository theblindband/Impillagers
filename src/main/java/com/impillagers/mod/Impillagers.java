package com.impillagers.mod;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.block.entity.ModBlockEntities;
import com.impillagers.mod.command.ModCommands;
//import com.impillagers.mod.component.ModDataComponentTypes;
import com.impillagers.mod.damage.ModDamageTypes;
import com.impillagers.mod.data.ModDataPacks;
import com.impillagers.mod.effect.ModEffects;
import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.entity.custom.dung_golem.DungGolemEntity;
import com.impillagers.mod.entity.custom.impillager.ImpillagerEntity;
import com.impillagers.mod.entity.custom.zombieimpillager.ZombieImpillagerEntity;
import com.impillagers.mod.event.ModEvents;
import com.impillagers.mod.item.ModItemGroups;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.item.sherd.ModSherds;
import com.impillagers.mod.particle.ModParticleTypes;
import com.impillagers.mod.screen.ModScreenHandlers;
import com.impillagers.mod.sounds.ModSoundEvents;
import com.impillagers.mod.util.EnchantRegistryHolder;
//import com.impillagers.mod.util.HudOverlayOpacityPayload;
import com.impillagers.mod.command.ModCommandListener;
import com.impillagers.mod.villager.ModTrades;
import com.impillagers.mod.villager.professions.ModProfessions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
//import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.item.HoeItem;
import net.minecraft.potion.Potions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//TODO: FIX
public class Impillagers implements ModInitializer {
	public static final String MOD_ID = "impillagers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing imp mischief.");

		ModItemGroups.registerModItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();
		ModBlockEntities.registerModBlockEntities();
		ModScreenHandlers.registerModScreenHandlers();
		ModParticleTypes.registerModParticles();
		ModEffects.registerModEffects();
		ModSoundEvents.registerModSounds();
		ModDataComponentTypes.registerDataComponentTypes();
		ModTrades.registerModTrades();
		ModEvents.registerModEvents();
		ModProfessions.registerModProfessions();
		ModCommandListener.registerListeners();
		ModCommands.registerCommands();
		ModDamageTypes.registerDamageTypes();
		ModDataPacks.registerDataPacks();
		//ModSherds.registerSherds();

		//Compostable Blocks
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.PURPLE_HEART_SAPLING, 0.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.PURPLE_HEART_LEAVES, 0.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.BELLADONNA, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.FIREFLY_BUSH, 0.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.DUNG_BLOCK, 1.0f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.DUNG_BALL, 0.5f);

		//Strippable Blocks
		StrippableBlockRegistry.register(ModBlocks.PURPLE_HEART_LOG, ModBlocks.STRIPPED_PURPLE_HEART_LOG);
		StrippableBlockRegistry.register(ModBlocks.PURPLE_HEART_WOOD, ModBlocks.STRIPPED_PURPLE_HEART_WOOD);

		//Flammable Blocks
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PURPLE_HEART_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PURPLE_HEART_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_PURPLE_HEART_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_PURPLE_HEART_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PURPLE_HEART_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PURPLE_HEART_SLAB, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PURPLE_HEART_FENCE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PURPLE_HEART_STAIRS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PURPLE_HEART_LEAVES, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BELLADONNA, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.FIREFLY_BUSH, 60, 100);

		//Tillable Blocks
		TillableBlockRegistry.register(ModBlocks.FERTILE_DIRT, HoeItem::canTillFarmland, HoeItem.createTillAction(ModBlocks.FERTILE_FARMLAND.getDefaultState()));

		//Potion Recipes
		//FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> builder.registerPotionRecipe(Potions.AWKWARD, ModBlocks.BELLADONNA.asItem(), Potions.STRONG_POISON));

		//Packet Payloads
		//PayloadTypeRegistry.playS2C().register(HudOverlayOpacityPayload.ID, HudOverlayOpacityPayload.CODEC);

		ServerLifecycleEvents.SERVER_STARTING.register(server -> registerAttributes());
		ServerLifecycleEvents.SERVER_STARTED.register(EnchantRegistryHolder::init);
	}

	private void registerAttributes() {
		//Register Mob Attributes on server start
		FabricDefaultAttributeRegistry.register(ModEntities.IMPILLAGER, ImpillagerEntity.createVillagerAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ZOMBIE_IMPILLAGER, ZombieImpillagerEntity.createZombieImpillagerAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.DUNG_GOLEM, DungGolemEntity.createDungGolemAttributes());
	}
}