package net.darkblade.smopmod.event;

import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.client.*;
import net.darkblade.smopmod.entity.client.hell_hippo.Baby_Hell_HippoModel;
import net.darkblade.smopmod.entity.client.hell_hippo.Hell_HippoModel;
import net.darkblade.smopmod.entity.client.krifto.KriftognathusModel;
import net.darkblade.smopmod.entity.client.niras.NirasmosaurusModel;
import net.darkblade.smopmod.entity.client.salmon.SalmonModel;
import net.darkblade.smopmod.entity.client.tango.TangofteroModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SMOP.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.HELL_HIPPO_LAYER, Hell_HippoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.TANGOFTERO_LAYER, TangofteroModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BABY_HELL_HIPPO_LAYER, Baby_Hell_HippoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.KRIFTO, KriftognathusModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.NIRAS, NirasmosaurusModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SALMON, SalmonModel::createBodyLayer);
    }

}
