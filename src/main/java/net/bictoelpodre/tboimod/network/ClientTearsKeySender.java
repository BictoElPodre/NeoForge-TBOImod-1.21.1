package net.bictoelpodre.tboimod.network;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.client.key.KeyBinding;
import net.bictoelpodre.tboimod.network.TearsHandler;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, value = Dist.CLIENT)
public class ClientTearsKeySender {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if(KeyBinding.TEARS_KEY.consumeClick()) {
            PacketDistributor.sendToServer(new TearsHandler.IsTearsKeyPressed(true));
        }
    }
}