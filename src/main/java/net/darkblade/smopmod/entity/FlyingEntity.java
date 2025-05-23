package net.darkblade.smopmod.entity;

import net.darkblade.smopmod.entity.navigation.NewFlyingPathNavigation;
import net.darkblade.smopmod.entity.navigation.NewGroundPathNavigation;
import net.darkblade.smopmod.packet.InitPackets;
import net.darkblade.smopmod.packet.StoCSyncFlying;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

public class FlyingEntity extends GenderedEntity {

    protected static final EntityDataAccessor<Boolean> IS_FLYING =
            SynchedEntityData.defineId(FlyingEntity.class, EntityDataSerializers.BOOLEAN);


    public FlyingEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    private int ticksSinceSpawn = 0;
    private int stableFlyTicks = 0;
    private static final int FLY_TOGGLE_THRESHOLD = 5;

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_FLYING, false);
    }

    public boolean isFlying() {return this.entityData.get(IS_FLYING);}

    public void setFlying(boolean value) {this.entityData.set(IS_FLYING, value);}


    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("IsFlying", isFlying());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setFlying(tag.getBoolean("IsFlying"));
    }

    public void switchNavigation(boolean fly) {
        System.out.println("[FlyingEntity] switchNavigation → fly: " + fly + ", tick: " + this.tickCount);

        if (fly) {
            this.moveControl = new FlyingMoveControl(this, 20, false);
            this.navigation = new NewFlyingPathNavigation(this, this.level());
            this.jumpControl.jump(); // Ayuda al despegue
            this.setFlying(true);
        } else {
            this.moveControl = new MoveControl(this);
            this.navigation = new NewGroundPathNavigation(this, this.level());
            this.setFlying(false);
        }

        this.setNoGravity(fly);

        if (!this.level().isClientSide()) {
            InitPackets.sendToAll(new StoCSyncFlying(this.getId(), this.isFlying()));
        }
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return !this.isFlying() && super.causeFallDamage(distance, damageMultiplier, source);
    }

    protected boolean shouldFly() {return !this.isOrderedToSit() && !this.onGround();}

    private boolean lastLoggedFlying = false;

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            ticksSinceSpawn++;

            boolean shouldBeFlying = this.shouldFly();

            // Diagnóstico
            if (ticksSinceSpawn <= 40) {
                System.out.println("[FlyingEntity] Tick " + ticksSinceSpawn + " → onGround: " + this.onGround() + ", isFlying: " + this.isFlying());
            }

            // Aplicar protección contra oscilación
            if (shouldBeFlying == this.isFlying()) {
                stableFlyTicks = 0; // ya está en el estado correcto, reiniciar contador
            } else {
                stableFlyTicks++;
                if (stableFlyTicks >= FLY_TOGGLE_THRESHOLD) {
                    System.out.println("[FlyingEntity] Cambio forzado → shouldFly: " + shouldBeFlying + ", isFlying: " + this.isFlying());
                    this.switchNavigation(shouldBeFlying);
                    stableFlyTicks = 0;
                }
            }
        }

        if (!this.level().isClientSide) {
            boolean current = this.entityData.get(IS_FLYING);
            if (current != this.lastLoggedFlying) {
                System.out.println("[SERVER] Tick " + this.tickCount + " → IS_FLYING: " + current);
                this.lastLoggedFlying = current;
            }
        }


    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);

        if (key.equals(IS_FLYING)) {
            boolean flying = this.entityData.get(IS_FLYING);
            System.out.println("[CLIENT] Tick " + this.tickCount + " → IS_FLYING: " + flying);
        }
    }


    @Override
    public void travel(Vec3 travelVector) {
        if (this.isFlying() && this.isControlledByLocalInstance()) {
            Vec3 motion = this.getDeltaMovement();

            // Limita la velocidad descendente para suavizar caída
            if (motion.y < -0.2D) {
                motion = new Vec3(motion.x, -0.2D, motion.z);
            }

            if (this.isInWater()) {
                this.moveRelative(0.02F, travelVector);
                this.move(MoverType.SELF, motion);
                this.setDeltaMovement(motion.scale(0.8F));
            } else if (this.isInLava()) {
                this.moveRelative(0.02F, travelVector);
                this.move(MoverType.SELF, motion);
                this.setDeltaMovement(motion.scale(0.5F));
            } else {
                this.moveRelative(this.getSpeed(), travelVector);
                this.move(MoverType.SELF, motion);
                this.setDeltaMovement(motion.scale(0.91F));
            }
        } else {
            // Movimiento terrestre o montado
            super.travel(travelVector);
        }
    }

    protected final AnimationState flyIdleAnimationState = new AnimationState();
    protected final AnimationState flyMoveAnimationState = new AnimationState();
    protected final AnimationState startFlightAnimationState = new AnimationState();
    protected final AnimationState landingAnimationState = new AnimationState();


    public AnimationState getFlyIdleAnimationState() { return flyIdleAnimationState; }
    public AnimationState getFlyMoveAnimationState() { return flyMoveAnimationState; }
    public AnimationState getStartFlightAnimationState() { return startFlightAnimationState; }
    public AnimationState getLandingAnimationState() { return landingAnimationState; }



    @Override
    public Set<EntityType<?>> getInterruptingEntityTypes() {return Collections.emptySet();}
}
