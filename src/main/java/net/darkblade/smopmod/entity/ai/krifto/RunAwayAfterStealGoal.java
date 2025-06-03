package net.darkblade.smopmod.entity.ai.krifto;

import net.darkblade.smopmod.entity.custom.KriftognathusEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class RunAwayAfterStealGoal extends Goal {

    private final KriftognathusEntity mob;
    private final double speed;
    private int fleeTicks;
    private double startY;

    public RunAwayAfterStealGoal(KriftognathusEntity mob, double speed) {
        this.mob = mob;
        this.speed = speed;
    }

    @Override
    public boolean canUse() {
        boolean canUse = mob.isHoldingLoot();
        if (canUse) {
            System.out.println("[RUNAWAY_GOAL] Activado: mob tiene loot en memoria.");
        }
        return canUse;
    }

    @Override
    public void start() {
        fleeTicks = 60 + mob.getRandom().nextInt(40); // entre 3 y 5s
        startY = mob.getY();
        System.out.println("[RUNAWAY_GOAL] Iniciando huida por " + fleeTicks + " ticks.");

        if (!mob.getIsFlying()) {
            mob.setIsFlying(true);
            mob.switchNavigation();
            System.out.println("[RUNAWAY_GOAL] Cambiado a navegación aérea.");
        }

        Vec3 away = mob.getLookAngle().scale(-1).add(0, 1.2, 0);
        BlockPos target = mob.blockPosition().offset(
                (int) Math.round(away.x * 10),
                (int) Math.round(away.y * 4),
                (int) Math.round(away.z * 10)
        );

        mob.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), speed);
        System.out.println("[RUNAWAY_GOAL] Moviéndose a posición de escape: " + target);
    }

    @Override
    public void tick() {
        fleeTicks--;
        System.out.println("[RUNAWAY_GOAL] Ticks restantes de huida: " + fleeTicks);

        if (mob.getNavigation().isDone()) {

            // Si ya no tiene loot, bajar si está muy alto sobre el suelo
            if (!mob.isHoldingLoot()) {
                int distToGround = mob.distanceToGround();
                if (distToGround > 5) {
                    mob.getNavigation().moveTo(mob.getX(), mob.getY() - 1.5, mob.getZ(), 1.0);
                    System.out.println("[RUNAWAY_GOAL] Descendiendo tras soltar/comer loot. Altura sobre suelo: " + distToGround);
                }
                return;
            }

            double rx = mob.getX() + mob.getRandom().nextDouble() * 6 - 3;
            double ry = mob.getY() + mob.getRandom().nextDouble() * 3 + 1;
            ry = Math.max(Math.min(ry, startY + 8), startY - 4); // clamp Y
            double rz = mob.getZ() + mob.getRandom().nextDouble() * 6 - 3;

            mob.getNavigation().moveTo(rx, ry, rz, speed);
            System.out.println("[RUNAWAY_GOAL] Reubicación aérea aleatoria a: " + rx + ", " + ry + ", " + rz);
        }
    }

    @Override
    public boolean canContinueToUse() {
        boolean cont = fleeTicks > 0 || mob.isHoldingLoot(); // sigue si aún está volando o cargando loot
        System.out.println("[RUNAWAY_GOAL] ¿Continuar?: " + cont);
        return cont;
    }

    @Override
    public void stop() {
        mob.setAttacking(false);
        System.out.println("[RUNAWAY_GOAL] Huida finalizada. Reset de animación ataque.");
    }


}