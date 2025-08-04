package com.impillagers.mod.block.entity;

import com.impillagers.mod.block.custom.WasteBasketBlock;
import com.impillagers.mod.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class WasteBasketBlockEntity extends BlockEntity {
    private static final int SCAN_RADIUS   = 7;
    private static final int SCAN_HEIGHT   = 3;
    private static final int SCAN_INTERVAL = 100;

    private int tickCounter = 0;

    public WasteBasketBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WASTE_BASKET_BLOCK_ENTITY, pos, state);
    }


    public static void tick(World world, BlockPos pos, BlockState state, WasteBasketBlockEntity be
    ) {
        if (world.isClient()) return;

        if (++be.tickCounter < SCAN_INTERVAL) return;
        be.tickCounter = 0;

        Box box = new Box(
                pos.getX() - SCAN_RADIUS, pos.getY() - SCAN_HEIGHT, pos.getZ() - SCAN_RADIUS,
                pos.getX() + SCAN_RADIUS, pos.getY() + SCAN_HEIGHT, pos.getZ() + SCAN_RADIUS
        );
        List<LivingEntity> found = world.getEntitiesByClass(LivingEntity.class, box, e -> e.getType().isIn(ModTags.EntityTypes.DROPS_DUNG));

        if (found.size() >= 3 && world.random.nextInt(50) == 0) {
            int current = state.get(WasteBasketBlock.DUNG_LEVEL);
            if (current < 4) {
                int next = current + 1;

                BlockState updated = state.with(WasteBasketBlock.DUNG_LEVEL, next);
                world.setBlockState(pos, updated, Block.NOTIFY_ALL);
                world.playSound(null, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, SoundEvents.BLOCK_MUD_PLACE, SoundCategory.BLOCKS, 0.8f, 1.0f);
            }
        }
    }
}
