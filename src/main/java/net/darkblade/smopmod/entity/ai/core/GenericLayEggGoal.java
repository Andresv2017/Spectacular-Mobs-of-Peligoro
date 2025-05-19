package net.darkblade.smopmod.entity.ai.core;

import net.darkblade.smopmod.entity.BaseEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;

public class GenericLayEggGoal<T extends BaseEntity> extends Goal {

    private final T entity;
    private final Block eggBlock;
    private int layEggTimer;

    public GenericLayEggGoal(T entity, Block eggBlock) {
        this.entity = entity;
        this.eggBlock = eggBlock;
    }

    @Override
    public boolean canUse() {
        return entity.hasEgg() && !entity.isMammal() && entity.onGround();
    }

    @Override
    public void start() {
        layEggTimer = 0;
    }

    @Override
    public void tick() {
        ++layEggTimer;

        if (layEggTimer > 40) { // ~2 segundos
            entity.tryLayEgg(eggBlock);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return entity.hasEgg() && layEggTimer <= 60;
    }
}

