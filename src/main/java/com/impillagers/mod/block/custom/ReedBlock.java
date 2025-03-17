package com.impillagers.mod.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class ReedBlock extends TallPlantBlock implements Fertilizable, Waterloggable {
    private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public ReedBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager.getDefaultState().with(HALF, DoubleBlockHalf.LOWER).with(WATERLOGGED, Boolean.FALSE)
        );
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity) {
            Vec3d motion = entity.getVelocity();
            entity.setVelocity(motion.multiply(0.9, 1.0, 0.9)); // Reduce the speed by half
        }
    }


    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT) || floor.isIn(BlockTags.SMALL_DRIPLEAF_PLACEABLE)
                || world.getFluidState(pos.up()).isEqualAndStill(Fluids.WATER) && super.canPlantOnTop(floor, world, pos);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = super.getPlacementState(ctx);
        return blockState != null
                ? withWaterloggedState(ctx.getWorld(), ctx.getBlockPos(), blockState)
                : null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            BlockPos blockPos = pos.up();
            BlockState blockState = TallPlantBlock.withWaterloggedState(
                    world, blockPos, this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER)
            );
            world.setBlockState(blockPos, blockState, Block.NOTIFY_ALL);
        }
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            return super.canPlaceAt(state, world, pos);
        } else {
            BlockPos blockPos = pos.down();
            BlockState blockState = world.getBlockState(blockPos);
            return this.canPlantOnTop(blockState, world, blockPos);
        }
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF, WATERLOGGED);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        dropStack(world, pos, new ItemStack(this));
    }
}
