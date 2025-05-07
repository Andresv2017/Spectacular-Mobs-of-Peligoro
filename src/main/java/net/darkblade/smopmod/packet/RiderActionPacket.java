package net.darkblade.smopmod.packet;

import net.darkblade.smopmod.entity.custom.Hell_HippoEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.HorseInventoryMenu;
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
            if (player == null) return;

            if (player.getVehicle() instanceof Hell_HippoEntity hellHippo) {
                switch (packet.actionType) {
                    case ATTACK -> hellHippo.performMountedAttack(player);
                    case FEAR -> hellHippo.performFearEffect(player);
                    case OPEN_INVENTORY -> {
                        System.out.println("[SERVER] Saddled: " + hellHippo.isSaddled() + ", HasChest: " + hellHippo.hasChest());
                        if (hellHippo.isSaddled() && hellHippo.hasChest()) {
                            hellHippo.openInventoryFor(player);
                        } else {
                            player.displayClientMessage(Component.literal("\u00a7cYou must equip a saddle and chest first."), true);
                        }
                    }

                }
            }
        });
        context.setPacketHandled(true);
    }


    public enum ActionType {
        ATTACK,
        FEAR,
        OPEN_INVENTORY // 👈 nuevo
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

