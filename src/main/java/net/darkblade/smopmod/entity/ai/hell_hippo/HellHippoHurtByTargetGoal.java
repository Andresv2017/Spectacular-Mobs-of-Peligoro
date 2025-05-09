package net.darkblade.smopmod.entity.ai.hell_hippo;

import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

public class HellHippoHurtByTargetGoal extends HurtByTargetGoal {

    public HellHippoHurtByTargetGoal(Hell_HippoEntity mob) {
        super(mob);
    }

    @Override
    public boolean canUse() {
        if (this.mob.isSleeping()) {
            return false; // 💤 Ignora daño si está dormido
        }
        return super.canUse();
    }
}

