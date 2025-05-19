package net.darkblade.smopmod.entity.util;

import net.darkblade.smopmod.entity.interfaces.IHasLeader;
import net.darkblade.smopmod.entity.interfaces.gropu_behaviour.GroupType;
import net.darkblade.smopmod.entity.interfaces.gropu_behaviour.IGroupBehaviour;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;

import java.util.Comparator;
import java.util.List;

public class GroupUtil {
    public static <T extends Mob & IGroupBehaviour & IHasLeader> void reassignLeaderIfNeeded(T self) {
        LivingEntity current = self.getGroupLeader();

        // Si el líder existe y está vivo, no hacer nada
        if (current != null && current.isAlive()) return;

        // Incluir al self en el grupo
        List<T> group = self.level().getEntitiesOfClass(
                (Class<T>) self.getClass(),
                self.getBoundingBox().inflate(22),
                e -> e.isAlive() && e.getGroupType() == GroupType.PACK
        );

        // Solo la entidad con el ID más bajo decide
        T coordinator = group.stream()
                .min(Comparator.comparingInt(Entity::getId))
                .orElse(self);

        if (coordinator != self) return;

        // Asignar nuevo líder
        if (!group.isEmpty()) {
            T newLeader = group.get(0);
            for (T member : group) {
                member.setGroupLeader(newLeader);
                member.setGlowingTag(false);
            }
            newLeader.setGlowingTag(true);
        } else {
            self.setGroupLeader(null);
            self.setGlowingTag(false);
        }
    }
}
