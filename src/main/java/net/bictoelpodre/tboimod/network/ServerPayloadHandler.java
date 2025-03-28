package net.bictoelpodre.tboimod.network;

import net.bictoelpodre.tboimod.event.ServerEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public class ServerPayloadHandler {

    public static void handleDataOnMain(final PayloadsRegistrar.OnKeyBind onKeyBind, final IPayloadContext context) {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("server recieved packet"));

    }
}
