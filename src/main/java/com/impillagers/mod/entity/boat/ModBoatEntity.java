package com.impillagers.mod.entity.boat;

import com.impillagers.mod.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class ModBoatEntity extends BoatEntity implements IModBoat {

    private static final TrackedData<Integer> MOD_BOAT_TYPE =
            DataTracker.registerData(ModBoatEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public ModBoatEntity(EntityType<? extends ModBoatEntity> type, World world) {
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

    @Override
    public void setModBoatType(int id) {
        this.dataTracker.set(MOD_BOAT_TYPE, id);
    }

    @Override
    public ModBoatType getModBoatType() {
        return ModBoatType.byId(this.dataTracker.get(MOD_BOAT_TYPE));
    }

    @Override
    public Item asItem() {
        return switch (this.getModBoatType()) {
            default -> ModItems.PURPLE_HEART_BOAT;
        };
    }
}