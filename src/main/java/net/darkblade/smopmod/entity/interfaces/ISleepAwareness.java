package net.darkblade.smopmod.entity.interfaces;


import net.minecraft.world.entity.LivingEntity;

public interface ISleepAwareness {
    /** Permite personalizar si el jugador despierta esta entidad */
    /** Solo para testear, Eliminar en el MOD */
    boolean shouldWakeOnPlayerProximity();
}

