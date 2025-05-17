package net.darkblade.smopmod.entity.ai.tangoftero;

import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;

public class FollowLeaderGoal extends Goal {

    private final TangofteroEntity follower;
    private TangofteroEntity leader;
    private final double speedModifier;
    private final float minDist;
    private int cooldown;

    public FollowLeaderGoal(TangofteroEntity follower, double speed, float minDist) {
        this.follower = follower;
        this.speedModifier = speed;
        this.minDist = minDist;
        this.cooldown = 0;
    }

    @Override
    public boolean canUse() {

        if (follower.isTame() || follower.getTarget() != null) return false;

        if (follower.getLeader() != null && follower.getLeader().isRemoved()) {
            follower.setLeader(null);
        }

        if (follower.getLeader() == null) {
            assignNearbyLeader();
        }

        leader = follower.getLeader();
        return leader != null && leader.distanceTo(follower) > minDist;
    }

    @Override
    public void start() {
        cooldown = 0;
    }

    @Override
    public void stop() {
        follower.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (leader == null) return;

        if (--cooldown <= 0) {
            cooldown = 1;
            follower.getNavigation().moveTo(leader, speedModifier);
        }
    }

    private void assignNearbyLeader() {
        List<TangofteroEntity> candidates = follower.level().getEntitiesOfClass(
                TangofteroEntity.class,
                follower.getBoundingBox().inflate(22),
                e -> e != follower
                        && e.isAlive()
                        && !e.isTame()
                        && !e.isBaby()
                        && e.getTarget() == null
                        && e.getLeader() == null
        );

        if (!candidates.isEmpty()) {
            follower.setLeader(candidates.get(0));
        } else {
            follower.setLeader(null);
        }
    }

}

