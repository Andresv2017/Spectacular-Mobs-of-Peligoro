package net.darkblade.smopmod.entity;

import net.darkblade.smopmod.entity.interfaces.sleep_system.ISleepingEntity;
import net.darkblade.smopmod.entity.util.SleepCycleController;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public abstract class BaseEntity extends TamableAnimal implements ISleepingEntity {

    protected static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(BaseEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> PREPARING_SLEEP = SynchedEntityData.defineId(BaseEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> AWAKENING = SynchedEntityData.defineId(BaseEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> WANDERING = SynchedEntityData.defineId(BaseEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(BaseEntity.class, EntityDataSerializers.BOOLEAN);

    protected final AnimationState preparingSleepState = new AnimationState();
    protected final AnimationState sleepState = new AnimationState();
    protected final AnimationState awakeingState = new AnimationState();

    protected final SleepCycleController<BaseEntity> sleepController =
            new SleepCycleController<>(this, preparingSleepState, sleepState, awakeingState, 20, 20);

    protected BaseEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SLEEPING, false);
        this.entityData.define(PREPARING_SLEEP, false);
        this.entityData.define(AWAKENING, false);
        this.entityData.define(WANDERING, false);
        this.entityData.define(HAS_EGG, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Sleeping", isSleeping());
        tag.putBoolean("PreparingSleep", isPreparingSleep());
        tag.putBoolean("Awakening", isAwakeing());
        tag.putBoolean("Wandering", isWandering());
        tag.putBoolean("IsMammal", this.isMammal);
        tag.putBoolean("HasEgg", this.hasEgg());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setSleeping(tag.getBoolean("Sleeping"));
        setPreparingSleep(tag.getBoolean("PreparingSleep"));
        setAwakeing(tag.getBoolean("Awakening"));
        setWandering(tag.getBoolean("Wandering"));
        this.isMammal = tag.getBoolean("IsMammal");
        this.setHasEgg(tag.getBoolean("HasEgg"));
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide()) {
            sleepController.tick(this.tickCount);
        }

        if (isOrderedToSit()) {
            getNavigation().stop();
            setDeltaMovement(Vec3.ZERO);
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (isSleeping() || isPreparingSleep() || isAwakeing()) {
            setDeltaMovement(Vec3.ZERO);
            getNavigation().stop();
            return;
        }
        super.travel(travelVector);
    }

    protected abstract SleepCycleController<BaseEntity> createSleepController();

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        if (!level().isClientSide()) {
            sleepController.interruptSleep("damage");
        }
        return result;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.isOwnedBy(player) && player.isShiftKeyDown()) {
            if (!isOrderedToSit() && !isWandering()) {
                this.setWandering(true);
                this.setOrderedToSit(false); // asegura que no quede en stay
                this.messageState("wandering", player);
            } else {
                this.setWandering(false);
                boolean willSit = !this.isOrderedToSit();
                this.setOrderedToSit(willSit);
                this.messageState(willSit ? "staying" : "following", player);
                if (willSit)
                    playStepSound(getOnPos(), level().getBlockState(getOnPos()));
            }
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    protected void messageState(String state, Player player) {
        player.displayClientMessage(this.getName().copy().append(" is now ").append(state), true);
    }

    // ISleepingEntity
    @Override public boolean isSleeping() { return entityData.get(SLEEPING); }
    @Override public void setSleeping(boolean value) { entityData.set(SLEEPING, value); }
    @Override public boolean isPreparingSleep() { return entityData.get(PREPARING_SLEEP); }
    @Override public void setPreparingSleep(boolean value) { entityData.set(PREPARING_SLEEP, value); }
    @Override public boolean isAwakeing() { return entityData.get(AWAKENING); }
    @Override public void setAwakeing(boolean value) { entityData.set(AWAKENING, value); }


    private boolean isFollowingOwner = false;

    public boolean isFollowingOwner() {return isFollowingOwner;}

    public void setFollowingOwner(boolean value) {this.isFollowingOwner = value;}

    public boolean isWandering() { return entityData.get(WANDERING); }
    public void setWandering(boolean wandering) { entityData.set(WANDERING, wandering); }



    @Override
    public void aiStep() {
        if (isSleeping() || isPreparingSleep() || isAwakeing()) {
            setTarget(null);
        }
        super.aiStep();
    }

    // --- CAMPOS DE REPRODUCCIÓN ---

    protected boolean isMammal = false;
    protected boolean hasEgg = false;

    public boolean isMammal() {
        return this.isMammal;
    }

    public void setMammal(boolean isMammal) {
        this.isMammal = isMammal;
    }

    public boolean hasEgg() {
        return this.entityData.get(HAS_EGG);
    }

    public void setHasEgg(boolean value) {
        this.entityData.set(HAS_EGG, value);
    }

    @Nullable
    public BlockPos tryLayEgg(Block eggBlock) {
        if (!this.hasEgg() || this.isMammal() || !this.onGround()) return null;

        BlockPos pos = this.blockPosition();
        Level level = this.level();

        boolean canPlace = level.getBlockState(pos).isAir() && level.getBlockState(pos.below()).isSolid();

        if (canPlace) {
            level.setBlock(pos, eggBlock.defaultBlockState(), 3);
            level.playSound(null, pos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 1.0F, 1.0F);
            this.setHasEgg(false);
            return pos;
        }

        return null;
    }

}

