package net.bictoelpodre.tboimod.client;

import net.bictoelpodre.tboimod.client.key.KeyBinding;
import net.bictoelpodre.tboimod.screen.CheatMenuScreen;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;

@EventBusSubscriber(modid = "thebindingofisaacmod", value = Dist.CLIENT)
public class CheatMenuKeyHandler {
    
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        // L key = 76
        if (event.getAction() == 1 && event.getKey() == 76) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null && mc.screen == null) {
                mc.setScreen(new CheatMenuScreen(null));
            }
        }
    }
}