package net.darkblade.smopmod.entity.ai.core;

import net.darkblade.smopmod.entity.BaseEntity;
import net.minecraft.world.entity.ai.goal.BreedGoal;

public class GenericBreedGoal<T extends BaseEntity> extends BreedGoal {

    private final T entity;

    public GenericBreedGoal(T entity, double speed) {
        super(entity, speed);
        this.entity = entity;
    }

    @Override
    protected void breed() {
        // Solo poner huevo si no es mamífero
        if (!entity.isMammal()) {
            entity.setHasEgg(true);
        }

        this.animal.setAge(6000);
        this.partner.setAge(6000);
        this.animal.resetLove();
        this.partner.resetLove();
    }

    @Override
    public boolean canUse() {
        // Evitar si está dormido
        if (entity.isSleeping() || entity.isPreparingSleep() || entity.isAwakeing()) return false;

        return super.canUse();
    }
}

