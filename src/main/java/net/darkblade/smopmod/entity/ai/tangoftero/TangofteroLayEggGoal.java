package net.darkblade.smopmod.entity.ai.tangoftero;

import net.darkblade.smopmod.block.ModBlocks;
import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class TangofteroLayEggGoal extends Goal {

    private final TangofteroEntity tangoftero;
    private int layEggTimer;

    public TangofteroLayEggGoal(TangofteroEntity tangoftero) {
        this.tangoftero = tangoftero;
    }

    @Override
    public boolean canUse() {
        return tangoftero.hasEgg() && tangoftero.onGround(); // No vuela, está quieto
    }

    @Override
    public void start() {
        layEggTimer = 0;
    }

    @Override
    public void tick() {
        ++layEggTimer;

        if (layEggTimer > 40) { // ~2 segundos
            Level level = tangoftero.level();
            BlockPos eggPos = tangoftero.blockPosition();

            if (level.getBlockState(eggPos).isAir() && level.getBlockState(eggPos.below()).isSolid()) {
                BlockState egg = ModBlocks.TANGOFTERO_EGG.get().defaultBlockState();
                level.setBlock(eggPos, egg, 3);
                level.playSound(null, eggPos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 1.0F, 1.0F);
                tangoftero.setHasEgg(false);
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        return tangoftero.hasEgg() && layEggTimer <= 60;
    }
}
