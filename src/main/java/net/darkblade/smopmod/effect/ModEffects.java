package net.darkblade.smopmod.effect;

import net.darkblade.smopmod.SMOP;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, SMOP.MOD_ID);

    public static final RegistryObject<MobEffect> FEAR =
            EFFECTS.register("fear", FearEffect::new);

    public static void register(IEventBus bus) {
        EFFECTS.register(bus);
    }
}
