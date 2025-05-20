package net.darkblade.smopmod.event;

import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.ai.core.protect_egg.ProtectEggBaseGoal;
import net.darkblade.smopmod.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = SMOP.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModForgeEvents {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();
        Block brokenBlock = level.getBlockState(pos).getBlock();

        // Check if the broken block is tagged as an egg
        if (!brokenBlock.builtInRegistryHolder().is(ModTags.Blocks.EGG_BLOCKS)) return;

        // Find mobs nearby that might react
        List<Mob> nearby = level.getEntitiesOfClass(
                Mob.class,
                new AABB(pos).inflate(10),
                mob -> true
        );

        for (Mob mob : nearby) {
            for (WrappedGoal wrapped : mob.goalSelector.getAvailableGoals()) {
                Goal goal = wrapped.getGoal();
                if (goal instanceof ProtectEggBaseGoal protectGoal) {
                    protectGoal.notifyEggBroken(pos);
                }
            }
        }
    }
}
