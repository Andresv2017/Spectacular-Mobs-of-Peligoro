package net.darkblade.smopmod.entity.ai.tangoftero;

import net.darkblade.smopmod.entity.BaseEntity;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class CustomWanderGoal extends WaterAvoidingRandomStrollGoal {

    private final BaseEntity mob;

    public CustomWanderGoal(BaseEntity mob, double speed) {
        super(mob, speed);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        // Si está siguiendo al dueño, no deambular
        if (mob.isFollowingOwner()) return false;

        return super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        if (mob.isFollowingOwner()) return false;

        return super.canContinueToUse();
    }
}

