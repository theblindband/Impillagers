package com.impillagers.mod.item.boat;

import com.impillagers.mod.entity.boat.ModBoatEntity;
import com.impillagers.mod.entity.boat.ModBoatType;
import com.impillagers.mod.entity.boat.ModChestBoatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.function.Predicate;

public class ModBoatItem extends Item {
    private static final Predicate<Entity> RIDERS = EntityPredicates.EXCEPT_SPECTATOR.and(Entity::canHit);

    private final boolean chest;
    private final ModBoatType variant;
    private final EntityType<? extends BoatEntity> boatType;
    private final EntityType<? extends BoatEntity> chestBoatType;

    public ModBoatItem(boolean chest, ModBoatType variant, EntityType<? extends ModBoatEntity> boatType, EntityType<? extends ModChestBoatEntity> chestBoatType, Settings settings) {
        super(settings);
        this.chest = chest;
        this.variant = variant;
        this.boatType = boatType;
        this.chestBoatType = chestBoatType;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        HitResult hit = raycast(world, user, RaycastContext.FluidHandling.ANY);

        if (hit.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(stack);
        }

        BoatEntity boat = createEntity(world, hit, stack, user);
        if (boat instanceof ModBoatEntity mb) {
            mb.setModBoatType(variant.ordinal());
        } else if (boat instanceof ModChestBoatEntity mcb) {
            mcb.setModBoatType(variant.ordinal());
        }

        boat.setYaw(user.getYaw());

        if (!world.isSpaceEmpty(boat, boat.getBoundingBox())) {
            return TypedActionResult.fail(stack);
        }

        if (!world.isClient) {
            world.spawnEntity(boat);
            world.emitGameEvent(user, GameEvent.ENTITY_PLACE, hit.getPos());
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(stack, world.isClient());
    }

    private BoatEntity createEntity(World world, HitResult hit, ItemStack stack, PlayerEntity player
    ) {
        Vec3d pos = hit.getPos();
        EntityType<? extends BoatEntity> typeToUse = chest ? chestBoatType : boatType;
        BoatEntity boat = typeToUse.create(world);
        if (boat != null){
            boat.refreshPositionAndAngles(pos.x, pos.y, pos.z, player.getYaw(), 0);
        }

        if (world instanceof ServerWorld server) {
            EntityType.copier(server, stack, player).accept(boat);
        }

        return boat;
    }
}
