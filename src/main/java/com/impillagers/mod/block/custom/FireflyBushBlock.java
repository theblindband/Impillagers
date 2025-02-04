package com.impillagers.mod.block.custom;

import com.impillagers.mod.particle.ModParticleTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class FireflyBushBlock extends PlantBlock {
    public static final MapCodec<DeadBushBlock> CODEC = createCodec(DeadBushBlock::new);
    protected static final float field_31080 = 6.0F;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);
    public static final BooleanProperty LIT = Properties.LIT;

    @Override
    public MapCodec<DeadBushBlock> getCodec() {
        return CODEC;
    }

    public FireflyBushBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LIT, Boolean.valueOf(false)));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        double d = (double)i + random.nextDouble();
        double e = (double)j + 0.7;
        double f = (double)k + random.nextDouble();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState currentState = world.getBlockState(pos);

        if(world.getLightLevel(LightType.BLOCK, pos) < 7 && world.getTimeOfDay() >= 13000 && world.getTimeOfDay() <= 23000) {
            BlockState newState = currentState.with(Properties.LIT, true);
            world.setBlockState(pos, newState);
            for (int l = 0; l < 1; l++) {
                mutable.set(i + MathHelper.nextInt(random, -10, 10), j + random.nextInt(10), k + MathHelper.nextInt(random, -10, 10));
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
        }else{
            BlockState newState = currentState.with(Properties.LIT, false);
            world.setBlockState(pos, newState);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}