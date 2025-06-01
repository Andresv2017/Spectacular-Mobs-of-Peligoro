package net.darkblade.smopmod.entity.ai.core;

import net.darkblade.smopmod.entity.BaseEntity;
import net.darkblade.smopmod.entity.WaterEntity;
import net.darkblade.smopmod.entity.ai.core.protect_egg.ProtectOwnEggGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;


public class GenericLayEggGoal<T extends BaseEntity> extends Goal {

    private final T entity;
    private final Block eggBlock;
    @Nullable
    private final ProtectOwnEggGoal protectGoal;
    private int layEggTimer;

    // Constructor for ProtectOwnEggGoal
    public GenericLayEggGoal(T entity, Block eggBlock, ProtectOwnEggGoal protectGoal) {
        this.entity = entity;
        this.eggBlock = eggBlock;
        this.protectGoal = protectGoal;
    }

    // Constructor for ProtectNearestEggGoal (no assignment)
    public GenericLayEggGoal(T entity, Block eggBlock) {
        this.entity = entity;
        this.eggBlock = eggBlock;
        this.protectGoal = null;
    }

    @Override
    public boolean canUse() {
        if (!entity.hasEgg() || entity.isMammal()) return false;

        // ✅ Si es una entidad acuática, no requiere estar onGround
        if (entity instanceof WaterEntity) {
            return true;
        }

        // ✅ Para las demás, requerimos que esté en el suelo
        return entity.onGround();
    }

    @Override
    public void start() {
        layEggTimer = 0;
    }

    @Override
    public void tick() {
        ++layEggTimer;

        if (layEggTimer > 40) {
            BlockPos eggPos = entity.tryLayEgg(eggBlock);

            // Only assign egg if goal was provided (ProtectOwnEggGoal)
            if (eggPos != null && protectGoal != null) {
                protectGoal.assignEgg(eggPos);
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        return entity.hasEgg() && layEggTimer <= 60;
    }
}




