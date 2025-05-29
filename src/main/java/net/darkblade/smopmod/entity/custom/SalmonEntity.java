package net.darkblade.smopmod.entity.custom;

import net.darkblade.smopmod.entity.WaterEntity;
import net.darkblade.smopmod.entity.ai.core.water.WaterWanderGoal;
import net.darkblade.smopmod.entity.ai.salmon.SalmonAttackGoal;
import net.darkblade.smopmod.entity.ai.salmon.SalmonDigGoal;
import net.darkblade.smopmod.entity.interfaces.sleep_system.ISleepAwareness;
import net.darkblade.smopmod.entity.interfaces.sleep_system.ISleepThreatEvaluator;
import net.darkblade.smopmod.entity.movecontrols.VerticalSwimmingMoveControl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class SalmonEntity extends WaterEntity implements ISleepThreatEvaluator, ISleepAwareness {

    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(SalmonEntity.class, EntityDataSerializers.BOOLEAN);

    public int attackAnimationTimeout = 0;

    public SalmonEntity(EntityType<? extends WaterEntity> type, Level level) {
        super(type, level);
        this.setOutOfWaterMaxTicks(80);     // 4 segundos
        this.setOutOfWaterDamage(2.0F);     // Daño por tick
        this.moveControl = new MoveControl();
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

        this.goalSelector.addGoal(0, new WaterWanderGoal<>(this));
        this.goalSelector.addGoal(1, new SalmonAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new SalmonDigGoal(this, 1.0D));
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
        if (this.digCommandCooldown > 0) {
            this.digCommandCooldown--;
        }
        if (!this.getNavigation().isDone()) {
            Vec3 move = this.getDeltaMovement();
            System.out.printf("[DEBUG] ΔMovimiento: %.3f %.3f %.3f | Velocidad: %.3f%n",
                    move.x, move.y, move.z, move.length());
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
        this.entityData.define(ATTACKING, false);
    }

    @Override
    public Set<EntityType<?>> getInterruptingEntityTypes() {
        return Set.of();
    }

    @Override
    public boolean shouldWakeOnPlayerProximity() {
        return false;
    }

    @Override
    public boolean shouldInterruptSleepDueTo(LivingEntity nearby) {
        return getInterruptingEntityTypes().contains(nearby.getType());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        boolean isMale = this.getRandom().nextBoolean();
        this.setMale(isMale);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    // ───────────────────────────────────────────────────── DIG GOAL ─────

    public int digCommandCooldown = 0; // este controla el reinicio del Goal

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getItem() == Items.PUFFERFISH) {
            if (this.digCommandCooldown > 0) {
                //System.out.println("[SALMON] Aún en cooldown para excavar.");
                return InteractionResult.FAIL;
            }

            this.setDigCommand(true);
            this.digCommandCooldown = 1200; // ⏱️ 1 minuto
            if (!player.isCreative()) stack.shrink(1);
            //System.out.println("[SALMON] Se activó el comando de excavación.");
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }


    private boolean digCommand = false;

    public boolean wantsToDig() {
        //System.out.println("[SALMON] Consulta wantsToDig(): " + digCommand);
        return digCommand;
    }

    public void setDigCommand(boolean value) {
        this.digCommand = value;
        //System.out.println("[SALMON] setDigCommand(" + value + ")");
    }

    public static final byte SNIFF_TARGET_EVENT_ID = 44;
    public final AnimationState sniffAnimationState = new AnimationState();

    public static final byte DIG_EVENT_ID = 45; // Usa un ID libre (no igual a otros eventos)
    public final AnimationState digAnimationState = new AnimationState();


    @Override
    public void handleEntityEvent(byte id) {
        if (id == SNIFF_TARGET_EVENT_ID) {
            this.sniffAnimationState.start(this.tickCount);
            System.out.println("[ANIM] Animación 'dig_target' activada desde evento de red.");
        } else if (id == DIG_EVENT_ID) {
            this.digAnimationState.start(this.tickCount);
            System.out.println("[ANIM] Animación 'dig' activada desde evento de red.");
        } else {
            super.handleEntityEvent(id);
        }
    }

    private class MoveControl extends VerticalSwimmingMoveControl {
        public MoveControl() {
            super(SalmonEntity.this, SWIM_SPEED_MODIFIER, MAX_ROT_CHANGE);
        }

        @Override
        public void tick() {
            if (!SalmonEntity.this.isStanding()) {
                if (this.operation == Operation.MOVE_TO && !SalmonEntity.this.getNavigation().isDone()) {
                    // Debug para confirmar que se ejecuta el tick de movimiento
                    System.out.printf("[MOVE] tick() activo → destino: (%.2f, %.2f, %.2f)%n", wantedX, wantedY, wantedZ);
                    super.tick();
                    SalmonEntity.this.setSpeed(SWIM_SPEED_MODIFIER); // Garantiza que se aplique velocidad
                } else {
                    SalmonEntity.this.setSpeed(0.0F);
                }
            } else {
                // Si está en modo de reposo en el fondo (standing), se detiene todo
                SalmonEntity.this.setSpeed(0.0F);
                SalmonEntity.this.setDeltaMovement(Vec3.ZERO);
            }
        }
    }


    private static final float SWIM_SPEED_MODIFIER = 0.65f;
    private static final float MAX_ROT_CHANGE = 70.0f;

}
