package net.darkblade.smopmod.entity;

import net.darkblade.smopmod.entity.navigation.FlyingPathNavigation;
import net.darkblade.smopmod.entity.navigation.GroundPathNavigation;
import net.darkblade.smopmod.packet.InitPackets;
import net.darkblade.smopmod.packet.StoCSyncFlying;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class FlyingEntity extends GenderedEntity{

    private static final EntityDataAccessor<Boolean> GOAL_WANT_FLYING = SynchedEntityData.defineId(FlyingEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(FlyingEntity.class, EntityDataSerializers.BOOLEAN);


    public void setGoalsRequireFlying(boolean b) { this.getEntityData().set(GOAL_WANT_FLYING, b); }
    public boolean getGoalsRequireFlying() { return this.getEntityData().get(GOAL_WANT_FLYING); }

    public void setIsFlying(boolean b) { this.getEntityData().set(FLYING, b); }
    public boolean getIsFlying() { return this.getEntityData().get(FLYING); }


    public FlyingEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);

        this.setPathfindingMalus(BlockPathTypes.WATER, -8f);
        this.setPathfindingMalus(BlockPathTypes.LAVA, -8f);

        this.moveControl = new MoveControl(this);
        this.navigation = new GroundPathNavigation(this, this.level());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(GOAL_WANT_FLYING, !this.onGround()); // o false por defecto
        this.entityData.define(FLYING, true); // comienza volando
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("GOAL_WANT_FLYING", getGoalsRequireFlying());
        tag.putBoolean("FLYING", getIsFlying());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("GOAL_WANT_FLYING")) setGoalsRequireFlying(tag.getBoolean("GOAL_WANT_FLYING"));
        if (tag.contains("FLYING")) setIsFlying(tag.getBoolean("FLYING"));
    }

    public void switchNavigation() {
        boolean wasFlying = getIsFlying();

        if (moveControl instanceof FlyingMoveControl) {
            this.moveControl = new MoveControl(this);
            this.navigation = new GroundPathNavigation(this, this.level());
            this.setIsFlying(false);
        } else {
            this.moveControl = new FlyingMoveControl(this, 20, false);
            this.navigation = new FlyingPathNavigation(this, this.level()).canFloat(true);
            this.jumpControl.jump();
            this.setIsFlying(true);
        }

        this.setNoGravity(getIsFlying());

        if (!level().isClientSide()) {
            InitPackets.sendToAll(new StoCSyncFlying(this.getId(), this.getIsFlying()));
        }
    }


    // ===== MÉTODO PARA DETECTAR CONTACTO CON EL SUELO =====

    protected boolean isTouchingSolidGround() {
        BlockPos pos = this.blockPosition().below();
        return !this.level().getBlockState(pos).isAir() && this.getDeltaMovement().y <= 0;
    }

    // ===== CONFIGURABLE POR ENTIDAD FINAL =====

    protected int maxGroundTicks() {
        return 80; // tiempo en tierra antes de despegar
    }

    protected int maxGroundedTicksWhileFlying() {
        return 10; // ticks tocando el suelo antes de forzar aterrizaje
    }

    // ===== ESTADO INTERNO =====

    private int groundTicks = 0;
    private int groundedWhileFlyingTicks = 0;

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide()) {
            this.handleAutoNavigationSwitch();
        }

        this.updateAnimations();
    }

    protected void handleAutoNavigationSwitch() {
        boolean isFlying = getIsFlying();
        boolean isTouching = isTouchingSolidGround();

        if (isFlying) {
            groundTicks = 0;

            if (isTouching) {
                groundedWhileFlyingTicks++;
                if (groundedWhileFlyingTicks >= maxGroundedTicksWhileFlying()) {
                    switchNavigation();
                    resetTimers();
                }
            } else {
                if (groundedWhileFlyingTicks > 0)
                    groundedWhileFlyingTicks = 0;
            }

        } else {
            groundedWhileFlyingTicks = 0;
            groundTicks++;

            if (groundTicks >= maxGroundTicks()) {
                switchNavigation();
                resetTimers();
            }
        }
    }


    // ===== MÉTODO AUXILIAR PARA LIMPIAR CONTADORES =====

    private void resetTimers() {
        groundTicks = 0;
        groundedWhileFlyingTicks = 0;
    }

    @Override
    public void travel(@NotNull Vec3 vec) {
        if (getIsFlying() && this.isControlledByLocalInstance()) {
            if (this.isInWater()) {
                this.moveRelative(0.02F, vec);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8F));
            } else if (this.isInLava()) {
                this.moveRelative(0.02F, vec);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
            } else {
                this.moveRelative(this.getSpeed(), vec);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.91F));
            }
        } else {
            super.travel(vec);
        }
    }

    @Override
    public boolean isNoGravity() {
        return this.getIsFlying();
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    public Set<EntityType<?>> getInterruptingEntityTypes() {
        return Set.of();
    }

    // ───────────────────────────────────────────────────── ANIMATIONS ─────

    protected final AnimationState flyIdleAnimationState = new AnimationState();
    protected final AnimationState flyMoveAnimationState = new AnimationState();

    public AnimationState getFlyIdleAnimationState() { return flyIdleAnimationState; }
    public AnimationState getFlyMoveAnimationState() { return flyMoveAnimationState; }

    @Override
    public void updateAnimations() {
        updateFlyingAnimations();
    }

    protected void updateFlyingAnimations() {
        if (isTouchingSolidGround()) {
            stopAllFlightAnimations();
            playGroundAnimations();
            return;
        }

        stopAllGroundAnimations();

        double speed = this.getDeltaMovement().horizontalDistanceSqr();

        if (speed > 0.05) {
            flyMoveAnimationState.startIfStopped(this.tickCount);
            stopAllFlightAnimationsExcept(flyMoveAnimationState);
            //System.out.println("[ANIM FLYING] Volando y moviéndose. Inicia 'fly_move'.");
        } else {
            flyIdleAnimationState.startIfStopped(this.tickCount);
            stopAllFlightAnimationsExcept(flyIdleAnimationState);
            //System.out.println("[ANIM FLYING] Volando quieto. Inicia 'fly_idle'.");
        }
    }

    protected boolean shouldPlayWaterIdleAnimation() {
        return this.isInWater();
    }

    protected void stopAllFlightAnimations() {
        flyIdleAnimationState.stop();
        flyMoveAnimationState.stop();
    }

    protected void stopAllFlightAnimationsExcept(AnimationState active) {
        if (active != flyIdleAnimationState) flyIdleAnimationState.stop();
        if (active != flyMoveAnimationState) flyMoveAnimationState.stop();
    }

    protected void stopAllGroundAnimations() {
        walkAnimationState.stop();
        sprintAnimationState.stop();
        idleAnimationState.stop();
        waterIdleAnimationState.stop();
    }

    protected void playGroundAnimations() {
        stopAllFlightAnimations(); // Limpieza por seguridad

        if (shouldPlayWaterIdleAnimation()) {
            if (!waterIdleAnimationState.isStarted()) {
                waterIdleAnimationState.start(this.tickCount);
                //System.out.println("[ANIM GROUND] En agua. Inicia 'water_idle'.");
            }
            walkAnimationState.stop();
            sprintAnimationState.stop();
            idleAnimationState.stop();
            return;
        }

        if (!isTouchingSolidGround()) {
            walkAnimationState.stop();
            sprintAnimationState.stop();
            idleAnimationState.stop();
            waterIdleAnimationState.stop();
            //System.out.println("[ANIM GROUND] No toca el suelo. Se detienen animaciones terrestres.");
            return;
        }

        double speed = this.getDeltaMovement().horizontalDistanceSqr();

        if (speed > 1.0E-6) {
            if (this.isSprinting()) {
                if (!sprintAnimationState.isStarted()) {
                    sprintAnimationState.start(this.tickCount);
                    //System.out.println("[ANIM GROUND] Sprint detectado. Inicia 'sprint'.");
                }
                walkAnimationState.stop();
                idleAnimationState.stop();
            } else {
                if (!walkAnimationState.isStarted()) {
                    walkAnimationState.start(this.tickCount);
                    //System.out.println("[ANIM GROUND] Caminando. Inicia 'walk'.");
                }
                sprintAnimationState.stop();
                idleAnimationState.stop();
            }
        } else {
            if (!idleAnimationState.isStarted()) {
                idleAnimationState.start(this.tickCount);
                //System.out.println("[ANIM GROUND] Quieto. Inicia 'idle'.");
            }
            walkAnimationState.stop();
            sprintAnimationState.stop();
        }

        waterIdleAnimationState.stop();
    }
}
