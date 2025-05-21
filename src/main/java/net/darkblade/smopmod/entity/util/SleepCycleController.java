package net.darkblade.smopmod.entity.util;

import net.darkblade.smopmod.entity.interfaces.sleep_system.ISleepAwareness;
import net.darkblade.smopmod.entity.interfaces.sleep_system.ISleepThreatEvaluator;
import net.darkblade.smopmod.entity.interfaces.sleep_system.ISleepingEntity;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * Controls the sleep cycle behavior of an entity, including state transitions between
 * awake, preparing to sleep, sleeping, and awakening.
 *
 * Supports interruption by threats or players, delayed re-sleeping after threats are gone,
 * and customizable logic through optional interfaces:
 *  - ISleepAwareness: to configure whether a player should wake the entity
 *  - ISleepThreatEvaluator: to define custom interrupt logic
 *  - ISleepingEntity: required base interface for sleep state management
 *
 * If an entity does not implement ISleepAwareness, the default behavior is to wake when a player is nearby.
 * This allows entities to be testable or reactive without needing custom logic.
 */
public class SleepCycleController<T extends Animal & ISleepingEntity> {

    private final T entity;
    private final AnimationState preparingSleepState;
    private final AnimationState sleepState;
    private final AnimationState awakeningState;

    private final int preparingSleepDuration;
    private final int awakeningDuration;

    private int preparingSleepTimer = -1;
    private int awakeningTimer = -1;
    private boolean wasNight = false;

    private static final int SLEEP_DELAY_TICKS = 100;
    private final int entityOffset;

    private int ticksSinceLastInterruption = -1;
    private int ticksSinceNoTarget = -1;

    // Debug helpers
    private int preparingSleepStartTick = -1;
    private int awakeningStartTick = -1;

    public SleepCycleController(
            T entity,
            AnimationState preparingSleepState,
            AnimationState sleepState,
            AnimationState awakeningState,
            int preparingSleepDuration,
            int awakeningDuration
    ) {
        this.entity = entity;
        this.preparingSleepState = preparingSleepState;
        this.sleepState = sleepState;
        this.awakeningState = awakeningState;
        this.preparingSleepDuration = preparingSleepDuration;
        this.awakeningDuration = awakeningDuration;
        this.entityOffset = entity.getId() % 10;
    }

