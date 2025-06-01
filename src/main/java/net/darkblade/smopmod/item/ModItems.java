package net.darkblade.smopmod.item;

import net.darkblade.smopmod.SMOP;
import net.darkblade.smopmod.block.ModBlocks;
import net.darkblade.smopmod.entity.ModEntities;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SMOP.MOD_ID);

    // ───────────────────────────────────────────────────── FOOD ─────
    public static final RegistryObject<Item> HELL_HIPPO_COOKED_MEAT = ITEMS.register("hell_hippo_cooked_meat",
            () -> new Item(new Item.Properties().food(ModFoods.HELL_HIPPO_COOKED_MEAT)));

    public static final RegistryObject<Item> HELL_HIPPO_RAW_MEAT = ITEMS.register("hell_hippo_raw_meat",
            () -> new Item(new Item.Properties().food(ModFoods.HELL_HIPPO_RAW_MEAT)));

    public static final RegistryObject<Item> RAW_SALMON = ITEMS.register("raw_salmon",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_SALMON)));

    // ───────────────────────────────────────────────────── SPAWN EGGS ─────

    public static final RegistryObject<Item> HELL_HIPPO_SPAWN_EGG = ITEMS.register("hell_hippo_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.HELL_HIPPO, 0x0a0909, 0xcd1d1d, new Item.Properties()));

    public static final RegistryObject<Item> TANGOFTERO_SPAWN_EGG = ITEMS.register("tangoftero_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.TANGOFTERO, 0x9019e8, 0xe6e1ea, new Item.Properties()));

    public static final RegistryObject<Item> KRIFTOGNATHUS_SPAWN_EGG = ITEMS.register("kriftognathus_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.KIFTO, 0xFFD700, 0xADD8E6, new Item.Properties()));

    public static final RegistryObject<Item> SALMON_SPAWN_EGG = ITEMS.register("salmon_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SALMON, 0xFA8072, 0x88773C, new Item.Properties()));

    // ───────────────────────────────────────────────────── ARMORS ─────

    public static final RegistryObject<Item> HELHIPPO_ARMOR = ITEMS.register("hellhippo_armor", () ->
            new HorseArmorItem(15, "", new Item.Properties().stacksTo(1)));

    // ───────────────────────────────────────────────────── EGGS ITEM ─────

    public static final RegistryObject<Item> TANGOFTERO_EGG_ITEM = ITEMS.register("tangoftero_egg",
            () -> new BlockItem(ModBlocks.TANGOFTERO_EGG.get(), new Item.Properties()));

    public static final RegistryObject<Item> KRIFTO_EGG_ITEM = ITEMS.register("krifto_egg",
            () -> new BlockItem(ModBlocks.KRIFFO_EGG.get(), new Item.Properties()));

    public static final RegistryObject<Item> SALMON_ROE_EGGS_ITEM = ITEMS.register("salmon_roe_eggs",
            () -> new BlockItem(ModBlocks.SALMON_ROE_EGGS.get(), new Item.Properties()));

    // ───────────────────────────────────────────────────── LIST DROPS─────

    public static final List<Item> SAND_DROPS = List.of(Items.STICK, Items.SANDSTONE);
    public static final List<Item> GRAVEL_DROPS = List.of(Items.FLINT);
    public static final List<Item> MUD_DROPS = List.of(Items.CLAY_BALL);
    public static final List<Item> DIRT_DROPS = List.of(Items.POTATO, Items.CARROT);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}