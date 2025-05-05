package net.darkblade.smopmod.entity.custom;

import com.google.common.collect.UnmodifiableIterator;
import net.darkblade.smopmod.effect.ModEffects;
import net.darkblade.smopmod.entity.ModEntities;
import net.darkblade.smopmod.entity.ai.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class Hell_HippoEntity extends Animal implements ItemSteerable, Saddleable {

    // ───────────────────────────────────────────────────── Synced Data ─────

    private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID;
    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME;
    private static final EntityDataAccessor<Boolean> DATA_TRUSTING;
    private static final EntityDataAccessor<Boolean> DATA_INTIMIDATING;
    private static final EntityDataAccessor<Boolean> DATA_SLEEPING;
    private static final EntityDataAccessor<Boolean> DATA_PREPARING_SLEEP;
    private static final EntityDataAccessor<Boolean> DATA_AWAKENING;
    private static final EntityDataAccessor<Boolean> DATA_MALE;
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS;

    // ───────────────────────────────────────────────────── Constructor ─────

    private final ItemBasedSteering steering;
    private final ServerBossEvent fearCooldownBossBar = new ServerBossEvent( Component.literal("Fear Cooldown"), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS);
    private final ServerBossEvent mountedAttackBossBar = new ServerBossEvent(Component.literal("Mounted Attack Cooldown"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_10);
    public UUID trustingPlayerUUID;

    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_289448_) -> {
        EntityType<?> entitytype = p_289448_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.GOAT || entitytype == EntityType.COW;
    };

    public Hell_HippoEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.steering = new ItemBasedSteering(this.entityData, DATA_BOOST_TIME, DATA_SADDLE_ID);
        this.setMaxUpStep(1.6F); // o incluso 2.0F para test
    }

    public int attackAnimationTimeout = 0;
    private int intimidatingTicks = 0;
    private int staringTicks = 0;
    private int fearCooldownTicks = 0;
    private int mountedAttackCooldownTicks = 0;
    private int biteAnimationTimeout = 0;
    private ItemStack pendingFood = ItemStack.EMPTY;
    private Player feedingPlayer = null;
    private int sleepPreparingTicks = 0;
    private int awakeningTicks = 0;


    private static final int FEAR_COOLDOWN_DURATION = 20 * 15; // 15 segundos
    private static final int MOUNTED_ATTACK_COOLDOWN = 40;

    // ───────────────────────────────────────────────────── Tick ─────

    @Override
    public void tick() {
        super.tick();

        // 🔥 Execute death animation
        if (this.isDeadOrDying()) {
            this.setDeltaMovement(Vec3.ZERO);
            return;
        }

        // Block movement if asleep or transitioning
        if (this.isPreparingSleep() || this.isSleeping() || this.isAwakening()) {
            this.setDeltaMovement(Vec3.ZERO);
        }

        if (!this.level().isClientSide) {
            // Cooldown Fear BossBar
            if (fearCooldownTicks > 0) {
                fearCooldownTicks--;
                fearCooldownBossBar.setProgress(Math.max(0.0F, (float) fearCooldownTicks / FEAR_COOLDOWN_DURATION));
                ServerPlayer rider = this.getRiderPlayer();
                fearCooldownBossBar.removeAllPlayers();
                if (rider != null) fearCooldownBossBar.addPlayer(rider);
            } else {
                fearCooldownBossBar.setProgress(0.0F);
                fearCooldownBossBar.setVisible(false);
                fearCooldownBossBar.removeAllPlayers();
            }

            // Cooldown Attack BossBar
            if (mountedAttackCooldownTicks > 0) {
                mountedAttackCooldownTicks--;
                mountedAttackBossBar.setProgress(Math.max(0.0F, (float) mountedAttackCooldownTicks / MOUNTED_ATTACK_COOLDOWN));
                ServerPlayer rider = this.getRiderPlayer();
                mountedAttackBossBar.removeAllPlayers();
                if (rider != null) {
                    mountedAttackBossBar.addPlayer(rider);
                    mountedAttackBossBar.setVisible(true);
                }
            } else {
                mountedAttackBossBar.setVisible(false);
                mountedAttackBossBar.removeAllPlayers();
            }

            // Intimidation timer
            if (this.isIntimidating()) {
                if (this.intimidatingTicks > 0) {
                    this.intimidatingTicks--;
                } else if (!this.isSleeping()) {
                    this.setIntimidating(false);
                    this.setTrusting(false);
                    this.trustingPlayerUUID = null;
                    Player player = this.level().getNearestPlayer(this, 10);
                    if (player != null) {
                        //player.displayClientMessage(Component.literal("§cHell Hippo has calmed down and forgotten your trust."), true);
                    }
                }
            }

            if (!this.isSleeping() && !this.isPreparingSleep() && this.hasEffect(MobEffects.WEAKNESS)) {
                this.setIntimidating(false);
                this.intimidatingTicks = 0;
                this.intimidateAnimationState.stop();

                //this.setDeltaMovement(Vec3.ZERO);
                this.setPreparingSleep(true);
                sleepPreparingTicks = 100;

                Player p = this.level().getNearestPlayer(this, 10);
                if (p != null) {
                    p.displayClientMessage(Component.literal("§b[HH] Preparing to sleep..."), true);
                }
            }

            if (this.isPreparingSleep()) {
                sleepPreparingTicks--;
                this.setDeltaMovement(Vec3.ZERO);
                if (sleepPreparingTicks <= 0) {
                    this.sleepPreparingAnimationState.stop();
                    this.setPreparingSleep(false);
                    this.setSleeping(true);

                    // 🔧 Force animation on client.
                    if (this.level().isClientSide) {
                        this.sleepAnimationState.start(this.tickCount);
                    }

                    Player p = this.level().getNearestPlayer(this, 10);
                    if (p != null) {
                        p.displayClientMessage(Component.literal("§9[HH] Hell Hippo is now sleeping."), true);
                    }
                }
            }

            if (this.isSleeping() && (!this.hasEffect(MobEffects.WEAKNESS) || this.isSaddled())) {
                this.setSleeping(false);
                this.sleepAnimationState.stop();
                this.awakeningTicks = 130;
                this.setAwakening(true);
                Player p = this.level().getNearestPlayer(this, 10);
                if (p != null) {
                    p.displayClientMessage(Component.literal("§e[HH] Hell Hippo is waking up..."), true);
                }
            }

            if (awakeningTicks > 0) {
                awakeningTicks--;
                if (awakeningTicks == 0) {
                    this.setAwakening(false);
                    Player p = this.level().getNearestPlayer(this, 10);
                    if (p != null) {
                        p.displayClientMessage(Component.literal("§a[HH] Hell Hippo is now awake."), true);
                    }
                }
            }

            // Sprint toggle and speed adjustment
            boolean isMounted = this.isVehicle();
            boolean isChasing = this.getTarget() != null && this.getTarget().isAlive();
            this.setSprinting(isMounted || isChasing);
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.isSprinting() ? 0.3F : 0.2F);

            // Stop movement during intimidation
            if (this.isIntimidating()) {
                this.setDeltaMovement(Vec3.ZERO);
                this.navigation.stop();
                this.getLookControl().setLookAt(this, 10.0F, 10.0F);
            }

            // Sink logic
            if (this.isInWater() && !this.isInLove() && !this.isVehicle()) {
                Vec3 velocity = this.getDeltaMovement();
                this.setDeltaMovement(velocity.x, velocity.y - 0.03D, velocity.z);
                this.hasImpulse = true;
            }
        }

        if (this.isSleeping()) {
            this.setDeltaMovement(Vec3.ZERO);
            return;
        }

        // Shake logic
        if (this.isInWaterRainOrBubble()) {
            this.isWet = true;
            this.shakeTicks = 0;
        } else if (this.isWet && !this.isShaking && this.onGround()) {
            this.isShaking = true;
            this.shakeTicks = 0;
            this.level().broadcastEntityEvent(this, (byte) 60);
        }

        if (this.isShaking) {
            this.shakeTicks++;
            this.setDeltaMovement(Vec3.ZERO);
            if (this.shakeTicks == 1) {
                this.playSound(SoundEvents.WOLF_SHAKE, 1.0F, 1.0F);
                this.shakeAnimationState.start(this.tickCount);
            }
            if (this.shakeTicks >= 70) {
                this.isWet = false;
                this.isShaking = false;
                this.shakeAnimationState.stop();
            }
        }

        // Attack logic
        if (this.attackAnimationTimeout > 0) {
            this.attackAnimationTimeout--;
            if (this.attackAnimationTimeout == 0) {
                this.setAttacking(false);
            }
        }

        // Feeding logic
        if (this.biteAnimationTimeout > 0) {
            this.biteAnimationTimeout--;
            if (this.biteAnimationTimeout == 0 && !this.level().isClientSide && !this.pendingFood.isEmpty()) {
                if (this.getHealth() < this.getMaxHealth()) {
                    this.heal(4.0F);
                }
                if (!this.isTrusting() && this.pendingFood.is(Items.BEEF)) {
                    this.setTrusting(true);
                    this.trustingPlayerUUID = this.feedingPlayer != null ? this.feedingPlayer.getUUID() : null;
                    this.playSound(SoundEvents.HOGLIN_ANGRY, 1.0F, 1.0F);
                }
                if (this.feedingPlayer != null && !this.feedingPlayer.getAbilities().instabuild) {
                    this.pendingFood.shrink(1);
                    this.feedingPlayer.displayClientMessage(Component.literal("§aHell Hippo has eaten the food!"), true);
                }
                this.pendingFood = ItemStack.EMPTY;
                this.feedingPlayer = null;
                this.biteAnimationState.stop();
            }
        }

        // Animations
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }

        // Trusting → Intimidate
        if (!this.level().isClientSide() && this.isTrusting() && !this.isSaddled() && this.trustingPlayerUUID != null) {
            Player player = this.level().getPlayerByUUID(this.trustingPlayerUUID);
            if (player != null) {
                double distance = this.distanceTo(player);
                if (!this.isIntimidating() && distance < 10.0D && this.hasLineOfSight(player)) {
                    this.setIntimidating(true);
                    this.intimidatingTicks = 300;
                    player.displayClientMessage(Component.literal("§6Hell Hippo is now intimidating!"), true);
                }
                if (this.isIntimidating()) {
                    Vec3 toEntity = this.position().subtract(player.position()).normalize();
                    double dot = player.getLookAngle().normalize().dot(toEntity);
                    if (dot > 0.95D) {
                        staringTicks++;
                        if (staringTicks >= 100) {
                            if (!player.hasEffect(ModEffects.FEAR.get())) {
                                player.addEffect(new MobEffectInstance(ModEffects.FEAR.get(), 300, 0));
                            }
                            player.displayClientMessage(Component.literal("§7You are terrified by the Hell Hippo!"), true);
                            staringTicks = 0;
                        }
                    } else {
                        staringTicks = 0;
                    }
                }
            }
        }
    }

    // ───────────────────────────────────────────────────── Fear System ─────

    public void triggerFearCooldown() {
        if (!this.level().isClientSide) {
            this.fearCooldownTicks = FEAR_COOLDOWN_DURATION;
            this.fearCooldownBossBar.setProgress(1.0f);
            this.fearCooldownBossBar.setVisible(true);

            ServerPlayer rider = this.getRiderPlayer();
            this.fearCooldownBossBar.removeAllPlayers();
            if (rider != null) {
                this.fearCooldownBossBar.addPlayer(rider);
            }
        }
    }

    public boolean isFearOnCooldown() {
        return this.fearCooldownTicks > 0;
    }


    public void performFearEffect(Player player) {
        if (this.isFearOnCooldown()) {
            player.displayClientMessage(Component.literal("§7Fear is on cooldown..."), true);
            return;
        }
        double range = 10.0D;
        Vec3 pos = this.position();
        LivingEntity controllingRider = this.getControllingPassenger();

        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class,
                new AABB(pos.x - range, pos.y - 2, pos.z - range, pos.x + range, pos.y + 2, pos.z + range),
                (entity) -> entity.isAlive()
                        && !(entity instanceof Hell_HippoEntity)
                        && entity != player
                        && entity != controllingRider
                        && !(entity instanceof TamableAnimal tamable && tamable.isOwnedBy(player))
        );

        for (LivingEntity entity : entities) {
            entity.addEffect(new MobEffectInstance(ModEffects.FEAR.get(), 60)); // 3 segundos
        }

        this.setIntimidating(true);
        this.intimidatingTicks = 60;
        this.lastIntimidateAnimationTick = -1;
        if (!this.level().isClientSide) {
            this.intimidateAnimationState.start(this.tickCount);
        }

        player.displayClientMessage(Component.literal("§9Hell Hippo unleashes FEAR!"), true);
        this.triggerFearCooldown();
    }


    @Nullable
    private ServerPlayer getRiderPlayer() {
        Entity passenger = this.getFirstPassenger();
        if (passenger instanceof ServerPlayer serverPlayer) {
            return serverPlayer;
        }
        return null;
    }

    // ───────────────────────────────────────────────────── Trust System ─────

    public boolean isTrusting() {return this.entityData.get(DATA_TRUSTING);}

    public void setTrusting(boolean trusting) {this.entityData.set(DATA_TRUSTING, trusting);}

    public boolean isIntimidating() {return this.entityData.get(DATA_INTIMIDATING);}

    public void setIntimidating(boolean intimidating) {this.entityData.set(DATA_INTIMIDATING, intimidating);}

    public boolean isSleeping() {return this.entityData.get(DATA_SLEEPING);}

    public void setSleeping(boolean sleeping) {this.entityData.set(DATA_SLEEPING, sleeping);}

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (!this.level().isClientSide) {
            // Attempt to use saddle
            if (itemstack.is(Items.SADDLE)) {
                if (this.isSleeping() && this.trustingPlayerUUID != null && pPlayer.getUUID().equals(this.trustingPlayerUUID)) {
                    this.equipSaddle(SoundSource.PLAYERS);
                    this.setSleeping(false);
                    this.setIntimidating(false);
                    this.intimidatingTicks = 0;
                    this.awakeningTicks = 130;
                    this.setAwakening(true);
                    this.removeEffect(MobEffects.WEAKNESS); //
                    if (!pPlayer.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    //pPlayer.displayClientMessage(Component.literal("§6Hell Hippo has been tamed with a Saddle and wakes up!"), true);
                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.FAIL;
                }
            }
            // Attempt to Trust Feed
            if (TRUST_ITEM.test(itemstack)) {
                this.usePlayerItem(pPlayer, pHand, itemstack);
                if (!this.isTrusting()) {
                    if (this.random.nextInt(3) == 0) {
                        this.setTrusting(true);
                        this.trustingPlayerUUID = pPlayer.getUUID();
                        this.level().broadcastEntityEvent(this, (byte) 41);
                        pPlayer.displayClientMessage(Component.literal("\u00a7aHell Hippo now trusts you, " + pPlayer.getName().getString()), true);
                    } else {
                        this.level().broadcastEntityEvent(this, (byte) 40);
                        pPlayer.displayClientMessage(Component.literal("\u00a7cHell Hippo remains cautious..."), true);
                    }
                } else {
                    pPlayer.displayClientMessage(Component.literal("\u00a7bHell Hippo already trusts you!"), true);
                }
                return InteractionResult.SUCCESS;
            }

            // Allow feeding with food if saddled
            if (this.isSaddled() && itemstack.isEdible() && pPlayer.isShiftKeyDown()) {
                if (!this.biteAnimationState.isStarted()) {
                    this.pendingFood = itemstack.copy();
                    this.feedingPlayer = pPlayer;
                    this.level().broadcastEntityEvent(this, (byte) 42); // ← nuevo byte para bite anim
                    this.biteAnimationTimeout = 20;
                    pPlayer.displayClientMessage(Component.literal("§eHell Hippo is eating..."), true);
                    return InteractionResult.SUCCESS;
                } else {
                    pPlayer.displayClientMessage(Component.literal("§cWait, still chewing..."), true);
                    return InteractionResult.CONSUME;
                }
            }

            if (this.isSaddled() && !this.isVehicle() && !itemstack.isEdible() && !pPlayer.isSecondaryUseActive()) {
                if (!this.isAwakening()) {
                    pPlayer.startRiding(this);
                    return InteractionResult.SUCCESS;
                } else {
                    pPlayer.displayClientMessage(Component.literal("§7[HH] Wait, still waking up..."), true);
                    return InteractionResult.FAIL;
                }
            }
        }

        return super.mobInteract(pPlayer, pHand);
    }

    // ───────────────────────────────────────────────────── Saddle System ─────

    @Override
    public void positionRider(Entity passenger, MoveFunction moveFunction) {
        super.positionRider(passenger, moveFunction);
        if (passenger instanceof LivingEntity) {
            Vec3 offset = new Vec3(0.0D, 1.55D, 0.0D);
            Vec3 pos = this.position();
            moveFunction.accept(passenger, pos.x + offset.x, pos.y + offset.y, pos.z + offset.z);

        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isIntimidating()) {
            this.setDeltaMovement(Vec3.ZERO);
            this.navigation.stop();
            return;
        }

        super.travel(travelVector);
    }

    @Override
    public boolean boost() {return this.steering.boost(this.getRandom());}

    @Override
    public boolean isSaddled() {return this.steering.hasSaddle();}

    @Override
    public boolean isSaddleable() {return this.isAlive() && !this.isBaby() && this.isSleeping() && this.isTrusting();}

    @Override
    public void equipSaddle(@Nullable SoundSource soundSource) {
        this.steering.setSaddle(true);
        if (soundSource != null) {
            this.level().playSound((Player)null, this, SoundEvents.PIG_SADDLE, soundSource, 0.5F, 1.0F);
        }
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }
    }

    @Override
    protected void tickRidden(Player rider, Vec3 travelVector) {
        super.tickRidden(rider, travelVector);
        this.setRot(rider.getYRot(), rider.getXRot() * 0.5F);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
        this.steering.tickBoost();

        // 🚀 Sprint logic for animation
        if (this.getDeltaMovement().horizontalDistanceSqr() > 0.03D) {
            this.setSprinting(true);
        } else {
            this.setSprinting(false);
        }
    }

    protected Vec3 getRiddenInput(Player pPlayer, Vec3 pTravelVector) {return new Vec3(0.0, 0.0, 1.0);}

    protected float getRiddenSpeed(Player pPlayer) {
        return (float)(this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 1 * (double)this.steering.boostFactor());
    }

    public Vec3 getDismountLocationForPassenger(LivingEntity pLivingEntity) {
        Direction direction = this.getMotionDirection();
        if (direction.getAxis() == Direction.Axis.Y) {
            return super.getDismountLocationForPassenger(pLivingEntity);
        } else {
            int[][] aint = DismountHelper.offsetsForDirection(direction);
            BlockPos blockpos = this.blockPosition();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
            UnmodifiableIterator var6 = pLivingEntity.getDismountPoses().iterator();

            while(var6.hasNext()) {
                Pose pose = (Pose)var6.next();
                AABB aabb = pLivingEntity.getLocalBoundsForPose(pose);
                int[][] var9 = aint;
                int var10 = aint.length;

                for(int var11 = 0; var11 < var10; ++var11) {
                    int[] aint1 = var9[var11];
                    blockpos$mutableblockpos.set(blockpos.getX() + aint1[0], blockpos.getY(), blockpos.getZ() + aint1[1]);
                    double d0 = this.level().getBlockFloorHeight(blockpos$mutableblockpos);
                    if (DismountHelper.isBlockFloorValid(d0)) {
                        Vec3 vec3 = Vec3.upFromBottomCenterOf(blockpos$mutableblockpos, d0);
                        if (DismountHelper.canDismountTo(this.level(), pLivingEntity, aabb.move(vec3))) {
                            pLivingEntity.setPose(pose);
                            return vec3;
                        }
                    }
                }
            }

            return super.getDismountLocationForPassenger(pLivingEntity);
        }
    }

    @Override
    public double getPassengersRidingOffset() {
        return this.getBbHeight() * 0.75D + 0.4D;
    }

    @javax.annotation.Nullable
    public LivingEntity getControllingPassenger() {
        if (this.isSaddled()) {
            Entity entity = this.getFirstPassenger();
            if (entity instanceof Player) {
                Player player = (Player)entity;
                if (player.getMainHandItem().is(Items.CARROT_ON_A_STICK) || player.getOffhandItem().is(Items.CARROT_ON_A_STICK)) {
                    return player;
                }
            }
        }

        return null;
    }

    public Vec3 getLeashOffset() {
        return new Vec3(0.0, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
    }

    // ───────────────────────────────────────────────────── Attack System ─────

    public void performMountedAttack(Player player) {
        if (this.mountedAttackCooldownTicks > 0) return;

        Vec3 eyePos = this.getEyePosition();
        Vec3 lookVec = this.getLookAngle();
        Vec3 reachEnd = eyePos.add(lookVec.scale(5.5));
        AABB attackBox = this.getBoundingBox().expandTowards(lookVec.scale(5.5)).inflate(1.0);

        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, attackBox,
                e -> e != this && e != player && e.isAlive() && this.hasLineOfSight(e));

        LivingEntity target = null;
        double closest = Double.MAX_VALUE;

        for (LivingEntity entity : targets) {
            AABB entityBox = entity.getBoundingBox().inflate(0.3);
            Optional<Vec3> hit = entityBox.clip(eyePos, reachEnd);
            if (hit.isPresent()) {
                double dist = eyePos.distanceToSqr(hit.get());
                if (dist < closest) {
                    closest = dist;
                    target = entity;
                }
            }
        }

        this.swing(InteractionHand.MAIN_HAND);
        this.setAttacking(true);
        this.attackAnimationTimeout = 20;
        this.mountedAttackCooldownTicks = 40;

        if (target != null) {
            target.hurt(this.damageSources().mobAttack(this),
                    (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));
            player.displayClientMessage(Component.literal("\u00a76Hell Hippo attacks (hit)!"), true);
        } else {
            this.playSound(SoundEvents.HOGLIN_ANGRY, 1.0F, 1.0F);
            player.displayClientMessage(Component.literal("\u00a7eHell Hippo swings but hits nothing."), true);
        }
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        if (this.isTrusting() && this.trustingPlayerUUID != null) {
            if (target instanceof Player player) {
                return !player.getUUID().equals(this.trustingPlayerUUID); // no atacar a su dueño
            }
            if (target instanceof TamableAnimal tamable) {
                if (tamable.isOwnedBy(this.level().getPlayerByUUID(this.trustingPlayerUUID))) {
                    return false;
                }
            }
        }
        return super.canAttack(target);
    }

    //───────────────────────────────────────────────────── Shake ─────

    private boolean isWet;
    private boolean isShaking;
    private int shakeTicks;

    //───────────────────────────────────────────────────── Sex ─────

    // ─────────────────────────────────────── Synced Data

    public boolean isMale() {
        return this.entityData.get(DATA_MALE);
    }

    public void setMale(boolean male) {
        this.entityData.set(DATA_MALE, male);
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason,
                                        @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        super.finalizeSpawn(level, difficulty, reason, spawnData, tag); // muy importante
        this.setMale(this.getRandom().nextBoolean());
        return spawnData;
    }


    @Override
    public boolean canMate(Animal other) {
        if (!(other instanceof Hell_HippoEntity hippo)) return false;
        return this.isMale() != hippo.isMale(); // solo macho + hembra
    }

    // ───────────────────────────────────────────────────── Sleep System ─────

    public boolean isPreparingSleep() {
        return this.entityData.get(DATA_PREPARING_SLEEP);
    }

    public void setPreparingSleep(boolean value) {
        this.entityData.set(DATA_PREPARING_SLEEP, value);
    }

    public boolean isAwakening() {
        return this.entityData.get(DATA_AWAKENING);
    }

    public void setAwakening(boolean value) {
        this.entityData.set(DATA_AWAKENING, value);
    }

    // ───────────────────────────────────────────────────── Animations ─────

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        return new AmphibiousPathNavigation(this, world);
    }
    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public double getFluidJumpThreshold() {
        return Double.MAX_VALUE;
    }

    private int eatCooldown = 0;
    private int lastIntimidateAnimationTick = -1;

    public final AnimationState eatAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState sprintAnimationState = new AnimationState();
    public final AnimationState waterIdleAnimationState = new AnimationState();
    public final AnimationState biteAnimationState = new AnimationState();
    public final AnimationState intimidateAnimationState = new AnimationState();
    public final AnimationState shakeAnimationState = new AnimationState();
    public final AnimationState sleepPreparingAnimationState = new AnimationState();
    public final AnimationState sleepAnimationState = new AnimationState();
    public final AnimationState awakeningAnimationState = new AnimationState();
    public final AnimationState deathAnimationState = new AnimationState();

    private void stopAllAnimationsExcept(AnimationState keep) {
        AnimationState[] all = {
                sleepPreparingAnimationState, sleepAnimationState, awakeningAnimationState,
                intimidateAnimationState, attackAnimationState, walkAnimationState,
                swimAnimationState, waterIdleAnimationState, idleAnimationState,
                sprintAnimationState, eatAnimationState, biteAnimationState, shakeAnimationState
        };

        for (AnimationState anim : all) {
            if (anim != keep) anim.stop();
        }
    }

    private void setupAnimationStates() {
        // 🔥 Reproducir animación de muerte si está muriendo
        if (this.isDeadOrDying()) {
            this.stopAllAnimationsExcept(deathAnimationState);
            if (!this.deathAnimationState.isStarted()) {
                this.deathAnimationState.start(this.tickCount);
            }
            return;
        }

        // 🔒 BLOQUEA todo si está en estados de sueño
        if (this.isPreparingSleep()) {
            if (!this.sleepPreparingAnimationState.isStarted()) {
                this.sleepPreparingAnimationState.start(this.tickCount);
            }
            this.stopAllAnimationsExcept(sleepPreparingAnimationState);
            return;
        }

        // 👇 PRIORIDAD primero awakening, luego sleep
        if (this.isAwakening()) {
            if (!this.awakeningAnimationState.isStarted()) {
                this.awakeningAnimationState.start(this.tickCount);
            }
            this.stopAllAnimationsExcept(awakeningAnimationState);
            return;
        }

        if (this.isSleeping()) {
            System.out.println("Sleeping anim check @ tick " + this.tickCount);
            this.stopAllAnimationsExcept(sleepAnimationState);
            if (!this.sleepAnimationState.isStarted()) {
                System.out.println("Starting sleep animation at tick " + this.tickCount);
                this.sleepAnimationState.start(this.tickCount);
            } else {
                System.out.println("Already running sleep animation at tick " + this.tickCount);
            }
            return;
        }

        // ⛔ STOP animación de intimidación si NO debe estar activa
        if (!this.isIntimidating() && this.intimidateAnimationState.isStarted()) {
            this.intimidateAnimationState.stop(); // <<<<<<<<<< ✅ clave
        }

        // ⏺️ Lógica de intimidación (solo si está activa y no en estado de sueño)
        if (this.isIntimidating()) {
            if (!this.intimidateAnimationState.isStarted() || this.tickCount - lastIntimidateAnimationTick >= 150) {
                this.intimidateAnimationState.start(this.tickCount);
                lastIntimidateAnimationTick = this.tickCount;
                this.playSound(SoundEvents.RAVAGER_ROAR, 1.0F, 1.0F);
            }
            this.stopAllAnimationsExcept(intimidateAnimationState);
            return;
        }

        if (this.isAttacking()) {
            if (!this.attackAnimationState.isStarted()) {
                this.attackAnimationState.start(this.tickCount);
                this.attackAnimationTimeout = 20; // duración visual del ataque
            }
        } else {
            if (this.attackAnimationState.isStarted() && this.attackAnimationTimeout <= 0) {
                this.attackAnimationState.stop();
            }
        }

        if (this.attackAnimationTimeout > 0) {
            this.attackAnimationTimeout--;
        }

        if (this.isInWaterOrBubble()) {
            if (this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6) {
                if (!this.swimAnimationState.isStarted()) {
                    this.swimAnimationState.start(this.tickCount);
                }
                this.walkAnimationState.stop();
                this.idleAnimationState.stop();
                this.waterIdleAnimationState.stop();
                this.eatAnimationState.stop();
            } else {
                if (!this.waterIdleAnimationState.isStarted()) {
                    this.waterIdleAnimationState.start(this.tickCount);
                }
                this.walkAnimationState.stop();
                this.swimAnimationState.stop();
                this.idleAnimationState.stop();
                this.eatAnimationState.stop();
            }
        } else {
            if (this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6) {
                if (this.isSprinting()) {
                    if (!this.sprintAnimationState.isStarted()) {
                        this.sprintAnimationState.start(this.tickCount);
                    }
                    this.walkAnimationState.stop();
                    this.idleAnimationState.stop();
                    this.waterIdleAnimationState.stop();
                    this.eatAnimationState.stop();
                } else {
                    if (!this.walkAnimationState.isStarted()) {
                        this.walkAnimationState.start(this.tickCount);
                    }
                    this.sprintAnimationState.stop();
                    this.idleAnimationState.stop();
                    this.waterIdleAnimationState.stop();
                    this.eatAnimationState.stop();
                }
            } else {
                if (!this.idleAnimationState.isStarted() && !this.eatAnimationState.isStarted()) {
                    this.idleAnimationState.start(this.tickCount);
                }
                this.walkAnimationState.stop();
                this.sprintAnimationState.stop();
                this.waterIdleAnimationState.stop();
                this.swimAnimationState.stop();

                if (this.onGround() && !this.isInWater() && !this.isSleeping() && !this.isAttacking()) {
                    if (this.eatCooldown <= 0 && this.random.nextInt(300) == 0) {
                        this.eatAnimationState.start(this.tickCount);
                        this.idleAnimationState.stop();
                        this.eatCooldown = 100;
                    }
                }
            }
        }

        if (this.eatCooldown > 0) {
            this.eatCooldown--;
        }

        if (this.eatCooldown == 0 && this.eatAnimationState.isStarted()) {
            this.eatAnimationState.stop();
        }
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
        if (this.level().isClientSide) {
            this.deathAnimationState.start(this.tickCount);
        }
    }

    // ───────────────────────────────────────────────────── Sounds ─────

    @Override
    protected @Nullable SoundEvent getAmbientSound(){return SoundEvents.HOGLIN_AMBIENT;}

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {return SoundEvents.RAVAGER_HURT;}

    @Override
    protected @Nullable SoundEvent getDeathSound() {return SoundEvents.HOGLIN_DEATH;}

    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
    }

    // ───────────────────────────────────────────────────── Serialization (NBT) ─────

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(DATA_SADDLE_ID, false);
        this.entityData.define(DATA_BOOST_TIME, 0);
        this.entityData.define(DATA_TRUSTING, false);
        this.entityData.define(DATA_INTIMIDATING, false);
        this.entityData.define(DATA_SLEEPING, false);
        this.entityData.define(DATA_PREPARING_SLEEP, false);
        this.entityData.define(DATA_AWAKENING, false);
        this.entityData.define(DATA_MALE, true);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.steering.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("Trusting", this.isTrusting());
        pCompound.putBoolean("Intimidating", this.isIntimidating());
        pCompound.putBoolean("Sleeping", this.isSleeping());
        pCompound.putBoolean("PreparingSleep", this.isPreparingSleep());
        pCompound.putBoolean("IsMale", this.isMale());
        if (this.trustingPlayerUUID != null) {
            pCompound.putUUID("TrustingPlayerUUID", this.trustingPlayerUUID);
        }
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.steering.readAdditionalSaveData(pCompound);
        if (pCompound.contains("Trusting")) {
            this.setTrusting(pCompound.getBoolean("Trusting"));
        }
        if (pCompound.contains("Intimidating")) {
            this.setIntimidating(pCompound.getBoolean("Intimidating"));
        }
        if (pCompound.contains("Sleeping")) {
            this.setSleeping(pCompound.getBoolean("Sleeping"));
        }
        if (pCompound.contains("PreparingSleep")) {
            this.setPreparingSleep(pCompound.getBoolean("PreparingSleep"));
        }
        if (pCompound.contains("IsMale")) {
            this.setMale(pCompound.getBoolean("IsMale"));
        }
        if (pCompound.hasUUID("TrustingPlayerUUID")) {
            this.trustingPlayerUUID = pCompound.getUUID("TrustingPlayerUUID");
        }
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (pKey.equals(DATA_SLEEPING)) {
            boolean sleeping = this.isSleeping();
            System.out.println("CLIENT: DATA_SLEEPING updated to " + sleeping + " @ tick " + this.tickCount);
            if (sleeping && !this.sleepAnimationState.isStarted()) {
                this.stopAllAnimationsExcept(sleepAnimationState);
                this.sleepAnimationState.start(this.tickCount);
                System.out.println("CLIENT: sleepAnimationState started from onSyncedDataUpdated()");
            }
        }
        if (DATA_BOOST_TIME.equals(pKey) && this.level().isClientSide) {
            this.steering.onSynced();
        }
        if (pKey.equals(DATA_INTIMIDATING) && this.level().isClientSide && this.isIntimidating()) {
            Minecraft.getInstance().player.displayClientMessage(Component.literal("§6Hell Hippo is intimidating you!"), true);
        }
        super.onSyncedDataUpdated(pKey);
    }

    // ───────────────────────────────────────────────────── Goals ─────

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(2, new Hell_HippoAttackGoal(this,1.0D, true));

        this.goalSelector.addGoal(1,new BreedGoal(this, 1.15D));
        this.goalSelector.addGoal(2, new HellHippoTemptGoal(this, 1.2D, FOOD_ITEMS));

        this.goalSelector.addGoal(2, new HellHippoWaterStrollGoal(this, 2.0));

        this.goalSelector.addGoal(6,new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(9,new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(10,new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new HellHippoDefendOwnerGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new HippoTargetPlayerGoal(this));
        this.targetSelector.addGoal(3, new HippoTargetPreyGoal(this, PREY_SELECTOR));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH,20.0)
                .add(Attributes.FOLLOW_RANGE,24D)
                .add(Attributes.MOVEMENT_SPEED, 0.250)
                .add(Attributes.ATTACK_SPEED, 0.250)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 2f);
    }

    // ───────────────────────────────────────────────────── Event Subscriptions ─────

    @Mod.EventBusSubscriber
    public static class ModEvents {

        @SubscribeEvent
        public static void onEntityAttack(LivingAttackEvent event) {
            if (event.getSource().getEntity() instanceof Hell_HippoEntity hippo) {
                if (hippo.isVehicle() && event.getEntity() == hippo.getFirstPassenger()) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 42) {
            this.biteAnimationState.start(this.tickCount);
            this.biteAnimationTimeout = 20; // duración de animación
        } else if (id == 60) {
            this.isShaking = true;
            this.shakeTicks = 0;
            this.shakeAnimationState.start(this.tickCount);
        } else {
            super.handleEntityEvent(id);
        }
    }

    // ───────────────────────────────────────────────────── Static Block ─────

    static {
        DATA_SADDLE_ID = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_BOOST_TIME = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.INT);
        DATA_TRUSTING = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_INTIMIDATING = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_SLEEPING = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_PREPARING_SLEEP = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_AWAKENING = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_MALE = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
        FOOD_ITEMS = Ingredient.of(new ItemLike[]{Items.CARROT, Items.BEEF});
    }

    // ───────────────────────────────────────────────────── Breeding ─────

    private static final Ingredient BREEDING_ITEM = Ingredient.of(Items.CARROT);
    private static final Ingredient TRUST_ITEM = Ingredient.of(Items.BEEF);

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob ageableMob) {return ModEntities.HELL_HIPPO.get().create(pLevel);}

    @Override
    public boolean isFood(ItemStack stack) {
        return BREEDING_ITEM.test(stack); //
    }

}