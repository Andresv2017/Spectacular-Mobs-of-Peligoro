package net.darkblade.smopmod.entity.navigation;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.*;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NewGroundPathNavigation extends GroundPathNavigation {

    public NewGroundPathNavigation(@NotNull Mob mob, Level level) {
        super(mob, level);
    }

    @Override
    protected PathFinder createPathFinder(int maxVisitedNodes) {
        this.nodeEvaluator = new WalkNodeEvaluator();
        this.nodeEvaluator.setCanPassDoors(true);
        return new NewPathFinder(this.nodeEvaluator, maxVisitedNodes);
    }

    @Override
    protected void followThePath() {
        Path path = Objects.requireNonNull(this.path);
        Vec3 entityPos = this.getTempMobPos();
        int pathLength = path.getNodeCount();

        // Detecta cambios de elevación
        for (int i = path.getNextNodeIndex(); i < path.getNodeCount(); i++) {
            if (path.getNode(i).y != Math.floor(entityPos.y)) {
                pathLength = i;
                break;
            }
        }

        Vec3 base = entityPos.add(-this.mob.getBbWidth() * 0.5F, 0.0F, -this.mob.getBbWidth() * 0.5F);
        Vec3 max = base.add(this.mob.getBbWidth(), this.mob.getBbHeight(), this.mob.getBbWidth());

        if (this.tryShortcut(path, this.mob.position(), pathLength, base, max)) {
            if (this.isAt(path, 0.5F) || this.atElevationChange(path) && this.isAt(path, this.mob.getBbWidth() * 0.5F)) {
                path.setNextNodeIndex(path.getNextNodeIndex() + 1);
            }
        }

        this.doStuckDetection(entityPos);
    }

    private boolean isAt(Path path, float threshold) {
        Vec3 target = path.getNextEntityPos(this.mob);
        return Mth.abs((float) (this.mob.getX() - target.x)) < threshold &&
                Mth.abs((float) (this.mob.getZ() - target.z)) < threshold &&
                Math.abs(this.mob.getY() - target.y) < 1.0D;
    }

    private boolean atElevationChange(@NotNull Path path) {
        int current = path.getNextNodeIndex();
        int end = Math.min(path.getNodeCount(), current + Mth.ceil(this.mob.getBbWidth() * 0.5F) + 1);
        int currentY = path.getNode(current).y;
        for (int i = current + 1; i < end; i++) {
            if (path.getNode(i).y != currentY)
                return true;
        }
        return false;
    }

    private boolean tryShortcut(Path path, @NotNull Vec3 origin, int pathLength, Vec3 base, Vec3 max) {
        for (int i = pathLength - 1; i > path.getNextNodeIndex(); i--) {
            Vec3 vec = path.getEntityPosAtNode(this.mob, i).subtract(origin);
            if (this.sweep(vec, base, max)) {
                path.setNextNodeIndex(i);
                return false;
            }
        }
        return true;
    }

    // Algoritmo de detección de colisión entre dos puntos en volumen
    private boolean sweep(@NotNull Vec3 vec, Vec3 base, Vec3 max) {
        float t = 0.0F;
        float max_t = (float) vec.length();
        if (max_t < EPSILON) return true;

        final float[] tr = new float[3];
        final int[] ldi = new int[3];
        final int[] tri = new int[3];
        final int[] step = new int[3];
        final float[] tDelta = new float[3];
        final float[] tNext = new float[3];
        final float[] normed = new float[3];

        for (int i = 0; i < 3; i++) {
            float value = element(vec, i);
            boolean dir = value >= 0.0F;
            step[i] = dir ? 1 : -1;
            float lead = element(dir ? max : base, i);
            tr[i] = element(dir ? base : max, i);
            ldi[i] = leadEdgeToInt(lead, step[i]);
            tri[i] = trailEdgeToInt(tr[i], step[i]);
            normed[i] = value / max_t;
            tDelta[i] = Mth.abs(max_t / value);
            float dist = dir ? (ldi[i] + 1 - lead) : (lead - ldi[i]);
            tNext[i] = tDelta[i] < Float.POSITIVE_INFINITY ? tDelta[i] * dist : Float.POSITIVE_INFINITY;
        }

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        do {
            int axis = (tNext[0] < tNext[1]) ? ((tNext[0] < tNext[2]) ? 0 : 2) : ((tNext[1] < tNext[2]) ? 1 : 2);
            float dt = tNext[axis] - t;
            t = tNext[axis];
            ldi[axis] += step[axis];
            tNext[axis] += tDelta[axis];

            for (int i = 0; i < 3; i++) {
                tr[i] += dt * normed[i];
                tri[i] = trailEdgeToInt(tr[i], step[i]);
            }

            int stepx = step[0], x0 = (axis == 0) ? ldi[0] : tri[0], x1 = ldi[0] + stepx;
            int stepy = step[1], y0 = (axis == 1) ? ldi[1] : tri[1], y1 = ldi[1] + stepy;
            int stepz = step[2], z0 = (axis == 2) ? ldi[2] : tri[2], z1 = ldi[2] + stepz;

            for (int x = x0; x != x1; x += stepx) {
                for (int z = z0; z != z1; z += stepz) {
                    for (int y = y0; y != y1; y += stepy) {
                        BlockState state = this.level.getBlockState(pos.set(x, y, z));
                        if (!state.isPathfindable(this.level, pos, PathComputationType.LAND)) return false;
                    }
                    BlockPathTypes below = this.nodeEvaluator.getBlockPathType(this.level, x, y0 - 1, z, this.mob);
                    if (below == BlockPathTypes.WATER || below == BlockPathTypes.LAVA || below == BlockPathTypes.OPEN) return false;
                    BlockPathTypes in = this.nodeEvaluator.getBlockPathType(this.level, x, y0, z, this.mob);
                    float malus = this.mob.getPathfindingMalus(in);
                    if (malus < 0.0F || malus >= 8.0F) return false;
                    if (in == BlockPathTypes.DAMAGE_FIRE || in == BlockPathTypes.DANGER_FIRE || in == BlockPathTypes.DAMAGE_OTHER) return false;
                }
            }
        } while (t <= max_t);

        return true;
    }

    // Métodos auxiliares
    private static int leadEdgeToInt(float coord, int step) {
        return Mth.floor(coord - step * EPSILON);
    }

    private static int trailEdgeToInt(float coord, int step) {
        return Mth.floor(coord + step * EPSILON);
    }

    private static float element(Vec3 vec, int index) {
        return switch (index) {
            case 0 -> (float) vec.x;
            case 1 -> (float) vec.y;
            case 2 -> (float) vec.z;
            default -> 0.0F;
        };
    }

    private static final float EPSILON = 1.0E-8F;
}
