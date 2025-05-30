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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HELL_HIPPO_COOKED_MEAT.get()))
                    .title(Component.translatable("creativetab.smop_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        // ────────────────────────── FOOD ─────
                        pOutput.accept(ModItems.HELL_HIPPO_COOKED_MEAT.get());
                        pOutput.accept(ModItems.HELL_HIPPO_RAW_MEAT.get());
                        pOutput.accept(ModItems.RAW_SALMON.get());
                        // ────────────────────────── SPAWN EGGS ─────
                        pOutput.accept(ModItems.HELL_HIPPO_SPAWN_EGG.get());
                        pOutput.accept(ModItems.TANGOFTERO_SPAWN_EGG.get());
                        pOutput.accept(ModItems.KRIFTOGNATHUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.SALMON_SPAWN_EGG.get());
                        // ────────────────────────── ARMOR ─────
                        pOutput.accept(ModItems.HELHIPPO_ARMOR.get());
                        // ────────────────────────── EGGS BLOCKS ─────
                        pOutput.accept(ModItems.TANGOFTERO_EGG_ITEM.get());
                        pOutput.accept(ModItems.KRIFTO_EGG_ITEM.get());
                        pOutput.accept(ModItems.SALMON_ROE_EGGS_ITEM.get());



                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
