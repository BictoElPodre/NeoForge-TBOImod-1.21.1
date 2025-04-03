package net.bictoelpodre.tboimod.hud;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.client.key.KeyBinding;
import net.bictoelpodre.tboimod.entity.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, value = Dist.CLIENT)
public class CustomHud {

    private static final ResourceLocation DEVILROOMSTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/devilroomstat.png");
    private static final ResourceLocation ANGELROOMSTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/angelroomstat.png");
    private static final ResourceLocation LUCKSTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/luckstat.png");
    private static final ResourceLocation RANGESTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/rangestat.png");
    private static final ResourceLocation SHOOTSPEEDSTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/shootspeedstat.png");
    private static final ResourceLocation SPEEDSTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/speedstat.png");
    private static final ResourceLocation TEARSSTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/tearsstat.png");
    private static final ResourceLocation DAMAGESTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/damagestat.png");

    private static int m =0;

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGuiLayerEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();

        if ((mc.player == null) || mc.options.hideGui) return;

        GuiGraphics guiGraphics = event.getGuiGraphics();

        if (KeyBinding.KEY_MAPPINGS.get(1).consumeClick()) {
            m = (m+1) % 2;
        }

        int size = 16 * ((1-m) / 1);

        guiGraphics.blit(ANGELROOMSTAT, 10, 175 + 18, 0, 0, size, size, size, size);
        guiGraphics.blit(DEVILROOMSTAT, 10, 175, 0, 0, size, size, size, size);
        guiGraphics.blit(LUCKSTAT, 10, 175 - 18, 0, 0, size, size, size, size);
        guiGraphics.blit(SHOOTSPEEDSTAT, 10, 175 - 18 * 2, 0, 0, size, size, size, size);
        guiGraphics.blit(RANGESTAT, 10, 175 - 18 * 3, 0, 0, size, size, size, size);
        guiGraphics.blit(DAMAGESTAT, 10, 175 - 18 * 4, 0, 0, size, size, size, size);
        guiGraphics.blit(TEARSSTAT, 10, 175 - 18 * 5, 0, 0, size, size, size, size);
        guiGraphics.blit(SPEEDSTAT, 10, 175 - 18 * 6, 0, 0, size, size, size, size);



    }
}
