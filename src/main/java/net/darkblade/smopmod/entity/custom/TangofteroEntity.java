package net.darkblade.smopmod.entity.custom;

import net.darkblade.smopmod.block.ModBlocks;
import net.darkblade.smopmod.entity.BaseEntity;
import net.darkblade.smopmod.entity.TangofteroVariant;
import net.darkblade.smopmod.entity.ai.core.FollowOwnerBaseGoal;
import net.darkblade.smopmod.entity.ai.core.GenericBreedGoal;
import net.darkblade.smopmod.entity.ai.core.GenericLayEggGoal;
import net.darkblade.smopmod.entity.ai.tangoftero.*;
import net.darkblade.smopmod.entity.interfaces.sleep_system.ISleepAwareness;
import net.darkblade.smopmod.entity.interfaces.sleep_system.ISleepThreatEvaluator;
import net.darkblade.smopmod.entity.interfaces.variants.RandomVariantCapable;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class TangofteroEntity extends BaseEntity implements ISleepThreatEvaluator, ISleepAwareness, RandomVariantCapable {

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(TangofteroEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(TangofteroEntity.class, EntityDataSerializers.BOOLEAN);

    public static final Predicate<LivingEntity> ENEMY_SELECTOR = (entity) -> entity.getMobType() == MobType.UNDEAD;


    public TangofteroEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();

        setupAnimationStates();
        updateFeedingBehavior();

    }

    // ───────────────────────────────────────────────────── ANIMATIONS ─────

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState preparingSleepState = new AnimationState();
    public final AnimationState sleepState = new AnimationState();
    public final AnimationState awakeningState = new AnimationState();

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
                awakeningState.stop();
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
                awakeningState.stop();
                lastAnimationChangeTick = this.tickCount;
            } else {
                sleepState.stop();
            }
        }

        if (key == AWAKENING) {
            if (this.isAwakeing()) {
                System.out.println("[CLIENT][Sync] → start awakeing");
                awakeningState.start(this.tickCount);
                sleepState.stop();
                preparingSleepState.stop();
                lastAnimationChangeTick = this.tickCount;
            } else {
                awakeningState.stop();
            }
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
            attackAnimationTimeout = 16; // Length in ticks of your animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 42) {
            this.biteAnimationState.start(this.tickCount); // bite animatiom
        } else if (id == 43) {
            this.roarAnimationState.start(this.tickCount); // roar animatiom
        } else {
            super.handleEntityEvent(id);
        }
    }

    // ───────────────────────────────────────────────────── GOALS ─────

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TangofteroAttackGoal(this,1.0D,true));
        this.goalSelector.addGoal(2, new GenericBreedGoal<>(this, 1.0));
        this.goalSelector.addGoal(3, new GenericLayEggGoal<>(this, ModBlocks.TANGOFTERO_EGG.get()));
        this.goalSelector.addGoal(5, new TangofteroTemptGoal(this, 1.2D, Ingredient.of(Items.ROTTEN_FLESH), false));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(10, new FollowOwnerBaseGoal(this, 1.0D, 10.0F, 2.0F, false, false));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new AssistFlockGoal(this, 10.0));
        this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, TangofteroEntity.ENEMY_SELECTOR));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH,10.0)
                .add(Attributes.FOLLOW_RANGE,28D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.ATTACK_SPEED, 0.5D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1f)
                .add(Attributes.ATTACK_DAMAGE, 2f);
    }

    // ───────────────────────────────────────────────────── ATTACK ─────

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    // ───────────────────────────────────────────────────── MOB INTERACT ─────

    private static final Item TAMING_ITEM = Items.RABBIT;
    private static final Item BREEDING_ITEM = Items.CHICKEN;
    private static final Item HIGH_HEAL_ITEM = Items.ROTTEN_FLESH;

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();

        if (item == TAMING_ITEM && !this.isTame()) {
            if (!player.level().isClientSide) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }

                if (this.random.nextInt(3) == 0) { // 1 en 3 chance
                    this.tame(player);
                    this.level().broadcastEntityEvent(this, (byte) 7); // ❤️ corazones
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6); // 💨 humo (fallo)
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }


        if (item == BREEDING_ITEM && this.isTame() && !this.isBaby() && !this.isInLove()) {
            if (!player.level().isClientSide) {
                this.setInLove(player);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        if (item.isEdible() && this.biteAnimationCooldown <= 0) {
            if (item.isEdible() && this.biteAnimationCooldown <= 0) {
                handleFeeding(item, player);
                return InteractionResult.sidedSuccess(this.level().isClientSide());
            }
        }
        return super.mobInteract(player, hand);
    }


    // ───────────────────────────────────────────────────── NBT ─────

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING,false);
        this.entityData.define(VARIANT, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getVariantId());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setVariantId(pCompound.getInt("Variant"));
    }

    // ───────────────────────────────────────────────────── Sounds ─────

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.CHICKEN_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    // ───────────────────────────────────────────────────── Variant ─────

    @Override
    public void setRandomVariant(RandomSource random) {
        int id = random.nextInt(TangofteroVariant.values().length);
        setVariantId(id);
    }

    @Override
    public int getVariantId() {
        return this.entityData.get(VARIANT);
    }

    public void setVariantId(int id) {
        this.entityData.set(VARIANT, id);
    }

    public TangofteroVariant getVariant() {
        return TangofteroVariant.byId(getVariantId());
    }

    public void setVariant(TangofteroVariant variant) {
        setVariantId(variant.getId());
    }

    @Override
    public int getMaxVariants() {
        return TangofteroVariant.values().length;
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

    @Override
    public TangofteroEntity getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {return stack.getItem() == BREEDING_ITEM;}

    // ───────────────────────────────────────────────────── SLEEP SYSTEM ─────


    @Override
    protected SleepCycleController<BaseEntity> createSleepController() {
        return new SleepCycleController<>(this, preparingSleepState, sleepState, awakeingState, 20, 20);
    }

    @Override
    public Set<EntityType<?>> getInterruptingEntityTypes() {
        return Collections.emptySet(); // STATIC
    }

    @Override
    public boolean shouldInterruptSleepDueTo(LivingEntity nearby) {
        return nearby.getMobType() == MobType.UNDEAD;
    }

    @Override
    public boolean shouldWakeOnPlayerProximity() {
        return false;
    }

    // ───────────────────────────────────────────────────── ROAR ─────

    private static final int ROAR_COOLDOWN_TICKS = 600;
    private int biteAnimationCooldown = 0;
    private int roarDelayTicks = -1;
    private boolean shouldRoar = false;
    private long lastRoarTime = -ROAR_COOLDOWN_TICKS;
    private int scareUndeadDelayTicks = -1;

    public final AnimationState biteAnimationState = new AnimationState();
    public final AnimationState roarAnimationState = new AnimationState();

    private void handleFeeding(Item item, Player player) {
        boolean isRottenFlesh = item == HIGH_HEAL_ITEM;

        // ✅ Curar solo si no está a full vida
        if (this.getHealth() < this.getMaxHealth()) {
            float healAmount = isRottenFlesh ? 6.0F : 3.0F;
            this.heal(healAmount);
        }

        // ✅ Consumir el ítem (incluso si está a vida completa)
        if (!player.getAbilities().instabuild) {
            player.getMainHandItem().shrink(1);
        }

        // ✅ Siempre reproducir "bite"
        this.biteAnimationCooldown = 20;
        this.level().broadcastEntityEvent(this, (byte) 42); // Bite animation
        this.level().broadcastEntityEvent(this, (byte) 7);  // Heart particles

        // ✅ Activar "roar" SOLO si es carne podrida, está tameado y cooldown listo
        if (isRottenFlesh && this.isTame() && this.tickCount - this.lastRoarTime >= ROAR_COOLDOWN_TICKS) {
            this.roarDelayTicks = 15;
            this.shouldRoar = true;
            this.lastRoarTime = this.tickCount;
        }
    }

    private void updateFeedingBehavior() {
        // 🕐 Reducir cooldown de curación
        if (this.biteAnimationCooldown > 0) {
            this.biteAnimationCooldown--;
        }

        // 🧠 Activar animaciones si están en curso
        this.biteAnimationState.animateWhen(this.biteAnimationState.isStarted(), this.tickCount);
        this.roarAnimationState.animateWhen(this.roarAnimationState.isStarted(), this.tickCount);

        // 🦷 Lanzar rugido si se cumplió el delay tras la mordida
        if (this.shouldRoar && this.roarDelayTicks-- <= 0) {
            this.shouldRoar = false;
            this.roarDelayTicks = -1;

            if (!this.level().isClientSide) {
                this.level().broadcastEntityEvent(this, (byte) 43); // animación de rugido en cliente
                this.scareUndeadDelayTicks = 40; // esperar 2 segundos para activar el efecto
            }
        }

        // 🧟 Activar efecto de rugido después del delay
        if (this.scareUndeadDelayTicks >= 0) {
            this.scareUndeadDelayTicks--;

            if (this.scareUndeadDelayTicks == 0 && !this.level().isClientSide) {
                this.scareUndeadMobs(); // ahuyenta mobs no-muertos
            }
        }
    }


    private void scareUndeadMobs() {
        List<Mob> nearbyUndead = this.level().getEntitiesOfClass(Mob.class,
                this.getBoundingBox().inflate(10),
                mob -> mob.getMobType() == MobType.UNDEAD && mob.isAlive());

        for (Mob mob : nearbyUndead) {
            double dx = mob.getX() - this.getX();
            double dz = mob.getZ() - this.getZ();
            double distance = Math.sqrt(dx * dx + dz * dz);

            if (distance == 0) continue; // evitar división por cero

            double multiplier = 7.0 / distance; // alejarse exactamente 7 bloques
            double targetX = mob.getX() + dx * multiplier;
            double targetZ = mob.getZ() + dz * multiplier;

            mob.getNavigation().moveTo(targetX, mob.getY(), targetZ, 1.2); // velocidad de huida
        }
    }

}

