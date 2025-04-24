package net.darkblade.smopmod.item;

import net.darkblade.smopmod.SMOP;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SMOP.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SMOP_TAB = CREATIVE_MODE_TABS.register("smop_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HELL_HIPPO_MEAT.get()))
                    .title(Component.translatable("creativetab.smop_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.HELL_HIPPO_MEAT.get());


                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
