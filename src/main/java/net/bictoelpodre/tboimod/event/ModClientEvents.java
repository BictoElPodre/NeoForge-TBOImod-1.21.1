package net.bictoelpodre.tboimod.event;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.client.key.KeyBinding;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(KeyBinding.TEARS_KEY);

    }
}
