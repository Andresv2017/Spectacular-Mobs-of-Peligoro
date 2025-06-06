package net.darkblade.smopmod.entity.ai.salmon;

import net.darkblade.smopmod.entity.custom.SalmonEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class SalmonAttackGoal extends MeleeAttackGoal {

    private final SalmonEntity entity;
    private int attackDelay = 5;
    private int ticksUntilNextAttack = 10;
    private boolean shouldCountTillNextAttack = false;
    private boolean startedAttackAnimation = false;

    public SalmonAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.entity = (SalmonEntity) pMob;
    }


    @Override
    public void start() {
        super.start();
        attackDelay = 6;
        ticksUntilNextAttack = 7;
        shouldCountTillNextAttack = false;
        startedAttackAnimation = false;
        entity.setAttacking(false);
        entity.attackAnimationTimeout = 0;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        if (pDistToEnemySqr <= this.getAttackReachSqr(pEnemy)) {
            shouldCountTillNextAttack = true;
        } else {
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            startedAttackAnimation = false;
            entity.attackAnimationTimeout = 0;
            resetAttackCooldown();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (shouldCountTillNextAttack) {
            ticksUntilNextAttack = Math.max(ticksUntilNextAttack - 1, 0);

            if (!startedAttackAnimation && ticksUntilNextAttack <= attackDelay) {
                entity.setAttacking(true);
                entity.attackAnimationTimeout = attackDelay;
                startedAttackAnimation = true;
            }

            if (ticksUntilNextAttack <= 0) {
                LivingEntity target = this.mob.getTarget();
                if (target != null && this.getAttackReachSqr(target) >= this.mob.distanceToSqr(target)) {
                    this.mob.getLookControl().setLookAt(target.getX(), target.getEyeY(), target.getZ());
                    this.performAttack(target);
                    resetAttackCooldown();
                }
            }
        }

        if (entity.attackAnimationTimeout > 0) {
            entity.attackAnimationTimeout--;
        } else if (startedAttackAnimation) {
            entity.setAttacking(false);
            startedAttackAnimation = false;
        }
    }

    protected void performAttack(LivingEntity target) {
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(target);
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay + 20);
    }

    @Override
    public void stop() {
        super.stop();
        shouldCountTillNextAttack = false;
        entity.setAttacking(false);
        entity.attackAnimationTimeout = 0;
        startedAttackAnimation = false;
    }

    @Override
    public boolean canUse() {
        if (entity.isSleeping() || entity.isPreparingSleep() || entity.isAwakeing()) {
            return false;
        }
        return super.canUse();
    }

}
