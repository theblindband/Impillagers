package com.impillagers.mod.block.entity;

import com.impillagers.mod.block.custom.SafeBlock;
import com.impillagers.mod.screen.custom.SafeScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.stream.IntStream;

public class SafeBlockEntity extends BlockEntity implements ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {

    private UUID ownerUuid;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);


    public SafeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SAFE_BLOCK_ENTITY, pos, state);
    }

    public void setOwner(UUID uuid) {
        this.ownerUuid = uuid;
        markDirty();                  // ensure it saves
    }

    public void clearOwner() {
        this.ownerUuid = null;
        markDirty();
    }

    public boolean isOwner(UUID uuid) {
        return this.ownerUuid != null && this.ownerUuid.equals(uuid);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (!getCachedState().get(SafeBlock.LOCKED)) {
            return IntStream.range(0, size()).toArray();
        }
        return new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return !getCachedState().get(SafeBlock.LOCKED);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return !getCachedState().get(SafeBlock.LOCKED);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Safe");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new SafeScreenHandler(syncId, playerInventory, this.pos);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.inventory, registryLookup);

        if (ownerUuid != null) {
            nbt.putUuid("Owner", ownerUuid);
        }
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.inventory, registryLookup);

        if (nbt.containsUuid("Owner")) {
            this.ownerUuid = nbt.getUuid("Owner");
        }
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
