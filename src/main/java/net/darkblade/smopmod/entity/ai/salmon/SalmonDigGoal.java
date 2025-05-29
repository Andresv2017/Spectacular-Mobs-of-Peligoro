package net.darkblade.smopmod.entity.ai.salmon;

import net.darkblade.smopmod.entity.custom.SalmonEntity;
import net.darkblade.smopmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.Path;

import java.util.*;

public class SalmonDigGoal extends Goal {

    private final SalmonEntity salmon;
    private final double speed;
    private BlockPos targetBlock = null;
    private BlockPos failedTargetBlock = null;
    private int tryTicks;
    private int movementPauseCooldown = 0;
    private int excavationTicks = -1;
    private final Map<BlockPos, Integer> retriesPerBlock = new HashMap<>();
    private static final int MAX_RETRIES = 3;


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

            // ❌ No intentar bloques que ya fallaron muchas veces
            if (retriesPerBlock.getOrDefault(pos, 0) >= MAX_RETRIES) continue;

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
            salmon.setStanding(false); // 🔒 Previene el modo STANDING mientras excava
            return true;
        }

        return false;
    }


    @Override
    public void start() {
        salmon.setStanding(false);
        tryTicks = 0;
        movementPauseCooldown = 0;

        if (targetBlock != null) {
            boolean success = salmon.getNavigation().moveTo(
                    targetBlock.getX() + 0.5,
                    targetBlock.getY() + 0.5,
                    targetBlock.getZ() + 0.5,
                    speed
            );

            if (!success) {
                // 🔁 Incrementa el contador de intentos para este bloque
                retriesPerBlock.merge(targetBlock, 1, Integer::sum);

                failedTargetBlock = targetBlock;
                salmon.setDigCommand(false);
                targetBlock = null;
            } else {
                salmon.level().broadcastEntityEvent(salmon, SalmonEntity.SNIFF_TARGET_EVENT_ID);
            }
        }
    }


    @Override
    public boolean canContinueToUse() {
        return targetBlock != null && tryTicks < 200;
    }

    @Override
    public void tick() {
        tryTicks++;

        if (targetBlock == null) return;

        // Reintenta moverse si no ha llegado
        if (!salmon.getNavigation().isInProgress() && excavationTicks == -1 && movementPauseCooldown <= 0) {
            boolean success = salmon.getNavigation().moveTo(
                    targetBlock.getX() + 0.5,
                    targetBlock.getY() + 0.5,
                    targetBlock.getZ() + 0.5,
                    speed
            );
            if (!success) {
                failedTargetBlock = targetBlock;
                targetBlock = null;
                return;
            }
            movementPauseCooldown = 20;
            return;
        }

        if (movementPauseCooldown > 0) {
            movementPauseCooldown--;
            return;
        }

        if (excavationTicks >= 0) {
            excavationTicks++;
            if (excavationTicks == 1) {
                salmon.level().broadcastEntityEvent(salmon, SalmonEntity.DIG_EVENT_ID);
            } else if (excavationTicks >= 35) {
                Block block = salmon.level().getBlockState(targetBlock).getBlock();
                ItemStack drop = getDropForBlock(block);

                if (!drop.isEmpty()) {
                    salmon.level().addFreshEntity(new ItemEntity(
                            salmon.level(),
                            targetBlock.getX() + 0.5,
                            targetBlock.getY() + 0.5,
                            targetBlock.getZ() + 0.5,
                            drop
                    ));
                }

                salmon.level().destroyBlock(targetBlock, false);

                salmon.setDigCommand(false);
                targetBlock = null;
                excavationTicks = -1;
            }
            return;
        }

        if (targetBlock != null && salmon.blockPosition().closerThan(targetBlock, 2.0) && excavationTicks == -1) {
            excavationTicks = 0;
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
