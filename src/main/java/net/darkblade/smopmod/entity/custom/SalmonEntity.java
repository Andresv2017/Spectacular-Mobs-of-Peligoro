package net.darkblade.smopmod.entity.custom;


import net.darkblade.smopmod.entity.WaterEntity;
import net.darkblade.smopmod.entity.ai.krifto.KriftoAttackGoal;
import net.darkblade.smopmod.entity.ai.salmon.SalmonAttackGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

import java.util.Set;

public class SalmonEntity extends WaterEntity {

    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(SalmonEntity.class, EntityDataSerializers.BOOLEAN);

    public int attackAnimationTimeout = 0;

    public SalmonEntity(EntityType<? extends WaterEntity> type, Level level) {
        super(type, level);
        this.setOutOfWaterMaxTicks(80);     // 4 segundos
        this.setOutOfWaterDamage(2.0F);     // Daño por tick
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_SPEED, 0.4D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1F)
                .add(Attributes.ATTACK_DAMAGE, 1.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SalmonAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 40));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        if (sleepController == null) {
            sleepController = createSleepController();
        }
        if (this.level().isClientSide()) {
            updateAquaticAnimations();
        }
    }

    public final AnimationState attackAnimationState = new AnimationState();

    @Override
    public void updateAquaticAnimations() {
        super.updateAquaticAnimations();
        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 13; // Length in ticks of your animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }
        if (!this.isAttacking()) {
            attackAnimationState.stop();
        }
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
    }

    @Override
    public Set<EntityType<?>> getInterruptingEntityTypes() {
        return Set.of();
    }
}
