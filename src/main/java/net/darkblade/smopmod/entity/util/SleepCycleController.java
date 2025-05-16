package net.darkblade.smopmod.entity.util;

import net.darkblade.smopmod.entity.interfaces.ISleepAwareness;
import net.darkblade.smopmod.entity.interfaces.ISleepThreatEvaluator;
import net.darkblade.smopmod.entity.interfaces.ISleepingEntity;
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
    private final AnimationState awakeingState;

    // Duration (in ticks) for each sleep transition animation
    private final int preparingSleepDuration;
    private final int awakeingDuration;

    // Internal timers for transition control
    private int preparingSleepTimer = -1;
    private int awakeingTimer = -1;
    private int ticksSinceLastInterruption = -1;
    private boolean wasNight = false;

    // Small offset to desynchronize multiple entities
    private final int entityOffset;

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
        this.entityOffset = entity.getId() % 10; // Light offset to prevent synchronized transitions
    }

    /**
     * Called every tick to handle state transitions and threat detection.
     */
    public void tick(int tickCount) {
        Level level = entity.level();
        boolean isClient = level.isClientSide();

        long timeOfDay = level.getDayTime() % 24000L;
        boolean isNight = (timeOfDay >= 13000L && timeOfDay <= 23000L);

        // Track night changes
        boolean wasNightBefore = this.wasNight;
        this.wasNight = isNight;

        // Count ticks since last interruption
        if (ticksSinceLastInterruption >= 0) {
            ticksSinceLastInterruption++;
        }

        if (!isClient) {

            // 🌙 Begin sleep preparation if night starts and entity is idle
            if (isNight && !wasNightBefore && !entity.isSleeping() && !entity.isPreparingSleep()
                    && awakeingTimer < 0 && preparingSleepTimer < 0) {
                entity.setPreparingSleep(true);
                entity.setSleeping(false);
                entity.setAwakeing(false);
                preparingSleepTimer = preparingSleepDuration + entityOffset;
                System.out.println("[SERVER] → preparing_sleep");
            }

            // ⏳ Complete transition from preparingSleep → sleep
            if (entity.isPreparingSleep() && preparingSleepTimer >= 0) {
                preparingSleepTimer--;
                if (preparingSleepTimer <= 0) {
                    preparingSleepTimer = -1;

                    if (!isNight) {
                        // Cancel transition if night ended mid-preparation
                        entity.setPreparingSleep(false);
                        System.out.println("[SERVER] → cancel preparing_sleep (switched to day)");
                    } else if (!entity.isSleeping()) {
                        entity.setPreparingSleep(false);
                        entity.setSleeping(true);
                        System.out.println("[SERVER] → sleep");
                    }
                }
            }

            // ☀️ Wake up if a threat or player is nearby while sleeping
            if ((entity.isSleeping() || entity.isPreparingSleep()) && awakeingTimer < 0) {
                List<LivingEntity> threats = entity.level().getEntitiesOfClass(LivingEntity.class,
                        entity.getBoundingBox().inflate(4),
                        other -> shouldInterruptSleep(entity, other));

                if (!threats.isEmpty()) {
                    interruptSleep("nearby threat");
                }
            }

            // 🌙 Re-enter sleep cycle after 30 seconds without threats
            if (!entity.isSleeping() && !entity.isPreparingSleep() && !entity.isAwakeing()
                    && isNight && ticksSinceLastInterruption >= 600
                    && preparingSleepTimer < 0 && awakeingTimer < 0) {

                List<LivingEntity> threats = entity.level().getEntitiesOfClass(LivingEntity.class,
                        entity.getBoundingBox().inflate(4),
                        other -> shouldInterruptSleep(entity, other));

                if (threats.isEmpty()) {
                    entity.setPreparingSleep(true);
                    preparingSleepTimer = preparingSleepDuration + entityOffset;
                    ticksSinceLastInterruption = -1;
                    System.out.println("[SERVER] → back to sleep after 30s without threats");
                }
            }

            // ⏱️ Finalize awakeing state
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

    /**
     * Forces the entity to wake up, transitioning into the awakeing state
     * and resetting interruption timers.
     *
     * @param reason Reason string for debugging
     */
    public void interruptSleep(String reason) {
        if (entity.isSleeping() || entity.isPreparingSleep()) {
            entity.setSleeping(false);
            entity.setPreparingSleep(false);
            entity.setAwakeing(true);
            awakeingTimer = awakeingDuration + entityOffset;
            ticksSinceLastInterruption = 0;
            System.out.println("[SERVER] → awakeing (interrupted by " + reason + ")");
        }
    }

    /**
     * Evaluates whether the presence of a nearby entity should interrupt the sleep of the sleeper entity.
     *
     * @param sleeperEntity The entity currently sleeping or preparing to sleep
     * @param nearbyEntity  The entity detected within proximity
     * @return true if sleep should be interrupted
     */
    private boolean shouldInterruptSleep(LivingEntity sleeperEntity, LivingEntity nearbyEntity) {
        if (nearbyEntity == sleeperEntity) return false;
        if (!nearbyEntity.isAlive()) return false;

        // 🧠 Player presence can interrupt sleep
        if (nearbyEntity instanceof Player player && !player.isSpectator()) {
            if (sleeperEntity instanceof ISleepAwareness aware) {
                return aware.shouldWakeOnPlayerProximity(); // Custom behavior per entity
            }
            return true; // Default: player always wakes up entities unless overridden
        }

        // 🔁 Custom sleep interruption logic
        if (sleeperEntity instanceof ISleepThreatEvaluator evaluator) {
            return evaluator.shouldInterruptSleepDueTo(nearbyEntity);
        }

        // 📋 Fallback to fixed interrupting types
        if (sleeperEntity instanceof ISleepingEntity sleeper) {
            return sleeper.getInterruptingEntityTypes().contains(nearbyEntity.getType());
        }

        return false;
    }

    // Note: If an entity does NOT implement ISleepAwareness,
    // it will default to being woken by nearby players.
    // This is useful for testing and allows optional override per entity.
}