package net.darkblade.smopmod.entity.ai;

import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

public class HellHippoDefendOwnerGoal extends TargetGoal {
    private final Hell_HippoEntity hellHippo;
    private LivingEntity lastAttacker;
    private int lastAttackTime;

    public HellHippoDefendOwnerGoal(Hell_HippoEntity hellHippo) {
        super(hellHippo, false);
        this.hellHippo = hellHippo;
    }

    @Override
    public boolean canUse() {
        if (hellHippo.isSleeping()) return false; // 🔒 No defender dormido
        if (!hellHippo.isTrusting() || hellHippo.trustingPlayerUUID == null) {
            return false;
        }
        Player owner = hellHippo.level().getPlayerByUUID(hellHippo.trustingPlayerUUID);
        if (owner == null) {
            return false;
        }
        this.lastAttacker = owner.getLastHurtByMob();
        this.lastAttackTime = owner.getLastHurtByMobTimestamp();
        return this.lastAttacker != null
                && this.lastAttackTime != this.hellHippo.getLastHurtByMobTimestamp()
                && this.canAttack(this.lastAttacker, TargetingConditions.DEFAULT);

    }

    @Override
    public void start() {
        this.hellHippo.setTarget(this.lastAttacker);
        super.start();
    }
}