package com.impillagers.mod.item.custom;

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
import net.minecraft.world.World;

public class CreeperOilItem extends Item {
    public CreeperOilItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        //The Following Section has been adjusted to implement a fix I had done to prevent Creeper Oil Duping, I have fully tested this in Singleplayer & Multiplayer - Minico
        if(user instanceof PlayerEntity player) {
            if (!player.isCreative()) {
                stack.decrement(1);
            }

            //This line has been modified to prevent block damage when mobGriefing is disabled, the clientside check is to ensure there is no desync between server and client, I have fully tested this in Singleplayer & Multiplayer - Minico
            world.createExplosion(user, user.getX(), user.getY(), user.getZ(), 2.0f, world.isClient() ? World.ExplosionSourceType.NONE : World.ExplosionSourceType.MOB);

            ItemStack glassBottle = new ItemStack(Items.GLASS_BOTTLE);
            player.giveItemStack(glassBottle);

            user.damage(world.getDamageSources().explosion(user, user), 40);
        }
        return stack.isEmpty() ? ItemStack.EMPTY : stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
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
