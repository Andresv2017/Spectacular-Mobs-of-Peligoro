package net.darkblade.smopmod.entity.ai;

import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HellHippoLeaveWaterShakeGoal extends Goal {
    private final Hell_HippoEntity hippo;
    private final Level level;
    private int waterTicks;
    private int shakeTicks;
    private boolean shouldShake;

    public HellHippoLeaveWaterShakeGoal(Hell_HippoEntity hippo) {
        this.hippo = hippo;
        this.level = hippo.level();
    }

    @Override
    public boolean canUse() {
        // Inicia si ha salido del agua Y había estado en agua más de 5s
        return !hippo.isInWater() && shouldShake;
    }

    @Override
    public void start() {
        this.hippo.getNavigation().stop();
        this.hippo.setDeltaMovement(Vec3.ZERO);
        this.shakeTicks = 70;
        hippo.shakeAnimationState.start(hippo.tickCount);

        if (hippo.getControllingPassenger() instanceof ServerPlayer rider) {
            rider.displayClientMessage(Component.literal("§bHell Hippo is shaking off water!"), true);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return shakeTicks > 0;
    }

    @Override
    public void stop() {
        waterTicks = 0;
        shakeTicks = 0;
        shouldShake = false;
        hippo.shakeAnimationState.stop();
    }

    @Override
    public void tick() {
        // Si está en el agua, cuenta ticks
        if (hippo.isInWater()) {
            waterTicks++;
            if (waterTicks >= 100) {
                shouldShake = true;
            }
        }

        // Si salimos y toca agitarse
        if (shouldShake && !hippo.isInWater() && shakeTicks > 0) {
            shakeTicks--;
            hippo.setDeltaMovement(Vec3.ZERO);
        }
    }
}
