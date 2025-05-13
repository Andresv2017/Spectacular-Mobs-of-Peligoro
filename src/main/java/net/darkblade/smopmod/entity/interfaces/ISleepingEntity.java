package net.darkblade.smopmod.entity.interfaces;

public interface ISleepingEntity {

    boolean isSleeping();
    void setSleeping(boolean sleeping);

    boolean isPreparingSleep();
    void setPreparingSleep(boolean preparing);

    boolean isAwakeing();               // ← nuevo
    void setAwakeing(boolean awakeing); // ← nuevo
}
