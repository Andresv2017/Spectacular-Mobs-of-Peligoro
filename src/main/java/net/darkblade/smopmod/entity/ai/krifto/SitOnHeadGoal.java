package net.darkblade.smopmod.entity.ai.krifto;

import net.darkblade.smopmod.entity.custom.KriftognathusEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class SitOnHeadGoal extends Goal {
    private final KriftognathusEntity entity;

    public SitOnHeadGoal(KriftognathusEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        if (!entity.isTame() || entity.isOrderedToSit()) return false;
        if (!(entity.getOwner() instanceof Player player)) return false;
        return !entity.isPassenger() &&
                player.getPassengers().isEmpty() &&
                entity.distanceTo(player) < 2.0;
    }

    @Override
    public void start() {
        if (entity.getOwner() instanceof Player player) {
            entity.startRiding(player, true); // Monta como pasajero
        }
    }
}


