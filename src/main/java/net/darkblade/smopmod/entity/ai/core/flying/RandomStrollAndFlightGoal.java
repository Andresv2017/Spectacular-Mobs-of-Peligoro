package net.darkblade.smopmod.entity.ai.core.flying;

import net.darkblade.smopmod.entity.custom.KriftognathusEntity;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.darkblade.smopmod.entity.FlyingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class RandomStrollAndFlightGoal extends WaterAvoidingRandomStrollGoal {

    private final FlyingEntity flyingMob;

    public RandomStrollAndFlightGoal(FlyingEntity p_25734_, double p_25735_) {
        super(p_25734_, p_25735_);
        this.flyingMob = p_25734_;
    }

    @Nullable
    @Override
    protected Vec3 getPosition() {
        if (isOnPlayersHead()) return null; // No genera movimiento si está en la cabeza

        if (!flyingMob.getIsFlying())
            return super.getPosition();

        Vec3 vec3 = this.mob.getViewVector(0.0F);
        Vec3 vec31 = HoverRandomPos.getPos(this.mob, 8, 7, vec3.x, vec3.z, ((float)Math.PI / 2F), 3, 1);
        return vec31 != null ? vec31 : AirAndWaterRandomPos.getPos(this.mob, 8, 4, -2, vec3.x, vec3.z, (float)Math.PI / 2F);
    }

    private boolean isOnPlayersHead() {
        return (flyingMob instanceof KriftognathusEntity kriftognathus) && kriftognathus.isOnPlayersHead();
    }
}
