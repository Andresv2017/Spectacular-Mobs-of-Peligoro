package net.darkblade.smopmod.entity.ai;

import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class Hell_HippoAttackGoal extends MeleeAttackGoal {

    private final Hell_HippoEntity entity;
    private int attackDelay = 7;
    private int ticksUntilNextAttack = 13;
    private boolean shouldCountTillNextAttack = false;
    private boolean startedAttackAnimation = false;

    public Hell_HippoAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.entity = (Hell_HippoEntity) pMob;
    }

    @Override
    public boolean canUse() {
        if (entity.isSleeping()) return false;
        if (this.entity.isTrusting() && this.entity.isSaddled() && this.entity.isVehicle()) {
            return false;
        }
        return super.canUse();
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 7;
        ticksUntilNextAttack = 13;
        startedAttackAnimation = false;
        shouldCountTillNextAttack = false;
        entity.setAttacking(false);
        entity.attackAnimationTimeout = 0;
    }

    private void performAttack(LivingEntity target) {
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(target);
    }

    @Override
    public void tick() {
        super.tick();

        if (entity.isSleeping()) return;

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
}