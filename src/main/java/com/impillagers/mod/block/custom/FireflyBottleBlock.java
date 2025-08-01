package com.impillagers.mod.block.custom;

import com.impillagers.mod.particle.ModParticleTypes;
import com.impillagers.mod.sounds.ModSoundEvents;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class FireflyBottleBlock extends LanternBlock {
    public static final BooleanProperty LIT = Properties.LIT;
    public static final BooleanProperty OPEN = Properties.OPEN;
    public static final BooleanProperty PARTICLES = BooleanProperty.of("particles");
    public static final BooleanProperty HANGING = Properties.HANGING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public FireflyBottleBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LIT, false).with(WATERLOGGED, false).with(HANGING, false).with(OPEN, false).with(PARTICLES, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT, HANGING, WATERLOGGED, OPEN, PARTICLES);
    }

    private boolean shouldBottleBeLit(World world, BlockPos pos, BlockState state) {
        return !world.isDay() && world.getLightLevel(pos) < 15 && !state.get(OPEN);
    }

    private boolean shouldBottleEmitParticles(World world, BlockState state) {
        return !world.isDay() && state.get(OPEN);
    }

    private void updateBottleLightState(BlockState state, World world, BlockPos pos) {
        boolean lit = shouldBottleBeLit(world, pos, state);
        boolean particles = shouldBottleEmitParticles(world, state);
        world.setBlockState(pos, state.with(LIT, lit).with(PARTICLES, particles), Block.NOTIFY_ALL);
    }

    private void scheduleNextTick(World world, BlockPos pos) {
        world.scheduleBlockTick(pos, this, 0);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient()) {
            scheduleNextTick(world, pos);
        }
        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        updateBottleLightState(state, world, pos);
        scheduleNextTick(world, pos);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(FireflyBottleBlock.PARTICLES)) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (int l = 0; l < 3; l++) {
                mutable.set(i + MathHelper.nextInt(random, -10, 10), j + random.nextInt(10), k + MathHelper.nextInt(random, -10, 10));
                BlockState blockState = world.getBlockState(mutable);
                if (!blockState.isFullCube(world, mutable)) {
                    world.addParticle(ModParticleTypes.FIREFLY, mutable.getX() + random.nextDouble(), mutable.getY() + random.nextDouble(), mutable.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            boolean open = state.get(OPEN);
            state = state.with(OPEN, !open);
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
            if (open) {
                world.playSound(null, pos, ModSoundEvents.FIREFLY_BOTTLE_CLOSE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            } else {
                world.playSound(null, pos, ModSoundEvents.FIREFLY_BOTTLE_OPEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
        return ActionResult.success(world.isClient());
    }
}