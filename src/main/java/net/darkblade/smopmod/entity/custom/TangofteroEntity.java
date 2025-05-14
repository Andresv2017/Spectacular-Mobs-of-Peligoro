package net.darkblade.smopmod.entity.custom;

import net.darkblade.smopmod.entity.ModEntities;
import net.darkblade.smopmod.entity.TangofteroVariant;
import net.darkblade.smopmod.entity.ai.tangoftero.*;
import net.darkblade.smopmod.entity.interfaces.ISleepingEntity;
import net.darkblade.smopmod.entity.util.SleepCycleController;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class TangofteroEntity extends TamableAnimal implements ISleepingEntity {

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(TangofteroEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(TangofteroEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(TangofteroEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(TangofteroEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> PREPARING_SLEEP = SynchedEntityData.defineId(TangofteroEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> AWAKENING = SynchedEntityData.defineId(TangofteroEntity.class, EntityDataSerializers.BOOLEAN);

    public TangofteroEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();
        sleepController.tick(this.tickCount);

        if (this.level().isClientSide()) {
            // Iniciar solo si la animación aún no está activa
            if (this.isPreparingSleep() && !this.preparingSleepState.isStarted()) {
                this.preparingSleepState.start(this.tickCount);
                this.sleepState.stop();
                this.awakeingState.stop();
            } else if (this.isSleeping() && !this.sleepState.isStarted()) {
                this.sleepState.start(this.tickCount);
                this.preparingSleepState.stop();
                this.awakeingState.stop();
            } else if (this.isAwakeing() && !this.awakeingState.isStarted()) {
                this.awakeingState.start(this.tickCount);
                this.sleepState.stop();
                this.preparingSleepState.stop();
            } else if (!this.isSleeping() && !this.isPreparingSleep() && !this.isAwakeing()) {
                // Estado neutro: detener animaciones de sueño
                this.sleepState.stop();
                this.preparingSleepState.stop();
                this.awakeingState.stop();
            }

            setupAnimationStates();
        }
    }


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 14;
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


    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH,10.0)
                .add(Attributes.FOLLOW_RANGE,28D)
                .add(Attributes.MOVEMENT_SPEED, 0.75D)
                .add(Attributes.ATTACK_SPEED, 0.5D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1f)
                .add(Attributes.ATTACK_DAMAGE, 2f);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING,false);
        this.entityData.define(VARIANT,0);
        this.entityData.define(HAS_EGG, false);
        this.entityData.define(SLEEPING, false);
        this.entityData.define(PREPARING_SLEEP, false);
        this.entityData.define(AWAKENING, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new TangofteroAttackGoal(this,1.0D,true));
        this.goalSelector.addGoal(1, new TangofteroBreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new TangofteroLayEggGoal(this));

        this.goalSelector.addGoal(3, new TangofteroTemptGoal(this, 1.2D, Ingredient.of(Items.ROTTEN_FLESH), false));

        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));

        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.TANGOFTERO.get().create(serverLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.ROTTEN_FLESH);
    }

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

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getTypeVariant());
        pCompound.putBoolean("Awakening", this.isAwakeing());
        pCompound.putBoolean("Sleeping", this.isSleeping());
        pCompound.putBoolean("PreparingSleep", this.isPreparingSleep());

    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(VARIANT, pCompound.getInt("Variant"));
        if (pCompound.contains("Awakening")) {
            this.setAwakeing(pCompound.getBoolean("Awakening"));
        }
        if (pCompound.contains("Sleeping")) this.setSleeping(pCompound.getBoolean("Sleeping"));
        if (pCompound.contains("PreparingSleep")) this.setPreparingSleep(pCompound.getBoolean("PreparingSleep"));

    }

    // ───────────────────────────────────────────────────── Variant ─────

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public TangofteroVariant getVariant() {
        return TangofteroVariant.byId(this.getTypeVariant() & 255);
    }

    public void setVariant(TangofteroVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        TangofteroVariant variant = Util.getRandom(TangofteroVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public static boolean checkTangofteroSpawnRules(EntityType<TangofteroEntity> p_218242_, LevelAccessor p_218243_, MobSpawnType p_218244_, BlockPos p_218245_, RandomSource p_218246_) {
        return checkAnimalSpawnRules(p_218242_,p_218243_,p_218244_,p_218245_,p_218246_);
    }

    // ───────────────────────────────────────────────────── Eggs ─────

    public boolean hasEgg() {
        return this.entityData.get(HAS_EGG);
    }

    public void setHasEgg(boolean hasEgg) {
        this.entityData.set(HAS_EGG, hasEgg);
    }

    // ───────────────────────────────────────────────────── SLEEP SYSTEM ─────

    public boolean isSleeping() {
        return this.entityData.get(SLEEPING);
    }

    public void setSleeping(boolean sleeping) {
        this.entityData.set(SLEEPING, sleeping);
    }

    public boolean isPreparingSleep() {
        return this.entityData.get(PREPARING_SLEEP);
    }

    public void setPreparingSleep(boolean preparing) {
        this.entityData.set(PREPARING_SLEEP, preparing);
    }

    public boolean isAwakeing() {
        return this.entityData.get(AWAKENING);
    }

    public void setAwakeing(boolean value) {
        this.entityData.set(AWAKENING, value);
    }

    public final AnimationState preparingSleepState = new AnimationState();
    public final AnimationState sleepState = new AnimationState();
    public final AnimationState awakeingState = new AnimationState();

    private final SleepCycleController<TangofteroEntity> sleepController =
            new SleepCycleController<>(this, preparingSleepState, sleepState, awakeingState, 22,20);


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
}

