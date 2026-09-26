package net.bictoelpodre.tboimod.network;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.client.key.KeyBinding;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, value = Dist.CLIENT)
public class ClientTearsKeySender {

    private static boolean wasShooting = false;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) return;

        boolean isShooting = KeyBinding.KEY_MAPPINGS.get(0).isDown();

        // Send packet when key state changes (pressed or released)
        if (isShooting != wasShooting) {
            PacketDistributor.sendToServer(new TearsHandler.IsTearsKeyPressed(isShooting));
            wasShooting = isShooting;
        }
    }
}