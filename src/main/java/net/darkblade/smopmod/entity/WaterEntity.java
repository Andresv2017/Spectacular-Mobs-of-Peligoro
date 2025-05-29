package net.darkblade.smopmod.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

public abstract class WaterEntity extends GenderedEntity {

    private static final EntityDataAccessor<Boolean> STANDING = SynchedEntityData.defineId(WaterEntity.class, EntityDataSerializers.BOOLEAN);

    public boolean isStanding() { return this.entityData.get(STANDING); }
    public void setStanding(boolean standing) { this.entityData.set(STANDING, standing); }

    public int timeSwimming;
    public int timeStanding;
    public int navigateTypeLength = 300;
    protected boolean hasStandingSize = false;

    private float fishPitch;

    protected int outOfWaterMaxTicks = 100;    // Tiempo máximo fuera del agua (en ticks)
    protected float outOfWaterDamage = 2.0F;   // Daño por asfixia
    private int outOfWaterTicks = 0;           // Contador interno

    public WaterEntity(EntityType<? extends WaterEntity> type, Level level) {
        super(type, level);
        this.getNavigation().setCanFloat(true);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WaterBoundPathNavigation(this, level);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected float getWaterSlowDown() {
        return 1F;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        Vec3 motion = this.getDeltaMovement();
        float pitchTarget = (float) motion.y * 3F;

        // ─── Lógica de descanso (STANDING) ───────────────────────
        if (this.isStanding()) {
            if (!hasStandingSize) {
                hasStandingSize = true;
                refreshDimensions();
                navigateTypeLength = 400 + random.nextInt(400);
                System.out.printf("[TICK] Entrando en modo STANDING en (%.2f, %.2f, %.2f)%n", this.getX(), this.getY(), this.getZ());
            }

            timeStanding++;
            timeSwimming = 0;
            pitchTarget = 0;

            if (this.getNavigation().isDone()) {
                this.getNavigation().stop();
                System.out.printf("[TICK] STANDING sin navegación → deteniendo en (%.2f, %.2f, %.2f)%n",
                        this.getX(), this.getY(), this.getZ()
                );
                if (!this.onGround()) {
                    this.setDeltaMovement(motion.add(0, -0.05, 0).multiply(0.5F, 1F, 0.5F));
                }
            }

        } else {
            if (hasStandingSize) {
                System.out.printf("[TICK] Saliendo de STANDING → reanudando nado desde (%.2f, %.2f, %.2f)%n",
                        this.getX(), this.getY(), this.getZ());
            }

            timeStanding = 0;
            timeSwimming++;

            if (hasStandingSize) {
                hasStandingSize = false;
                double d = (this.getBbHeight() * 0.35F + this.getY());
                refreshDimensions();
                this.setPos(this.getX(), d, this.getZ());
                navigateTypeLength = 400 + random.nextInt(400);
            }

            System.out.printf("[TICK] NADANDO → posición (%.2f, %.2f, %.2f), delta: (%.3f, %.3f, %.3f)%n",
                    this.getX(), this.getY(), this.getZ(),
                    motion.x, motion.y, motion.z);
        }

        // ─── Inclinación vertical del pez ───────────────────────
        fishPitch = Mth.approachDegrees(
                fishPitch,
                Mth.clamp(pitchTarget, -1.4F, 1.4F) * -(180F / (float) Math.PI),
                5
        );

        // ─── Daño por asfixia ────────────────────────────────
        if (!this.isInWaterRainOrBubble()) {
            outOfWaterTicks++;
            if (outOfWaterTicks > outOfWaterMaxTicks) {
                this.hurt(this.damageSources().dryOut(), outOfWaterDamage);
            }
        } else {
            outOfWaterTicks = 0;
        }

        // ─── Comportamiento "flop" fuera del agua ─────────────
        if (!this.isInWaterOrBubble() && this.isAlive()) {
            if (this.onGround()) {
                this.setDeltaMovement(motion.add(
                        (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F,
                        0.5D,
                        (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F
                ));
                this.setYRot(this.random.nextFloat() * 360.0F);
                this.playSound(getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
            }
        }

        // ─── Depuración adicional opcional ────────────────────
        if (!this.getNavigation().isDone()) {
            System.out.printf("[TICK] Navegando hacia destino, velocidad actual: %.3f%n", motion.length());
        }
    }


    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.isInWater() && this.onGround() && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add(
                    (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F,
                    0.4F,
                    (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F
            ));
            this.setOnGround(false);
            this.hasImpulse = true;
        }
    }

    // ───────────────────────────────────────────────────── ANIMATIONS ─────

    protected final AnimationState swimAnimationState = new AnimationState();
    protected final AnimationState swimSprintAnimationState = new AnimationState();
    protected final AnimationState floopingAnimationState = new AnimationState();
    protected final AnimationState waterDeathAnimationState = new AnimationState();
    protected final AnimationState landDeathAnimationState = new AnimationState();

    public AnimationState getSwimAnimationState() {return swimAnimationState;}
    public AnimationState getSwimSprintAnimationState() {return swimSprintAnimationState;}
    public AnimationState getFloopingAnimationState() {return floopingAnimationState;}
    public AnimationState getWaterDeathAnimationState() {return waterDeathAnimationState;}
    public AnimationState getLandDeathAnimationState() {return landDeathAnimationState;}


    private boolean hasPlayedWaterDeath = false;

    public void updateAquaticAnimations() {
        if (this.isDeadOrDying()) {
            if (this.isInWater()) {
                if (!hasPlayedWaterDeath) {
                    waterDeathAnimationState.start(this.tickCount);
                    hasPlayedWaterDeath = true;
                    stopAllWaterAnimationsExcept(waterDeathAnimationState);
                }
            } else {
                if (!landDeathAnimationState.isStarted()) {
                    landDeathAnimationState.start(this.tickCount);
                    stopAllWaterAnimationsExcept(landDeathAnimationState);
                }
            }
            return;
        }

        if (hasPlayedWaterDeath) {
            hasPlayedWaterDeath = false;
        }

        if (!this.isInWater()) {
            floopingAnimationState.startIfStopped(this.tickCount);
            stopAllWaterAnimationsExcept(floopingAnimationState);
            return;
        }

        double speed = this.getDeltaMovement().horizontalDistanceSqr();

        if (this.isSprinting()) {
            swimSprintAnimationState.startIfStopped(this.tickCount);
            stopAllWaterAnimationsExcept(swimSprintAnimationState);
        } else if (speed > 1.0E-6) {
            swimAnimationState.startIfStopped(this.tickCount);
            stopAllWaterAnimationsExcept(swimAnimationState);
        } else {
            waterIdleAnimationState.startIfStopped(this.tickCount);
            stopAllWaterAnimationsExcept(waterIdleAnimationState);
        }
    }

    protected void stopAllWaterAnimationsExcept(AnimationState active) {
        if (active != waterIdleAnimationState) waterIdleAnimationState.stop();
        if (active != swimAnimationState) swimAnimationState.stop();
        if (active != swimSprintAnimationState) swimSprintAnimationState.stop();
        if (active != floopingAnimationState) floopingAnimationState.stop();
        if (active != waterDeathAnimationState) waterDeathAnimationState.stop();
    }

    @Override
    public void updateAnimations() {
        updateAquaticAnimations();
    }

    public int getOutOfWaterMaxTicks() {
        return outOfWaterMaxTicks;
    }

    public float getOutOfWaterDamage() {
        return outOfWaterDamage;
    }

    public int getOutOfWaterTicks() {
        return outOfWaterTicks;
    }

    public void setOutOfWaterMaxTicks(int ticks) {
        this.outOfWaterMaxTicks = ticks;
    }

    public void setOutOfWaterDamage(float damage) {
        this.outOfWaterDamage = damage;
    }

    public void setOutOfWaterTicks(int ticks) {
        this.outOfWaterTicks = ticks;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STANDING, false);
    }
}
