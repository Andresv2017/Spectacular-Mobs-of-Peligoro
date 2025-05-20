package net.darkblade.smopmod.structures;

import com.mojang.serialization.Codec;
import net.darkblade.smopmod.block.ModBlocks;
import net.darkblade.smopmod.entity.ModEntities;
import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.*;
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

        // ☀️ Verificar cielo despejado
        if (!isSurfaceExposedToSky(context, context.chunkGenerator(), blockpos)) {
            return Optional.empty();
        }

        // 📐 Verificar pendiente
        if (isSlopeTooSteep(context, context.chunkGenerator(), blockpos)) {
            return Optional.empty();
        }

        // 💧 Reubicar si hay agua cerca
        if (isNearWater(context, context.chunkGenerator(), blockpos)) {
            blockpos = findDryLandNearby(context, context.chunkGenerator(), blockpos);
        }

        // 🏘 Verificar si hay una aldea plains dentro del área de chunks
        Registry<Structure> structureRegistry = context.registryAccess().registryOrThrow(Registries.STRUCTURE);
        Holder.Reference<Structure> villageHolder = structureRegistry.getHolderOrThrow(
                ResourceKey.create(Registries.STRUCTURE, new ResourceLocation("minecraft:village_plains"))
        );
        Structure villageStructure = villageHolder.value();

        ChunkPos centerChunk = context.chunkPos();
        boolean foundVillageNearby = false;

        int searchRadius = 3; // escanea 7x7 chunks (~112x112 bloques)

        outer:
        for (int dx = -searchRadius; dx <= searchRadius; dx++) {
            for (int dz = -searchRadius; dz <= searchRadius; dz++) {
                ChunkPos scanChunk = new ChunkPos(centerChunk.x + dx, centerChunk.z + dz);
                StructureStart start = villageStructure.generate(
                        context.registryAccess(),
                        context.chunkGenerator(),
                        context.biomeSource(),
                        context.randomState(),
                        context.structureTemplateManager(),
                        context.seed(),
                        scanChunk,
                        0,
                        context.heightAccessor(),
                        context.validBiome()
                );

                if (start != null && start.isValid()) {
                    BlockPos villagePos = start.getBoundingBox().getCenter();

                    // Mover el nido cerca de la aldea y ajustar la altura al terreno real
                    BlockPos offsetPos = villagePos.offset(10, 0, 10);
                    int newY = getTerrainHeightAtPosition(context, context.chunkGenerator(), offsetPos);
                    blockpos = new BlockPos(offsetPos.getX(), newY, offsetPos.getZ());

                    System.out.println("[DEBUG] Aldea plains detectada. Reposicionando nido a " + blockpos);
                    foundVillageNearby = true;
                    break outer;
                }
            }
        }

        if (!foundVillageNearby) {
            System.out.println("[DEBUG] Cancelado: no hay aldea plains cerca de " + blockpos);
            return Optional.empty();
        }

        // ✅ Si todo es válido, permitir generación
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

        @Override
        public void postProcess(WorldGenLevel pLevel, StructureManager pStructureManager, ChunkGenerator pGenerator,
                                RandomSource pRandom, BoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
            // Primero genera la estructura normalmente
            super.postProcess(pLevel, pStructureManager, pGenerator, pRandom, pBox, pChunkPos, pPos);

            // Obtener posición base de la estructura (donde empieza el template)
            BlockPos basePos = this.templatePosition;

            // Recorrer el área horizontal de la estructura
            for (int x = 0; x < this.getBoundingBox().getXSpan(); x++) {
                for (int z = 0; z < this.getBoundingBox().getZSpan(); z++) {

                    int worldX = basePos.getX() + x;
                    int worldZ = basePos.getZ() + z;
                    int startY = basePos.getY() - 1;

                    BlockPos.MutableBlockPos filler = new BlockPos.MutableBlockPos(worldX, startY, worldZ);

                    // Bajar desde y-1 hasta encontrar un bloque sólido, rellenando con tierra
                    while (filler.getY() > pLevel.getMinBuildHeight() &&
                            pLevel.getBlockState(filler).isAir()) {
                        pLevel.setBlock(filler, Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                        filler.move(Direction.DOWN);
                    }
                }
            }
        }


    }
}