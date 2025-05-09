package net.darkblade.smopmod.entity.ai.hell_hippo;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class HellHippoWaterStrollGoal extends Goal {
    private final PathfinderMob mob;
    private final double speedModifier;
    private int cooldownTicks;

    public HellHippoWaterStrollGoal(PathfinderMob mob, double speedModifier) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return this.mob.isInWater() && this.mob.onGround();
    }

    private int impulseCooldown = 20; // Start at 1 second

    @Override
    public void tick() {
        if (--cooldownTicks <= 0) {
            cooldownTicks = 40 + this.mob.getRandom().nextInt(40); // Random move cada 2-4 segundos
            Vec3 randomPos = getRandomWaterSurfacePos();
            if (randomPos != null) {
                Path path = this.mob.getNavigation().createPath(BlockPos.containing(randomPos), 1);
                if (path != null && path.canReach()) {
                    this.mob.getNavigation().moveTo(path, speedModifier);
                }
            }
        }

        if (this.mob.isInWater() && !this.mob.getNavigation().isDone()) {
            if (--impulseCooldown <= 0) {
                Vec3 velocity = this.mob.getDeltaMovement();
                this.mob.setDeltaMovement(velocity.x, 0.35D, velocity.z); // Impulso leve hacia arriba
                impulseCooldown = 20; // Cada 20 ticks = 1 segundo exacto
            }
        }
    }




    private Vec3 getRandomWaterSurfacePos() {
        Vec3 basePos = this.mob.position();
        double offsetX = this.mob.getRandom().nextGaussian() * 5;
        double offsetZ = this.mob.getRandom().nextGaussian() * 5;
        BlockPos pos = BlockPos.containing(basePos.x + offsetX, basePos.y, basePos.z + offsetZ);
        if (this.mob.level().getFluidState(pos).isEmpty()) {
            return null; // Solo aceptar si está bajo agua
        }
        return Vec3.atBottomCenterOf(pos);
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse();
    }
}