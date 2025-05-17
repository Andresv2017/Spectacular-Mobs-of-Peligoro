package net.darkblade.smopmod.entity.ai.tangoftero;

import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;
import java.util.List;

public class AssistFlockGoal extends Goal {
    private final TangofteroEntity tango;
    private final double radius;

    public AssistFlockGoal(TangofteroEntity tango, double radius) {
        this.tango = tango;
        this.radius = radius;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    @Override
    public boolean canUse() {

        if (tango.isTame() || tango.getTarget() != null) return false;

        List<TangofteroEntity> allies = tango.level().getEntitiesOfClass(
                TangofteroEntity.class,
                tango.getBoundingBox().inflate(radius),
                e -> e != tango && e.isAlive() && !e.isTame() && e.getTarget() != null && tango.canAttack(e.getTarget())
        );

        return !allies.isEmpty();
    }

    @Override
    public void start() {
        List<TangofteroEntity> allies = tango.level().getEntitiesOfClass(
                TangofteroEntity.class,
                tango.getBoundingBox().inflate(radius),
                e -> e != tango && e.isAlive() && !e.isTame() && e.getTarget() != null && tango.canAttack(e.getTarget())
        );

        for (TangofteroEntity ally : allies) {
            LivingEntity allyTarget = ally.getTarget();
            if (allyTarget != null) {
                tango.setTarget(allyTarget);
                break;
            }
        }
    }
}

