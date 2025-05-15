package net.darkblade.smopmod.entity.api;

public interface ISleepingEntity {

    boolean isSleeping();
    void setSleeping(boolean sleeping);

    boolean isPreparingSleep();
    void setPreparingSleep(boolean preparing);

    boolean isAwakeing();               // ← nuevo
    void setAwakeing(boolean awakeing); // ← nuevo
}
