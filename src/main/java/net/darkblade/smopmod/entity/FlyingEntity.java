package net.darkblade.smopmod.entity;

import net.darkblade.smopmod.entity.navigation.TBFlyingPathNavigation;
import net.darkblade.smopmod.entity.navigation.TBGroundPathNavigation;
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
        this.navigation = new TBGroundPathNavigation(this, this.level());
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
            this.navigation = new TBGroundPathNavigation(this, this.level());
            this.setIsFlying(false);
        } else {
            this.moveControl = new FlyingMoveControl(this, 20, false);
            this.navigation = new TBFlyingPathNavigation(this, this.level()).canFloat(true);
            this.jumpControl.jump();
            this.setIsFlying(true);
        }

        this.setNoGravity(getIsFlying());

        if (!level().isClientSide()) {
            InitPackets.sendToAll(new StoCSyncFlying(this.getId(), this.getIsFlying()));
        }

        System.out.println("[NAV] Cambio de navegación: " + (wasFlying ? "Vuelo → Tierra" : "Tierra → Vuelo"));
    }


    protected boolean isTouchingSolidGround() {
        BlockPos pos = this.blockPosition().below();
        return !this.level().getBlockState(pos).isAir() && this.getDeltaMovement().y <= 0;
    }

    // Personalizable por entidad
    protected int switchNavigationInterval() {
        return 100; // default, override in entity
    }

    private int ticksSinceLastSwitch = 0;
    private int groundedWhileFlyingTicks = 0;

    private boolean prevTouchingGround = false;

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide()) {
            ticksSinceLastSwitch++;

            // 1. Alternancia automática por intervalo
            if (ticksSinceLastSwitch >= switchNavigationInterval()) {
                System.out.println("[NAV] Intervalo alcanzado: " + ticksSinceLastSwitch + " ticks. Alternando navegación.");
                switchNavigation();
                ticksSinceLastSwitch = 0;
            }

            // 2. Aterrizaje forzado si permanece en el suelo
            if (getIsFlying()) {
                if (isTouchingSolidGround()) {
                    groundedWhileFlyingTicks++;
                    System.out.println("[NAV] Tocando suelo mientras vuela: " + groundedWhileFlyingTicks + " ticks.");

                    if (groundedWhileFlyingTicks >= 10) {
                        System.out.println("[NAV] Fuerza aterrizaje tras 10 ticks en el suelo durante vuelo.");
                        switchNavigation();
                        groundedWhileFlyingTicks = 0;
                        ticksSinceLastSwitch = 0;
                    }

                } else {
                    if (groundedWhileFlyingTicks > 0) {
                        System.out.println("[NAV] Dejó de tocar el suelo. Reiniciando contador de aterrizaje forzado.");
                    }
                    groundedWhileFlyingTicks = 0;
                }
            }
        }

        boolean currentTouching = isTouchingSolidGround();

        if (currentTouching != prevTouchingGround) {
            prevTouchingGround = currentTouching;

            if (currentTouching && !level().isClientSide()) {
                System.out.println("[PARTICLE DEBUG] Tocando suelo: " + this.blockPosition());

                ((ServerLevel) level()).sendParticles(
                        ParticleTypes.HEART,   // tipo
                        getX(), getY(), getZ(), // posición
                        5,                     // cantidad
                        0, 0, 0,               // offsets (X, Y, Z)
                        0.01                   // velocidad
                );
            }
        }
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
}
