package net.darkblade.smopmod.packet;


import net.darkblade.smopmod.entity.FlyingEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;


import java.util.function.Supplier;

public class StoCSyncFlying {

    private final int id;
    private final boolean flying;

    public StoCSyncFlying(int id, boolean flying) {
        this.id = id;
        this.flying = flying;
    }

    public StoCSyncFlying(FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readBoolean());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(id);
        buffer.writeBoolean(flying);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Entity entity = Minecraft.getInstance().level.getEntity(id);
            if (entity instanceof FlyingEntity flyingEntity) {
                flyingEntity.setIsFlying(flying);
            }
        });
        context.get().setPacketHandled(true);
    }
}

