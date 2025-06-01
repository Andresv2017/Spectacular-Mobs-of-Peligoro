package net.darkblade.smopmod.entity.ai.core.water;

import net.darkblade.smopmod.entity.WaterEntity;
import net.darkblade.smopmod.entity.custom.SalmonEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class WaterWanderGoal<T extends WaterEntity> extends Goal {

    protected final T entity;
    protected double x;
    protected double y;
    protected double z;
    protected boolean wantsToStand;

    public WaterWanderGoal(T entity) {
        this.entity = entity;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // 🛑 Cancelar wander si el salmón quiere excavar
        if (entity instanceof SalmonEntity salmon && salmon.wantsToDig()) {
            return false;
        }

        // ⏳ Verifica si está en modo STANDING y aún no terminó el tiempo
        if (entity.getRandom().nextInt(100) != 0 && entity.isStanding() && entity.timeStanding < entity.navigateTypeLength) {
            return false;
        }

        // 🧠 Decide si quiere quedarse quieto
        if (entity.isStanding()) {
            this.wantsToStand = false;
        } else {
            this.wantsToStand = entity.timeSwimming > 300 || entity.getRandom().nextFloat() < 0.2F;
        }

        // 🎯 Intenta encontrar una posición de destino
        Vec3 target = this.getPosition();
        if (target == null) {
            //System.out.println("[WANDER] No se encontró posición válida.");
            return false;
        }

        this.x = target.x;
        this.y = target.y;
        this.z = target.z;
        return true;
    }


    @Override
    public void start() {
        entity.setStanding(false);

        // 👇 1. Notifica al MoveControl
        entity.getMoveControl().setWantedPosition(this.x, this.y, this.z, 1.0);
        //System.out.printf("[DEBUG] setWantedPosition a (%.2f, %.2f, %.2f)%n", x, y, z);

        // 👇 2. Inicia la navegación
        boolean success = entity.getNavigation().moveTo(this.x, this.y, this.z, 1.0);

        // 👇 ✅ AQUI va la verificación #4
        /*
        System.out.printf(
                "[DEBUG] moveTo éxito: %s, isDone(): %s, isInProgress(): %s, actualPos: (%.2f, %.2f, %.2f)%n",
                success,
                entity.getNavigation().isDone(),
                entity.getNavigation().isInProgress(),
                entity.getX(), entity.getY(), entity.getZ()
        );
         */
        if (success) {
            //System.out.printf("[WANDER] Movimiento iniciado a (%.2f, %.2f, %.2f)%n", x, y, z);
        } else {
            //System.out.println("[WANDER] Falló el movimiento: path inválido.");
        }
    }

    @Override
    public boolean canContinueToUse() {
        boolean result = !entity.getNavigation().isDone() && entity.distanceToSqr(x, y, z) > 9;
        if (!result) {
            //System.out.println("[WANDER] Deteniendo uso. Path terminado o cerca.");
        }
        return result;
    }

    @Override
    public void stop() {
        BlockPos ground = entity.blockPosition();
        int down = 0;
        while (entity.level().getFluidState(ground).is(FluidTags.WATER) && down < 3 && ground.getY() > entity.level().getMinBuildHeight()) {
            ground = ground.below();
            down++;
        }

        if (this.wantsToStand && down <= 2) {
            //System.out.println("[WANDER] Entrando en modo standing.");
            entity.setStanding(true);
            entity.getNavigation().stop();
            entity.setDeltaMovement(Vec3.ZERO);
        }
    }

    @Nullable
    protected Vec3 getPosition() {
        BlockPos water = findWaterBlock();
        if (entity.isInWaterOrBubble()) {
            if (water == null) {
                //System.out.println("[WANDER] No se encontró bloque de agua.");
                return null;
            }

            while (entity.level().getFluidState(water.below()).is(FluidTags.WATER) && water.getY() > entity.level().getMinBuildHeight() + 1) {
                water = water.below();
            }

            BlockState floor = entity.level().getBlockState(water.below());
            if (wantsToStand && (floor.is(Blocks.MAGMA_BLOCK) || (!floor.getFluidState().isEmpty() && !floor.getFluidState().is(FluidTags.WATER)))) {
                //System.out.println("[WANDER] Piso inválido para standing.");
                return null;
            }

            BlockPos above = water.above(wantsToStand ? 1 : 3 + entity.getRandom().nextInt(3));
            while (!entity.level().getFluidState(above).is(FluidTags.WATER) && above.getY() > water.getY()) {
                above = above.below();
            }

            Vec3 vec3 = Vec3.atCenterOf(above);
            if (isTargetBlocked(vec3)) {
                //System.out.println("[WANDER] Camino bloqueado por colisión.");
                return null;
            }

            return vec3;
        } else {
            if (water == null) {
                //System.out.println("[WANDER] No hay agua cercana fuera del agua.");
                return null;
            }
            return Vec3.atCenterOf(water);
        }
    }

    protected BlockPos findWaterBlock() {
        BlockPos result = null;
        RandomSource random = entity.getRandom();
        int range = 20;
        for (int i = 0; i < 15; i++) {
            BlockPos pos = entity.blockPosition().offset(
                    random.nextInt(range) - range / 2,
                    random.nextInt(range) - range / 2,
                    random.nextInt(range) - range / 2
            );
            if (entity.level().getFluidState(pos).is(FluidTags.WATER) && pos.getY() > entity.level().getMinBuildHeight()) {
                result = pos;
            }
        }
        return result;
    }

    protected boolean isTargetBlocked(Vec3 target) {
        Vec3 origin = new Vec3(entity.getX(), entity.getEyeY(), entity.getZ());
        HitResult result = entity.level().clip(new ClipContext(origin, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
        return result.getType() != HitResult.Type.MISS;
    }
}


