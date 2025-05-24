package net.darkblade.smopmod.entity.ai.core.flying;

import net.darkblade.smopmod.entity.FlyingEntity;
import net.darkblade.smopmod.entity.interfaces.flight.FlightState;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class RandomStrollAndFlightGoal extends WaterAvoidingRandomStrollGoal {

    private final FlyingEntity flyingMob;

    public RandomStrollAndFlightGoal(FlyingEntity mob, double speed) {
        super(mob, speed);
        this.flyingMob = mob;
    }

    @Override
    public boolean canUse() {
        boolean result = isAllowedToWander();
        if (result) {
            System.out.println("[GOAL] RandomStrollAndFlightGoal activado → Estado: " + flyingMob.getFlightState());
        }
        return result;
    }

    @Override
    public boolean canContinueToUse() {
        return isAllowedToWander();
    }

    private boolean isAllowedToWander() {
        return flyingMob.getFlightState() == FlightState.GROUND
                || flyingMob.getFlightState() == FlightState.FLY_IDLE
                || flyingMob.getFlightState() == FlightState.FLY_MOVE;
    }

    @Nullable
    @Override
    protected Vec3 getPosition() {
        if (flyingMob.getFlightState() == FlightState.FLY_IDLE || flyingMob.getFlightState() == FlightState.FLY_MOVE) {
            Vec3 look = this.mob.getViewVector(0.0F);
            Vec3 pos = HoverRandomPos.getPos(this.mob, 8, 7, look.x, look.z, ((float)Math.PI / 2F), 3, 1);
            return pos != null ? pos : AirAndWaterRandomPos.getPos(this.mob, 8, 4, -2, look.x, look.z, (float)Math.PI / 2F);
        }
        return super.getPosition(); // movimiento terrestre
    }
}


