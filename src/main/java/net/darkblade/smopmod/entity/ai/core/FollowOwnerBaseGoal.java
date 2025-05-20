package net.darkblade.smopmod.entity.ai.core;

import net.darkblade.smopmod.entity.BaseEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

import java.util.EnumSet;

public class FollowOwnerBaseGoal extends Goal {

    private final Level level;
    private final BaseEntity mob;
    private LivingEntity owner;
    private final double speedModifier;
    private int timeToRecalcPath = 0;
    private PathNavigation navigation;
    private boolean canFly = false;
    private boolean swims = false;
    private float oldWaterCost;
    private final float startDistance;
    private final float stopDistance;

    /**
     * @param mob The mob to apply the goal to.
     * @param speed Movement speed toward owner.
     * @param startDist Min distance to begin following.
     * @param stopDist Distance to stop pathing.
     * @param flies True if the mob can fly.
     * @param swims True if the mob can swim.
     */
    public FollowOwnerBaseGoal(BaseEntity mob, double speed, float startDist, float stopDist, boolean flies, boolean swims) {
        this.level = mob.level();
        this.mob = mob;
        this.owner = mob.getOwner();
        this.speedModifier = speed;
        this.navigation = mob.getNavigation();
        this.startDistance = startDist;
        this.stopDistance = stopDist;
        this.canFly = flies;
        this.swims = swims;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.mob.getOwner();
        if (livingentity == null || mob.isWandering() || livingentity.isSpectator()) return false;
        if (unableToMove()) return false;
        if (this.mob.distanceToSqr(livingentity) < (this.startDistance * this.startDistance)) return false;
        this.owner = livingentity;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.navigation.isDone() || mob.isWandering()) return false;
        if (unableToMove()) return false;
        return this.mob.distanceToSqr(this.owner) > (this.stopDistance * this.stopDistance);
    }

    private boolean unableToMove() {
        return this.mob.isOrderedToSit() || this.mob.isPassenger() || this.mob.isLeashed();
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.mob.getPathfindingMalus(BlockPathTypes.WATER);
        this.mob.setFollowingOwner(true);
    }

    @Override
    public void stop() {
        this.owner = null;
        this.navigation.stop();
        this.mob.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
        this.mob.setFollowingOwner(false);
    }


    @Override
    public void tick() {
        this.mob.getLookControl().setLookAt(this.owner, 10.0F, this.mob.getMaxHeadXRot());

        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);

            double distanceSqr = this.mob.distanceToSqr(this.owner);
            if (distanceSqr >= 144.0D) {
                teleportToOwner();
                return;
            }

            BlockPos targetPos = this.navigation.getTargetPos();
            BlockPos ownerPos = this.owner.blockPosition();

            // Solo mover si no hay path o el destino está lejos del owner
            if (!this.navigation.isInProgress() || targetPos == null || !targetPos.closerThan(ownerPos, 2)) {
                this.navigation.moveTo(this.owner, this.speedModifier);
            }
        }
    }


    private void teleportToOwner() {
        BlockPos blockpos = this.owner.blockPosition();
        for(int i = 0; i < 10; ++i) {
            int dx = randomIntInclusive(-3, 3);
            int dy = randomIntInclusive(-1, 1);
            int dz = randomIntInclusive(-3, 3);
            if (maybeTeleportTo(blockpos.getX() + dx, blockpos.getY() + dy, blockpos.getZ() + dz)) return;
        }
    }

    private boolean maybeTeleportTo(int x, int y, int z) {
        if (Math.abs(x - this.owner.getX()) < 2.0D && Math.abs(z - this.owner.getZ()) < 2.0D) return false;
        if (!canTeleportTo(new BlockPos(x, y, z))) return false;
        this.mob.moveTo(x + 0.5D, y, z + 0.5D, this.mob.getYRot(), this.mob.getXRot());
        this.navigation.stop();
        return true;
    }

    private boolean canTeleportTo(BlockPos pos) {
        BlockPathTypes type = WalkNodeEvaluator.getBlockPathTypeStatic(this.level, pos.mutable());
        BlockState below = this.level.getBlockState(pos.below());

        if (this.swims && type != BlockPathTypes.WALKABLE && !below.is(Blocks.WATER)) return false;
        if (this.canFly && type != BlockPathTypes.WALKABLE && !below.is(Blocks.AIR)) return false;
        if (!this.canFly && !this.swims && (type != BlockPathTypes.WALKABLE || below.getBlock() instanceof LeavesBlock)) return false;

        BlockPos offset = pos.subtract(this.mob.blockPosition());
        return this.level.noCollision(this.mob, this.mob.getBoundingBox().move(offset));
    }

    private int randomIntInclusive(int min, int max) {
        return this.mob.getRandom().nextInt(max - min + 1) + min;
    }

    /**
     * Call this method if you change the mob's navigation instance.
     */
    public void refreshNavigatorPath() {
        this.navigation = this.mob.getNavigation();
    }
}

