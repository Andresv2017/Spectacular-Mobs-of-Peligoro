package net.darkblade.smopmod.block;

import net.darkblade.smopmod.entity.ModEntities;
import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.darkblade.smopmod.item.ModItems;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TangofteroEggBlock extends Block {

    public static final IntegerProperty HATCH = IntegerProperty.create("hatch", 0, 2);

    public static final IntegerProperty EGGS = IntegerProperty.create("eggs", 1, 4);

    public TangofteroEggBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG));
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, 0).setValue(EGGS, 1));

    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            level.scheduleTick(pos, this, 100); // 5 segundos
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int hatch = state.getValue(HATCH);
        if (hatch < 2) {
            level.setBlock(pos, state.setValue(HATCH, hatch + 1), 2);
            level.scheduleTick(pos, this, 100); // sigue incubando
        } else {
            int eggCount = state.getValue(EGGS);

            // Elimina el bloque
            level.removeBlock(pos, false);

            for (int i = 0; i < eggCount; i++) {
                TangofteroEntity baby = ModEntities.TANGOFTERO.get().create(level);
                if (baby != null) {
                    baby.setAge(-24000); // bebé
                    // Leve offset para que no spawneen todos en el mismo punto
                    double offsetX = 0.3 * (random.nextDouble() - 0.5);
                    double offsetZ = 0.3 * (random.nextDouble() - 0.5);
                    baby.moveTo(pos.getX() + 0.5 + offsetX, pos.getY(), pos.getZ() + 0.5 + offsetZ, random.nextFloat() * 360.0F, 0.0F);
                    level.addFreshEntity(baby);
                }
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

        // Asegúrate que el jugador sostiene un huevo de Tangoftero
        if (stack.getItem() == ModItems.TANGOFTERO_EGG_ITEM.get()) {
            int currentEggs = state.getValue(EGGS);

            if (currentEggs < 4) {
                // Aumenta el valor de EGGS
                level.setBlock(pos, state.setValue(EGGS, currentEggs + 1), 3);

                // Consume el ítem si no está en creativo
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }

                // Feedback opcional
                level.playSound(null, pos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 1.0F, 1.0F);

                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                // Ya tiene 4 huevos → feedback opcional
                return InteractionResult.FAIL;
            }
        }

        return InteractionResult.PASS;
    }

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
    public BlockState updateShape(BlockState state, net.minecraft.core.Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return this.canSurvive(state, level, pos) ? state : Blocks.AIR.defaultBlockState();
    }


    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // Hitbox ampliada: asegura que se rendericen todos los huevos, incluidos los externos
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    }

}

