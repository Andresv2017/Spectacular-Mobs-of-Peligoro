package net.darkblade.smopmod.entity.util.flight;

import net.darkblade.smopmod.entity.FlyingEntity;
import net.darkblade.smopmod.entity.interfaces.flight.FlightState;

public class FlightStateController {

    private final FlyingEntity entity;
    private int stateStartTick = -1;
    private FlightState lastState = FlightState.GROUND;

    public FlightStateController(FlyingEntity entity) {
        this.entity = entity;
    }

    public void update() {
        if (entity.level().isClientSide) return;

        FlightState current = entity.getFlightState();
        boolean onGround = entity.onGround();
        double speed = entity.getDeltaMovement().horizontalDistanceSqr();
        double vertical = entity.getDeltaMovement().y;

        // Protección de primeros ticks
        if (entity.tickCount <= 5) {
            System.out.println("[DEBUG] Protegiendo estado → Tick: " + entity.tickCount + ", Estado actual: " + current);
            return;
        }

        // Detectar cambio de estado
        if (current != lastState) {
            stateStartTick = entity.tickCount;
            lastState = current;
        }

        // Evita avanzar durante la duración de estados temporales
        if (current == FlightState.START_FLIGHT && entity.tickCount - stateStartTick < entity.getStartFlightDuration()) return;
        if (current == FlightState.BOOST && entity.tickCount - stateStartTick < entity.getBoostDuration()) return;
        if (current == FlightState.LANDING && entity.tickCount - stateStartTick < entity.getLandingDuration()) return;

        // ✅ FINALIZA aterrizaje si ya está en el suelo
        if (current == FlightState.LANDING
                && onGround
                && entity.tickCount - stateStartTick >= entity.getLandingDuration()) {
            System.out.println("[FLIGHT] Aterrizaje completado. Estado → GROUND (tick " + entity.tickCount + ")");
            entity.setFlightState(FlightState.GROUND);
            entity.switchNavigation(false);
            return;
        }

        // Aterrizaje solo si no quiere volar y aún está en el aire
        if (!entity.wantsToFly() && !onGround) {
            if (current != FlightState.LANDING) {
                System.out.println("[FLIGHT] Iniciando descenso (tick " + entity.tickCount + ")");
                entity.setFlightState(FlightState.LANDING);
                entity.switchNavigation(false);
            }
            return;
        }

        // Despegue desde el suelo
        if ((current == FlightState.GROUND || current == FlightState.LANDING || current == FlightState.FLY_IDLE)
                && entity.wantsToFly()
                && entity.tickCount > 5
                && !entity.isFlying()) {

            System.out.println("[FLIGHT] Iniciando vuelo desde estado " + current + " (tick " + entity.tickCount + ")");
            entity.setFlightState(FlightState.START_FLIGHT);
            entity.switchNavigation(true);
            return;
        }


        // Impulso desde flotación
        if (current == FlightState.FLY_IDLE && speed > entity.getFlightMoveThreshold()) {
            entity.setFlightState(FlightState.BOOST);
            return;
        }

        // Vuelo activo
        if (speed > entity.getFlightMoveThreshold()) {
            if (current != FlightState.FLY_MOVE) {
                entity.setFlightState(FlightState.FLY_MOVE);
            }
            return;
        }

        // Flotando
        if (current != FlightState.FLY_IDLE) {
            entity.setFlightState(FlightState.FLY_IDLE);
        }
    }

    public void resetStateTracking() {
        this.lastState = entity.getFlightState();
        this.stateStartTick = entity.tickCount;
    }


}