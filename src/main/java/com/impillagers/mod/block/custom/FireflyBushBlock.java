package com.impillagers.mod.block.custom;

import com.impillagers.mod.item.ModItems;
import com.impillagers.mod.particle.ModParticleTypes;
import com.impillagers.mod.sounds.ModSoundEvents;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class FireflyBushBlock extends PlantBlock implements Fertilizable {
    public static final MapCodec<DeadBushBlock> CODEC = createCodec(DeadBushBlock::new);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);
    public static final BooleanProperty LIT = Properties.LIT;
    public static final BooleanProperty USED = BooleanProperty.of("used");
    private int timeToRelight = 0;

    @Override
    public MapCodec<DeadBushBlock> getCodec() {
        return CODEC;
    }

    public FireflyBushBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LIT, Boolean.FALSE));
        this.setDefaultState(this.getDefaultState().with(USED, Boolean.FALSE));
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
        double d = (double) i + random.nextDouble();
        double e = (double) j + 0.7;
        double f = (double) k + random.nextDouble();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState currentState = world.getBlockState(pos);
        boolean used = state.get(USED);

        if (!used) {
            if (world.getLightLevel(LightType.BLOCK, pos) < 7 && world.getTimeOfDay() >= 13000 && world.getTimeOfDay() <= 23000) {
                BlockState newState = currentState.with(Properties.LIT, true);
                world.setBlockState(pos, newState);
                for (int l = 0; l < 1; l++) {
                    mutable.set(i + MathHelper.nextInt(random, -10, 10), j + random.nextInt(10), k + MathHelper.nextInt(random, -10, 10));
                    BlockState blockState = world.getBlockState(mutable);
                    if (!blockState.isFullCube(world, mutable)) {
                        world.playSound(pos.getX(), pos.getY(), pos.getZ(), ModSoundEvents.FIREFLY_BUSH, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
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
            } else {
                BlockState newState = currentState.with(Properties.LIT, false);
                world.setBlockState(pos, newState);
            }
        } else {
            if (timeToRelight > 0) {
                timeToRelight = timeToRelight - 1;
            } else { world.setBlockState(pos, state.with(USED, false));}
        }
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

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT, USED);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
            {
                if (!world.isClient()) {
                    boolean used = state.get(USED);
                    if (!used) {
                        ItemStack heldItem = player.getStackInHand(hand);

                        Item myItem = ModItems.EMPTY_JAR;
                        Item newItem = ModItems.FIREFLY_JAR;

                        if (heldItem.getItem() == myItem) {
                            heldItem.decrement(1);

                            ItemStack newItemStack = new ItemStack(newItem, 1);
                            player.giveItemStack(newItemStack);

                            world.setBlockState(pos, state.with(USED, true));
                            timeToRelight = 10000;
                            world.playSound(
                                    null,
                                    pos.getX(),
                                    pos.getY(),
                                    pos.getZ(),
                                    SoundEvents.BLOCK_BEEHIVE_ENTER,
                                    SoundCategory.NEUTRAL,
                                    0.75F,
                                    0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
                            );
                            return ItemActionResult.CONSUME;
                        } else {
                            world.setBlockState(pos, state.with(USED, true));
                            timeToRelight = 10000;
                            world.playSound(
                                    null,
                                    pos.getX(),
                                    pos.getY(),
                                    pos.getZ(),
                                    SoundEvents.ENTITY_BEE_POLLINATE,
                                    SoundCategory.NEUTRAL,
                                    0.8F,
                                    0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
                            );
                            return ItemActionResult.SUCCESS;
                        }
                    }
                }

            }
        return ItemActionResult.FAIL;
    }

}