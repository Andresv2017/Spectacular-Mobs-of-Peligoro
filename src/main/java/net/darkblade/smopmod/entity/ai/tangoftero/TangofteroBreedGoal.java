package net.darkblade.smopmod.entity.ai.tangoftero;

import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.minecraft.world.entity.ai.goal.BreedGoal;

public class TangofteroBreedGoal extends BreedGoal {

    private final TangofteroEntity tangoftero;

    public TangofteroBreedGoal(TangofteroEntity entity, double speed) {
        super(entity, speed);
        this.tangoftero = entity;
    }

    @Override
    protected void breed() {
        this.tangoftero.setHasEgg(true); // 🔁 esto es lo importante
        this.animal.setAge(6000);
        this.partner.setAge(6000);
        this.animal.resetLove();
        this.partner.resetLove();
    }


    @Override
    public boolean canUse() {
        if (tangoftero.isSleeping() || tangoftero.isPreparingSleep() || tangoftero.isAwakeing()) {
            return false;
        }
        return super.canUse();
    }

}
