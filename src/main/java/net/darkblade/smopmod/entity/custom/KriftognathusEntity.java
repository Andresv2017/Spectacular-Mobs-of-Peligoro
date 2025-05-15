package net.darkblade.smopmod.entity.custom;

import net.darkblade.smopmod.entity.interfaces.ISleepingEntity;
import net.darkblade.smopmod.entity.util.SleepCycleController;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class KriftognathusEntity extends TamableAnimal implements ISleepingEntity {

    public KriftognathusEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    // ───── Synced Data ─────
    private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(KriftognathusEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> PREPARING_SLEEP = SynchedEntityData.defineId(KriftognathusEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> AWAKENING = SynchedEntityData.defineId(KriftognathusEntity.class, EntityDataSerializers.BOOLEAN);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SLEEPING, false);
        this.entityData.define(PREPARING_SLEEP, false);
        this.entityData.define(AWAKENING, false);
    }

    // ───── Animaciones (Java Keyframes) ─────
    public final AnimationState preparingSleepState = new AnimationState();
    public final AnimationState sleepState = new AnimationState();
    public final AnimationState awakeingState = new AnimationState();

    private final SleepCycleController<KriftognathusEntity> sleepController =
            new SleepCycleController<>(this, preparingSleepState, sleepState, awakeingState, 50, 70); // puedes cambiar a 20,20 si lo deseas


    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            sleepController.tick(this.tickCount);
        }
    }

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


    // ───── Guardado NBT ─────
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

    // ───── Getters / Setters de flags ─────
    @Override public boolean isSleeping() { return this.entityData.get(SLEEPING); }
    @Override public void setSleeping(boolean v) { this.entityData.set(SLEEPING, v); }

    @Override public boolean isPreparingSleep() { return this.entityData.get(PREPARING_SLEEP); }
    @Override public void setPreparingSleep(boolean v) { this.entityData.set(PREPARING_SLEEP, v); }

    @Override public boolean isAwakeing() { return this.entityData.get(AWAKENING); }
    @Override public void setAwakeing(boolean v) { this.entityData.set(AWAKENING, v); }

    // ───── Atributos base ─────
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.75D)
                .add(Attributes.ATTACK_SPEED, 0.5D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1F)
                .add(Attributes.ATTACK_DAMAGE, 2.0F);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null; // no reproducción por ahora
    }
}
