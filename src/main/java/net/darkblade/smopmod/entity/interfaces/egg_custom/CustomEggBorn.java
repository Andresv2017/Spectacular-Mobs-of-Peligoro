package net.darkblade.smopmod.entity.interfaces.egg_custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public interface CustomEggBorn {
    void onEggBorn(ServerLevel level, BlockPos pos);
}
