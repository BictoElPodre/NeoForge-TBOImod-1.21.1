package net.bictoelpodre.tboimod.hud;

import net.bictoelpodre.tboimod.capability.CharacterCapability;
import net.bictoelpodre.tboimod.character.ModCharacter;
import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.event.InputEvent;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, value = Dist.CLIENT)
public class StatsHud {
    
    // Iconos (mismos que CustomHud)
    private static final ResourceLocation DAMAGESTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/damagestat.png");
    private static final ResourceLocation TEARSSTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/tearsstat.png");
    private static final ResourceLocation RANGESTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/rangestat.png");
    private static final ResourceLocation SPEEDSTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/speedstat.png");
    private static final ResourceLocation SHOOTSPEEDSTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/shootspeedstat.png");
    private static final ResourceLocation LUCKSTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/luckstat.png");
    private static final ResourceLocation DEVILROOMSTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/devilroomstat.png");
    private static final ResourceLocation ANGELROOMSTAT = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/angelroomstat.png");
    
    private static final int ICON_SIZE = 14;
    private static final int ICON_X = 8;
    private static final int VALUE_X = ICON_X + ICON_SIZE + 2; // 24 (al lado derecho del icono)
    private static final int SPACING = 15;
    private static final int HUD_TOTAL_HEIGHT = 8 * SPACING + 25; // 8 stats + barra vida + padding
    private static final int MIN_SCREEN_HEIGHT = 180; // Altura mínima para mostrar HUD
    
    // Configuración adaptativa (porcentaje de la altura de pantalla)
    private static final double BOTTOM_MARGIN_PERCENT = 0.06; // 6% desde abajo
    private static final int MIN_BOTTOM_MARGIN = 20; // Mínimo en píxeles escalados
    private static final int MAX_BOTTOM_MARGIN = 60; // Máximo en píxeles escalados
    
    private static boolean showHud = true;
    private static boolean compactMode = false; // Modo compacto (solo iconos, sin valores) - tecla H para alternar
    
    @SubscribeEvent
    public static void onRenderGui(RenderGuiLayerEvent.Post event) {
        if (!showHud) return;
        
        Minecraft mc = Minecraft.getInstance();
        
        if (mc.player == null || mc.options.hideGui) return;
        
        LocalPlayer player = mc.player;
        
        // Obtener stats del capability
        var attachment = player.getData(net.bictoelpodre.tboimod.capability.CharacterCapability.CHARACTER_STATS);
        
        GuiGraphics gui = event.getGuiGraphics();
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        
        // Verificar altura mínima
        if (screenHeight < MIN_SCREEN_HEIGHT) return;
        
        // Calcular margen inferior adaptativo (porcentaje de altura con clamp)
        int bottomMargin = (int) Math.max(MIN_BOTTOM_MARGIN, Math.min(MAX_BOTTOM_MARGIN, screenHeight * BOTTOM_MARGIN_PERCENT));
        
        // Calcular posición base desde el borde inferior (adaptable a cualquier resolución)
        int hudBottomY = screenHeight - bottomMargin;
        int startY = hudBottomY - HUD_TOTAL_HEIGHT;
        
        // Seguridad: si el HUD se sale por arriba, ajustar
        if (startY < 10) {
            startY = 10;
            hudBottomY = startY + HUD_TOTAL_HEIGHT;
        }
        
        if (attachment == null) {
            // Si no hay capability, solo renderizar iconos
            renderIconsOnly(gui, startY);
            return;
        }
        
        // Modo compacto: solo iconos
        if (compactMode) {
            renderIconsOnly(gui, startY);
        } else {
        
        // Renderizar iconos y valores (orden de abajo hacia arriba: SPEED, TEARS, DAMAGE, RANGE, SHOOTSPEED, LUCK, DEVIL, ANGEL)
        // Los stats empiezan en startY y van hacia arriba
        int y = startY; // SPEED (primero, más abajo)
        renderStatWithIcon(gui, SPEEDSTAT, "SPD", String.format("%.2f", attachment.getBaseSpeed()), 0x6BCB77, y);
        
        y += SPACING; // TEARS
        renderStatWithIcon(gui, TEARSSTAT, "TEARS", String.format("%.2f/s", ModCharacter.calculateFireRate(attachment.getBaseTearRate())), 0x6BCBFF, y);
        
        y += SPACING; // DAMAGE
        renderStatWithIcon(gui, DAMAGESTAT, "DMG", String.format("%.2f", attachment.getBaseDamage()), 0xFF6B6B, y);
        
        y += SPACING; // RANGE
        renderStatWithIcon(gui, RANGESTAT, "RANGE", String.format("%.1f", ModCharacter.calculateEffectiveRange(attachment.getBaseRange(), attachment.getBaseShotSpeed())), 0xFFD93D, y);
        
        y += SPACING; // SHOOT SPEED
        renderStatWithIcon(gui, SHOOTSPEEDSTAT, "SHOT SPD", String.format("%.2f", attachment.getBaseShotSpeed()), 0xFFB347, y);
        
        y += SPACING; // LUCK
        renderStatWithIcon(gui, LUCKSTAT, "LUCK", String.format("%.1f", attachment.getBaseLuck()), 0xD4A5FF, y);
        
        y += SPACING; // DEVIL ROOM
        renderStatWithIcon(gui, DEVILROOMSTAT, "DEVIL", "0%", 0xFF4444, y);
        
        y += SPACING; // ANGEL ROOM
        renderStatWithIcon(gui, ANGELROOMSTAT, "ANGEL", "0%", 0x44FFFF, y);
        
        // Barra de vida (debajo de los iconos, en hudBottomY)
        renderHealthBar(gui, hudBottomY, attachment);
        }
    }
    