    public void tick(int tickCount) {
        Level level = entity.level();
        boolean isClient = level.isClientSide();

        long timeOfDay = level.getDayTime() % 24000L;
        boolean isNight = (timeOfDay >= 13000L && timeOfDay <= 23000L);

        boolean wasNightBefore = this.wasNight;
        this.wasNight = isNight;

        if (ticksSinceLastInterruption >= 0) {
            ticksSinceLastInterruption++;
        }

        if (!isClient) {
            if (entity.getTarget() == null) {
                if (ticksSinceNoTarget >= 0) {
                    ticksSinceNoTarget++;
                } else {
                    ticksSinceNoTarget = 0;
                }
            } else {
                ticksSinceNoTarget = -1;
            }

            if (isNight && !entity.isSleeping() && !entity.isPreparingSleep()
                    && awakeningTimer < 0 && preparingSleepTimer < 0 && entity.getTarget() == null
                    && ticksSinceNoTarget >= SLEEP_DELAY_TICKS) {

                entity.setPreparingSleep(true);
                entity.setSleeping(false);
                entity.setAwakeing(false);
                preparingSleepTimer = preparingSleepDuration + entityOffset;
                preparingSleepStartTick = tickCount;
                ticksSinceNoTarget = -1;

                System.out.println("[SERVER] → preparing_sleep STARTED at tick " + tickCount + ", duration = " + preparingSleepTimer);
            }

            if (entity.isPreparingSleep() && preparingSleepTimer >= 0) {
                preparingSleepTimer--;
                System.out.println("[DEBUG] preparingSleepTimer: " + preparingSleepTimer + " / target: " + (preparingSleepDuration + entityOffset));
                if (preparingSleepTimer <= 0) {
                    preparingSleepTimer = -1;

                    if (!isNight) {
                        entity.setPreparingSleep(false);
                        System.out.println("[SERVER] → cancel preparing_sleep (switched to day)");
                    } else if (!entity.isSleeping()) {
                        entity.setPreparingSleep(false);
                        entity.setSleeping(true);
                        System.out.println("[SERVER] → sleep STARTED at tick " + tickCount + ", lasted " + (tickCount - preparingSleepStartTick) + " ticks");
                    }
                }
            }

            if ((entity.isSleeping() || entity.isPreparingSleep()) && awakeningTimer < 0) {
                List<LivingEntity> threats = entity.level().getEntitiesOfClass(LivingEntity.class,
                        entity.getBoundingBox().inflate(4),
                        other -> shouldInterruptSleep(entity, other));

                if (!threats.isEmpty()) {
                    interruptSleep("nearby threat", tickCount);
                }
            }

            if (!isNight && wasNightBefore && entity.isSleeping() && awakeningTimer < 0) {
                interruptSleep("dawn", tickCount);
            }

            if (!entity.isSleeping() && !entity.isPreparingSleep() && !entity.isAwakeing()
                    && isNight && ticksSinceLastInterruption >= SLEEP_DELAY_TICKS
                    && ticksSinceNoTarget >= SLEEP_DELAY_TICKS && preparingSleepTimer < 0 && awakeningTimer < 0) {

                List<LivingEntity> threats = entity.level().getEntitiesOfClass(LivingEntity.class,
                        entity.getBoundingBox().inflate(4),
                        other -> shouldInterruptSleep(entity, other));

                if (threats.isEmpty()) {
                    entity.setPreparingSleep(true);
                    preparingSleepTimer = preparingSleepDuration + entityOffset;
                    preparingSleepStartTick = tickCount;
                    ticksSinceLastInterruption = -1;
                    ticksSinceNoTarget = -1;

                    System.out.println("[SERVER] → back to sleep STARTED at tick " + tickCount + ", duration = " + preparingSleepTimer);
                }
            }

            if (awakeningTimer >= 0) {
                awakeningTimer--;
                System.out.println("[DEBUG] awakeningTimer: " + awakeningTimer + " / target: " + (awakeningDuration + entityOffset));
                if (awakeningTimer <= 0) {
                    if (entity.isAwakeing()) {
                        entity.setAwakeing(false);
                        System.out.println("[SERVER] → awakeing ENDED at tick " + tickCount + ", lasted " + (tickCount - awakeningStartTick) + " ticks");
                    }
                    awakeningTimer = -1;
                }
            }
        }
    }

    public void interruptSleep(String reason, int tickCount) {
        if (entity.isSleeping() || entity.isPreparingSleep()) {
            entity.setSleeping(false);
            entity.setPreparingSleep(false);
            entity.setAwakeing(true);
            awakeningTimer = awakeningDuration + entityOffset;
            awakeningStartTick = tickCount;

            ticksSinceLastInterruption = 0;
            ticksSinceNoTarget = 0;

            System.out.println("[SERVER] → awakeing STARTED at tick " + tickCount + " (reason: " + reason + "), duration = " + awakeningTimer);
        }
    }

    private boolean shouldInterruptSleep(LivingEntity sleeperEntity, LivingEntity nearbyEntity) {
        if (nearbyEntity == sleeperEntity) return false;
        if (!nearbyEntity.isAlive()) return false;

        if (nearbyEntity instanceof Player player && !player.isSpectator()) {
            if (sleeperEntity instanceof ISleepAwareness aware) {
                return aware.shouldWakeOnPlayerProximity();
            }
            return true;
        }

        if (sleeperEntity instanceof ISleepThreatEvaluator evaluator) {
            return evaluator.shouldInterruptSleepDueTo(nearbyEntity);
        }

        if (sleeperEntity instanceof ISleepingEntity sleeper) {
            return sleeper.getInterruptingEntityTypes().contains(nearbyEntity.getType());
        }

        return false;
    }
}

    // Note: If an entity does NOT implement ISleepAwareness,
    // it will default to being woken by nearby players.
    // This is useful for testing and allows optional override per entity.
