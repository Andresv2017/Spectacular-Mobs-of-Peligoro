package net.darkblade.smopmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;


import java.util.function.Supplier;

public class RoeEggsBlock extends Block {
    private final Supplier<? extends EntityType<? extends LivingEntity>> entityTypeSupplier;
    private final int minSpawn;
    private final int maxSpawn;
    private final int hatchDelayMin;
    private final int hatchDelayMax;
    private final SoundEvent hatchSound;

    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 1.5, 16.0);

    public RoeEggsBlock(Supplier<? extends EntityType<? extends LivingEntity>> entityTypeSupplier,
                        int minSpawn, int maxSpawn,
                        int hatchDelayMin, int hatchDelayMax,
                        SoundEvent hatchSound,
                        Properties properties) {
        super(properties);
        this.entityTypeSupplier = entityTypeSupplier;
        this.minSpawn = minSpawn;
        this.maxSpawn = maxSpawn;
        this.hatchDelayMin = hatchDelayMin;
        this.hatchDelayMax = hatchDelayMax;
        this.hatchSound = hatchSound;
    }

    // ✅ Permitir colocación dentro de agua fuente
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return fluid.is(FluidTags.WATER) && fluid.getAmount() == 8 ? this.defaultBlockState() : null;
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return context.getLevel().getFluidState(context.getClickedPos()).is(FluidTags.WATER);
    }

    // ✅ Permitir que el agua "fluya a través" del bloque
    @Override
    public FluidState getFluidState(BlockState state) {
        return Fluids.WATER.getSource(false);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState,
                                  LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (!state.isAir()) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return !this.canSurvive(state, level, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, dir, neighborState, level, pos, neighborPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        FluidState fluid = level.getFluidState(pos);
        return fluid.getType() == Fluids.WATER && fluid.isSource();
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide()) {
            int delay = getHatchDelay(level.getRandom());
            System.out.println("[DEBUG] Huevo colocado en " + pos + " con hatchDelay de " + delay + " ticks.");
            level.scheduleTick(pos, this, delay);
        }
    }

    private int getHatchDelay(RandomSource random) {
        return random.nextInt(hatchDelayMin, hatchDelayMax + 1);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!this.canSurvive(state, level, pos)) {
            destroyBlock(level, pos);
        } else {
            System.out.println("[DEBUG] ¡Huevo en " + pos + " ha eclosionado en el tick " + level.getGameTime() + "!");
            hatch(level, pos, random);
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity.getType() == EntityType.FALLING_BLOCK) {
            destroyBlock(level, pos);
        }
    }

    private void hatch(ServerLevel level, BlockPos pos, RandomSource random) {
        destroyBlock(level, pos);
        level.playSound(null, pos, hatchSound, SoundSource.BLOCKS, 1.0F, 1.0F);
        spawnEntities(level, pos, random);

        level.sendParticles(ParticleTypes.HEART,
                pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.5,
                6, 0.25, 0.1, 0.25, 0.0);
    }

    private void destroyBlock(Level level, BlockPos pos) {
        level.destroyBlock(pos, false);
    }

    private void spawnEntities(ServerLevel level, BlockPos pos, RandomSource random) {
        int count = random.nextInt(minSpawn, maxSpawn + 1);
        EntityType<? extends LivingEntity> type = entityTypeSupplier.get();

        for (int i = 0; i < count; ++i) {
            LivingEntity entity = type.create(level);
            if (entity != null) {
                double x = pos.getX() + getRandomOffset(random);
                double z = pos.getZ() + getRandomOffset(random);
                float yaw = random.nextFloat() * 360.0F;
                entity.moveTo(x, pos.getY() - 0.5, z, yaw, 0.0F);

                if (entity instanceof Mob mob) {
                    mob.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), MobSpawnType.SPAWN_EGG, null, null);
                    mob.setPersistenceRequired();
                }

                if (entity instanceof AgeableMob ageable) {
                    ageable.setAge(-24000);
                }

                level.addFreshEntity(entity);

                System.out.println("[DEBUG] Nació una entidad: " +
                        entity.getEncodeId() + " en " + pos + " (tick " + level.getGameTime() + ")");
            }
        }
    }

    private double getRandomOffset(RandomSource random) {
        return Mth.clamp(random.nextDouble(), 0.2, 0.8);
    }
}



