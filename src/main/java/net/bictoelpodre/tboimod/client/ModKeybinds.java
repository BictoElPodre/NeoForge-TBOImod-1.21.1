package net.bictoelpodre.tboimod.client;

import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {

    public static final String TBOICATEGORY = "key.categories.thebindingofisaacmod";
    public static KeyMapping SHOOT_SNOWBALL;

    public static void register(RegisterKeyMappingsEvent keyMappingsEvent) {
        SHOOT_SNOWBALL = new KeyMapping("key.thebindingofisaacmod.shoot_snowball", GLFW.GLFW_KEY_C, TBOICATEGORY);
        keyMappingsEvent.register(SHOOT_SNOWBALL);
    }
}