    /** Renderiza solo los iconos (modo compacto o sin capability) */
    private static void renderIconsOnly(GuiGraphics gui, int startY) {
        int y = startY;
        gui.blit(SPEEDSTAT, ICON_X, y, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
        
        y += SPACING;
        gui.blit(TEARSSTAT, ICON_X, y, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
        
        y += SPACING;
        gui.blit(DAMAGESTAT, ICON_X, y, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
        
        y += SPACING;
        gui.blit(RANGESTAT, ICON_X, y, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
        
        y += SPACING;
        gui.blit(SHOOTSPEEDSTAT, ICON_X, y, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
        
        y += SPACING;
        gui.blit(LUCKSTAT, ICON_X, y, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
        
        y += SPACING;
        gui.blit(DEVILROOMSTAT, ICON_X, y, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
        
        y += SPACING;
        gui.blit(ANGELROOMSTAT, ICON_X, y, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
    }
    
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        // Tecla H para alternar HUD (mostrar/ocultar)
        if (event.getAction() == 1 && event.getKey() == 72) { // H key
            showHud = !showHud;
        }
        // Tecla J para alternar modo compacto (solo iconos / iconos + valores)
        if (event.getAction() == 1 && event.getKey() == 74) { // J key
            compactMode = !compactMode;
        }
    }
    
    private static void renderStatWithIcon(GuiGraphics gui, ResourceLocation icon, String label, String value, int color, int y) {
        Minecraft mc = Minecraft.getInstance();
        
        // Renderizar icono
        gui.blit(icon, ICON_X, y, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
        
        if (!compactMode) {
            // Valor al lado derecho del icono, centrado verticalmente
            // Icono: 16px alto, Fuente: ~9px alto -> offset vertical = (16 - 9) / 2 ≈ 3-4
            int valueY = y + 4; // Centrado vertical con icono de 16px
            gui.drawString(mc.font, value, VALUE_X, valueY, color, false);
        }
    }
    
    private static void renderHealthBar(GuiGraphics gui, int startY, net.bictoelpodre.tboimod.capability.CharacterCapability.ICharacterStats attachment) {
        Minecraft mc = Minecraft.getInstance();
        
        int maxRedHearts = Math.min(12, attachment.getMaxHealth() / 2);
        int maxSoulHearts = Math.min(12, attachment.getMaxSoulHearts() / 2);
        int maxBlackHearts = Math.min(12, attachment.getMaxBlackHearts() / 2);
        
        float playerHealth = mc.player.getHealth();
        int currentRedHalfHearts = (int) Math.ceil(playerHealth * 2);
        
        int currentSoulHalfHearts = 0; // TODO: implementar lectura real
        int currentBlackHalfHearts = 0; // TODO: implementar lectura real
        
        int barY = startY;
        int x = ICON_X;
        
        // Dibujar corazones rojos
        for (int i = 0; i < maxRedHearts; i++) {
            int hx = x + i * 10;
            int hy = barY;
            
            // Fondo del corazón (vacío - gris oscuro)
            gui.fill(hx, hy, hx + 8, hy + 8, 0xFF333333);
            
            // Corazón lleno o medio
            int heartStartHalf = i * 2;
            if (heartStartHalf < currentRedHalfHearts) {
                if (heartStartHalf + 1 < currentRedHalfHearts) {
                    // Corazón lleno - rojo
                    gui.fill(hx, hy, hx + 8, hy + 8, 0xFFCC0000);
                } else {
                    // Medio corazón
                    gui.fill(hx, hy, hx + 4, hy + 8, 0xFFCC0000);
                    gui.fill(hx + 4, hy, hx + 8, hy + 8, 0xFF333333);
                }
            }
        }
        
        x += maxRedHearts * 10;
        
        // Dibujar soul hearts (azules)
        for (int i = 0; i < maxSoulHearts; i++) {
            int hx = x + i * 10;
            int hy = barY;
            
            // Fondo
            gui.fill(hx, hy, hx + 8, hy + 8, 0xFF333333);
            
            // Soul heart - azul
            if (i * 2 < currentSoulHalfHearts) {
                if (i * 2 + 1 < currentSoulHalfHearts) {
                    gui.fill(hx, hy, hx + 8, hy + 8, 0xFF0066FF);
                } else {
                    gui.fill(hx, hy, hx + 4, hy + 8, 0xFF0066FF);
                    gui.fill(hx + 4, hy, hx + 8, hy + 8, 0xFF333333);
                }
            }
        }
        
        x += maxSoulHearts * 10;
        
        // Dibujar black hearts (gris oscuro con borde)
        for (int i = 0; i < maxBlackHearts; i++) {
            int hx = x + i * 10;
            int hy = barY;
            
            // Fondo
            gui.fill(hx, hy, hx + 8, hy + 8, 0xFF333333);
            
            // Black heart - gris muy oscuro
            if (i * 2 < currentBlackHalfHearts) {
                if (i * 2 + 1 < currentBlackHalfHearts) {
                    gui.fill(hx, hy, hx + 8, hy + 8, 0xFF444444);
                    // Borde para distinguir
                    gui.fill(hx, hy, hx + 8, hy + 1, 0xFF666666);
                    gui.fill(hx, hy, hx + 1, hy + 8, 0xFF666666);
                    gui.fill(hx + 7, hy, hx + 8, hy + 8, 0xFF666666);
                    gui.fill(hx, hy + 7, hx + 8, hy + 8, 0xFF666666);
                } else {
                    gui.fill(hx, hy, hx + 4, hy + 8, 0xFF444444);
                    gui.fill(hx + 4, hy, hx + 8, hy + 8, 0xFF333333);
                }
            }
        }
        
        // Mostrar vida numérica al final
        float totalMaxHearts = (attachment.getMaxHealth() + attachment.getMaxSoulHearts() + attachment.getMaxBlackHearts()) / 2f;
        gui.drawString(mc.font, String.format("%.1f/%.1f", playerHealth, totalMaxHearts), x + 5, barY + 1, 0xFFCC0000, false);
    }
    
    public static boolean isHudVisible() {
        return showHud;
    }
}