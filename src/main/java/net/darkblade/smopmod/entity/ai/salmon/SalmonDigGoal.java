package net.darkblade.smopmod.entity.ai.salmon;

import net.darkblade.smopmod.entity.custom.SalmonEntity;
import net.darkblade.smopmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.Path;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class SalmonDigGoal extends Goal {

    private final SalmonEntity salmon;
    private final double speed;
    private BlockPos targetBlock = null;
    private BlockPos failedTargetBlock = null;
    private int tryTicks;
    private int digTargetAnimCooldown = 0;
    private int movementPauseCooldown = 0; // ⏸ nuevo: pausa entre tramos

    public SalmonDigGoal(SalmonEntity salmon, double speed) {
        this.salmon = salmon;
        this.speed = speed;
    }

    @Override
    public boolean canUse() {
        if (!salmon.wantsToDig()) return false;
        if (targetBlock != null) return true;

        BlockPos origin = salmon.blockPosition();
        Level level = salmon.level();
        int range = 15;

        List<BlockPos> candidates = new ArrayList<>();

        for (BlockPos pos : BlockPos.betweenClosed(origin.offset(-range, -1, -range), origin.offset(range, 1, range))) {
            if (!pos.closerThan(origin, range)) continue;
            if (pos.equals(failedTargetBlock)) continue;

            Block block = level.getBlockState(pos).getBlock();
            if ((block == Blocks.SAND || block == Blocks.GRAVEL || block == Blocks.MUD || block == Blocks.DIRT)
                    && isTouchingWater(level, pos)
                    && !level.getBlockState(pos.above()).isSolidRender(level, pos.above())) {

                Path path = salmon.getNavigation().createPath(pos, 0);
                if (path == null || path.getNodeCount() == 0) continue;

                candidates.add(pos.immutable());
            }
        }

        if (!candidates.isEmpty()) {
            candidates.sort(Comparator.comparingDouble(pos -> pos.distSqr(origin)));
            int limit = Math.min(5, candidates.size());
            BlockPos chosen = candidates.get(salmon.getRandom().nextInt(limit));
            targetBlock = chosen;
            return true;
        }

        return false;
    }

    @Override
    public void start() {
        tryTicks = 0;
        movementPauseCooldown = 0;

        if (targetBlock != null) {
            boolean success = salmon.getNavigation().moveTo(
                    targetBlock.getX() + 0.5,
                    targetBlock.getY() + 0.5,
                    targetBlock.getZ() + 0.5,
                    speed
            );
            System.out.println("[AQUAGOAL] moveTo iniciado → " + success);
            if (!success) {
                System.out.println("[AQUAGOAL] Navegación fallida. Cancelando objetivo.");
                failedTargetBlock = targetBlock;
                salmon.setDigCommand(false);
                targetBlock = null;
            } else {
                // 🎬 Reproducir animación una vez
                salmon.level().broadcastEntityEvent(salmon, SalmonEntity.DIG_TARGET_EVENT_ID);
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        return targetBlock != null
                && !salmon.getNavigation().isDone()
                && tryTicks < 200;
    }

    @Override
    public void tick() {
        tryTicks++;

        // ⏳ Movimiento por tramos
        if (movementPauseCooldown > 0) {
            movementPauseCooldown--;
            return;
        }

        if (tryTicks % 40 == 0) { // cada 2 segundos intenta moverse
            boolean success = salmon.getNavigation().moveTo(
                    targetBlock.getX() + 0.5,
                    targetBlock.getY() + 0.5,
                    targetBlock.getZ() + 0.5,
                    speed
            );
            movementPauseCooldown = 20; // pausa de 1 segundo
        }

        // 🎯 Excavación al llegar
        if (targetBlock != null && salmon.blockPosition().closerThan(targetBlock, 2.0)) {
            Block block = salmon.level().getBlockState(targetBlock).getBlock();
            ItemStack drop = getDropForBlock(block);

            if (!drop.isEmpty()) salmon.spawnAtLocation(drop);
            salmon.level().destroyBlock(targetBlock, false);

            salmon.setDigCommand(false);
            targetBlock = null;
        }
    }

    private ItemStack getDropForBlock(Block block) {
        if (block == Blocks.SAND) return LootPool.pickRandomItem(ModItems.SAND_DROPS);
        if (block == Blocks.GRAVEL) return LootPool.pickRandomItem(ModItems.GRAVEL_DROPS);
        if (block == Blocks.MUD) return LootPool.pickRandomItem(ModItems.MUD_DROPS);
        if (block == Blocks.DIRT) return LootPool.pickRandomItem(ModItems.DIRT_DROPS);
        return ItemStack.EMPTY;
    }

    private boolean isTouchingWater(Level level, BlockPos pos) {
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos adjacent = pos.relative(dir);
            if (adjacent.getY() > pos.getY()) continue;
            FluidState fluid = level.getFluidState(adjacent);
            if (!fluid.isEmpty() && fluid.isSource() && fluid.getType() == Fluids.WATER) return true;
        }
        return false;
    }

    public static class LootPool {
        private static final Random random = new Random();

        public static ItemStack pickRandomItem(List<Item> items) {
            if (items.isEmpty()) return ItemStack.EMPTY;
            return new ItemStack(items.get(random.nextInt(items.size())));
        }
    }
}
