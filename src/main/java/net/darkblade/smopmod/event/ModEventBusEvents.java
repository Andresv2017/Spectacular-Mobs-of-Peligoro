package net.darkblade.smopmod.event;

import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.ModEntities;
import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SMOP.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes (EntityAttributeCreationEvent event) {
        event.put(ModEntities.HELL_HIPPO.get(), Hell_HippoEntity.createAttributes().build());
    }
}
