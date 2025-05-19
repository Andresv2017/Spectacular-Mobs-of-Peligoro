package net.darkblade.smopmod.block;

import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.ModEntities;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SMOP.MOD_ID);

    public static final RegistryObject<Block> TANGOFTERO_EGG = BLOCKS.register("tangoftero_egg",
            () -> new SmallEggBlock(ModEntities.TANGOFTERO, 200));

    public static final RegistryObject<Block> KRIFFO_EGG = BLOCKS.register("krifto_egg",
            () -> new SmallEggBlock(ModEntities.KIFTO, 200));

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
