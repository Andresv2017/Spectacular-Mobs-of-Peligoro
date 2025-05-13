package net.darkblade.smopmod.entity.ai.tangoftero;

import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.crafting.Ingredient;

public class TangofteroTemptGoal extends TemptGoal {
    private final TangofteroEntity tangoftero;

    public TangofteroTemptGoal(TangofteroEntity tangoftero, double speed, Ingredient temptItems, boolean canScare) {
        super(tangoftero, speed, temptItems, canScare);
        this.tangoftero = tangoftero;
    }

    @Override
    public boolean canUse() {
        if (tangoftero.isSleeping() || tangoftero.isPreparingSleep() || tangoftero.isAwakeing()) {
            return false;
        }
        return super.canUse();
    }
}