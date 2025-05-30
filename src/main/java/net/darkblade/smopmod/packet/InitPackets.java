package net.darkblade.smopmod.packet;

import net.darkblade.smopmod.SMOP;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkConstants;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class InitPackets {

    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
                    new ResourceLocation(SMOP.MOD_ID, "main"))
            .serverAcceptedVersions(s -> true)
            .clientAcceptedVersions(s -> true)
            .networkProtocolVersion(() -> NetworkConstants.NETVERSION)
            .simpleChannel();

    public static void register() {
        int id = 0;

        INSTANCE.messageBuilder(StoCSyncFlying.class, id++)
                .encoder(StoCSyncFlying::encode)
                .decoder(StoCSyncFlying::new)
                .consumerMainThread(StoCSyncFlying::handle)
                .add();
    }

    public static void sendToAll(Object msg) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }

    public static void sendToClient(Object msg, ServerPlayer player) {
        if (INSTANCE.isRemotePresent(player.connection.connection)) {
            INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
        }
    }

    public static void sendToServer(Object msg) {
        INSTANCE.send(PacketDistributor.SERVER.noArg(), msg);
    }
}
