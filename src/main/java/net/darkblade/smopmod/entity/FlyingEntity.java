package net.darkblade.smopmod.entity;

import net.darkblade.smopmod.entity.interfaces.flight.FlightState;
import net.darkblade.smopmod.entity.navigation.NewFlyingPathNavigation;
import net.darkblade.smopmod.entity.navigation.NewGroundPathNavigation;
import net.darkblade.smopmod.entity.util.flight.FlightStateController;
import net.darkblade.smopmod.entity.util.flight.IFlyingStateConfigurable;
import net.darkblade.smopmod.packet.InitPackets;
import net.darkblade.smopmod.packet.StoCSyncFlying;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Set;

public class FlyingEntity extends GenderedEntity implements IFlyingStateConfigurable {

    protected static final EntityDataAccessor<Boolean> IS_FLYING =
            SynchedEntityData.defineId(FlyingEntity.class, EntityDataSerializers.BOOLEAN);


    public FlyingEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_FLYING, false);
        this.entityData.define(FLIGHT_STATE, FlightState.GROUND.ordinal());
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
        if (!tag.contains("FlightState")) {
            this.setFlightState(FlightState.GROUND);
        }
    }

    public void switchNavigation(boolean fly) {
        //System.out.println("[FlyingEntity] switchNavigation → fly: " + fly + ", tick: " + this.tickCount);

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

    private boolean lastLoggedFlying = false;
    private FlightState lastLoggedState = null;

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide()) {
            flightStateController.update();

            if (this.tickCount % 40 == 0) {
                System.out.println("[STATE] Tick " + this.tickCount + " → FLIGHT_STATE: " + this.getFlightState()
                        + ", isFlying: " + this.isFlying()
                        + ", wantsToFly: " + this.wantsToFly()
                        + ", goalsRequireFlying: " + this.getGoalsRequireFlying());
            }
        }

        // 🔇 Se eliminan logs de sincronización
        FlightState current = this.getFlightState();
        if (current != lastLoggedState) {
            lastLoggedState = current;
        }

        boolean flying = this.entityData.get(IS_FLYING);
        if (flying != lastLoggedFlying) {
            lastLoggedFlying = flying;
        }

        // 🔇 Animaciones de debug solo si estás en el cliente
        if (this.tickCount % 20 == 0 && this.level().isClientSide) {
            this.logGroundAnimations(this.tickCount); // desde BaseEntity
            this.debugActiveAnimations();
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
    protected final AnimationState boostAnimationState = new AnimationState();

    public AnimationState getFlyIdleAnimationState() { return flyIdleAnimationState; }
    public AnimationState getFlyMoveAnimationState() { return flyMoveAnimationState; }
    public AnimationState getStartFlightAnimationState() { return startFlightAnimationState; }
    public AnimationState getLandingAnimationState() { return landingAnimationState; }
    public AnimationState getBoostAnimationState() { return boostAnimationState; }

    @Override
    public Set<EntityType<?>> getInterruptingEntityTypes() {return Collections.emptySet();}







    protected static final EntityDataAccessor<Integer> FLIGHT_STATE =
            SynchedEntityData.defineId(FlyingEntity.class, EntityDataSerializers.INT);

    public FlightState getFlightState() {
        return FlightState.values()[this.entityData.get(FLIGHT_STATE)];
    }

    public void setFlightState(FlightState state) {
        if (!this.level().isClientSide) {
            this.entityData.set(FLIGHT_STATE, state.ordinal());
        }
    }

    @Override
    public int getStartFlightDuration() { return 20; }

    @Override
    public int getBoostDuration() { return 10; }

    @Override
    public int getLandingDuration() { return 20; }

    @Override
    public double getFlightMoveThreshold() { return 0.03; }

    @Override
    public boolean wantsToFly() {
        boolean result = this.getGoalsRequireFlying() || this.getTarget() != null || this.isFollowingOwner();
        //System.out.println("[AI] wantsToFly() → " + result + " | GoalsRequireFlying: " + this.getGoalsRequireFlying());
        return result;
    }

    private final FlightStateController flightStateController = new FlightStateController(this);

    public FlightStateController getFlightStateController() {
        return this.flightStateController;
    }


    private FlightState lastSyncedState = null;

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);

        if (FLIGHT_STATE.equals(key)) {
            stopAllFlightAnimations(); // 🔁 Siempre detiene todas antes de activar una nueva

            FlightState state = this.getFlightState();

            // Evita activar animaciones de vuelo si no está volando
            if (!this.isFlying() && state != FlightState.GROUND) {
                System.out.println("[DEBUG] Cancelando animación aérea en tierra: " + state);
                return;
            }

            switch (state) {
                case GROUND -> {
                    // Nada aquí. BaseEntity activa animaciones terrestres.
                }
                case START_FLIGHT -> startFlightAnimationState.start(this.tickCount);
                case FLY_IDLE -> flyIdleAnimationState.start(this.tickCount);
                case BOOST -> boostAnimationState.start(this.tickCount);
                case FLY_MOVE -> flyMoveAnimationState.start(this.tickCount);
                case LANDING -> landingAnimationState.start(this.tickCount);
            }
        }
    }



    protected void stopAllGroundAnimations() {
        idleAnimationState.stop();
        walkAnimationState.stop();
        sprintAnimationState.stop();
        deathAnimationState.stop();
    }


    private void stopAllFlightAnimations() {
        startFlightAnimationState.stop();
        flyIdleAnimationState.stop();
        flyMoveAnimationState.stop();
        boostAnimationState.stop();
        landingAnimationState.stop();
    }

    public void debugActiveAnimations() {
        //System.out.println("[DEBUG] Animaciones activas en tick " + this.tickCount + ":");

        if (startFlightAnimationState.isStarted()) System.out.println(" - start_flight");
        if (flyIdleAnimationState.isStarted()) System.out.println(" - fly_idle");
        if (flyMoveAnimationState.isStarted()) System.out.println(" - fly_move");
        if (boostAnimationState.isStarted()) System.out.println(" - boost");
        if (landingAnimationState.isStarted()) System.out.println(" - landing");
    }

    protected boolean goalsRequireFlying = false;

    public boolean getGoalsRequireFlying() {
        return goalsRequireFlying;
    }

    public void setGoalsRequireFlying(boolean flying) {
        this.goalsRequireFlying = flying;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason,
                                        @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {

        BlockPos posBelow = this.blockPosition().below();
        boolean hasSolidGround = world.getBlockState(posBelow).entityCanStandOn(world, posBelow, this);

        if (hasSolidGround) {
            this.setFlightState(FlightState.GROUND);
            this.setFlying(false);
            this.setNoGravity(false);
            this.switchNavigation(false);
        } else {
            this.setFlightState(FlightState.FLY_IDLE); // 🛠️ Empieza flotando si no hay suelo
            this.setFlying(true);
            this.setNoGravity(true);
            this.switchNavigation(true);
        }

        // 🔁 Reforzar animación segura
        this.stopAllFlightAnimations();
        this.stopAllGroundAnimations();

        // 🔐 Reforzar sincronía lógica con el controlador (si accedes al campo o usas setter)
        if (!this.level().isClientSide()) {
            this.getFlightStateController().resetStateTracking(); // <-- este método deberías tenerlo
        }

        System.out.println("[DEBUG] Finalize Spawn → FLIGHT_STATE: " + this.getFlightState() + ", isFlying: " + this.isFlying());
        return super.finalizeSpawn(world, difficulty, reason, data, tag);
    }


}
