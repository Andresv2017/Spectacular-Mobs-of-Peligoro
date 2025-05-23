package net.darkblade.smopmod.packet;

import net.darkblade.smopmod.entity.FlyingEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StoCSyncFlying {
    private final int entityId;
    private final boolean isFlying;

    public StoCSyncFlying(int entityId, boolean isFlying) {
        this.entityId = entityId;
        this.isFlying = isFlying;
    }

    public StoCSyncFlying(FriendlyByteBuf buffer) {
        this.entityId = buffer.readInt();
        this.isFlying = buffer.readBoolean();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(entityId);
        buffer.writeBoolean(isFlying);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            ClientLevel level = Minecraft.getInstance().level;
            if (level == null) return;

            Entity entity = level.getEntity(entityId);
            if (entity instanceof FlyingEntity flyingEntity) {
                flyingEntity.setFlying(isFlying);
            }
        });
        contextSupplier.get().setPacketHandled(true);
    }
}

