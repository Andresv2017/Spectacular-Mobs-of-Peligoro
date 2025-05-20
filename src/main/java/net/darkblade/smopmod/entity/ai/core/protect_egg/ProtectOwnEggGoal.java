package net.darkblade.smopmod.entity.ai.core.protect_egg;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;

import java.util.function.Predicate;

public class ProtectOwnEggGoal extends ProtectEggBaseGoal {

    /**
     * Protects a single egg placed by this mob. The egg must be assigned externally via assignEgg().
     */
    public ProtectOwnEggGoal(
            Animal mob,
            int stayNearEggRadius,
            int defenseRadius,
            boolean attackOnApproach,
            boolean attackOnBreak,
            Predicate<LivingEntity> enemySelector,
            EggBreakReaction eggBreakReaction) {

        super(mob, stayNearEggRadius, defenseRadius, attackOnApproach, attackOnBreak, enemySelector, eggBreakReaction);
    }

    /**
     * Called automatically by the parent class. This goal does not search for eggs,
     * it only uses the egg position assigned manually.
     */
    @Override
    protected BlockPos findTargetEgg() {
        return targetEggPos;
    }

    /**
     * Assigns the egg position to protect. Should be called when the mob lays an egg.
     */
    public void assignEgg(BlockPos pos) {
        this.targetEggPos = pos.immutable();
    }
}
