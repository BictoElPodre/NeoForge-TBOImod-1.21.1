package net.bictoelpodre.tboimod.client.key;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class KeyBinding {
    public static final String KEY_CATEGORY_TUTORIAL = "key.categories.thebindingofisaacmod";

    public static final String TEARS_SHOOTING = "key.thebindingofisaacmod.tears_shooting";
    public static final String TOGGLE_HUD_SIZE = "key.thebindingofisaacmod.toggle_hud_size";

    public static final List<KeyMapping> KEY_MAPPINGS= new ArrayList<>();

    static {
        KEY_MAPPINGS.add(new KeyMapping(
                TEARS_SHOOTING,
                KeyConflictContext.IN_GAME,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                KEY_CATEGORY_TUTORIAL));

        KEY_MAPPINGS.add(new KeyMapping(
                TOGGLE_HUD_SIZE,
                KeyConflictContext.IN_GAME,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_TUTORIAL));

    }
}
