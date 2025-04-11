package com.impillagers.mod.datagen;

import com.impillagers.mod.entity.ModEntities;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.SummonedEntityCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider  extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    //This class simply exists as TheBlindBandit6 didn't want to switch to Advancement datagen but wanted to keep it as reference, this does not run - Minico

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {

        /*AdvancementEntry rootAdvancement = Advancement.Builder.create()
                .display(
                        Blocks.CARVED_PUMPKIN,
                        Text.translatable("advancements.adventure.summon_dung_golem.title"),
                        Text.translatable("advancements.adventure.summon_dung_golem.description"),
                        Identifier.of("textures/gui/advancements/backgrounds/adventure.png"), // Background image used
                        AdvancementFrame.GOAL, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("summoned_golem", SummonedEntityCriterion.Conditions.create(EntityPredicate.Builder.create().type(ModEntities.DUNG_GOLEM)))
                .build(consumer, "impillagers" + "/summon_dung_golem");*/
    }
}
