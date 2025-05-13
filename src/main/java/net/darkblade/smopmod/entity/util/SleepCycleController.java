package net.darkblade.smopmod.entity.util;

import net.darkblade.smopmod.entity.interfaces.ISleepingEntity;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

public class SleepCycleController<T extends Animal & ISleepingEntity> {

    private final T entity;
    private final AnimationState preparingSleepState;
    private final AnimationState sleepState;
    private final AnimationState awakeingState;

    private final int preparingSleepDuration;
    private final int awakeingDuration;

    private int preparingSleepTimer = 0;
    private int awakeingTimer = 0;
    private boolean wasNight = false;

    public SleepCycleController(
            T entity,
            AnimationState preparingSleepState,
            AnimationState sleepState,
            AnimationState awakeingState,
            int preparingSleepDuration,  // ← desde entidad
            int awakeingDuration         // ← desde entidad
    ) {
        this.entity = entity;
        this.preparingSleepState = preparingSleepState;
        this.sleepState = sleepState;
        this.awakeingState = awakeingState;
        this.preparingSleepDuration = preparingSleepDuration;
        this.awakeingDuration = awakeingDuration;
    }

    public void tick(int tickCount) {
        Level level = entity.level();
        boolean isClient = level.isClientSide();

        long timeOfDay = level.getDayTime() % 24000L;
        boolean isNight = (timeOfDay >= 13000L && timeOfDay <= 23000L);
        System.out.println("[SleepCycle] Hora: " + timeOfDay + " → isNight = " + isNight);

        // ⬇️ LÓGICA DE ESTADO: SOLO EN SERVIDOR
        if (!isClient) {
            if (isNight && !wasNight && !entity.isSleeping() && !entity.isPreparingSleep()) {
                entity.setPreparingSleep(true);
                preparingSleepTimer = preparingSleepDuration;
                entity.setSleeping(false);
                entity.setAwakeing(false);
                System.out.println("[SERVER] → preparing_sleep");
            }

            if (entity.isPreparingSleep() && preparingSleepTimer > 0) {
                preparingSleepTimer--;
                if (preparingSleepTimer == 0) {
                    entity.setPreparingSleep(false);
                    entity.setSleeping(true);
                    entity.setAwakeing(false);
                    System.out.println("[SERVER] → sleep");
                }
            }

            if (!isNight && wasNight && entity.isSleeping()) {
                entity.setSleeping(false);
                entity.setPreparingSleep(false);
                entity.setAwakeing(true);
                awakeingTimer = awakeingDuration + 1;
                System.out.println("[SERVER] → awakeing");
            }

            if (awakeingTimer > 0) {
                awakeingTimer--;
                if (awakeingTimer == 0) {
                    entity.setAwakeing(false);
                    System.out.println("[SERVER] → awakeing ended");
                }
            }
        }

        wasNight = isNight;
    }

}
