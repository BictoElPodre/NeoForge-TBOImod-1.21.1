package net.bictoelpodre.tboimod.hud;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.client.key.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, value = Dist.CLIENT)
public class ChoseCharacterHud {

    private static final ResourceLocation AZAZEL_FRAME = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/azazel_frame.png");

    private static int m = 0;

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGuiLayerEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();

        if ((mc.player == null) || mc.options.hideGui) return;

        GuiGraphics guiGraphics = event.getGuiGraphics();

        if (KeyBinding.KEY_MAPPINGS.get(2).consumeClick()) {
            m = (m+1) % 2;
        }

        int size = 32 * ((1-m) / 1);
        guiGraphics.blit(AZAZEL_FRAME, 0, 0, 0, 0, size, size, size, size);
    }
}
