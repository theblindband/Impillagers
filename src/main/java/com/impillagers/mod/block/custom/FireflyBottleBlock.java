package com.impillagers.mod.block.custom;

import com.impillagers.mod.particle.ModParticleTypes;
import com.impillagers.mod.sounds.ModSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class FireflyBottleBlock extends LanternBlock {
    public static final BooleanProperty LIT = Properties.LIT;
    public static final BooleanProperty HANGING = Properties.HANGING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public FireflyBottleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LIT, Boolean.TRUE).with(HANGING, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        double d = (double) i + random.nextDouble();
        double e = (double) j + 0.7;
        double f = (double) k + random.nextDouble();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState currentState = world.getBlockState(pos);
        boolean lit = state.get(LIT);

        if(!lit) {
            if (world.getLightLevel(LightType.BLOCK, pos) < 11 && world.getTimeOfDay() >= 13000 && world.getTimeOfDay() <= 23000) {
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), ModSoundEvents.FIREFLY_BUSH, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
                for (int l = 0; l < 1; l++) {
                    mutable.set(i + MathHelper.nextInt(random, -5, 5), j + MathHelper.nextInt(random, -5, 5), k + MathHelper.nextInt(random, -5, 5));
                    BlockState blockState = world.getBlockState(mutable);
                    if (!blockState.isFullCube(world, mutable)) {
                        world.addParticle(
                                ModParticleTypes.FIREFLY,
                                (double) mutable.getX() + random.nextDouble(),
                                (double) mutable.getY() + random.nextDouble(),
                                (double) mutable.getZ() + random.nextDouble(),
                                0.0,
                                0.0,
                                0.0
                        );
                    }
                }
                for (int l = 0; l < 1; l++) {
                    mutable.set(i + MathHelper.nextInt(random, -5, 5), j + MathHelper.nextInt(random, -5, 5), k + MathHelper.nextInt(random, -5, 5));
                    BlockState blockState = world.getBlockState(mutable);
                    if (!blockState.isFullCube(world, mutable)) {
                        world.addParticle(
                                ModParticleTypes.FIREFLY,
                                (double) mutable.getX() + random.nextDouble(),
                                (double) mutable.getY() + random.nextDouble(),
                                (double) mutable.getZ() + random.nextDouble(),
                                0.0,
                                0.0,
                                0.0
                        );
                    }
                }
            }
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        boolean lit = state.get(LIT);

        if(lit){
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }else{
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }

        if (player.canModifyBlocks()) {
            if (world.isClient) {
                return ActionResult.SUCCESS;
            } else {
                BlockState litState = state.cycle(LIT);
                world.setBlockState(pos, litState, Block.NOTIFY_LISTENERS);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, litState));
                return ActionResult.CONSUME;
            }
        } else {
            return super.onUse(state, world, pos, player, hit);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT, HANGING, WATERLOGGED);
    }
}
