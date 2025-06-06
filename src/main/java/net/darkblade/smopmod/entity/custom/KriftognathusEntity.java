package net.darkblade.smopmod.entity.custom;

import net.darkblade.smopmod.block.ModBlocks;
import net.darkblade.smopmod.entity.BaseEntity;
import net.darkblade.smopmod.entity.FlyingEntity;
import net.darkblade.smopmod.entity.ai.core.FollowOwnerBaseGoal;
import net.darkblade.smopmod.entity.ai.core.GenericBreedGoal;
import net.darkblade.smopmod.entity.ai.core.flying.FlyFromNowAndThenGoal;
import net.darkblade.smopmod.entity.ai.core.flying.FollowOwnerFlyingGoal;
import net.darkblade.smopmod.entity.ai.core.flying.RandomStrollAndFlightGoal;
import net.darkblade.smopmod.entity.ai.core.protect_egg.EggGoalRegistry;
import net.darkblade.smopmod.entity.ai.core.protect_egg.ProtectEggBaseGoal;
import net.darkblade.smopmod.entity.ai.krifto.KriftoAttackGoal;
import net.darkblade.smopmod.entity.ai.krifto.RunAwayAfterStealGoal;
import net.darkblade.smopmod.entity.ai.krifto.SitOnHeadGoal;
import net.darkblade.smopmod.entity.ai.krifto.StealItemGoal;
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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class KriftognathusEntity extends FlyingEntity implements ISleepThreatEvaluator, ISleepAwareness, CustomEggBorn {

    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(KriftognathusEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> SPAWN_BIOME = SynchedEntityData.defineId(KriftognathusEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> ON_PLAYERS_HEAD = SynchedEntityData.defineId(KriftognathusEntity.class, EntityDataSerializers.BOOLEAN);

    public KriftognathusEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState onHeadFallingAnimationState = new AnimationState();

    public int attackAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick(); // sigue usando lógica general de BaseEntity/FlyingEntity

        if (sleepController == null) {
            sleepController = createSleepController();
        }

        if (!level().isClientSide()) {
            if (!this.isOnPlayersHead()) {
                this.handleAutoNavigationSwitch(); // heredado de FlyingEntity
            }
        }

        if (this.level().isClientSide()) {
            this.updateAnimations();
        }

        tickLootBehavior();

        if (this.isOnPlayersHead() && this.getOwner() instanceof Player player) {
            if (player.isCrouching()) {
                this.setOnPlayersHead(false);
                this.setOrderedToSit(false);
                this.setNoGravity(false);
                stopOnHeadAnimations(); // 💡
                return;
            }

            this.setPosRaw(player.getX(), player.getEyeY() + 0.05D, player.getZ());
            this.setDeltaMovement(Vec3.ZERO);
            this.setNoGravity(true);
            this.fallDistance = 0.0F;
            this.setOnGround(false);
            this.getNavigation().stop();

            float headYaw = player.getYHeadRot();
            this.setYRot(headYaw);
            this.setXRot(0);
            this.yBodyRot = headYaw;
            this.yHeadRot = headYaw;

            this.setOrderedToSit(true);

            // Efecto de caída retardada + animación de vuelo
            Vec3 motion = player.getDeltaMovement();
            boolean isFalling = motion.y < 0.0 && player.fallDistance > 0.0F && !player.onGround() && !player.isInWater();

            if (isFalling) {
                // Caída retardada tipo pollo
                Vec3 newMotion = new Vec3(motion.x, Math.max(motion.y, -0.15), motion.z);
                player.setDeltaMovement(newMotion);
                player.fallDistance = 0.0F;

                // Animación cayendo
                playOnly(onHeadFallingAnimationState);
                System.out.println("[KRIFTO ANIM] 🛫 on_head_falling");
            } else {
                // Animación quieto sobre la cabeza
                playOnly(onHeadIdleAnimationState);
                System.out.println("[KRIFTO ANIM] 🪶 on_head_idle");
            }

        }

        if (!level().isClientSide()) {
            System.out.print("[DEBUG ANIM HEAD] Activas: ");
            if (onHeadIdleAnimationState.isStarted()) System.out.print("onHead ");
            if (flyIdleAnimationState.isStarted()) System.out.print("flyIdle ");
            if (flyMoveAnimationState.isStarted()) System.out.print("flyMove ");
            if (walkAnimationState.isStarted()) System.out.print("walk ");
            if (sprintAnimationState.isStarted()) System.out.print("sprint ");
            if (idleAnimationState.isStarted()) System.out.print("idle ");
            if (waterIdleAnimationState.isStarted()) System.out.print("waterIdle ");
            if (attackAnimationState.isStarted()) System.out.print("attack ");
            if (swoopAnimationState.isStarted()) System.out.print("swoop ");
            System.out.println();
        }

    }

    protected void playOnly(AnimationState stateToPlay) {
        for (AnimationState state : List.of(
                idleAnimationState,
                walkAnimationState,
                sprintAnimationState,
                flyIdleAnimationState,
                flyMoveAnimationState,
                waterIdleAnimationState,
                onHeadIdleAnimationState,
                onHeadFallingAnimationState,
                attackAnimationState,
                swoopAnimationState
        )) {
            if (state != stateToPlay) {
                if (state.isStarted()) {
                    state.stop();
                    System.out.println("[ANIM DEBUG] 🔴 Deteniendo: " + state);
                }
            }
        }

        if (!stateToPlay.isStarted()) {
            stateToPlay.start(this.tickCount);
            System.out.println("[ANIM DEBUG] 🟢 Reproduciendo: " + stateToPlay);
        }
    }

    protected void stopOnHeadAnimations() {
        onHeadIdleAnimationState.stop();
        onHeadFallingAnimationState.stop();
    }



    // ───────────────────────────────────────────────────── ANIMATIONS ─────

    public final AnimationState attackAnimationState = new AnimationState();

    @Override
    public void updateAnimations() {
        super.updateAnimations();

        // Animación sobre la cabeza del jugador
        if (this.isOnPlayersHead()) {
            return; // 🔒 Deja que tick() se encargue de las animaciones en la cabeza
        }

        // Animación de ataque (por AnimationState)
        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 8;
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.isAttacking()) {
            attackAnimationState.stop();
        }

        if (swoopAnimationState.isStarted() && swoopAnimationState.getAccumulatedTime() > 1600) {
            swoopAnimationState.stop();
            System.out.println("[ANIM] swoop terminado automáticamente (duración > 750ms)");
        }
    }

    @Override
    protected void updateFlyingAnimations() {
        // Si está sobre la cabeza del jugador, detener animaciones de vuelo
        if (this.isOnPlayersHead()) {
            stopAllFlightAnimations();
            return;
        }

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
        } else {
            flyIdleAnimationState.startIfStopped(this.tickCount);
            stopAllFlightAnimationsExcept(flyIdleAnimationState);
        }
    }

    @Override
    protected void playGroundAnimations() {
        stopAllFlightAnimations(); // Limpieza por seguridad

        // 🔒 Si está en la cabeza del jugador, solo se ejecuta on_head
        if (this.isOnPlayersHead()) {
            walkAnimationState.stop();
            sprintAnimationState.stop();
            idleAnimationState.stop();
            waterIdleAnimationState.stop();
            return;
        }

        if (shouldPlayWaterIdleAnimation()) {
            if (!waterIdleAnimationState.isStarted()) {
                waterIdleAnimationState.start(this.tickCount);
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
            return;
        }

        double speed = this.getDeltaMovement().horizontalDistanceSqr();

        if (speed > 1.0E-6) {
            if (this.isSprinting()) {
                if (!sprintAnimationState.isStarted()) {
                    sprintAnimationState.start(this.tickCount);
                }
                walkAnimationState.stop();
                idleAnimationState.stop();
            } else {
                if (!walkAnimationState.isStarted()) {
                    walkAnimationState.start(this.tickCount);
                }
                sprintAnimationState.stop();
                idleAnimationState.stop();
            }
        } else {
            if (!idleAnimationState.isStarted()) {
                idleAnimationState.start(this.tickCount);
            }
            walkAnimationState.stop();
            sprintAnimationState.stop();
        }

        waterIdleAnimationState.stop();
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
        this.goalSelector.addGoal(2, new FollowOwnerFlyingGoal(this, 1.2D, 6.0F, 2.0F));
        this.goalSelector.addGoal(2, new GenericBreedGoal<>(this, 1.0));
        this.goalSelector.addGoal(3, new RandomStrollAndFlightGoal(this, 1.0));
        this.goalSelector.addGoal(4, new FlyFromNowAndThenGoal(this));

        EggGoalRegistry.registerWithOwnGoal(
                this,
                ModBlocks.KRIFFO_EGG, // El bloque de huevo
                4, 6, // stayNearEggRadius, defenseRadius
                true, true, // attackOnApproach, attackOnBreak
                ProtectEggBaseGoal.EggBreakReaction.IGNORE, // No huye si lo rompen
                PREY_SELECTOR,
                6// Prioridad base del goal
        );
        this.goalSelector.addGoal(7, new StealItemGoal(this, 15.0D, 10));
        this.goalSelector.addGoal(8, new RunAwayAfterStealGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(11, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(12, new FollowOwnerBaseGoal(this, 1.0D, 10.0F, 2.0F, true, false));


        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.20D)
                .add(Attributes.FLYING_SPEED, 0.25D)
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

        // ✅ Prioridad: interacción por shift (stay/wander/sit)
        if (this.isTame() && this.isOwnedBy(player) && player.isShiftKeyDown()) {
            return super.mobInteract(player, hand); // delega al BaseEntity
        }

        // ✅ Tameo
        if (item == TAMING_ITEM && !this.isTame()) {
            if (!player.level().isClientSide) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }

                if (this.random.nextInt(3) == 0) {
                    this.tame(player);
                    this.level().broadcastEntityEvent(this, (byte) 7); // ❤️
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6); // 💨
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        // ✅ Reproducción
        if (item == BREEDING_ITEM && !this.isBaby() && !this.isInLove()) {
            if (!player.level().isClientSide) {
                this.setInLove(player);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        // ✅ Montarse en la cabeza si es dueño
        if (this.isTame() && this.isOwnedBy(player) && !this.isPassenger()) {
            this.setOnPlayersHead(true);
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
        this.entityData.define(ON_PLAYERS_HEAD, false);
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
        return new SleepCycleController<>(this, preparingSleepState, sleepState, awakeningState,
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
        pCompound.putBoolean("OnPlayersHead", this.isOnPlayersHead());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("SpawnBiome")) {
            String path = pCompound.getString("SpawnBiome");
            this.setSpawnBiomePath(path);
        }
        this.setOnPlayersHead(pCompound.getBoolean("OnPlayersHead"));
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

    // ───────────────────────────────────────────────────── Steal Goal ─────

    private ItemStack heldLoot = ItemStack.EMPTY;
    private int lootTicks = 0;
    private boolean holdingLoot = false;

    public void setHeldLoot(ItemStack stack) {
        this.heldLoot = stack;
        this.lootTicks = 0;
    }

    public ItemStack getHeldLoot() {
        return this.heldLoot;
    }

    public void setHoldingLoot(boolean value) {
        this.holdingLoot = value;
    }

    public boolean isHoldingLoot() {
        return this.holdingLoot && !this.heldLoot.isEmpty();
    }

    public void tickLootBehavior() {
        if (!isHoldingLoot()) {
            // Verifica si está volando demasiado alto después de terminar
            if (getIsFlying()) {
                int dist = distanceToGround();
                if (dist > 5) {
                    this.getNavigation().moveTo(getX(), getY() - 1.5, getZ(), 1.0);
                    System.out.println("[Krifto] Bajando tras soltar/comer loot. Altura sobre suelo: " + dist);
                }
            }
            return;
        }

        lootTicks++;

        if (heldLoot.isEdible()) {
            if (lootTicks >= 60) {
                System.out.println("[Krifto] Ate stolen item: " + heldLoot.getDisplayName().getString());
                heldLoot = ItemStack.EMPTY;
                holdingLoot = false;
            }
        } else {
            if (lootTicks >= 300) {
                System.out.println("[Krifto] Dropped stolen item: " + heldLoot.getDisplayName().getString());
                ItemEntity drop = new ItemEntity(level(), getX(), getY() + 0.5, getZ(), heldLoot);
                drop.setPickUpDelay(40);
                level().addFreshEntity(drop);
                heldLoot = ItemStack.EMPTY;
                holdingLoot = false;
            }
        }
    }

    public int distanceToGround() {
        BlockPos pos = this.blockPosition();
        Level level = this.level();

        int distance = 0;
        while (pos.getY() > level.getMinBuildHeight()) {
            pos = pos.below();
            distance++;

            if (!level.getBlockState(pos).isAir()) {
                return distance;
            }
        }
        return distance;
    }

    public final AnimationState swoopAnimationState = new AnimationState();

    public void playSwoopAnimation() {
        swoopAnimationState.start(this.tickCount);
    }

    // ───────────────────────────────────────────────────── Sit On Head ─────

    public boolean isOnPlayersHead() {
        return this.entityData.get(ON_PLAYERS_HEAD);
    }

    public void setOnPlayersHead(boolean value) {
        this.entityData.set(ON_PLAYERS_HEAD, value);
    }

    @Override
    public void stopRiding() {
        super.stopRiding();
        this.setOnPlayersHead(false);
        stopOnHeadAnimations();
    }


    public final AnimationState onHeadIdleAnimationState = new AnimationState();

    @Override
    public void lookAt(Entity entity, float maxYawChange, float maxPitchChange) {
        if (!this.isOnPlayersHead()) {
            super.lookAt(entity, maxYawChange, maxPitchChange);
        }
    }

    @Override
    public boolean isPushable() {
        return !this.isOnPlayersHead(); // solo colisiona si no está sobre el jugador
    }

    @Override
    protected void doPush(Entity entity) {
        if (!this.isOnPlayersHead()) {
            super.doPush(entity);
        }
    }


}
