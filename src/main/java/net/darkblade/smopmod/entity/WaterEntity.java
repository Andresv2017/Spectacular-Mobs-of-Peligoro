package net.darkblade.smopmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
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
    }

    // === Configuración personalizada ===

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
}
