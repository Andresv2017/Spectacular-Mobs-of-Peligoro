package net.darkblade.smopmod.structures;

import com.mojang.serialization.Codec;
import net.darkblade.smopmod.block.ModBlocks;
import net.darkblade.smopmod.entity.ModEntities;
import net.darkblade.smopmod.entity.custom.KriftognathusEntity;
import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Optional;

public class TangofteroNestStructure extends Structure {
    public static final Codec<TangofteroNestStructure> CODEC = simpleCodec(TangofteroNestStructure::new);
    private static final ResourceLocation TEMPLATE = new ResourceLocation("smop", "tangoftero_nest");

    public TangofteroNestStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        Rotation rotation = Rotation.getRandom(context.random());
        BlockPos blockpos = this.getLowestYIn5by5BoxOffset7Blocks(context, rotation);

        int terrainHeight = getTerrainHeightAtPosition(context, context.chunkGenerator(), blockpos);
        blockpos = new BlockPos(blockpos.getX(), terrainHeight, blockpos.getZ());

        if (!isSurfaceExposedToSky(context, context.chunkGenerator(), blockpos)) {
            return Optional.empty();
        }

        if (isSlopeTooSteep(context, context.chunkGenerator(), blockpos)) {
            return Optional.empty();
        }

        if (isNearWater(context, context.chunkGenerator(), blockpos)) {
            blockpos = findDryLandNearby(context, context.chunkGenerator(), blockpos);
        }

        BlockPos finalPos = blockpos;
        return Optional.of(new Structure.GenerationStub(finalPos, builder ->
                builder.addPiece(new Piece(context.structureTemplateManager(), TEMPLATE, finalPos))));
    }

    public StructureType<?> type() {
        return StructureRegister.TANGOFTERO_NEST.get();
    }

    private boolean isSurfaceExposedToSky(Structure.GenerationContext context, ChunkGenerator chunkGenerator, BlockPos pos) {
        int surfaceY = chunkGenerator.getBaseHeight(pos.getX(), pos.getZ(), Heightmap.Types.WORLD_SURFACE, context.heightAccessor(), context.randomState());
        return pos.getY() >= surfaceY;
    }

    private int getTerrainHeightAtPosition(Structure.GenerationContext context, ChunkGenerator chunkGenerator, BlockPos pos) {
        return chunkGenerator.getBaseHeight(pos.getX(), pos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
    }

    private boolean isSlopeTooSteep(Structure.GenerationContext context, ChunkGenerator chunkGenerator, BlockPos pos) {
        int maxHeightDifference = 5;
        int baseHeight = getTerrainHeightAtPosition(context, chunkGenerator, pos);

        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                int currentHeight = getTerrainHeightAtPosition(context, chunkGenerator, pos.offset(x, 0, z));
                if (Math.abs(currentHeight - baseHeight) > maxHeightDifference) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNearWater(Structure.GenerationContext context, ChunkGenerator chunkGenerator, BlockPos pos) {
        int radius = 5;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos checkPos = pos.offset(x, 0, z);
                if (chunkGenerator.getBaseColumn(checkPos.getX(), checkPos.getZ(), context.heightAccessor(), context.randomState()).getBlock(checkPos.getY()).getBlock() == Blocks.WATER) {
                    return true;
                }
            }
        }
        return false;
    }

    private BlockPos findDryLandNearby(Structure.GenerationContext context, ChunkGenerator chunkGenerator, BlockPos pos) {
        int searchRadius = 10;
        for (int x = -searchRadius; x <= searchRadius; x++) {
            for (int z = -searchRadius; z <= searchRadius; z++) {
                BlockPos checkPos = pos.offset(x, 0, z);
                if (!isNearWater(context, chunkGenerator, checkPos)) {
                    return checkPos;
                }
            }
        }
        return pos;
    }

    public static class Piece extends TemplateStructurePiece {
        public Piece(StructureTemplateManager manager, ResourceLocation location, BlockPos pos) {
            super(StructureRegister.PIECE.get(), 0, manager, location, location.toString(), makeSettings(), pos);
        }

        public Piece(StructureTemplateManager manager, CompoundTag tag) {
            super(StructureRegister.PIECE.get(), tag, manager, r -> makeSettings());
        }

        public Piece(StructurePieceSerializationContext ctx, CompoundTag tag) {
            this(ctx.structureTemplateManager(), tag);
        }

        protected void addAdditionalSaveData(StructurePieceSerializationContext ctx, CompoundTag tag) {
            super.addAdditionalSaveData(ctx, tag);
        }

        private static StructurePlaceSettings makeSettings() {
            return new StructurePlaceSettings()
                    .setRotation(Rotation.NONE)
                    .setMirror(Mirror.NONE)
                    .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        }

        @Override
        protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
            if ("tango_egg".equals(name)) {
                int eggCount = 2 + random.nextInt(2); // genera 2 o 3 (inclusive)

                BlockState state = ModBlocks.TANGOFTERO_EGG.get()
                        .defaultBlockState()
                        .setValue(BlockStateProperties.EGGS, eggCount);

                level.setBlock(pos, state, 2);
                System.out.println("[STRUCTURE] Tangoftero Egg (" + eggCount + ") generado en " + pos);
            }
            if ("tango_spawn".equals(name)) {
                TangofteroEntity tangoftero = new TangofteroEntity(ModEntities.TANGOFTERO.get(), level.getLevel());

                if (tangoftero != null) {
                    tangoftero.setPersistenceRequired(); // evita que desaparezca con distancia
                    tangoftero.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, random.nextFloat() * 360F, 0.0F);
                    tangoftero.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), MobSpawnType.STRUCTURE, null, null);
                    level.addFreshEntityWithPassengers(tangoftero);

                    System.out.println("[SPAWN] Tangoftero generado en " + pos);
                }
            }
        }

    }
}