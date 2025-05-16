package net.darkblade.smopmod.entity.util;

import net.darkblade.smopmod.entity.interfaces.ISleepingEntity;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class SleepCycleController<T extends Animal & ISleepingEntity> {

    private final T entity;
    private final AnimationState preparingSleepState;
    private final AnimationState sleepState;
    private final AnimationState awakeingState;

    private final int preparingSleepDuration;
    private final int awakeingDuration;

    private int preparingSleepTimer = -1;
    private int awakeingTimer = -1;
    private boolean wasNight = false;

    private final int entityOffset; // Para desfase

    public SleepCycleController(
            T entity,
            AnimationState preparingSleepState,
            AnimationState sleepState,
            AnimationState awakeingState,
            int preparingSleepDuration,
            int awakeingDuration
    ) {
        this.entity = entity;
        this.preparingSleepState = preparingSleepState;
        this.sleepState = sleepState;
        this.awakeingState = awakeingState;
        this.preparingSleepDuration = preparingSleepDuration;
        this.awakeingDuration = awakeingDuration;
        this.entityOffset = entity.getId() % 10; // desfase leve entre entidades
    }

    private long lastLoggedTime = -1;

    public void tick(int tickCount) {
        Level level = entity.level();
        boolean isClient = level.isClientSide();

        long timeOfDay = level.getDayTime() % 24000L;
        boolean isNight = (timeOfDay >= 13000L && timeOfDay <= 23000L);

        // Solo para depuración
        if (timeOfDay != lastLoggedTime) {
            lastLoggedTime = timeOfDay;
            System.out.println("[SleepCycle] Hora: " + timeOfDay + " → isNight = " + isNight);
        }

        boolean wasNightBefore = this.wasNight;
        this.wasNight = isNight;

        // 🕒 Contar ticks desde la última interrupción
        if (ticksSinceLastInterruption >= 0) {
            ticksSinceLastInterruption++;
        }

        if (!isClient) {

            // 🌙 Iniciar transición a sueño
            if (isNight && !wasNightBefore && !entity.isSleeping() && !entity.isPreparingSleep() && awakeingTimer < 0 && preparingSleepTimer < 0) {
                entity.setPreparingSleep(true);
                entity.setSleeping(false);
                entity.setAwakeing(false);
                preparingSleepTimer = preparingSleepDuration + entityOffset;
                System.out.println("[SERVER] → preparing_sleep");
            }

            if (entity.isPreparingSleep() && preparingSleepTimer >= 0) {
                preparingSleepTimer--;
                if (preparingSleepTimer <= 0) {
                    preparingSleepTimer = -1;

                    // Cancelar si ya amaneció
                    if (!isNight) {
                        entity.setPreparingSleep(false);
                        System.out.println("[SERVER] → cancel preparing_sleep (cambió a día)");
                    } else if (!entity.isSleeping()) {
                        entity.setPreparingSleep(false);
                        entity.setSleeping(true);
                        System.out.println("[SERVER] → sleep");
                    }
                }
            }

            // ☀️ Iniciar transición a despertar (solo si timer está inactivo)
            if ((entity.isSleeping() || entity.isPreparingSleep()) && awakeingTimer < 0) {
                List<LivingEntity> nearbyThreats = entity.level().getEntitiesOfClass(LivingEntity.class,
                        entity.getBoundingBox().inflate(4),
                        other -> isPredatorOrEnemy(entity, other));

                if (!nearbyThreats.isEmpty()) {
                    interruptSleep("entidad cercana");
                }
            }

            // 🌙 Volver a dormir tras 30s si no hay amenazas cerca
            if (!entity.isSleeping() && !entity.isPreparingSleep() && !entity.isAwakeing()
                    && isNight && ticksSinceLastInterruption >= 600
                    && preparingSleepTimer < 0 && awakeingTimer < 0) {

                List<LivingEntity> nearby = entity.level().getEntitiesOfClass(LivingEntity.class,
                        entity.getBoundingBox().inflate(4),
                        other -> isPredatorOrEnemy(entity, other));

                if (nearby.isEmpty()) {
                    entity.setPreparingSleep(true);
                    preparingSleepTimer = preparingSleepDuration + entityOffset;
                    ticksSinceLastInterruption = -1; // 🔁 reiniciar contador
                    System.out.println("[SERVER] → vuelve a dormir tras 30s sin amenazas");
                }
            }

            // ⏱️ Finalizar despertar
            if (awakeingTimer >= 0) {
                awakeingTimer--;
                if (awakeingTimer <= 0) {
                    if (entity.isAwakeing()) {
                        entity.setAwakeing(false);
                    }
                    awakeingTimer = -1;
                    System.out.println("[SERVER] → awakeing ended");
                }
            }
        }
    }

    private int ticksSinceLastInterruption = -1;

    public void interruptSleep(String reason) {
        if (entity.isSleeping() || entity.isPreparingSleep()) {
            entity.setSleeping(false);
            entity.setPreparingSleep(false);
            entity.setAwakeing(true);
            awakeingTimer = awakeingDuration + entityOffset;

            ticksSinceLastInterruption = 0; // ← 🟡 reinicia contador
            System.out.println("[SERVER] → awakeing (interrumpido por " + reason + ")");
        }
    }

    private boolean isPredatorOrEnemy(LivingEntity sleeper, LivingEntity other) {
        if (other == sleeper) return false;
        if (!other.isAlive()) return false;

        if (sleeper instanceof ISleepingEntity se) {
            return se.getPredatorTypes().contains(other.getType());
        }

        return false;
    }

}