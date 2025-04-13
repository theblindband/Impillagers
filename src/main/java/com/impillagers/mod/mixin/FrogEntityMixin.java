package com.impillagers.mod.mixin;

import com.impillagers.mod.block.ModBlocks;
import com.impillagers.mod.util.ModTags;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FrogEntity.class)
public abstract class FrogEntityMixin extends AnimalEntity {

    protected FrogEntityMixin(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isIn(ModTags.Items.FROG_POISONOUS_FOOD)) {
            this.kill();

            if (!player.isCreative()) {
                stack.decrement(1);
            }

            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_FROG_EAT, SoundCategory.NEUTRAL, 0.75F, 0.4F / (this.getWorld().getRandom().nextFloat() * 0.4F + 0.8F));

            if (stack.isOf(ModBlocks.FIREFLY_BOTTLE.asItem()) || stack.isOf(ModBlocks.FIREFLY_BUSH.asItem())) {
                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_BEE_POLLINATE, SoundCategory.NEUTRAL, 0.75F, 0.4F / (this.getWorld().getRandom().nextFloat() * 0.4F + 0.8F));
            }

            if (!this.getWorld().isClient && player instanceof ServerPlayerEntity serverPlayer) {
                Criteria.PLAYER_INTERACTED_WITH_ENTITY.trigger(serverPlayer, stack, this);
            }

            return ActionResult.SUCCESS;
        }

        return super.interactMob(player, hand);
    }
}