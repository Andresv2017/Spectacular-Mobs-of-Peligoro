package net.darkblade.smopmod.entity.interfaces;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public interface ISleepingEntity {

    boolean isSleeping();
    void setSleeping(boolean val);

    boolean isPreparingSleep();
    void setPreparingSleep(boolean val);

    boolean isAwakening();
    void setAwakening(boolean val);

    AnimationState getSleepAnimation();

    void setDeltaMovement(Vec3 movement);

    int tickCount();

    Level level();

    Component getDisplayName();
}

