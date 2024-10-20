package com.impillagers.mod;

import com.google.common.base.Suppliers;
import com.impillagers.mod.entity.ModEntities;
import com.impillagers.mod.entity.renderer.CarnivourusPlantRenderer;
import com.impillagers.mod.entity.renderer.ImpillagerRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.item.ModCreativeModeTabs;
import com.impillagers.mod.block.ModBlocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.util.Map;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Impillagers.MOD_ID)
public class Impillagers {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "impillagers";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Impillagers(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        //Registers
        //Creative Mode Tabs
        ModCreativeModeTabs.register(modEventBus);
        //Blocks and Items
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        //Entities
        ModEntities.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation id(String string) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, string);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    //This is for adding mod items to vanilla tabs
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            EntityRenderers.register(ModEntities.IMPILLAGER.get(), ImpillagerRenderer::new);
            EntityRenderers.register(ModEntities.CARNIVOURUS_PLANT.get(), CarnivourusPlantRenderer::new);

        }
    }
}
