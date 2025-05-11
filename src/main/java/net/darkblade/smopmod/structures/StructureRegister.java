package net.darkblade.smopmod.structures;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class StructureRegister {

    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_DEF_REG =
            DeferredRegister.create(Registries.STRUCTURE_PIECE, "smop");

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE_DEF_REG =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, "smop");

    public static final RegistryObject<StructureType<TangofteroNestStructure>> TANGOFTERO_NEST =
            STRUCTURE_TYPE_DEF_REG.register("tangoftero_nest",
                    () -> () -> TangofteroNestStructure.CODEC);

    public static final RegistryObject<StructurePieceType> PIECE =
            STRUCTURE_PIECE_DEF_REG.register("tangoftero_nest",
                    () -> TangofteroNestStructure.Piece::new);
}
