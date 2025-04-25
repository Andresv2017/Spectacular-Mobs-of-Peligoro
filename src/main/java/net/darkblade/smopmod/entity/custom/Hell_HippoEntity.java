package net.darkblade.smopmod.entity.custom;

import com.google.common.collect.UnmodifiableIterator;
import net.darkblade.smopmod.entity.ModEntities;
import net.darkblade.smopmod.entity.ai.Hell_HippoAttackGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class Hell_HippoEntity extends Animal implements ItemSteerable, Saddleable {

    private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID;
    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME;
    private static final Ingredient FOOD_ITEMS;
    private final ItemBasedSteering steering;

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

        if(this.level().isClientSide()){
            setupAnimationStates();
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

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(DATA_SADDLE_ID, false);
        this.entityData.define(DATA_BOOST_TIME, 0);
    }

    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_289448_) -> {
        EntityType<?> entitytype = p_289448_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.GOAT || entitytype == EntityType.COW || entitytype == EntityType.PLAYER;
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

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));

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

        super.onSyncedDataUpdated(pKey);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.steering.addAdditionalSaveData(pCompound);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.steering.readAdditionalSaveData(pCompound);
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        boolean flag = this.isFood(pPlayer.getItemInHand(pHand));
        if (!flag && this.isSaddled() && !this.isVehicle() && !pPlayer.isSecondaryUseActive()) {
            if (!this.level().isClientSide) {
                pPlayer.startRiding(this);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
            if (!interactionresult.consumesAction()) {
                ItemStack itemstack = pPlayer.getItemInHand(pHand);
                return itemstack.is(Items.SADDLE) ? itemstack.interactLivingEntity(pPlayer, this, pHand) : InteractionResult.PASS;
            } else {
                return interactionresult;
            }
        }
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
        return this.isAlive() && !this.isBaby();
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

    static {
        DATA_SADDLE_ID = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_BOOST_TIME = SynchedEntityData.defineId(Hell_HippoEntity.class, EntityDataSerializers.INT);
        FOOD_ITEMS = Ingredient.of(new ItemLike[]{Items.CARROT, Items.POTATO, Items.BEETROOT});
    }

    @Override
    public double getPassengersRidingOffset() {
        return this.getBbHeight() * 0.75D + 0.4D;
    }


}
