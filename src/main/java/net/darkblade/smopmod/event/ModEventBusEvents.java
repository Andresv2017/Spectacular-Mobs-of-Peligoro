package net.darkblade.smopmod.event;

import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.entity.ModEntities;
import net.darkblade.smopmod.entity.custom.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SMOP.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes (EntityAttributeCreationEvent event) {
        event.put(ModEntities.HELL_HIPPO.get(), Hell_HippoEntity.createAttributes().build());
        event.put(ModEntities.TANGOFTERO.get(), TangofteroEntity.createAttributes().build());
        event.put(ModEntities.KIFTO.get(), KriftognathusEntity.createAttributes().build());
        event.put(ModEntities.NIRAS.get(), NirasmosaurusEntity.createAttributes().build());
        event.put(ModEntities.SALMON.get(), SalmonEntity.createAttributes().build());
    }
}
