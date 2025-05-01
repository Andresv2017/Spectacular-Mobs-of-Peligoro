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

    public HellHippoLeaveWaterShakeGoal(Hell_HippoEntity hippo) {
        this.hippo = hippo;
        this.level = hippo.level();
    }

    @Override
    public boolean canUse() {
        return hippo.isInWater() && !hippo.isVehicle() && hippo.onGround();
    }

    @Override
    public void start() {
        this.hippo.getNavigation().stop();
        this.hippo.setDeltaMovement(Vec3.ZERO);
        ((Hell_HippoEntity) this.hippo).shakeAnimationState.start(this.hippo.tickCount);
        this.shakeTicks = 70;

        // ✅ Muestra el mensaje si hay jinete
        if (this.hippo.getControllingPassenger() instanceof ServerPlayer rider) {
            rider.displayClientMessage(Component.literal("§bHell Hippo is shaking off water!"), true);
        }
    }


    @Override
    public void stop() {
        waterTicks = 0;
        shakeTicks = 0;
        hippo.shakeAnimationState.stop();
    }

    @Override
    public boolean canContinueToUse() {
        return true;
    }

    @Override
    public void tick() {
        if (hippo.isInWater()) {
            waterTicks++;
        } else if (waterTicks >= 100 && shakeTicks < 70) { // 5s en agua + 3.5s quieto
            if (shakeTicks == 0) {
                hippo.shakeAnimationState.start(hippo.tickCount);
                hippo.getNavigation().stop();
                hippo.setDeltaMovement(Vec3.ZERO);
                if (hippo.getControllingPassenger() instanceof ServerPlayer rider) {
                    rider.displayClientMessage(Component.literal("§bHell Hippo is shaking off water!"), true);
                }
            }
            shakeTicks++;
            hippo.setDeltaMovement(Vec3.ZERO);
        }
    }
}
