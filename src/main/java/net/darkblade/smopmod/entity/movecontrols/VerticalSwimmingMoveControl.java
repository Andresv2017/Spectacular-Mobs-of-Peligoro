package net.darkblade.smopmod.entity.movecontrols;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

public class VerticalSwimmingMoveControl extends MoveControl {

    private final Mob mob;
    private float secondSpeedModifier;
    private float maxRotChange;

    public VerticalSwimmingMoveControl(Mob mob, float secondSpeedModifier, float maxRotChange) {
        super(mob);
        this.mob = mob;
        this.secondSpeedModifier = secondSpeedModifier;
        this.maxRotChange = maxRotChange;
    }

    public void tick() {
        // 🛠️ Forzar desactivar la gravedad para permitir movimiento vertical libre
        this.mob.setNoGravity(true);

        //System.out.printf("[MOVE] Tick ejecutado. Operation: %s, Navigation isDone: %s%n",
        //        this.operation, this.mob.getNavigation().isDone());

        if (this.operation == Operation.MOVE_TO && !this.mob.getNavigation().isDone()) {
            Vec3 ed = this.mob.getNavigation().getTargetPos().getCenter();

            // Depuración visual
            //((ServerLevel) mob.level()).sendParticles(ParticleTypes.HEART, ed.x, ed.y, ed.z, 0, 0, 0, 0, 1);
            //((ServerLevel) mob.level()).sendParticles(ParticleTypes.SNEEZE, wantedX, wantedY, wantedZ, 0, 0, 0, 0, 1);

            double d0 = this.wantedX - this.mob.getX();
            double d1 = this.wantedY - this.mob.getY();
            double d2 = this.wantedZ - this.mob.getZ();

            if (!Double.isFinite(d0) || !Double.isFinite(d1) || !Double.isFinite(d2)) {
                this.mob.setSpeed(0.0F);
                return;
            }

            double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            if (d3 < 1.0E-6) {
                this.mob.setSpeed(0.0F);
                return;
            }

            double d4 = Math.sqrt(d0 * d0 + d2 * d2); // Para cálculo de rotación horizontal
            float f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED) * secondSpeedModifier);
            float rotBy = this.maxRotChange;

            if (d4 < this.mob.getBbWidth() + 1.4F) {
                f1 *= 0.7F;
                if (d4 < 0.3F) {
                    rotBy = 0;
                } else {
                    rotBy = Math.max(40, this.maxRotChange);
                }
            }

            // 🎯 Movimiento en todas las direcciones, normalizado
            Vec3 movement = new Vec3(d0 / d3 * f1, d1 / d3 * f1, d2 / d3 * f1);
            this.mob.setDeltaMovement(movement);
            //System.out.printf("[MOVE] Aplicando deltaMove (%.3f, %.3f, %.3f)%n", movement.x, movement.y, movement.z);

            // 🔄 Rotación horizontal
            float f = (float) (Mth.atan2(d2, d0) * 57.2957763671875D) - 90.0F;
            float currentYRot = this.mob.getYRot();
            float newYRot = this.rotlerp(currentYRot, f, rotBy);
            this.mob.setYRot(newYRot);
            this.mob.yBodyRot = newYRot;

            float deltaRot = Mth.degreesDifferenceAbs(currentYRot, newYRot);
            //System.out.printf("[ROT] Deseado: %.2f°, Antes: %.2f°, Después: %.2f°, Aplicado: %.2f°, Máx permitido: %.2f°%n",
            //        f, currentYRot, newYRot, deltaRot, rotBy);

            this.mob.setSpeed(f1);
        } else {
            this.mob.setSpeed(0.0F);
        }
    }
}
