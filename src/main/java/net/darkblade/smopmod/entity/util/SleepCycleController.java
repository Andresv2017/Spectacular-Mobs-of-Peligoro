package net.darkblade.smopmod.entity.util;

import net.darkblade.smopmod.entity.interfaces.ISleepingEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class SleepCycleController<T extends Mob & ISleepingEntity> {

    private final T entity;

    public int sleepPreparingTicks = 0;
    public int awakeningTicks = 0;

    public SleepCycleController(T entity) {
        this.entity = entity;
    }

    public void tick() {
        boolean isNight = entity.level().getDayTime() % 24000L >= 13000L;
        boolean isDay = !isNight;

        if (isNight && !entity.isSleeping() && !entity.isPreparingSleep()) {
            entity.setPreparingSleep(true);
            sleepPreparingTicks = 100;
            Player p = entity.level().getNearestPlayer(entity, 10);
            if (p != null) {
                p.displayClientMessage(Component.literal("§b[" + entity.getDisplayName().getString() + "] Preparing to sleep..."), true);
            }
        }

        if (entity.isPreparingSleep()) {
            sleepPreparingTicks--;
            entity.setDeltaMovement(Vec3.ZERO);
            if (sleepPreparingTicks <= 0) {
                entity.setPreparingSleep(false);
                entity.setSleeping(true);
                if (entity.level().isClientSide) {
                    entity.getSleepAnimation().start(entity.tickCount);
                }
                Player p = entity.level().getNearestPlayer(entity, 10);
                if (p != null) {
                    p.displayClientMessage(Component.literal("§9[" + entity.getDisplayName().getString() + "] is now sleeping."), true);
                }
            }
        }

        if (entity.isSleeping() && isDay) {
            entity.setSleeping(false);
            entity.getSleepAnimation().stop();
            awakeningTicks = 30;
            entity.setAwakening(true);
            Player p = entity.level().getNearestPlayer(entity, 10);
            if (p != null) {
                p.displayClientMessage(Component.literal("§e[" + entity.getDisplayName().getString() + "] is waking up..."), true);
            }
        }

        if (awakeningTicks > 0) {
            awakeningTicks--;
            if (awakeningTicks == 0) {
                entity.setAwakening(false);
                Player p = entity.level().getNearestPlayer(entity, 10);
                if (p != null) {
                    p.displayClientMessage(Component.literal("§a[" + entity.getDisplayName().getString() + "] is now awake."), true);
                }
            }
        }
    }
}