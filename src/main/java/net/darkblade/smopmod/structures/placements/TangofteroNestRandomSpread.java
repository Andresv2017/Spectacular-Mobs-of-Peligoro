package net.darkblade.smopmod.structures.placements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Vec3i;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

import java.util.Optional;

public class TangofteroNestRandomSpread extends RandomSpreadStructurePlacement {
    public static final Codec<TangofteroNestRandomSpread> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Vec3i.offsetCodec(16).optionalFieldOf("locate_offset", Vec3i.ZERO).forGetter(p -> p.locateOffset()),
                    FrequencyReductionMethod.CODEC.optionalFieldOf("frequency_reduction_method", FrequencyReductionMethod.DEFAULT).forGetter(p -> p.frequencyReductionMethod()),
                    Codec.floatRange(0.0F, 1.0F).optionalFieldOf("frequency", 1.0F).forGetter(p -> p.frequency()),
                    ExtraCodecs.NON_NEGATIVE_INT.fieldOf("salt").forGetter(p -> p.salt()),
                    ExclusionZone.CODEC.optionalFieldOf("exclusion_zone").forGetter(p -> p.exclusionZone()),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("spacing").forGetter(RandomSpreadStructurePlacement::spacing),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("separation").forGetter(RandomSpreadStructurePlacement::separation),
                    RandomSpreadType.CODEC.optionalFieldOf("spread_type", RandomSpreadType.LINEAR).forGetter(RandomSpreadStructurePlacement::spreadType),
                    Codec.intRange(0, Integer.MAX_VALUE).optionalFieldOf("min_distance_from_world_origin").forGetter(TangofteroNestRandomSpread::minDistanceFromWorldOrigin)
            ).apply(instance, TangofteroNestRandomSpread::new)
    );

    private final Optional<Integer> minDistanceFromWorldOrigin;

    public TangofteroNestRandomSpread(
            Vec3i offset,
            FrequencyReductionMethod freq,
            float freqVal,
            int salt,
            Optional<ExclusionZone> exclusion,
            int spacing,
            int separation,
            RandomSpreadType spreadType,
            Optional<Integer> minDist
    ) {
        super(offset, freq, freqVal, salt, exclusion, spacing, separation, spreadType);
        this.minDistanceFromWorldOrigin = minDist;
    }

    public Optional<Integer> minDistanceFromWorldOrigin() {
        return this.minDistanceFromWorldOrigin;
    }

    @Override
    public StructurePlacementType<?> type() {
        return StructurePlacementTypeRegister.TANGOFTERO_RANDOM_SPREAD.get();
    }
}
