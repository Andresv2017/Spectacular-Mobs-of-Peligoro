package net.darkblade.smopmod.entity.ai.krifto;

import net.darkblade.smopmod.entity.custom.KriftognathusEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class StealItemGoal extends Goal {

    private final KriftognathusEntity thief;
    private final double radius;
    private final int requiredTicks;
    private Player targetPlayer;
    private int timeNearTarget;
    private boolean shouldSteal;

    public StealItemGoal(KriftognathusEntity entity, double radius, int seconds) {
        this.thief = entity;
        this.radius = radius;
        this.requiredTicks = seconds * 20;
    }

    @Override
    public boolean canUse() {
        if (thief.isBaby() || thief.isTame()) return false;

        List<Player> players = thief.level().getEntitiesOfClass(Player.class,
                thief.getBoundingBox().inflate(radius),
                p -> !p.isCreative() && !p.isSpectator() && p.isAlive());

        if (!players.isEmpty()) {
            targetPlayer = players.get(0);
            System.out.println("[STEAL_GOAL] Player detected: " + targetPlayer.getName().getString());
            return true;
        }

        return false;
    }


    @Override
    public void start() {
        timeNearTarget = 0;
        shouldSteal = false;
        System.out.println("[STEAL_GOAL] Started watching player...");
    }

    @Override
    public void tick() {
        if (targetPlayer == null || !targetPlayer.isAlive()) {
            System.out.println("[STEAL_GOAL] Target invalid. Stopping.");
            return;
        }

        double distance = thief.distanceTo(targetPlayer);

        if (distance > radius) {
            System.out.println("[STEAL_GOAL] Player left radius. Resetting.");
            stop();
            return;
        }

        if (!shouldSteal) {
            timeNearTarget++;
            System.out.println("[STEAL_GOAL] Watching player... " + timeNearTarget + "/" + requiredTicks);

            // Durante esta fase, solo ronda
            wanderNearPlayer();

            if (timeNearTarget >= requiredTicks) {
                System.out.println("[STEAL_GOAL] Time complete. Preparing to steal...");
                shouldSteal = true;
            }

        } else {
            // Fase de acercamiento y robo
            if (distance > 1.5) {
                System.out.println("[STEAL_GOAL] Closing in to steal...");
                thief.getNavigation().moveTo(targetPlayer, 1.3);
            } else {
                System.out.println("[STEAL_GOAL] Close enough. Stealing item.");
                thief.getNavigation().stop();
                stealItem();
                stop(); // Goal finalizado tras robar
            }
        }
    }

    private void wanderNearPlayer() {
        if (thief.getNavigation().isDone() && thief.getRandom().nextInt(20) == 0) {
            double offsetX = (thief.getRandom().nextDouble() - 0.5) * 4;
            double offsetZ = (thief.getRandom().nextDouble() - 0.5) * 4;
            BlockPos pos = targetPlayer.blockPosition().offset((int) offsetX, 0, (int) offsetZ);
            thief.getNavigation().moveTo(pos.getX(), pos.getY(), pos.getZ(), 1.0);
            System.out.println("[STEAL_GOAL] Wandering near target.");
        }
    }

    private void stealItem() {
        if (targetPlayer == null || targetPlayer.getInventory().isEmpty()) {
            System.out.println("[STEAL_GOAL] Inventory empty or invalid.");
            return;
        }

        for (int i = 0; i < targetPlayer.getInventory().getContainerSize(); i++) {
            ItemStack stack = targetPlayer.getInventory().getItem(i);
            if (!stack.isEmpty()) {
                ItemStack stolen = stack.split(1);

                thief.setHeldLoot(stolen);
                thief.setHoldingLoot(true);

                // 🎬 Animación de swoop
                thief.playSwoopAnimation();
                System.out.println("[STEAL_GOAL] Activando animación: swoop");

                // Mensaje al jugador
                if (!targetPlayer.level().isClientSide() && targetPlayer instanceof ServerPlayer serverPlayer) {
                    serverPlayer.displayClientMessage(
                            Component.literal("You have been robbed!").withStyle(ChatFormatting.RED),
                            true
                    );
                }

                System.out.println("[STEAL_GOAL] Stolen item: " + stolen.getDisplayName().getString());
                thief.setAttacking(true);
                return;
            }
        }

        System.out.println("[STEAL_GOAL] No valid item found to steal.");
    }

    @Override
    public boolean canContinueToUse() {
        boolean active = !thief.isTame()
                && targetPlayer != null
                && targetPlayer.isAlive()
                && thief.distanceTo(targetPlayer) <= radius;

        System.out.println("[STEAL_GOAL] Can continue? " + active);
        return active;
    }


    @Override
    public void stop() {
        System.out.println("[STEAL_GOAL] Stopping. Resetting state.");
        targetPlayer = null;
        timeNearTarget = 0;
        shouldSteal = false;
        thief.setAttacking(false);
        thief.getNavigation().stop();
    }
}
