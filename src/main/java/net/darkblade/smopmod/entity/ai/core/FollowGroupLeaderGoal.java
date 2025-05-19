package net.darkblade.smopmod.entity.ai.core;

import net.darkblade.smopmod.entity.interfaces.IHasLeader;
import net.darkblade.smopmod.entity.interfaces.gropu_behaviour.GroupType;
import net.darkblade.smopmod.entity.interfaces.gropu_behaviour.IGroupBehaviour;
import net.darkblade.smopmod.entity.util.GroupUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class FollowGroupLeaderGoal<T extends Mob & IGroupBehaviour & IHasLeader> extends Goal {

    private final T follower;
    private LivingEntity leader;
    private final double speedModifier;
    private final float minDistance;
    private final float maxDistance;
    private int cooldown = 0;

    public FollowGroupLeaderGoal(T follower, double speedModifier, float minDistance, float maxDistance) {
        this.follower = follower;
        this.speedModifier = speedModifier;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (follower.getGroupType() != GroupType.PACK) return false;
        if (follower.getTarget() != null) return false;

        leader = follower.getGroupLeader();

        if (leader == null || leader.isRemoved()) {
            GroupUtil.reassignLeaderIfNeeded(follower);
            leader = follower.getGroupLeader();
        }

        return leader != null && follower.distanceTo(leader) > maxDistance;
    }

    @Override
    public void start() {
        cooldown = 0;
    }

    @Override
    public boolean canContinueToUse() {
        return leader != null && !leader.isRemoved() &&
                follower.distanceTo(leader) > minDistance;
    }

    @Override
    public void tick() {
        if (leader == null || --cooldown > 0) return;
        cooldown = 10;
        follower.getNavigation().moveTo(leader, speedModifier);
    }
}
