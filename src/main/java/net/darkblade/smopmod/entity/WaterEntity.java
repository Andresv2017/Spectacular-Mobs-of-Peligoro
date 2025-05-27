package net.darkblade.smopmod.entity;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;

public abstract class WaterEntity extends GenderedEntity {

    protected int outOfWaterMaxTicks = 100;    // Tiempo máximo fuera del agua (en ticks)
    protected float outOfWaterDamage = 2.0F;   // Daño por asfixia
    private int outOfWaterTicks = 0;           // Contador interno

    public WaterEntity(EntityType<? extends WaterEntity> type, Level level) {
        super(type, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.01F, true);
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

        if (!this.isInWaterRainOrBubble()) {
            outOfWaterTicks++;
            if (outOfWaterTicks > outOfWaterMaxTicks) {
                this.hurt(this.damageSources().dryOut(), outOfWaterDamage);
            }
        } else {
            outOfWaterTicks = 0;
        }

        if (this.level().isClientSide()) {
            updateAquaticAnimations();
            logActiveWaterAnimation();
        }

        if (!this.level().isClientSide()) {
            this.updateWaterSprintStatus();
        }
    }


    public void setOutOfWaterMaxTicks(int ticks) {
        this.outOfWaterMaxTicks = ticks;
    }

    public void setOutOfWaterDamage(float damage) {
        this.outOfWaterDamage = damage;
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

    protected void updateWaterSprintStatus() {
        boolean isChasing = this.getTarget() != null && this.getTarget().isAlive();
        this.setSprinting(isChasing);

        double baseSpeed = this.getAttributeValue(Attributes.MOVEMENT_SPEED);
        double sprintSpeed = this.getAttributeValue(Attributes.ATTACK_SPEED);

        this.getAttribute(Attributes.MOVEMENT_SPEED)
                .setBaseValue(this.isSprinting() ? sprintSpeed : baseSpeed);
    }

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

    protected void logActiveWaterAnimation() {
        if (swimAnimationState.isStarted()) {
            System.out.println("[ANIM] swim");
        } else if (swimSprintAnimationState.isStarted()) {
            System.out.println("[ANIM] swim_sprint");
        } else if (floopingAnimationState.isStarted()) {
            System.out.println("[ANIM] flooping");
        } else if (waterDeathAnimationState.isStarted()) {
            System.out.println("[ANIM] water_death");
        } else if (waterIdleAnimationState.isStarted()) {
            System.out.println("[ANIM] water_idle");
        } else {
            System.out.println("[ANIM] ninguna activa");
        }
    }

    @Override
    public void updateAnimations() {
        updateAquaticAnimations();
    }
}
