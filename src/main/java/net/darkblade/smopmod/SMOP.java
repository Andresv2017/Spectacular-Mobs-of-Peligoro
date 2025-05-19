package net.darkblade.smopmod;

import com.mojang.logging.LogUtils;

import net.darkblade.smopmod.block.ModBlocks;
import net.darkblade.smopmod.effect.ModEffects;
import net.darkblade.smopmod.entity.ModEntities;
import net.darkblade.smopmod.entity.client.hell_hippo.Hell_HippoRenderer;
import net.darkblade.smopmod.entity.client.krifto.KriftognathusRender;
import net.darkblade.smopmod.entity.client.niras.NirasmosaurusRender;
import net.darkblade.smopmod.entity.client.tango.TangofteroRender;
import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.darkblade.smopmod.entity.custom.KriftognathusEntity;
import net.darkblade.smopmod.entity.custom.TangofteroEntity;
import net.darkblade.smopmod.item.ModCreativeModTabs;
import net.darkblade.smopmod.item.ModItems;
import net.darkblade.smopmod.packet.RiderActionPacket;
import net.darkblade.smopmod.structures.StructureRegister;
import net.darkblade.smopmod.structures.placements.StructurePlacementTypeRegister;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SMOP.MOD_ID)
public class SMOP
{
    public static final String MOD_ID = "smop";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SMOP(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        RiderActionPacket.ModMessages.register();
        ModEffects.EFFECTS.register(modEventBus);


        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);

        ModEntities.register(modEventBus);

        StructureRegister.STRUCTURE_TYPE_DEF_REG.register(modEventBus);
        StructureRegister.STRUCTURE_PIECE_DEF_REG.register(modEventBus);
        StructurePlacementTypeRegister.STRUCTURE_PLACEMENT_TYPE.register(modEventBus);


        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntities.TANGOFTERO.get(), SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING, TangofteroEntity::checkTangofteroSpawnRules);
        });

        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntities.HELL_HIPPO.get(), SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING, Hell_HippoEntity::checkHell_HippoSpawnRules);
        });

        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntities.KIFTO.get(), SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING, KriftognathusEntity::checkKriftoSpawnRules);
        });

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.HELL_HIPPO.get(), Hell_HippoRenderer::new);
            EntityRenderers.register(ModEntities.TANGOFTERO.get(), TangofteroRender::new);
            EntityRenderers.register(ModEntities.KIFTO.get(), KriftognathusRender::new);
            EntityRenderers.register(ModEntities.NIRAS.get(), NirasmosaurusRender::new);
            event.enqueueWork(() -> {
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.TANGOFTERO_EGG.get(), RenderType.cutout());
            });
            event.enqueueWork(() -> {
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.KRIFFO_EGG.get(), RenderType.cutout());
            });
        }
    }
}
