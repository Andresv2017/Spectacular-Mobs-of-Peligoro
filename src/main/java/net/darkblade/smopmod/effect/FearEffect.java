package net.darkblade.smopmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class FearEffect extends MobEffect {
    public FearEffect() {
        super(MobEffectCategory.HARMFUL, 0x4A148C);

        // Aplicar modificadores de atributos similares a Weakness y Slowness
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                "91AEAA56-376B-4498-935B-2F7F68070635",
                -4.0D, // igual a Weakness 0
                AttributeModifier.Operation.ADDITION
        );

        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                "7107DE5E-7CE8-4030-940E-514C1F160890",
                -0.15D, // similar a Slowness 0
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );// color púrpura oscuro
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
