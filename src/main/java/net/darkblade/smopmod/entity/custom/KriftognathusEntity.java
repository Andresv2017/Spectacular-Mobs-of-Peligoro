package net.darkblade.smopmod.entity.custom;

import net.darkblade.smopmod.block.ModBlocks;
import net.darkblade.smopmod.entity.BaseEntity;
import net.darkblade.smopmod.entity.GenderedEntity;
import net.darkblade.smopmod.entity.ai.core.GenericBreedGoal;
import net.darkblade.smopmod.entity.ai.core.GenericLayEggGoal;
import net.darkblade.smopmod.entity.ai.core.protect_egg.EggGoalRegistry;
import net.darkblade.smopmod.entity.ai.core.protect_egg.ProtectEggBaseGoal;
import net.darkblade.smopmod.entity.ai.core.protect_egg.ProtectOwnEggGoal;
import net.darkblade.smopmod.entity.ai.krifto.KriftoAttackGoal;
import net.darkblade.smopmod.entity.interfaces.egg_custom.CustomEggBorn;
import net.darkblade.smopmod.entity.interfaces.sleep_system.ISleepAwareness;
import net.darkblade.smopmod.entity.interfaces.sleep_system.ISleepThreatEvaluator;
import net.darkblade.smopmod.entity.util.SleepCycleController;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Predicate;

public class KriftognathusEntity extends GenderedEntity implements ISleepThreatEvaluator, ISleepAwareness, CustomEggBorn {

    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(KriftognathusEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> SPAWN_BIOME = SynchedEntityData.defineId(KriftognathusEntity.class, EntityDataSerializers.STRING);


    public KriftognathusEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();
        setupAnimationStates();
        if (sleepController == null) {
            sleepController = createSleepController();
        }
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

    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_289448_) -> {
        EntityType<?> entitytype = p_289448_.getType();
        return entitytype == EntityType.SNIFFER || entitytype == EntityType.FOX;
    };

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new KriftoAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new GenericBreedGoal<>(this, 1.0));
        EggGoalRegistry.registerWithOwnGoal(
                this,
                ModBlocks.KRIFFO_EGG, // El bloque de huevo
                4, 6, // stayNearEggRadius, defenseRadius
                true, true, // attackOnApproach, attackOnBreak
                ProtectEggBaseGoal.EggBreakReaction.IGNORE, // No huye si lo rompen
                PREY_SELECTOR,
                4// Prioridad base del goal
        );
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.20D)
                .add(Attributes.ATTACK_SPEED, 0.4D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1F)
                .add(Attributes.ATTACK_DAMAGE, 2.0F);
    }

    private static final Item TAMING_ITEM = Items.RABBIT;
    private static final Item BREEDING_ITEM = Items.CHICKEN;

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


        if (item == BREEDING_ITEM && !this.isBaby() && !this.isInLove()) {
            if (!player.level().isClientSide) {
                this.setInLove(player);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return super.mobInteract(player, hand);
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
        this.entityData.define(SPAWN_BIOME, "default");
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


    // ───────────────────────────────────────────────────── SLEEP SYSTEM  ─────

    @Override
    protected SleepCycleController<BaseEntity> createSleepController() {
        return new SleepCycleController<>(this, preparingSleepState, sleepState, awakeingState,
                getPreparingSleepDuration(), getAwakeningDuration());
    }

    @Override
    protected int getPreparingSleepDuration() {
        return 50;
    }

    @Override
    protected int getAwakeningDuration() {
        return 70;
    }

    public Set<EntityType<?>> getInterruptingEntityTypes() {
        return Set.of(EntityType.ZOMBIE, EntityType.BEE);
    }

    @Override
    public boolean shouldInterruptSleepDueTo(LivingEntity nearby) {
        return getInterruptingEntityTypes().contains(nearby.getType());
    }

    @Override
    public boolean shouldWakeOnPlayerProximity() {
        return false;
    }

    // ───────────────────────────────────────────────────── BIOMES VARIANTS  ─────

    private String spawnBiomePath = "default";

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        SpawnGroupData result = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        this.sleepController = createSleepController();

        // ─── Assing Born Biome ───
        ResourceLocation biomeKey = pLevel.registryAccess()
                .registryOrThrow(Registries.BIOME)
                .getKey(pLevel.getBiome(this.blockPosition()).value());

        String biomePath = biomeKey != null ? biomeKey.getPath() : "default";
        this.spawnBiomePath = biomePath;
        this.entityData.set(SPAWN_BIOME, biomePath);

        boolean isMale = this.getRandom().nextBoolean();
        this.setMale(isMale);

        return result;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("SpawnBiome", this.getSpawnBiomePath());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("SpawnBiome")) {
            String path = pCompound.getString("SpawnBiome");
            this.setSpawnBiomePath(path);
        }
    }

    public static boolean checkKriftoSpawnRules(EntityType<KriftognathusEntity> p_218242_, LevelAccessor p_218243_, MobSpawnType p_218244_, BlockPos p_218245_, RandomSource p_218246_) {
        return checkAnimalSpawnRules(p_218242_,p_218243_,p_218244_,p_218245_,p_218246_);
    }

    // ───────────────────────────────────────────────────── BIOMES VARIANTS EGGS ─────

    @Override
    public void onEggBorn(ServerLevel level, BlockPos pos) {
        assignBiomeTexture(level); // Texture Logic
        boolean isMale = this.getRandom().nextBoolean();  // Gender From GenderEntity
        this.setMale(isMale);
    }

    public String getSpawnBiomePath() {
        return this.entityData.get(SPAWN_BIOME);
    }

    public void setSpawnBiomePath(String biomePath) {
        this.spawnBiomePath = biomePath;
        if (!this.level().isClientSide) {
            this.entityData.set(SPAWN_BIOME, biomePath);
        }
    }

    public void assignBiomeTexture(ServerLevel level) {
        // Get the ResourceLocation of the biome at the current position
        ResourceLocation biomeKey = level.registryAccess()
                .registryOrThrow(Registries.BIOME)
                .getKey(level.getBiome(this.blockPosition()).value());

        // Extract only the "path", for example: "jungle", "snowy_taiga", etc.
        String biomePath = biomeKey != null ? biomeKey.getPath() : "default";

        // Store it locally
        this.spawnBiomePath = biomePath;

        // Sync with the client if on the server
        if (!level.isClientSide()) {
            this.entityData.set(SPAWN_BIOME, biomePath);
        }

        // (Optional) Debug message
        System.out.println("[BiomeTexture] Asignado: " + biomePath + " para entidad ID: " + this.getId());
    }
}
