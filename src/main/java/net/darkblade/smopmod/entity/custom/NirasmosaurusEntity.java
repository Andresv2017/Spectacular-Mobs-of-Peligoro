package net.darkblade.smopmod.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class NirasmosaurusEntity extends TamableAnimal {

    public NirasmosaurusEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.20D)
                .add(Attributes.ATTACK_SPEED, 0.4D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1F)
                .add(Attributes.ATTACK_DAMAGE, 2.0F);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}
