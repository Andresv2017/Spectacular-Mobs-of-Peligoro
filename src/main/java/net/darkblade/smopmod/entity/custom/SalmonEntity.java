package net.darkblade.smopmod.entity.custom;



import net.darkblade.smopmod.entity.WaterEntity;
import net.darkblade.smopmod.entity.ai.salmon.SalmonAttackGoal;
import net.darkblade.smopmod.entity.ai.salmon.SalmonDigGoal;
import net.darkblade.smopmod.entity.interfaces.sleep_system.ISleepAwareness;
import net.darkblade.smopmod.entity.interfaces.sleep_system.ISleepThreatEvaluator;
import net.darkblade.smopmod.item.ModItems;
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
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class SalmonEntity extends WaterEntity implements ISleepThreatEvaluator, ISleepAwareness {

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
        this.goalSelector.addGoal(2, new SalmonDigGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 1.0D, 40));


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
    protected int getPreparingSleepDuration() {
        return 5;
    }

    @Override
    protected int getAwakeningDuration() {
        return 5;
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
                System.out.println("[SALMON] Aún en cooldown para excavar.");
                return InteractionResult.FAIL;
            }

            this.setDigCommand(true);
            this.digCommandCooldown = 1200; // ⏱️ 1 minuto
            if (!player.isCreative()) stack.shrink(1);
            System.out.println("[SALMON] Se activó el comando de excavación.");
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }


    private boolean digCommand = false;

    public boolean wantsToDig() {
        System.out.println("[SALMON] Consulta wantsToDig(): " + digCommand);
        return digCommand;
    }

    public void setDigCommand(boolean value) {
        this.digCommand = value;
        System.out.println("[SALMON] setDigCommand(" + value + ")");
    }

    public static final byte DIG_TARGET_EVENT_ID = 44;
    public final AnimationState digTargetAnimationState = new AnimationState();


    @Override
    public void handleEntityEvent(byte id) {
        if (id == DIG_TARGET_EVENT_ID) {
            this.digTargetAnimationState.start(this.tickCount);
            System.out.println("[ANIM] Animación 'dig_target' activada desde evento de red.");
        } else {
            super.handleEntityEvent(id);
        }
    }

}
