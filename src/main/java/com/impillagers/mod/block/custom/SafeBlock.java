package com.impillagers.mod.block.custom;

import com.impillagers.mod.block.entity.SafeBlockEntity;
import com.impillagers.mod.sounds.ModSoundEvents;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
//TODO: DEPRECATED METHOD USED
public class SafeBlock extends BlockWithEntity implements BlockEntityProvider{
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LOCKED = Properties.LOCKED;

    private static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 1.0, 2.0, 14.0, 13.0, 14.0);

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.success(true);
        }

        SafeBlockEntity be = (SafeBlockEntity) world.getBlockEntity(pos);
        if (be == null) return ActionResult.FAIL;

        boolean locked = state.get(LOCKED);
        UUID playerId = player.getUuid();

        if (!player.isSneaking()) {
            if (!locked || be.isOwner(playerId)) {
                player.openHandledScreen(be);
                world.playSound(null,
                        pos,
                        SoundEvents.BLOCK_IRON_DOOR_OPEN,
                        SoundCategory.BLOCKS,
                        1.0F,
                        1.0F);
                return ActionResult.SUCCESS;
            } else {
                player.sendMessage(
                        Text.translatable("message.impillagers.safe.locked"),
                        true
                );
                return ActionResult.FAIL;
            }
        }

        if (locked) {
            if (be.isOwner(playerId)) {
                world.setBlockState(pos,
                        state.with(SafeBlock.LOCKED, false),
                        Block.NOTIFY_ALL);
                be.clearOwner();
                world.playSound(null,
                        pos,
                        ModSoundEvents.SAFE_UNLOCK,
                        SoundCategory.BLOCKS,
                        1.0F,
                        1.0F);

                player.sendMessage(
                        Text.translatable("message.impillagers.safe.unlocked"),
                        true
                );
            } else {
                player.sendMessage(
                        Text.translatable("message.impillagers.safe.not_owner"),
                        true
                );
            }
        } else {
            world.setBlockState(pos,
                    state.with(SafeBlock.LOCKED, true),
                    Block.NOTIFY_ALL);
            be.setOwner(playerId);
            world.playSound(null,
                    pos,
                    ModSoundEvents.SAFE_LOCK,
                    SoundCategory.BLOCKS,
                    1.0F,
                    1.0F);

            player.sendMessage(
                    Text.translatable("message.impillagers.safe.locked_success"),
                    true
            );
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof SafeBlockEntity){
                ItemScatterer.spawn(world, pos, ((SafeBlockEntity) blockEntity));
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    public SafeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(LOCKED, false));
    }

    //Hit Box
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    //Facing Block State
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LOCKED);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SafeBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
