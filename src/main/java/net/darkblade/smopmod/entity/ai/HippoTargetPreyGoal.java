package net.darkblade.smopmod.entity.ai;

import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;

import java.util.function.Predicate;

public class HippoTargetPreyGoal extends NearestAttackableTargetGoal<Animal> {
    private final Hell_HippoEntity hippo;

    public HippoTargetPreyGoal(Hell_HippoEntity mob, Predicate<LivingEntity> preySelector) {
        super(mob, Animal.class, false, preySelector);
        this.hippo = mob;
    }

    @Override
    public boolean canUse() {
        return !hippo.isSaddled() && super.canUse();
    }
}
