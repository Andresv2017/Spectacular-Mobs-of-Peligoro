package net.darkblade.smopmod.event;

import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.client.Hell_HippoModel;
import net.darkblade.smopmod.entity.client.ModModelLayers;
import net.darkblade.smopmod.entity.client.TangofteroModel;
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
    }

}
