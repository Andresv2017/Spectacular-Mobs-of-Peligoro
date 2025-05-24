package net.darkblade.smopmod.entity.ai.core.flying;

import net.darkblade.smopmod.entity.FlyingEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class FlyFromNowAndThenGoal extends Goal {

    protected final FlyingEntity mob;
    private int timer;
    private BlockPos landingPos = null;

    public FlyFromNowAndThenGoal(FlyingEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE)); // Indica que este goal mueve a la entidad
        this.timer = mob.getRandom().nextInt(100, 2000); // Inicial para testeo
    }

    @Override
    public boolean canUse() {
        boolean result = !mob.isTame() && !mob.isOrderedToSit();
        System.out.println("[AI] canUse() → " + result + " | Tame: " + mob.isTame() + ", Sit: " + mob.isOrderedToSit());
        return result;
    }

    @Override
    public boolean canContinueToUse() {
        return !mob.isTame() && !mob.isOrderedToSit();
    }

    @Override
    public void tick() {
        // Alterna el deseo de volar periódicamente
        if (--timer <= 0) {
            boolean newState = !mob.getGoalsRequireFlying();
            mob.setGoalsRequireFlying(newState);
            System.out.println("[AI] goalsRequireFlying → " + newState + " (tick " + mob.tickCount + ")");
            this.timer = mob.getRandom().nextInt(100, 2000); // Reinicia el temporizador
            landingPos = null; // Resetea el objetivo de aterrizaje
        }

        // Planea un aterrizaje si está a punto de aterrizar
        else if (timer < 50 && mob.getGoalsRequireFlying() && !mob.onGround()) {
            if (landingPos == null) {
                landingPos = findLandingSpot();
                if (landingPos != null) {
                    System.out.println("[AI] Planeando aterrizaje en " + landingPos);
                }
            }

            if (landingPos != null) {
                mob.getNavigation().moveTo(landingPos.getX(), landingPos.getY(), landingPos.getZ(), 1.0D);
            }
        }
    }

    protected BlockPos findLandingSpot() {
        BlockPos position = mob.blockPosition();
        Level level = mob.level();

        // Busca el primer bloque sólido hacia abajo
        while (position.getY() > level.getMinBuildHeight() && level.isEmptyBlock(position)) {
            position = position.below();
        }

        // Evita agua
        if (!level.getFluidState(position).isEmpty()) {
            return null;
        }

        return position;
    }
}

