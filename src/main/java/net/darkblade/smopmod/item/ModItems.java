package net.darkblade.smopmod.item;

import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.block.ModBlocks;
import net.darkblade.smopmod.entity.ModEntities;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
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

    public static final RegistryObject<Item> HELL_HIPPO_SPAWN_EGG = ITEMS.register("hell_hippo_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.HELL_HIPPO, 0x0a0909, 0xcd1d1d, new Item.Properties()));

    public static final RegistryObject<Item> TANGOFTERO_SPAWN_EGG = ITEMS.register("tangoftero_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.TANGOFTERO, 0x9019e8, 0xe6e1ea, new Item.Properties()));

    public static final RegistryObject<Item> KRIFTOGNATHUS_SPAWN_EGG = ITEMS.register("kriftognathus_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.KIFTO, 0xFFD700, 0xADD8E6, new Item.Properties()));

    public static final RegistryObject<Item> HELHIPPO_ARMOR = ITEMS.register("hellhippo_armor", () ->
            new HorseArmorItem(15, "", new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> TANGOFTERO_EGG_ITEM = ITEMS.register("tangoftero_egg",
            () -> new BlockItem(ModBlocks.TANGOFTERO_EGG.get(), new Item.Properties()));

    public static final RegistryObject<Item> KRIFTO_EGG_ITEM = ITEMS.register("krifto_egg",
            () -> new BlockItem(ModBlocks.KRIFFO_EGG.get(), new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}