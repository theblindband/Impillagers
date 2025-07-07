package com.impillagers.mod.block.custom;

import com.impillagers.mod.block.entity.ModBlockEntities;
import com.impillagers.mod.block.entity.WasteBasketBlockEntity;
import com.impillagers.mod.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class WasteBasketBlock extends BlockWithEntity {

    public static final IntProperty DUNG_LEVEL = IntProperty.of("dung_level", 0, 5);

    public WasteBasketBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(DUNG_LEVEL, 0)
        );
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(WasteBasketBlock::new);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DUNG_LEVEL);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient()) {
            return ActionResult.PASS;
        }

        ItemStack inHand = player.getMainHandStack();
        if (!inHand.isEmpty()) {
            return ActionResult.PASS;
        }

        int level = state.get(DUNG_LEVEL);
        if (level == 0) {
            return ActionResult.PASS;
        }

        int count = world.random.nextInt(3) + 2;
        ItemStack drop = new ItemStack(ModItems.DUNG_BALL, count);

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 1.0;
        double z = pos.getZ() + 0.5;
        ItemEntity entity = new ItemEntity(world, x, y, z, drop);
        world.spawnEntity(entity);

        int nextLevel = level - 1;
        world.setBlockState(
                pos,
                state.with(DUNG_LEVEL, nextLevel),
                Block.NOTIFY_ALL
        );
        world.playSound(null, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, SoundEvents.BLOCK_MUD_PLACE, SoundCategory.BLOCKS, 0.8f, 1.0f);
        return ActionResult.SUCCESS;
    }


    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            World world,
            BlockState state,
            BlockEntityType<T> type
    ) {
        if (world.isClient()) return null;
        if (type == ModBlockEntities.WASTE_BASKET_BLOCK_ENTITY) {
            return (w, pos, st, be) ->
                    WasteBasketBlockEntity.tick(w, pos, st, (WasteBasketBlockEntity) be);
        }
        return null;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WasteBasketBlockEntity(pos, state);
    }
}