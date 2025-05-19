package net.darkblade.smopmod.entity.interfaces.sleep_system;

import net.minecraft.world.entity.LivingEntity;

public interface ISleepThreatEvaluator  {
    boolean shouldInterruptSleepDueTo(LivingEntity nearby);
}
