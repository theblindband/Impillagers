package com.impillagers.mod.block.custom;

import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.List;

public class WasteBasketBlock extends Block implements InventoryProvider {

    private static final VoxelShape NS_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0F, 0.0F, 0.0F, 2.0F, 16.0F, 16.0F),
            Block.createCuboidShape(14.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F),
            Block.createCuboidShape(2.0F, 0.0F, 0.0F, 14.0F, 12.0F, 2.0F),
            Block.createCuboidShape(2.0F, 0.0F, 14.0F, 14.0F, 12.0F, 16.0F),
            Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 2.0F, 16.0F));

    private static final VoxelShape EW_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 2.0F),
            Block.createCuboidShape(14.0F, 0.0F, 2.0F, 16.0F, 12.0F, 14.0F),
            Block.createCuboidShape(0.0F, 0.0F, 14.0F, 16.0F, 16.0F, 16.0F),
            Block.createCuboidShape(0.0F, 0.0F, 2.0F, 2.0F, 12.0F, 14.0F),
            Block.createCuboidShape(0.0F, 0.0F, 0.0F, 16.0F, 2.0F, 6.0F));

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty DUNG_LEVEL = IntProperty.of("dung_level", 0, 4);
    private static final int SCAN_RADIUS = 7;
    private static final int SCAN_HEIGHT = 3;

    public WasteBasketBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(DUNG_LEVEL, 0));
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {return state.get(DUNG_LEVEL) < 4;}

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isClient) return;

        Box box = new Box(pos.getX() - SCAN_RADIUS, pos.getY() - SCAN_HEIGHT, pos.getZ() - SCAN_RADIUS, pos.getX() + SCAN_RADIUS, pos.getY() + SCAN_HEIGHT, pos.getZ() + SCAN_RADIUS);
        List<LivingEntity> found = world.getEntitiesByClass(LivingEntity.class, box, e -> e.getType().isIn(ModTags.EntityTypes.DROPS_DUNG));

        if (found.size() >= 3) {
            int current = state.get(DUNG_LEVEL);
            if (current < 4) {
                int next = current + 1;
                world.setBlockState(pos, state.with(DUNG_LEVEL, next), Block.NOTIFY_ALL);
                world.playSound(null, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, SoundEvents.BLOCK_MUD_PLACE, SoundCategory.BLOCKS, 0.8f, 1.0f);
            }
        }
    }

    public ItemStack extractDung(World world, BlockPos pos, BlockState state, int amount) {
        int level = state.get(DUNG_LEVEL);
        if (level <= 0) {
            return ItemStack.EMPTY;
        }

        int nextLevel = level - 1;

        world.setBlockState(pos, state.with(DUNG_LEVEL, nextLevel), Block.NOTIFY_ALL);
        world.playSound(null, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, SoundEvents.BLOCK_MUD_PLACE, SoundCategory.BLOCKS, 0.8f, 1.0f);

    return new ItemStack(ModItems.DUNG_BALL, amount);
}

    @Override
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos) {
        return new WasteBasketInventory(state, world, pos);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            int level = state.get(DUNG_LEVEL);
            if (level > 0) {
                int totalDung = 0;
                for (int i = 0; i < level; i++) {
                    totalDung += world.random.nextInt(4) + 1;
                }
                if (totalDung > 0) {
                ItemScatterer.spawn(world, pos, new SimpleInventory(new ItemStack(ModItems.DUNG_BALL, totalDung)));
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case EAST, WEST -> EW_SHAPE;
            default -> NS_SHAPE;
        };
    }

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
        builder.add(FACING, DUNG_LEVEL);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient()) {return ActionResult.PASS;}

        ItemStack inHand = player.getMainHandStack();
        if (!inHand.isEmpty()) {return ActionResult.PASS;}

        ItemStack extracted = extractDung(world, pos, state, world.random.nextInt(4) + 1);
        if (!extracted.isEmpty()) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 1.0;
            double z = pos.getZ() + 0.5;
            ItemEntity entity = new ItemEntity(world, x, y, z, extracted);
            world.spawnEntity(entity);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private record WasteBasketInventory(BlockState state, WorldAccess world, BlockPos pos) implements SidedInventory {
        @Override
        public int size() {return 1;}

        @Override
        public boolean isEmpty() {return state.get(DUNG_LEVEL) <= 0;}

        @Override
        public ItemStack getStack(int slot) {return isEmpty() ? ItemStack.EMPTY : new ItemStack(ModItems.DUNG_BALL);}

        @Override
        public ItemStack removeStack(int slot, int amount) {
            if (isEmpty() || slot != 0 || amount < 1) {
                return ItemStack.EMPTY;
            }
            WasteBasketBlock block = (WasteBasketBlock) state.getBlock();
            return block.extractDung((World) world, pos, state, Math.min(amount, state.get(DUNG_LEVEL)));
        }
        @Override
        public ItemStack removeStack(int slot) {return removeStack(slot, 1);}

        @Override
        public void setStack(int slot, ItemStack stack) {}

        @Override
        public void markDirty() {}

        @Override
        public boolean canPlayerUse(PlayerEntity player) {return false;}
        @Override
        public void clear() {}

        @Override
        public int[] getAvailableSlots(Direction side) {return side == Direction.DOWN ? new int[]{0} : new int[0];}

        @Override
        public boolean canInsert(int slot, ItemStack stack, Direction dir) {return false;}

        @Override
        public boolean canExtract(int slot, ItemStack stack, Direction dir) {return dir == Direction.DOWN && slot == 0 && !isEmpty();}
    }
}