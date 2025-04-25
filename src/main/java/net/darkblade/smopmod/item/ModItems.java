package net.darkblade.smopmod.item;

import net.darkblade.smopmod.SMOP;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SMOP.MOD_ID);

    public static final RegistryObject<Item> HELL_HIPPO_COOKED_MEAT = ITEMS.register("hell_hippo_cooked_meat",
            () -> new Item(new Item.Properties().food(ModFoods.HELL_HIPPO_COOKED_MEAT)));

    public static final RegistryObject<Item> HELL_HIPPO_RAW_MEAT = ITEMS.register("hell_hippo_raw_meat",
            () -> new Item(new Item.Properties().food(ModFoods.HELL_HIPPO_RAW_MEAT)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}