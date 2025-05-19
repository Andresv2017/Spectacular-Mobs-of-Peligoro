package net.darkblade.smopmod.entity.interfaces;

import net.minecraft.world.entity.LivingEntity;

public interface IHasLeader {
    LivingEntity getGroupLeader();
    void setGroupLeader(LivingEntity leader);
}
