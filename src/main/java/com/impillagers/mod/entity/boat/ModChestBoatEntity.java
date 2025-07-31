package com.impillagers.mod.entity.boat;

import com.impillagers.mod.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class ModChestBoatEntity extends ChestBoatEntity implements IModBoat {

    private static final TrackedData<Integer> MOD_BOAT_TYPE = DataTracker.registerData(ModChestBoatEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public ModChestBoatEntity(EntityType<? extends ModChestBoatEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MOD_BOAT_TYPE, 0);
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("ModBoatType", this.getModBoatType().ordinal());
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setModBoatType(ModBoatType.byId(nbt.getInt("ModBoatType")).ordinal());
    }

    public void setModBoatType(int id) {
        this.dataTracker.set(MOD_BOAT_TYPE, id);
    }

    public ModBoatType getModBoatType() {
        return ModBoatType.byId(this.dataTracker.get(MOD_BOAT_TYPE));
    }

    @Override
    public Item asItem() {
        return switch (this.getModBoatType()) {
            default -> ModItems.PURPLE_HEART_CHEST_BOAT;
        };
    }
}