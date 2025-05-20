package net.darkblade.smopmod.entity.ai.core.protect_egg;

import net.darkblade.smopmod.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public abstract class ProtectEggBaseGoal extends Goal {

    protected final Animal mob;
    protected final int stayNearEggRadius;
    protected final int defenseRadius;
    protected final boolean attackOnApproach;
    protected final boolean attackOnBreak;
    protected final Predicate<LivingEntity> enemySelector;
    protected final EggBreakReaction eggBreakReaction;

    protected BlockPos targetEggPos;

    public enum EggBreakReaction {
        FLEE,
        IGNORE
    }

    public ProtectEggBaseGoal(
            Animal mob,
            int stayNearEggRadius,
            int defenseRadius,
            boolean attackOnApproach,
            boolean attackOnBreak,
            Predicate<LivingEntity> enemySelector,
            EggBreakReaction eggBreakReaction) {
        this.mob = mob;
        this.stayNearEggRadius = stayNearEggRadius;
        this.defenseRadius = defenseRadius;
        this.attackOnApproach = attackOnApproach;
        this.attackOnBreak = attackOnBreak;
        this.enemySelector = enemySelector;
        this.eggBreakReaction = eggBreakReaction;
    }

    @Nullable
    protected abstract BlockPos findTargetEgg();

    protected void onEggReached() {
        mob.getNavigation().stop();
        mob.getLookControl().setLookAt(Vec3.atCenterOf(targetEggPos));
    }

    @Override
    public boolean canUse() {
        if (mob instanceof TamableAnimal tamable) {
            if (tamable.isOrderedToSit() || tamable.isTame())
                return false;
        }

        targetEggPos = findTargetEgg();
        return targetEggPos != null;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetEggPos == null) return false;

        if (mob.level().isLoaded(targetEggPos)) {
            Block block = mob.level().getBlockState(targetEggPos).getBlock();
            return block.builtInRegistryHolder().is(ModTags.Blocks.EGG_BLOCKS);
        }

        return false;
    }

    @Override
    public void stop() {
        targetEggPos = null;
    }

    @Override
    public void tick() {
        if (targetEggPos == null) return;

        // If currently targeting an enemy, pursue it if still in range
        if (mob.getTarget() != null) {
            double distToTarget = mob.distanceToSqr(mob.getTarget());

            if (distToTarget > defenseRadius * defenseRadius) {
                mob.setTarget(null); // Stop chasing if too far
            }

            return; // Skip repositioning while pursuing
        }

        // Stay near the egg if no threat
        double dist = mob.distanceToSqr(Vec3.atCenterOf(targetEggPos));
        if (dist > stayNearEggRadius * stayNearEggRadius) {
            Vec3 center = Vec3.atCenterOf(targetEggPos);
            mob.getNavigation().moveTo(center.x, center.y, center.z, 1.1f);
        } else {
            onEggReached();
            checkForThreats();
        }
    }

    protected void checkForThreats() {
        if (!attackOnApproach || targetEggPos == null) return;

        List<LivingEntity> threats = mob.level().getEntitiesOfClass(
                LivingEntity.class,
                new AABB(targetEggPos).inflate(defenseRadius),
                e -> e != mob && enemySelector.test(e)
        );

        if (!threats.isEmpty()) {
            mob.setTarget(threats.get(0));
        }
    }

    public void notifyEggBroken(BlockPos brokenPos) {
        if (targetEggPos == null || !targetEggPos.equals(brokenPos)) return;

        switch (eggBreakReaction) {
            case FLEE -> {
                Vec3 escape = DefaultRandomPos.getPosAway(mob, 16, 7, Vec3.atCenterOf(targetEggPos));
                if (escape != null) {
                    mob.getNavigation().moveTo(escape.x, escape.y, escape.z, 1.2);
                }
            }
            case IGNORE -> {
                // Do nothing
            }
        }

        if (attackOnBreak) {
            List<LivingEntity> threats = mob.level().getEntitiesOfClass(
                    LivingEntity.class,
                    new AABB(brokenPos).inflate(defenseRadius),
                    e -> e != mob && enemySelector.test(e)
            );

            if (!threats.isEmpty()) {
                mob.setTarget(threats.get(0));
            }
        }
    }
}