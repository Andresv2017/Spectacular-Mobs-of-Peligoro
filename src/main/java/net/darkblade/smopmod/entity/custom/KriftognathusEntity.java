package net.darkblade.smopmod.entity.custom;

import net.darkblade.smopmod.entity.ai.krifto.KriftoAttackGoal;
import net.darkblade.smopmod.entity.ai.tangoftero.TangofteroAttackGoal;
import net.darkblade.smopmod.entity.interfaces.ISleepingEntity;
import net.darkblade.smopmod.entity.util.SleepCycleController;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class KriftognathusEntity extends TamableAnimal implements ISleepingEntity, net.darkblade.smopmod.entity.api.ISleepingEntity {

    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(KriftognathusEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(KriftognathusEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> PREPARING_SLEEP = SynchedEntityData.defineId(KriftognathusEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> AWAKENING = SynchedEntityData.defineId(KriftognathusEntity.class, EntityDataSerializers.BOOLEAN);

    public KriftognathusEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            sleepController.tick(this.tickCount);
        }
        setupAnimationStates();
    }

    // ───────────────────────────────────────────────────── ANIMATIONS ─────

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState preparingSleepState = new AnimationState();
    public final AnimationState sleepState = new AnimationState();
    public final AnimationState awakeingState = new AnimationState();

    private int lastAnimationChangeTick = -20;
    private static final int MIN_TICKS_BETWEEN_ANIMS = 3;

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);

        if (!this.level().isClientSide()) return;

        // Anti-colisión: espera unos ticks antes de iniciar otra animación
        if (this.tickCount - lastAnimationChangeTick < MIN_TICKS_BETWEEN_ANIMS) {
            return;
        }

        if (key == PREPARING_SLEEP) {
            if (this.isPreparingSleep()) {
                System.out.println("[CLIENT][Sync] → start preparing_sleep");
                preparingSleepState.start(this.tickCount);
                sleepState.stop();
                awakeingState.stop();
                lastAnimationChangeTick = this.tickCount;
            } else {
                preparingSleepState.stop();
            }
        }

        if (key == SLEEPING) {
            if (this.isSleeping()) {
                System.out.println("[CLIENT][Sync] → start sleep");
                sleepState.start(this.tickCount);
                preparingSleepState.stop();
                awakeingState.stop();
                lastAnimationChangeTick = this.tickCount;
            } else {
                sleepState.stop();
            }
        }

        if (key == AWAKENING) {
            if (this.isAwakeing()) {
                System.out.println("[CLIENT][Sync] → start awakeing");
                awakeingState.start(this.tickCount);
                sleepState.stop();
                preparingSleepState.stop();
                lastAnimationChangeTick = this.tickCount;
            } else {
                awakeingState.stop();
            }
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 48;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 15; // Length in ticks of your animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    // ───────────────────────────────────────────────────── GOALS ─────

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new KriftoAttackGoal(this, 1.0D, true));

        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_SPEED, 0.4D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1F)
                .add(Attributes.ATTACK_DAMAGE, 2.0F);
    }

    // ───────────────────────────────────────────────────── ATTACK ─────

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    // ───────────────────────────────────────────────────── NBT ─────

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING,false);
        this.entityData.define(SLEEPING, false);
        this.entityData.define(PREPARING_SLEEP, false);
        this.entityData.define(AWAKENING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Sleeping", this.isSleeping());
        tag.putBoolean("PreparingSleep", this.isPreparingSleep());
        tag.putBoolean("Awakening", this.isAwakeing());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Sleeping")) this.setSleeping(tag.getBoolean("Sleeping"));
        if (tag.contains("PreparingSleep")) this.setPreparingSleep(tag.getBoolean("PreparingSleep"));
        if (tag.contains("Awakening")) this.setAwakeing(tag.getBoolean("Awakening"));
    }

    // ───────────────────────────────────────────────────── Sounds ─────

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.PARROT_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.PARROT_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PARROT_DEATH;
    }

    // ───────────────────────────────────────────────────── SLEEP SYSTEM ─────

    @Override public boolean isSleeping() { return this.entityData.get(SLEEPING); }
    @Override public void setSleeping(boolean v) { this.entityData.set(SLEEPING, v); }

    @Override public boolean isPreparingSleep() { return this.entityData.get(PREPARING_SLEEP); }
    @Override public void setPreparingSleep(boolean v) { this.entityData.set(PREPARING_SLEEP, v); }

    @Override public boolean isAwakeing() { return this.entityData.get(AWAKENING); }
    @Override public void setAwakeing(boolean v) { this.entityData.set(AWAKENING, v); }

    private final SleepCycleController<KriftognathusEntity> sleepController =
            new SleepCycleController<>(this, preparingSleepState, sleepState, awakeingState, 50, 70); // puedes cambiar a 20,20 si lo deseas

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isPreparingSleep() || this.isSleeping() || this.isAwakeing()) {
            this.setDeltaMovement(Vec3.ZERO);
            this.getNavigation().stop();
            return;
        }
        super.travel(travelVector);
    }

    @Override
    public void aiStep() {
        if (this.isSleeping() || this.isPreparingSleep() || this.isAwakeing()) {
            this.setTarget(null);
        }
        super.aiStep();
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }
}
