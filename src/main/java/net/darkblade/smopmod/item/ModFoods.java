package net.darkblade.smopmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties HELL_HIPPO_COOKED_MEAT = new FoodProperties.Builder().nutrition(4)
            .saturationMod(0.5f).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200), 0.1f).meat().build();

    public static final FoodProperties HELL_HIPPO_RAW_MEAT = new FoodProperties.Builder().nutrition(2)
            .saturationMod(0.5f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 200), 0.1f).meat().build();

    public static final FoodProperties RAW_SALMON = new FoodProperties.Builder().nutrition(2)
            .saturationMod(0.5f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 200), 0.1f).meat().build();
}
