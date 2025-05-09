package net.darkblade.smopmod.entity.ai.hell_hippo;

import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class HellHippoTemptGoal extends Goal {
    private final Hell_HippoEntity hippo;
    private final double speed;
    private final Ingredient temptItems;
    private Player targetPlayer;
    private final Level level;

    public HellHippoTemptGoal(Hell_HippoEntity hippo, double speed, Ingredient temptItems) {
        this.hippo = hippo;
        this.speed = speed;
        this.temptItems = temptItems;
        this.level = hippo.level();
    }

    @Override
    public boolean canUse() {
        this.targetPlayer = this.level.getNearestPlayer(this.hippo, 10.0D);
        return targetPlayer != null && this.temptItems.test(targetPlayer.getMainHandItem());
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse();
    }

    @Override
    public void tick() {
        if (this.targetPlayer != null) {
            this.hippo.getLookControl().setLookAt(targetPlayer, 30.0F, 30.0F);
            this.hippo.getNavigation().moveTo(targetPlayer, this.speed);

            // Opcional: reproducir sonidos o animaciones aquí
        }
    }

    @Override
    public void stop() {
        this.targetPlayer = null;
        this.hippo.getNavigation().stop();
    }
}

