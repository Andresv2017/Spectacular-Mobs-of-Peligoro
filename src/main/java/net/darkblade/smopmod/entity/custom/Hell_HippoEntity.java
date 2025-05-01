package net.darkblade.smopmod.entity.custom;

import com.google.common.collect.UnmodifiableIterator;
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
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
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
        this.setMaxUpStep(2.0F); // o incluso 2.0F para test
    }

    public int attackAnimationTimeout = 0;
    private int intimidatingTicks = 0;
    private int staringTicks = 0;
    private int fearCooldownTicks = 0;
    private int mountedAttackCooldownTicks = 0;
    private int biteAnimationTimeout = 0;
    private ItemStack pendingFood = ItemStack.EMPTY;
    private Player feedingPlayer = null;

    private static final int FEAR_COOLDOWN_DURATION = 20 * 15; // 15 segundos
    private static final int MOUNTED_ATTACK_COOLDOWN = 40;

    // ───────────────────────────────────────────────────── Tick ─────

    @Override
    public void tick() {
        super.tick();

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
                        player.displayClientMessage(Component.literal("§cHell Hippo has calmed down and forgotten your trust."), true);
                    }
                }
            }

            // Wake up logic
            if (this.isSleeping() && !this.hasEffect(MobEffects.WEAKNESS)) {
                this.setSleeping(false);
                if (this.trustingPlayerUUID != null) {
                    Player trustingPlayer = this.level().getPlayerByUUID(this.trustingPlayerUUID);
                    if (trustingPlayer != null) {
                        trustingPlayer.displayClientMessage(Component.literal("§eHell Hippo wakes up."), true);
                    }
                }
            }

            // Sprint toggle and speed adjustment
            boolean isMounted = this.isVehicle();
            boolean isChasing = this.getTarget() != null && this.getTarget().isAlive();
            this.setSprinting(isMounted || isChasing);
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.isSprinting() ? 0.3F : 0.2F);

            // Detener movimiento durante intimidación
            if (this.isIntimidating()) {
                this.setDeltaMovement(Vec3.ZERO);
                this.navigation.stop();
                this.getLookControl().setLookAt(this, 10.0F, 10.0F); // opcional: mirada fija
            }

            // Sink logic
            if (this.isInWater() && !this.isInLove() && !this.isVehicle()) {
                Vec3 velocity = this.getDeltaMovement();
                this.setDeltaMovement(velocity.x, velocity.y - 0.03D, velocity.z);
                this.hasImpulse = true;
            }
        }

        // Sleeping disables all movement
        if (this.isSleeping()) {
            this.setDeltaMovement(Vec3.ZERO);
            return;
        }

        // === Water exit shake logic ===
        if (this.isInWaterRainOrBubble()) {
            this.isWet = true;
            this.shakeTicks = 0;
        } else if (this.isWet && !this.isShaking && this.onGround()) {
            this.isShaking = true;
            this.shakeTicks = 0;
            this.level().broadcastEntityEvent(this, (byte) 60); // para iniciar animación en cliente
        }

        if (this.isShaking) {
            this.shakeTicks++;
            this.setDeltaMovement(Vec3.ZERO); // Se mantiene quieto durante sacudida

            if (this.shakeTicks == 1) {
                this.playSound(SoundEvents.WOLF_SHAKE, 1.0F, 1.0F); // o tu sonido personalizado
                this.shakeAnimationState.start(this.tickCount);
            }

            if (this.shakeTicks >= 70) {
                this.isWet = false;
                this.isShaking = false;
                this.shakeAnimationState.stop();
            }
        }

        // Attack animation handling
        if (this.attackAnimationTimeout > 0) {
            this.attackAnimationTimeout--;
            if (this.attackAnimationTimeout == 0) {
                this.setAttacking(false);
            }
        }

        // Bite animation handling (feeding)
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

                if (this.feedingPlayer != null) {
                    if (!this.feedingPlayer.getAbilities().instabuild) {
                        this.pendingFood.shrink(1);
                    }
                    this.feedingPlayer.displayClientMessage(Component.literal("§aHell Hippo has eaten the food!"), true);
                }

                this.pendingFood = ItemStack.EMPTY;
                this.feedingPlayer = null;
                this.biteAnimationState.stop();
            }
        }

        // Animations client-side
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }

        // Passive trust-check behavior
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
                            if (!player.hasEffect(MobEffects.WEAKNESS)) {
                                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                            }
                            if (!player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
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
            this.fearCooldownBossBar.removeAllPlayers(); // Limpiar
            if (rider != null) {
                this.fearCooldownBossBar.addPlayer(rider);
            }
        }
    }

    public boolean isFearOnCooldown() {return fearCooldownTicks > 0;}

    public void performFearEffect(Player player) {
        if (this.isFearOnCooldown()) {
            // No mostrar mensaje ya que la BossBar indica el cooldown
            return;
        }

        double range = 10.0D;
        Vec3 pos = this.position();
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class,
                new AABB(pos.x - range, pos.y - 2, pos.z - range, pos.x + range, pos.y + 2, pos.z + range),
                (entity) -> entity.isAlive()
                        && !(entity instanceof Hell_HippoEntity)
                        && entity != player
                        && !(entity instanceof TamableAnimal tamable && tamable.isOwnedBy(player))
        );

        for (LivingEntity entity : entities) {
            entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
        }

        player.displayClientMessage(Component.literal("§9Hell Hippo unleashes FEAR!"), true);

        this.triggerFearCooldown();
    }

    public void startSeenBy(ServerPlayer player) {
        if (this.isFearOnCooldown()) {
            this.fearCooldownBossBar.addPlayer(player);
        }
        if (this.mountedAttackCooldownTicks > 0) {
            this.mountedAttackBossBar.addPlayer(player);
        }
    }

    public void stopSeenBy(ServerPlayer player) {
        this.fearCooldownBossBar.removePlayer(player);
        this.mountedAttackBossBar.removePlayer(player);
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
            // Intento de usar Saddle
            if (itemstack.is(Items.SADDLE)) {
                if (this.isSleeping() && this.trustingPlayerUUID != null && pPlayer.getUUID().equals(this.trustingPlayerUUID)) {
                    this.equipSaddle(SoundSource.PLAYERS);
                    this.setSleeping(false); // 👈 Se despierta
                    this.setIntimidating(false); // 👈 Deja de intimidar si estaba
                    this.intimidatingTicks = 0;  // 👈 Resetea conteo intimidante
                    // 🚫 NO tocar Trusting aquí: se mantiene confiado
                    if (!pPlayer.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    pPlayer.displayClientMessage(Component.literal("§6Hell Hippo has been tamed with a Saddle and wakes up!"), true);
                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.FAIL;
                }
            }

            // Intento de alimentar
            if (this.isFood(itemstack)) {
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

            // Permitir alimentar con comida si está saddled
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

            // Intento de montar (solo si no está usando item de comida)
            if (this.isSaddled() && !this.isVehicle() && !itemstack.isEdible() && !pPlayer.isSecondaryUseActive()) {
                pPlayer.startRiding(this);
                return InteractionResult.SUCCESS;
            }

        }

        return super.mobInteract(pPlayer, pHand);
    }

    // ───────────────────────────────────────────────────── Saddle System ─────

    @Override
    public void positionRider(Entity passenger, MoveFunction moveFunction) {
        super.positionRider(passenger, moveFunction);
        if (passenger instanceof LivingEntity) {
            Vec3 offset = new Vec3(0.0D, 1.55D, 0.0D); // 🔽 Baja al jinete 0.3 bloques
            Vec3 pos = this.position();
            moveFunction.accept(passenger, pos.x + offset.x, pos.y + offset.y, pos.z + offset.z);

        }
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

    private LivingEntity findNearestAttackableTarget() {
        double range = 2.0D;
        Vec3 forward = this.getLookAngle().normalize();
        Vec3 start = this.getEyePosition();
        Vec3 end = start.add(forward.scale(range));

        AABB searchBox = new AABB(start, end).inflate(0.5);

        List<LivingEntity> candidates = this.level().getEntitiesOfClass(LivingEntity.class, searchBox,
                (entity) -> entity.isAlive()
                        && !(entity instanceof Hell_HippoEntity)
                        && this.hasLineOfSight(entity)
        );

        return candidates.stream()
                .min((e1, e2) -> Double.compare(this.distanceToSqr(e1), this.distanceToSqr(e2)))
                .orElse(null);
    }

    public boolean canAttackTarget(LivingEntity target) {
        if (this.isSaddled() && this.isTrusting() && this.getFirstPassenger() != null) {
            return false; // No atacar si está confiado, ensillado y montado
        }
        if (this.isVehicle()) {
            return false; // También no atacar si simplemente está montado
        }
        return true;
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
                    return false; // no atacar mascotas de su dueño
                }
            }
        }
        return super.canAttack(target);
    }

    //───────────────────────────────────────────────────── Shake ─────

    private boolean isWet;
    private boolean isShaking;
    private int shakeTicks;
    private int waterTicks;


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
        return Double.MAX_VALUE; // NO saltos en agua nunca
    }

    private int eatCooldown = 0; // Cooldown interno para comer
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

    private void setupAnimationStates() {
        if (this.biteAnimationState.isStarted()) {
            if (this.biteAnimationTimeout > 0) {
                // Mientras la animación está activa, bloquea otras
                this.attackAnimationState.stop();
                this.walkAnimationState.stop();
                this.swimAnimationState.stop();
                this.waterIdleAnimationState.stop();
                this.idleAnimationState.stop();
                this.sprintAnimationState.stop();
                this.eatAnimationState.stop();
                return;
            } else {
                // Forzar stop si se quedó colgada
                this.biteAnimationState.stop();
            }
        }
        if (this.isIntimidating()) {
            if (!this.intimidateAnimationState.isStarted() || this.tickCount - lastIntimidateAnimationTick >= 150) {
                this.intimidateAnimationState.start(this.tickCount);
                lastIntimidateAnimationTick = this.tickCount;
                this.playSound(SoundEvents.RAVAGER_ROAR, 1.0F, 1.0F); // ← Puedes cambiar este sonido
            }
            this.attackAnimationState.stop();
            this.walkAnimationState.stop();
            this.swimAnimationState.stop();
            this.waterIdleAnimationState.stop();
            this.idleAnimationState.stop();
            this.sprintAnimationState.stop();
            this.eatAnimationState.stop();
            this.biteAnimationState.stop();
            return;
        } else {
            this.intimidateAnimationState.stop();
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

    // Checks if the Hell Hippo is currently moving horizontally.
    public boolean isMoving() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
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
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.steering.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("Trusting", this.isTrusting());
        pCompound.putBoolean("Intimidating", this.isIntimidating());
        pCompound.putBoolean("Sleeping", this.isSleeping());
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
        if (pCompound.hasUUID("TrustingPlayerUUID")) {
            this.trustingPlayerUUID = pCompound.getUUID("TrustingPlayerUUID");
        }
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_BOOST_TIME.equals(pKey) && this.level().isClientSide) {
            this.steering.onSynced();
        }
        if (pKey.equals(DATA_INTIMIDATING) && this.level().isClientSide && this.isIntimidating()) {
            Minecraft.getInstance().player.displayClientMessage(Component.literal("§6Hell Hippo is intimidating you!"), true);
        }
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public boolean addEffect(MobEffectInstance effectInstance, @Nullable Entity source) {
        if (!this.level().isClientSide && effectInstance.getEffect() == MobEffects.WEAKNESS) {
            if (this.isIntimidating() && !this.isSleeping()) {
                this.setSleeping(true);
                if (source instanceof Player player) {
                    player.displayClientMessage(Component.literal("§9Hell Hippo falls asleep... Zzz..."), true);
                }
            }
        }
        return super.addEffect(effectInstance, source);
    }

    // ───────────────────────────────────────────────────── Goals ─────

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(2, new Hell_HippoAttackGoal(this,1.0D, true));

        this.goalSelector.addGoal(1,new BreedGoal(this, 1.15D));
        this.goalSelector.addGoal(2, new HellHippoTemptGoal(this, 1.2D, Ingredient.of(Items.CARROT_ON_A_STICK, Items.BEEF)));

        //this.goalSelector.addGoal(2, new HellHippoWaterStrollGoal(this, 2.0));

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
        FOOD_ITEMS = Ingredient.of(new ItemLike[]{Items.CARROT, Items.BEEF});
    }

    // ───────────────────────────────────────────────────── Breeding ─────

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob ageableMob) {return ModEntities.HELL_HIPPO.get().create(pLevel);}

    @Override
    public boolean isFood(ItemStack pStack) {return pStack.is(Items.BEEF);}
}