package net.darkblade.smopmod.block;

import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.ModEntities;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SMOP.MOD_ID);

    public static final RegistryObject<Block> TANGOFTERO_EGG = BLOCKS.register("tangoftero_egg",
            () -> new SmallEggBlock(ModEntities.TANGOFTERO, 300));

    public static final RegistryObject<Block> KRIFFO_EGG = BLOCKS.register("krifto_egg",
            () -> new SmallEggBlock(ModEntities.KIFTO, 300));

    public static final RegistryObject<Block> SALMON_ROE_EGGS = BLOCKS.register("salmon_roe_eggs",
            () -> new RoeEggsBlock(
                    ModEntities.SALMON,
                    3, 6,                    // min/max spawn
                    200, 800,               // hatch delay (10–40 segundos)
                    SoundEvents.FROGSPAWN_HATCH,
                    BlockBehaviour.Properties.of()
                            .noCollission()
                            .strength(0.5F)
            ));

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
