package net.bictoelpodre.tboimod.client.key;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_TUTORIAL = "key.categories.thebindingofisaacmod";
    public static final String TEARS_SHOOTING = "key.thebindingofisaacmod.tears_shooting";

    public static final KeyMapping TEARS_KEY =
            new KeyMapping(TEARS_SHOOTING,
                    KeyConflictContext.IN_GAME,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_C,
                    KEY_CATEGORY_TUTORIAL);

}
