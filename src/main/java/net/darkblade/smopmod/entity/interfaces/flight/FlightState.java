package net.darkblade.smopmod.entity.interfaces.flight;

public enum FlightState {
    /** La entidad está completamente en el suelo */
    GROUND,

    /** Transición de despegue: se activa cuando empieza a elevarse desde el suelo */
    START_FLIGHT,

    /** Está en el aire sin movimiento horizontal significativo */
    FLY_IDLE,

    /** Transición desde flotación a vuelo activo (impulso) */
    BOOST,

    /** Vuelo activo con movimiento horizontal */
    FLY_MOVE,

    /** Transición de aterrizaje: se activa al volver al suelo */
    LANDING
}

