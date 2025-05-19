package net.darkblade.smopmod.entity.interfaces.gropu_behaviour;

import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public interface IGroupBehaviour {
    GroupType getGroupType();
    GroupReaction getGroupReaction();

    default boolean isInGroup() {
        return getGroupType() != GroupType.NONE;
    }

    default List<LivingEntity> getNearbyGroupMembers(LivingEntity self, double radius) {
        return self.level().getEntitiesOfClass(LivingEntity.class, self.getBoundingBox().inflate(radius),
                e -> e != self &&
                        e instanceof IGroupBehaviour other &&
                        ((IGroupBehaviour) e).getGroupType() == this.getGroupType() &&
                        e.getType() == self.getType());
    }

    default boolean shouldFleeAsGroup() {
        return getGroupReaction() == GroupReaction.EVASIVE;
    }

    default boolean shouldDefendAsGroup() {
        return getGroupReaction() == GroupReaction.DEFENSIVE;
    }

    default boolean hasNeutralGroupResponse() {
        return getGroupReaction() == GroupReaction.NEUTRAL;
    }
}


