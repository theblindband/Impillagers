package com.impillagers.mod;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.component.ModDataComponentTypes;
import com.impillagers.mod.effect.ModEffects;
import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.entity.custom.ImpillagerEntity;
import com.impillagers.mod.item.ModItemGroups;
import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.particle.ModParticleTypes;
import com.impillagers.mod.sounds.ModSoundEvents;
import com.impillagers.mod.villager.Banker;
import com.impillagers.mod.villager.DungCollector;
import com.impillagers.mod.villager.ModTrades;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		ModParticleTypes.registerModParticles();
		ModEffects.registerModEffects();
		ModSoundEvents.registerModSounds();
		ModDataComponentTypes.registerDataComponentTypes();
		ModTrades.registerModTrades();

		//Compostable Blocks
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.PURPLE_HEART_SAPLING, 0.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.PURPLE_HEART_LEAVES, 0.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.BELLADONNA, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.FIREFLY_BUSH, 0.65f);
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

		//Villagers
		DungCollector.registerVillager();
		Banker.registerVillager();

		//Mob Attributes
		FabricDefaultAttributeRegistry.register(ModEntities.IMPILLAGER, ImpillagerEntity.createVillagerAttributes());

		//Potion Recipes
		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, Item.fromBlock(ModBlocks.BELLADONNA), Potions.STRONG_POISON);
		});
	}
}