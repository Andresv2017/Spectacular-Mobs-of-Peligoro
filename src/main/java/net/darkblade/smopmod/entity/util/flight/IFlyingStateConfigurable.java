package net.darkblade.smopmod.entity.util.flight;

public interface IFlyingStateConfigurable {

    /** Tiempo en ticks para la animación de despegue */
    int getStartFlightDuration();

    /** Tiempo en ticks para la animación de impulso inicial */
    int getBoostDuration();

    /** Tiempo en ticks para la animación de aterrizaje */
    int getLandingDuration();

    /** Umbral de velocidad horizontal para considerar que está en vuelo activo */
    double getFlightMoveThreshold();

    /** Define si la entidad tiene intención de volar (comportamiento de IA o estado) */
    boolean wantsToFly();
}
