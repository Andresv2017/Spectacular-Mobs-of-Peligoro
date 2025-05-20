package net.darkblade.smopmod.entity.ai.core.protect_egg;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Predicate;

public class ProtectNearestEggGoal extends ProtectEggBaseGoal {

    protected final List<RegistryObject<Block>> eggBlocks;
    protected final int searchRadius;

    /**
     * This goal scans for nearby egg blocks and protects the first one it finds.
     * It does not require external assignment like ProtectOwnEggGoal.
     */
    public ProtectNearestEggGoal(
            Animal mob,
            int searchRadius,
            int stayNearEggRadius,
            int defenseRadius,
            boolean attackOnApproach,
            boolean attackOnBreak,
            Predicate<LivingEntity> enemySelector,
            EggBreakReaction eggBreakReaction,
            List<RegistryObject<Block>> eggBlocks) {

        super(mob, stayNearEggRadius, defenseRadius, attackOnApproach, attackOnBreak, enemySelector, eggBreakReaction);
        this.eggBlocks = eggBlocks;
        this.searchRadius = searchRadius;
    }

    /**
     * Searches within the specified radius for a block that matches any of the registered eggBlocks.
     * Returns the first valid egg position found.
     */
    @Override
    protected BlockPos findTargetEgg() {
        if (!(mob.level() instanceof ServerLevel serverLevel)) return null;

        BlockPos mobPos = mob.blockPosition();
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();

        for (int x = -searchRadius; x <= searchRadius; x++) {
            for (int y = -3; y <= 3; y++) { // vertical search limited for performance
                for (int z = -searchRadius; z <= searchRadius; z++) {
                    cursor.set(mobPos.getX() + x, mobPos.getY() + y, mobPos.getZ() + z);

                    Block block = serverLevel.getBlockState(cursor).getBlock();

                    for (RegistryObject<Block> eggBlock : eggBlocks) {
                        if (block == eggBlock.get()) {
                            return cursor.immutable(); // found a matching egg block
                        }
                    }
                }
            }
        }

        return null; // no egg found in search radius
    }
}

