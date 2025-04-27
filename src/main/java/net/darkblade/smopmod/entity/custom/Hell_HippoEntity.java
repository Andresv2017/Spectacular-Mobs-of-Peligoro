package net.darkblade.smopmod.entity.custom;

import com.google.common.collect.UnmodifiableIterator;
import net.darkblade.smopmod.entity.ModEntities;
import net.darkblade.smopmod.entity.ai.HellHippoDefendOwnerGoal;
import net.darkblade.smopmod.entity.ai.Hell_HippoAttackGoal;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class Hell_HippoEntity extends Animal implements ItemSteerable, Saddleable {

    private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID;
    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME;
    private static final EntityDataAccessor<Boolean> DATA_TRUSTING;
    private static final EntityDataAccessor<Boolean> DATA_INTIMIDATING;
    private static final EntityDataAccessor<Boolean> DATA_SLEEPING;
    private static final Ingredient FOOD_ITEMS;
    private final ItemBasedSteering steering;
    public UUID trustingPlayerUUID;

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);

    public Hell_HippoEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.steering = new ItemBasedSteering(this.entityData, DATA_BOOST_TIME, DATA_SADDLE_ID);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();

        if (this.isSleeping()) {
            this.setDeltaMovement(Vec3.ZERO);
            return;
        }

        if(this.level().isClientSide()){
            setupAnimationStates();
        }

        if (!this.level().isClientSide && this.isTrusting() && !this.isSaddled() && this.trustingPlayerUUID != null) {
            Player player = this.level().getPlayerByUUID(this.trustingPlayerUUID);
            if (player != null) {
                double distance = this.distanceTo(player);
                if (!this.isIntimidating() && distance < 10.0D && this.hasLineOfSight(player)) {
                    this.setIntimidating(true);
                    player.displayClientMessage(Component.literal("§6Hell Hippo is intimidating you!"), true);
                }
                if (this.isIntimidating()) {
                    Vec3 toEntity = this.position().subtract(player.position()).normalize();
                    double dot = player.getLookAngle().normalize().dot(toEntity);
                    if (dot > 0.95D) {
                        if (!player.hasEffect(MobEffects.WEAKNESS)) {
                            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
                        }
                        if (!player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
                        }
                        player.displayClientMessage(Component.literal("§cYou are terrified by the Hell Hippo!"), true);
                    }
                }
            }
        }
    }

    private void setupAnimationStates (){

        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20; // Length in ticks of your animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6f, 1f);
        } else {
            f = 0;
        }
        this.walkAnimation.update(f,0.2f);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public boolean isTrusting() {
        return this.entityData.get(DATA_TRUSTING);
    }

    public void setTrusting(boolean trusting) {
        this.entityData.set(DATA_TRUSTING, trusting);
    }

    public boolean isIntimidating() {
        return this.entityData.get(DATA_INTIMIDATING);
    }

    public void setIntimidating(boolean intimidating) {
        this.entityData.set(DATA_INTIMIDATING, intimidating);
    }

    public boolean isSleeping() {
        return this.entityData.get(DATA_SLEEPING);
    }

    public void setSleeping(boolean sleeping) {
        this.entityData.set(DATA_SLEEPING, sleeping);
    }

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

    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_289448_) -> {
        EntityType<?> entitytype = p_289448_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.GOAT || entitytype == EntityType.COW;
    };


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new Hell_HippoAttackGoal(this,1.0D, true));

        this.goalSelector.addGoal(1,new BreedGoal(this, 1.15D));
        this.goalSelector.addGoal(2,new TemptGoal(this, 1.2D, Ingredient.of(Items.BEEF),false));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2, Ingredient.of(new ItemLike[]{Items.CARROT_ON_A_STICK}), false));


        this.goalSelector.addGoal(3,new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5,new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(6,new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new HellHippoDefendOwnerGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH,20.0)
                .add(Attributes.FOLLOW_RANGE,24D)
                .add(Attributes.MOVEMENT_SPEED, 0.250)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 2f);
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

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_BOOST_TIME.equals(pKey) && this.level().isClientSide) {
            this.steering.onSynced();
        }
        if (pKey.equals(DATA_INTIMIDATING) && this.level().isClientSide && this.isIntimidating()) {
            Minecraft.getInstance().player.displayClientMessage(Component.literal("§6Hell Hippo is intimidating you!"), true);
        }
        super.onSyncedDataUpdated(pKey);
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

    public boolean canAttackTarget(LivingEntity target) {
        if (this.isSaddled() && this.isTrusting() && this.getFirstPassenger() != null) {
            return false; // No atacar si está confiado, ensillado y montado
        }
        if (this.isVehicle()) {
            return false; // También no atacar si simplemente está montado
        }
        return true;
    }


    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (itemstack.is(Items.SADDLE)) {
            if (!this.level().isClientSide) {
                if (this.isSleeping() && this.isTrusting() && this.trustingPlayerUUID != null && pPlayer.getUUID().equals(this.trustingPlayerUUID)) {
                    this.equipSaddle(SoundSource.PLAYERS);
                    this.setSleeping(false);
                    if (!pPlayer.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    pPlayer.displayClientMessage(Component.literal("§6Hell Hippo has been tamed with a Saddle!"), true);
                    return InteractionResult.SUCCESS;
                } else {
                    pPlayer.displayClientMessage(Component.literal("§cYou cannot tame the Hell Hippo yet!"), true);
                    return InteractionResult.FAIL;
                }
            }
            return InteractionResult.SUCCESS;
        }

        if (this.isFood(itemstack)) {
            if (!this.level().isClientSide) {
                this.usePlayerItem(pPlayer, pHand, itemstack);
                if (!this.isTrusting()) {
                    if (this.random.nextInt(3) == 0) {
                        this.setTrusting(true);
                        this.trustingPlayerUUID = pPlayer.getUUID();
                        this.level().broadcastEntityEvent(this, (byte) 41);
                        pPlayer.displayClientMessage(Component.literal("§aHell Hippo now trusts you " + pPlayer.getName().getString()), true);
                    } else {
                        this.level().broadcastEntityEvent(this, (byte) 40);
                        pPlayer.displayClientMessage(Component.literal("§cHell Hippo remains cautious..."), true);
                    }
                } else {
                    pPlayer.displayClientMessage(Component.literal("§bHell Hippo already trusts you!"), true);
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        if (!this.level().isClientSide && this.isSaddled() && !this.isVehicle() && !pPlayer.isSecondaryUseActive()) {
            pPlayer.startRiding(this);
            return InteractionResult.SUCCESS;
        }

        // Si no es Saddle ni comida, ni montar, recién aquí hacemos super:
        return super.mobInteract(pPlayer, pHand);
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


    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob ageableMob) {
        return ModEntities.HELL_HIPPO.get().create(pLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.BEEF);
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound(){
        return SoundEvents.HOGLIN_AMBIENT;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.RAVAGER_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.HOGLIN_DEATH;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
    }

    //Montable

    @Override
    public boolean boost() {
        return this.steering.boost(this.getRandom());
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby() && this.isSleeping() && this.isTrusting();
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }

    }

    @Override
    public void equipSaddle(@Nullable SoundSource soundSource) {
        this.steering.setSaddle(true);
        if (soundSource != null) {
            this.level().playSound((Player)null, this, SoundEvents.PIG_SADDLE, soundSource, 0.5F, 1.0F);
        }
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

    protected void tickRidden(Player pPlayer, Vec3 pTravelVector) {
        super.tickRidden(pPlayer, pTravelVector);
        this.setRot(pPlayer.getYRot(), pPlayer.getXRot() * 0.5F);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
        this.steering.tickBoost();
    }

    protected Vec3 getRiddenInput(Player pPlayer, Vec3 pTravelVector) {
        return new Vec3(0.0, 0.0, 1.0);
    }

    protected float getRiddenSpeed(Player pPlayer) {
        return (float)(this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 1 * (double)this.steering.boostFactor());
    }

    public Vec3 getLeashOffset() {
        return new Vec3(0.0, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
    }

    @Override
    public boolean isSaddled() {
        return this.steering.hasSaddle();
    }

    @Override
    public double getPassengersRidingOffset() {
        return this.getBbHeight() * 0.75D + 0.4D;
    }

    static {
        DATA_SADDLE_ID = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_BOOST_TIME = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.INT);
        DATA_TRUSTING = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_INTIMIDATING = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_SLEEPING = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
        FOOD_ITEMS = Ingredient.of(new ItemLike[]{Items.CARROT, Items.BEEF});
    }
}
