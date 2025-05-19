package net.darkblade.smopmod.block;

import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.darkblade.smopmod.entity.interfaces.egg_custom.CustomEggBorn;
import net.darkblade.smopmod.entity.interfaces.variants.RandomVariantCapable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class SmallEggBlock extends Block {

    public static final IntegerProperty HATCH = IntegerProperty.create("hatch", 0, 2);
    public static final IntegerProperty EGGS = IntegerProperty.create("eggs", 1, 4);
    private static final VoxelShape ONE_EGG_AABB = Block.box(3.0, 0.0, 3.0, 12.0, 7.0, 12.0);
    private static final VoxelShape MULTIPLE_EGGS_AABB = Block.box(1.0, 0.0, 1.0, 15.0, 7.0, 15.0);

    private final RegistryObject<? extends EntityType<? extends AgeableMob>> mobType;
    private final int incubationTimeTicks;

    public SmallEggBlock(RegistryObject<? extends EntityType<? extends AgeableMob>> mobType, int incubationTimeTicks) {
        super(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG));
        this.mobType = mobType;
        this.incubationTimeTicks = incubationTimeTicks;
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, 0).setValue(EGGS, 1));
    }

    // --- Comportamiento de destrucción por pisadas o caídas ---
    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully()) {
            this.destroyEgg(level, state, pos, entity, 100);
        }
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!(entity instanceof Zombie)) {
            this.destroyEgg(level, state, pos, entity, 3);
        }
        super.fallOn(level, state, pos, entity, fallDistance);
    }

    private void destroyEgg(Level level, BlockState state, BlockPos pos, Entity entity, int chance) {
        if (this.canDestroyEgg(level, entity) && !level.isClientSide && level.random.nextInt(chance) == 0 && state.is(this)) {
            this.decreaseEggs(level, pos, state);
        }
    }

    private boolean canDestroyEgg(Level level, Entity entity) {
        if (!(entity instanceof LivingEntity)) return false;
        if (entity.getType() == mobType.get() || entity instanceof Bat) return false;
        return entity instanceof Player || ForgeEventFactory.getMobGriefingEvent(level, entity);
    }

    private void decreaseEggs(Level level, BlockPos pos, BlockState state) {
        int eggs = state.getValue(EGGS);
        level.playSound(null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);

        if (eggs <= 1) {
            level.destroyBlock(pos, false);
        } else {
            level.setBlock(pos, state.setValue(EGGS, eggs - 1), 2);
            level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(state));
            level.levelEvent(2001, pos, Block.getId(state));
        }
    }

    // --- Incubación y eclosión ---
    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            level.scheduleTick(pos, this, incubationTimeTicks);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int hatch = state.getValue(HATCH);

        if (hatch < 2) {
            level.setBlock(pos, state.setValue(HATCH, hatch + 1), 2);
            level.scheduleTick(pos, this, incubationTimeTicks);
        } else {
            int eggCount = state.getValue(EGGS);
            level.removeBlock(pos, false);

            for (int i = 0; i < eggCount; i++) {
                AgeableMob baby = mobType.get().create(level);
                if (baby != null) {
                    baby.setAge(-24000);
                    baby.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.0F, 0.0F);

                    // ⬇️ Aquí es donde lo pones
                    if (baby instanceof RandomVariantCapable vc) {
                        vc.setRandomVariant(level.getRandom());
                    }

                    // Lógica personalizada de nacimiento
                    if (baby instanceof CustomEggBorn born) {
                        born.onEggBorn(level, pos);
                    }

                    level.addFreshEntity(baby);
                }
            }
        }
    }


    // --- Estados y forma ---
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HATCH, EGGS);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    public boolean canSurvive(BlockState state, LevelAccessor level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return this.canSurvive(state, level, pos) ? state : Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext ctx) {
        return state.getValue(EGGS) > 1 ? MULTIPLE_EGGS_AABB : ONE_EGG_AABB;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState blockstate = ctx.getLevel().getBlockState(ctx.getClickedPos());
        return blockstate.is(this) ? blockstate.setValue(EGGS, Math.min(4, blockstate.getValue(EGGS) + 1)) : this.defaultBlockState();
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext ctx) {
        return !ctx.isSecondaryUseActive() && ctx.getItemInHand().is(this.asItem()) && state.getValue(EGGS) < 4 || super.canBeReplaced(state, ctx);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity be, ItemStack stack) {
        super.playerDestroy(level, player, pos, state, be, stack);
        this.decreaseEggs(level, pos, state);
    }
}
