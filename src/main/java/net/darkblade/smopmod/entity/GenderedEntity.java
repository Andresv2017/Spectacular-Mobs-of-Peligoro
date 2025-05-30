package net.darkblade.smopmod.entity;

import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.darkblade.smopmod.entity.interfaces.gender.Gendered;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

public abstract class GenderedEntity extends BaseEntity implements Gendered {

    private static final EntityDataAccessor<Boolean> DATA_MALE =
            SynchedEntityData.defineId(GenderedEntity.class, EntityDataSerializers.BOOLEAN);

    public GenderedEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_MALE, true); // por defecto macho
    }

    @Override
    public boolean isMale() {
        return this.entityData.get(DATA_MALE);
    }

    @Override
    public void setMale(boolean male) {
        this.entityData.set(DATA_MALE, male);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("IsMale", isMale());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setMale(tag.getBoolean("IsMale"));
    }

    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public boolean canMate(Animal other) {
        if (!(other instanceof GenderedEntity gendered)) return false;
        return this.isMale() != ((GenderedEntity) other).isMale(); // solo macho + hembra
    }

}
