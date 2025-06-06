package net.darkblade.smopmod.entity.ai.core.flying;

import net.darkblade.smopmod.entity.FlyingEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class FollowOwnerFlyingGoal extends Goal {

    private final FlyingEntity mob;
    private final double speedModifier;
    private final Level level;
    private final float startDistance;
    private final float stopDistance;
    private PathNavigation navigation;
    private LivingEntity owner;
    private int timeToRecalcPath;

    public FollowOwnerFlyingGoal(FlyingEntity mob, double speed, float startDist, float stopDist) {
        this.mob = mob;
        this.speedModifier = speed;
        this.level = mob.level();
        this.startDistance = startDist;
        this.stopDistance = stopDist;
        this.navigation = mob.getNavigation();
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity owner = mob.getOwner();
        if (owner == null || mob.isOrderedToSit() || mob.isPassenger() || mob.isLeashed() || mob.isWandering()) {
            //System.out.println("[FOLLOW_GOAL] ❌ No se puede usar: sin dueño o está sentado/pasajero/en correa.");
            return false;
        }
        if (mob.distanceToSqr(owner) < (startDistance * startDistance)) {
            //System.out.println("[FOLLOW_GOAL] 🟡 Muy cerca del dueño. No necesita seguirlo.");
            return false;
        }
        this.owner = owner;
        //System.out.println("[FOLLOW_GOAL] ✅ Activado. Siguiendo al dueño: " + owner.getName().getString());
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        boolean result = !navigation.isDone()
                && !mob.isOrderedToSit()
                && !mob.isPassenger()
                && mob.distanceToSqr(owner) > (stopDistance * stopDistance);
        //System.out.println("[FOLLOW_GOAL] ▶️ Continuar: " + result);
        return result;
    }

    @Override
    public void start() {
        timeToRecalcPath = 0;
        mob.setFollowingOwner(true);
        //System.out.println("[FOLLOW_GOAL] 🚀 Comenzando seguimiento.");
    }

    @Override
    public void stop() {
        owner = null;
        navigation.stop();
        mob.setFollowingOwner(false);
        //System.out.println("[FOLLOW_GOAL] ⛔ Detenido.");
    }

    @Override
    public void tick() {
        mob.getLookControl().setLookAt(owner, 10.0F, mob.getMaxHeadXRot());

        if (--timeToRecalcPath <= 0) {
            timeToRecalcPath = adjustedTickDelay(10);

            double distance = mob.distanceTo(owner);
            //System.out.println("[FOLLOW_GOAL] 📏 Distancia al dueño: " + distance);

            if (distance >= 12.0D) {
                //System.out.println("[FOLLOW_GOAL] 📦 Teletransportando: demasiado lejos.");
                teleportToOwner();
                return;
            }

            if (!mob.getIsFlying() && distance > 8.0F) {
                mob.setIsFlying(true);
                mob.switchNavigation();
                this.navigation = mob.getNavigation();
                ///System.out.println("[FOLLOW_GOAL] 🛫 Activando vuelo y cambiando navegación.");
            }

            // 💡 Basado en distancia real, no en targetPos
            if (!navigation.isInProgress() || distance > 2.5D) {
                navigation.moveTo(owner, speedModifier);
                //System.out.println("[FOLLOW_GOAL] 🧭 (FORZADO) Moviéndose hacia el dueño.");
            } else {
                //System.out.println("[FOLLOW_GOAL] ⏳ Ruta actual no modificada.");
            }
        }
    }


    private void teleportToOwner() {
        BlockPos ownerPos = owner.blockPosition();
        for (int i = 0; i < 10; ++i) {
            int dx = randomIntInclusive(-3, 3);
            int dy = randomIntInclusive(-1, 1);
            int dz = randomIntInclusive(-3, 3);
            if (tryTeleportTo(ownerPos.offset(dx, dy, dz))) return;
        }
    }

    private boolean tryTeleportTo(BlockPos pos) {
        if (Math.abs(pos.getX() - owner.getX()) < 2.0D && Math.abs(pos.getZ() - owner.getZ()) < 2.0D) return false;
        if (!canTeleportTo(pos)) return false;
        mob.moveTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, mob.getYRot(), mob.getXRot());
        navigation.stop();
        //System.out.println("[FOLLOW_GOAL] ✨ Teletransportado exitosamente.");
        return true;
    }

    private boolean canTeleportTo(BlockPos pos) {
        BlockPathTypes type = WalkNodeEvaluator.getBlockPathTypeStatic(level, pos.mutable());
        BlockState below = level.getBlockState(pos.below());
        boolean isSafe = type == BlockPathTypes.WALKABLE || below.isAir();
        return isSafe && level.noCollision(mob, mob.getBoundingBox().move(Vec3.atCenterOf(pos).subtract(mob.position())));
    }

    private int randomIntInclusive(int min, int max) {
        return mob.getRandom().nextInt(max - min + 1) + min;
    }
}