package net.darkblade.smopmod.entity.ai.hell_hippo;

import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

public class HippoTargetPlayerGoal extends NearestAttackableTargetGoal<Player> {
    private final Hell_HippoEntity hippo;

    public HippoTargetPlayerGoal(Hell_HippoEntity mob) {
        super(mob, Player.class, true);
        this.hippo = mob;
    }

    @Override
    public boolean canUse() {
        return !hippo.isSaddled() && super.canUse();
    }
}
