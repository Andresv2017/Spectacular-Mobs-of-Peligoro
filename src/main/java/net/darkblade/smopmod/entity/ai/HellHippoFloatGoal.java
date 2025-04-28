package net.darkblade.smopmod.entity.ai;

import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class HellHippoFloatGoal extends Goal {
    private final Hell_HippoEntity hippo;

    public HellHippoFloatGoal(Hell_HippoEntity hippo) {
        this.hippo = hippo;
        this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return hippo.isInWater() && !hippo.isPassenger();
    }

    @Override
    public void tick() {
        if (hippo.isInWater()) {
            double fluidSurfaceY = hippo.getFluidHeight(FluidTags.WATER) + hippo.getBlockY();
            double shoulderY = hippo.getBoundingBox().maxY - 0.54D;

            Vec3 velocity = hippo.getDeltaMovement();

            if (shoulderY < fluidSurfaceY) {
                hippo.setDeltaMovement(velocity.x, 0.1D, velocity.z);
            } else {
                hippo.setDeltaMovement(velocity.x, Math.max(0.0D, velocity.y * 0.9D), velocity.z);
            }

            hippo.hasImpulse = true;
        }
    }
}