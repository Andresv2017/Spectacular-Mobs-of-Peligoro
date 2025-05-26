package net.darkblade.smopmod.entity.custom;

import net.darkblade.smopmod.entity.WaterEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

import java.util.Set;

public class SalmonEntity extends WaterEntity {

    public SalmonEntity(EntityType<? extends WaterEntity> type, Level level) {
        super(type, level);
        this.setOutOfWaterMaxTicks(80);     // 4 segundos
        this.setOutOfWaterDamage(2.0F);     // Daño por tick
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.40D)
                .add(Attributes.ATTACK_SPEED, 0.4D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1F)
                .add(Attributes.ATTACK_DAMAGE, 1.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new RandomSwimmingGoal(this, 1.0D, 40));
    }

    @Override
    public Set<EntityType<?>> getInterruptingEntityTypes() {
        return Set.of();
    }
}
