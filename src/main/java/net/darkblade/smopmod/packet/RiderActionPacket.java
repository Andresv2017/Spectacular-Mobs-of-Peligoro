package net.darkblade.smopmod.packet;

import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class RiderActionPacket {

    private final ActionType actionType;

    public RiderActionPacket(ActionType actionType) {
        this.actionType = actionType;
    }

    public static void encode(RiderActionPacket packet, FriendlyByteBuf buf) {
        buf.writeEnum(packet.actionType);
    }

    public static RiderActionPacket decode(FriendlyByteBuf buf) {
        return new RiderActionPacket(buf.readEnum(ActionType.class));
    }

    public static void handle(RiderActionPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null && player.getVehicle() instanceof Hell_HippoEntity hellHippo) {
                if (packet.actionType == ActionType.ATTACK) {
                    hellHippo.performMountedAttack(player);
                } else if (packet.actionType == ActionType.FEAR) {
                    hellHippo.performFearEffect(player);
                }
            }
        });
        context.setPacketHandled(true);
    }

    public enum ActionType {
        ATTACK,
        FEAR
    }

    public static class ModMessages {
        private static final String PROTOCOL_VERSION = "1";
        public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
                new net.minecraft.resources.ResourceLocation("smopmod", "main"),
                () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
        );

        private static int id = 0;

        public static void register() {
            INSTANCE.registerMessage(id++, RiderActionPacket.class, RiderActionPacket::encode, RiderActionPacket::decode, RiderActionPacket::handle);
        }

        public static void sendToServer(RiderActionPacket packet) {
            INSTANCE.sendToServer(packet);
        }
    }
}

