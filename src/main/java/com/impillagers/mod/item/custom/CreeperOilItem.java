package com.impillagers.mod.item.custom;

import com.impillagers.mod.damage.ModDamageTypes;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.Objects;

public class CreeperOilItem extends Item {
    public CreeperOilItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);

        if (!world.isClient() && user instanceof ServerPlayerEntity serverPlayerEntity) {

            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));

            if (!serverPlayerEntity.isCreative()) {
                serverPlayerEntity.giveItemStack(new ItemStack(Items.GLASS_BOTTLE));
                stack.decrement(1);
            }

            Objects.requireNonNull(serverPlayerEntity.getServer()).execute(() -> {
                World.ExplosionSourceType explosionType;
                if (world.getDifficulty() == Difficulty.PEACEFUL) {
                    explosionType = world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? World.ExplosionSourceType.TNT : World.ExplosionSourceType.NONE;
                } else {
                    explosionType = World.ExplosionSourceType.MOB;
                }
                world.createExplosion(serverPlayerEntity, serverPlayerEntity.getX(), serverPlayerEntity.getY(), serverPlayerEntity.getZ(), 2.0f, explosionType);

                serverPlayerEntity.damage(ModDamageTypes.getStrongStuff(serverPlayerEntity), 40);
            });
        }
        return stack.isEmpty() ? ItemStack.EMPTY : stack;
    }
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
