package net.darkblade.smopmod.structures.placements;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class StructurePlacementTypeRegister {
    public static final DeferredRegister<StructurePlacementType<?>> STRUCTURE_PLACEMENT_TYPE =
            DeferredRegister.create(Registries.STRUCTURE_PLACEMENT, "smop");

    public static final RegistryObject<StructurePlacementType<TangofteroNestRandomSpread>> TANGOFTERO_RANDOM_SPREAD =
            STRUCTURE_PLACEMENT_TYPE.register("tangoftero_random_spread", () -> () -> TangofteroNestRandomSpread.CODEC);
}
