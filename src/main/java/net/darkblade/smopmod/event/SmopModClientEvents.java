package net.darkblade.smopmod.event;

import net.darkblade.smopmod.packet.RiderActionPacket;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class SmopModClientEvents {

    public static final KeyMapping ATTACK_KEY = new KeyMapping("key.smopmod.attack", GLFW.GLFW_KEY_R, "key.categories.smopmod");
    public static final KeyMapping FEAR_KEY = new KeyMapping("key.smopmod.fear", GLFW.GLFW_KEY_G, "key.categories.smopmod");
    public static final KeyMapping OPEN_INVENTORY_KEY = new KeyMapping("key.smopmod.open_inventory", GLFW.GLFW_KEY_V, "key.categories.smopmod");

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ATTACK_KEY);
        event.register(FEAR_KEY);
        event.register(OPEN_INVENTORY_KEY); // 👈 nuevo
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (ATTACK_KEY.consumeClick()) {
                RiderActionPacket.ModMessages.sendToServer(new RiderActionPacket(RiderActionPacket.ActionType.ATTACK));
            }
            if (FEAR_KEY.consumeClick()) {
                RiderActionPacket.ModMessages.sendToServer(new RiderActionPacket(RiderActionPacket.ActionType.FEAR));
            }
            if (OPEN_INVENTORY_KEY.consumeClick()) {
                RiderActionPacket.ModMessages.sendToServer(new RiderActionPacket(RiderActionPacket.ActionType.OPEN_INVENTORY)); // 👈 nuevo
            }
        }
    }
}
