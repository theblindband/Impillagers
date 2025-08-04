package com.impillagers.mod.block.custom;

import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.particle.ModParticleTypes;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
//import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class FireflyBushBlock extends PlantBlock {
    public static final BooleanProperty LIT = Properties.LIT;
    public static final IntProperty COOLDOWN = IntProperty.of("cooldown", 0, 10);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);

    public FireflyBushBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LIT, false).with(COOLDOWN, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT, COOLDOWN);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    private boolean shouldBushBeLit(World world, BlockPos pos) {
        return !world.isDay() && world.getLightLevel(pos) < 13;
    }

    private void updateBushLightState(BlockState state, World world, BlockPos pos) {
        if (state.get(COOLDOWN) == 0) {
            boolean lit = shouldBushBeLit(world, pos);
            world.setBlockState(pos, state.with(LIT, lit), Block.NOTIFY_ALL);
        }
    }

    private void scheduleNextTick(World world, BlockPos pos, Random random) {
        int delay = MathHelper.nextInt(random, 150, 300);
        world.scheduleBlockTick(pos, this, delay);
    }

    private void scheduleCooldownTick(World world, BlockPos pos) {
        world.scheduleBlockTick(pos, this, 120);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient()) {
            if (state.get(COOLDOWN) == 0) {
                scheduleNextTick(world, pos, world.getRandom());
            } else {
                scheduleCooldownTick(world, pos);
            }
        }
        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(COOLDOWN) > 0) {
            int current = state.get(COOLDOWN);
            int next = current - 1;
            if (next <= 0) {
                BlockState newState = state.with(COOLDOWN, 0).with(LIT, shouldBushBeLit(world, pos));
                world.setBlockState(pos, newState, Block.NOTIFY_ALL);
                scheduleNextTick(world, pos, random);
            } else {
                BlockState newState = state.with(COOLDOWN, next).with(LIT, false);
                world.setBlockState(pos, newState, Block.NOTIFY_ALL);
                scheduleCooldownTick(world, pos);
            }
        } else {
            updateBushLightState(state, world, pos);
            scheduleNextTick(world, pos, random);
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            for (int l = 0; l < 1; l++) {
                mutable.set(i + MathHelper.nextInt(random, -10, 10), j + random.nextInt(10), k + MathHelper.nextInt(random, -10, 10));
                BlockState blockState = world.getBlockState(mutable);
                if (!blockState.isFullCube(world, mutable)) {
                    world.addParticle(
                            ModParticleTypes.FIREFLY,
                            mutable.getX() + random.nextDouble(),
                            mutable.getY() + random.nextDouble(),
                            mutable.getZ() + random.nextDouble(),
                            0.0,
                            0.0,
                            0.0
                    );
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(COOLDOWN) == 0) {
            updateBushLightState(state, world, pos);
            scheduleNextTick(world, pos, random);
        } else {
            int newCooldown = state.get(COOLDOWN) - 1;
            BlockState newState = state.with(COOLDOWN, newCooldown).with(LIT, false);
            world.setBlockState(pos, newState, Block.NOTIFY_ALL);
            scheduleCooldownTick(world, pos);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            if (state.get(LIT)) {
                world.setBlockState(pos, state.with(LIT, false), Block.NOTIFY_ALL);
                ItemStack heldItem = player.getStackInHand(hand);
                if (heldItem.getItem() == Items.GLASS_BOTTLE) {
                    heldItem.decrement(1);
                    ItemStack newItemStack = new ItemStack(ModItems.FIREFLY_BOTTLE, 1);
                    player.giveItemStack(newItemStack);
                    world.playSound(null,
                            pos.getX(), pos.getY(), pos.getZ(),
                            SoundEvents.BLOCK_BEEHIVE_ENTER,
                            SoundCategory.NEUTRAL,
                            0.75F,
                            0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                    BlockState newState = state.with(LIT, false).with(COOLDOWN, 10);
                    world.setBlockState(pos, newState, Block.NOTIFY_ALL);
                    scheduleCooldownTick(world, pos);
                    return ActionResult.CONSUME;
                } else {
                    world.playSound(null,
                            pos.getX(), pos.getY(), pos.getZ(),
                            SoundEvents.ENTITY_BEE_POLLINATE,
                            SoundCategory.NEUTRAL,
                            0.75F,
                            0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.SUCCESS;
    }
}