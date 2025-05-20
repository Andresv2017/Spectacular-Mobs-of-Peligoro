package net.darkblade.smopmod.entity.ai.core.protect_egg;

import net.darkblade.smopmod.entity.BaseEntity;
import net.darkblade.smopmod.entity.ai.core.GenericLayEggGoal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Predicate;

public class EggGoalRegistry {

    /**
     * Registers both the ProtectOwnEggGoal and the GenericLayEggGoal with egg assignment.
     */
    public static <T extends BaseEntity> void registerWithOwnGoal(
            T mob,
            RegistryObject<Block> eggBlock,
            int stayNearRadius,
            int defenseRadius,
            boolean attackOnApproach,
            boolean attackOnBreak,
            ProtectEggBaseGoal.EggBreakReaction reaction,
            Predicate<LivingEntity> enemySelector,
            int basePriority
    ) {
        ProtectOwnEggGoal protectGoal = new ProtectOwnEggGoal(
                mob,
                stayNearRadius,
                defenseRadius,
                attackOnApproach,
                attackOnBreak,
                enemySelector,
                reaction
        );

        mob.goalSelector.addGoal(basePriority, protectGoal);
        mob.goalSelector.addGoal(basePriority + 1, new GenericLayEggGoal<>(mob, eggBlock.get(), protectGoal));
    }

    /**
     * Registers both the ProtectNearestEggGoal and the GenericLayEggGoal (no assignment needed).
     */
    public static <T extends BaseEntity> void registerWithNearestGoal(
            T mob,
            RegistryObject<Block> eggBlock,
            int searchRadius,
            int stayNearRadius,
            int defenseRadius,
            boolean attackOnApproach,
            boolean attackOnBreak,
            ProtectEggBaseGoal.EggBreakReaction reaction,
            Predicate<LivingEntity> enemySelector,
            int basePriority
    ) {
        mob.goalSelector.addGoal(basePriority, new ProtectNearestEggGoal(
                mob,
                searchRadius,
                stayNearRadius,
                defenseRadius,
                attackOnApproach,
                attackOnBreak,
                enemySelector,
                reaction,
                List.of(eggBlock)
        ));

        mob.goalSelector.addGoal(basePriority + 1, new GenericLayEggGoal<>(mob, eggBlock.get()));
    }
}

