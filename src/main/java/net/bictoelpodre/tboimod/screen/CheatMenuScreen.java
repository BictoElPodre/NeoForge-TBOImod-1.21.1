package net.bictoelpodre.tboimod.screen;

import net.bictoelpodre.tboimod.capability.CharacterCapability;
import net.bictoelpodre.tboimod.character.ModCharacter;
import net.bictoelpodre.tboimod.client.key.KeyBinding;
import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CheatMenuScreen extends Screen {
    
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/cheat_menu_bg.png");
    
    private final Screen lastScreen;
    private List<StatEntry> statEntries = new ArrayList<>();
    private Button saveButton;
    private Button resetButton;
    private Button closeButton;
    
    // Scroll
    private int scrollOffset = 0;
    private int contentHeight = 0;
    private int maxVisibleEntries = 6; // Número de entradas visibles a la vez
    
    private static final int MENU_WIDTH = 340;
    private static final int MENU_HEIGHT = 300; // Reducido, ahora es scrollable
    private static final int ENTRY_HEIGHT = 32;
    private static final int START_Y = 40;
    private static final int LABEL_WIDTH = 140;
    private static final int INPUT_WIDTH = 100;
    private static final int GAP = 10;
    private static final int SCROLL_BAR_WIDTH = 8;
    
    public CheatMenuScreen(Screen lastScreen) {
        super(Component.literal("Cheat Menu - Edit Stats"));
        this.lastScreen = lastScreen;
    }
    
    @Override
    protected void init() {
        super.init();
        
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int menuX = centerX - MENU_WIDTH / 2;
        int menuY = centerY - MENU_HEIGHT / 2;
        
        // Obtener stats actuales del capability
        var mc = Minecraft.getInstance();
        var player = mc.player;
        var attachment = player != null ? player.getData(CharacterCapability.CHARACTER_STATS) : null;
        
        // Crear entradas para cada stat
        statEntries.clear();
        
        if (attachment != null) {
            // Mostrar los MISMOS valores que el HUD (calculados para Tears y Range)
            addStatEntry("Damage", attachment.getBaseDamage(), v -> attachment.setBaseDamage(v), null);
            // Tears: mostrar fire rate calculado (igual que HUD), editar base tear rate
            addStatEntry("Tears", ModCharacter.calculateFireRate(attachment.getBaseTearRate()), 
                v -> attachment.setBaseTearRate(reverseFireRate(v)), null);
            // Range: mostrar effective range calculado (igual que HUD), editar base range
            addStatEntry("Range", ModCharacter.calculateEffectiveRange(attachment.getBaseRange(), attachment.getBaseShotSpeed()), 
                v -> attachment.setBaseRange(reverseEffectiveRange(v, attachment.getBaseShotSpeed())), null);
            addStatEntry("Shot Speed", attachment.getBaseShotSpeed(), v -> attachment.setBaseShotSpeed(v), null);
            addStatEntry("Speed", attachment.getBaseSpeed(), v -> attachment.setBaseSpeed(v), null);
            addStatEntry("Luck", attachment.getBaseLuck(), v -> attachment.setBaseLuck(v), null);
            addStatEntry("Max Health", attachment.getMaxHealth() / 2f, v -> attachment.setMaxHealth((int)(v * 2)), null);
            addStatEntry("Max Soul Hearts", attachment.getMaxSoulHearts() / 2f, v -> attachment.setMaxSoulHearts((int)(v * 2)), null);
            addStatEntry("Max Black Hearts", attachment.getMaxBlackHearts() / 2f, v -> attachment.setMaxBlackHearts((int)(v * 2)), null);
        }
        
        // Calcular altura total del contenido
        contentHeight = statEntries.size() * ENTRY_HEIGHT + 20;
        
        // Botones (fijos en la parte inferior)
        saveButton = Button.builder(Component.literal("Save & Sync"), btn -> {
            saveAndSync();
            onClose();
        }).bounds(menuX + 20, menuY + MENU_HEIGHT - 40, 100, 20).build();
        
        resetButton = Button.builder(Component.literal("Reset to Default"), btn -> {
            resetToDefault();
        }).bounds(menuX + MENU_WIDTH - 120, menuY + MENU_HEIGHT - 40, 100, 20).build();
        
        closeButton = Button.builder(Component.literal("Close"), btn -> onClose()).bounds(centerX - 50, menuY + MENU_HEIGHT - 10, 100, 20).build();
        
        addRenderableWidget(saveButton);
        addRenderableWidget(resetButton);
        addRenderableWidget(closeButton);
        
        // Añadir EditBoxes
        for (StatEntry entry : statEntries) {
            addRenderableWidget(entry.editBox);
        }
        
        // Posicionar EditBoxes inicial
        repositionEntries(menuX, menuY);
    }
    
    private void addStatEntry(String label, float value, StatSetter setter, String calculatedDisplay) {
        StatEntry entry = new StatEntry(label, value, setter, calculatedDisplay);
        statEntries.add(entry);
    }
    
    private void repositionEntries(int menuX, int menuY) {
        int y = menuY + START_Y - scrollOffset;
        int labelX = menuX + 20;
        int inputX = labelX + LABEL_WIDTH + GAP;
        
        for (StatEntry entry : statEntries) {
            // Solo posicionar si está en el área visible (con margen)
            if (y + ENTRY_HEIGHT > menuY + START_Y - 10 && y < menuY + MENU_HEIGHT - 50) {
                entry.editBox.setPosition(inputX, y - 2);
                entry.editBox.visible = true;
            } else {
                entry.editBox.visible = false;
            }
            y += ENTRY_HEIGHT;
        }
    }
    
    private void saveAndSync() {
        for (StatEntry entry : statEntries) {
            entry.apply();
        }
        var mc = Minecraft.getInstance();
        var player = mc.player;
        if (player != null) {
            var attachment = player.getData(CharacterCapability.CHARACTER_STATS);
            if (attachment != null) {
                // El capability se guarda automáticamente via NBT
            }
        }
    }
    
    private void resetToDefault() {
        var mc = Minecraft.getInstance();
        var player = mc.player;
        if (player != null) {
            var attachment = player.getData(CharacterCapability.CHARACTER_STATS);
            if (attachment != null) {
                attachment.applyCharacter(net.bictoelpodre.tboimod.character.ModCharacters.ISAAC_CHARACTER);
                updateEditBoxes(attachment);
            }
        }
    }
    
    private void updateEditBoxes(CharacterCapability.ICharacterStats attachment) {
        int i = 0;
        statEntries.get(i++).editBox.setValue(String.format(Locale.US, "%.2f", attachment.getBaseDamage()));
        statEntries.get(i++).editBox.setValue(String.format(Locale.US, "%.2f", ModCharacter.calculateFireRate(attachment.getBaseTearRate())));
        statEntries.get(i++).editBox.setValue(String.format(Locale.US, "%.1f", ModCharacter.calculateEffectiveRange(attachment.getBaseRange(), attachment.getBaseShotSpeed())));
        statEntries.get(i++).editBox.setValue(String.format(Locale.US, "%.2f", attachment.getBaseShotSpeed()));
        statEntries.get(i++).editBox.setValue(String.format(Locale.US, "%.2f", attachment.getBaseSpeed()));
        statEntries.get(i++).editBox.setValue(String.format(Locale.US, "%.2f", attachment.getBaseLuck()));
        statEntries.get(i++).editBox.setValue(String.format(Locale.US, "%.1f", attachment.getMaxHealth() / 2f));
        statEntries.get(i++).editBox.setValue(String.format(Locale.US, "%.1f", attachment.getMaxSoulHearts() / 2f));
        statEntries.get(i++).editBox.setValue(String.format(Locale.US, "%.1f", attachment.getMaxBlackHearts() / 2f));
    }
    
    // Reverse formulas to convert calculated values back to base values
    private static float reverseFireRate(float fireRate) {
        // fireRate = 30 / (tearDelay + 1)
        // tearDelay = max(1, 16 - floor(tearRate))
        // We need to find tearRate that produces this fireRate
        // This is approximate since the formula uses floor
        if (fireRate <= 0) return 0;
        float tearDelay = (30f / fireRate) - 1f;
        // tearDelay = max(1, 16 - floor(tearRate))
        // So 16 - floor(tearRate) = tearDelay (if tearDelay >= 1)
        // floor(tearRate) = 16 - tearDelay
        // tearRate ≈ 16 - tearDelay
        float baseTearRate = 16f - tearDelay;
        return Math.max(0, baseTearRate);
    }
    
    private static float reverseEffectiveRange(float effectiveRange, float shotSpeed) {
        // Reverse of the new physics-based formula:
        // horizontalVelocity = shotSpeed * 1.5f
        // timeToGround = 100
        // drag = 0.99
        // maxLifetime = min(600, rangeStat * 20)
        // actualLifetime = min(maxLifetime, 100)
        // distance = horizontalVelocity * (1 - drag^actualLifetime) / (1 - drag)
        //
        // Reverse: given distance and shotSpeed, find rangeStat
        
        float horizontalVelocity = shotSpeed * 1.5f;
        float drag = 0.99f;
        int timeToGround = 100;
        float maxPossibleDistance = horizontalVelocity * (1f - (float)Math.pow(drag, timeToGround)) / (1f - drag);
        
        // If effectiveRange >= maxPossibleDistance, rangeStat >= 5.0 (range * 20 = 100)
        if (effectiveRange >= maxPossibleDistance) {
            return 5.0f; // rangeStat = 100/20 = 5.0 (capped by timeToGround)
        } else {
            // Inverse: distance = v * (1 - drag^t) / (1 - drag)
            // drag^t = 1 - distance * (1 - drag) / v
            // t = log(1 - distance * (1 - drag) / v) / log(drag)
            // where t = min(range * 20, 100)
            // So if t < 100: range = t / 20
            // If t = 100: range >= 5.0
            
            float ratio = 1f - effectiveRange * (1f - drag) / horizontalVelocity;
            if (ratio <= 0) return 5.0f; // capped
            float t = (float)(Math.log(ratio) / Math.log(drag));
            if (t >= 100) return 5.0f;
            return Math.max(0, t / 20f);
        }
    }
    
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int menuX = centerX - MENU_WIDTH / 2;
        int menuY = centerY - MENU_HEIGHT / 2;
        
        // Fondo del menú
        guiGraphics.fill(menuX, menuY, menuX + MENU_WIDTH, menuY + MENU_HEIGHT, 0xFF222222);
        guiGraphics.fill(menuX, menuY, menuX + MENU_WIDTH, menuY + 30, 0xFF333333);
        
        // Título
        guiGraphics.drawCenteredString(this.font, this.title, centerX, menuY + 8, 0xFFFFFFFF);
        
        // Área de contenido con clipping (scrollable)
        int contentX = menuX + 10;
        int contentY = menuY + START_Y;
        int contentWidth = MENU_WIDTH - 20;
        int contentVisibleHeight = MENU_HEIGHT - START_Y - 60; // Espacio para botones
        
        // Dibujar fondo del área scrollable
        guiGraphics.fill(contentX - 5, contentY - 5, contentX + contentWidth + 5, contentY + contentVisibleHeight + 5, 0xFF111111);
        
        // Renderizar labels de stats (con scroll offset)
        int y = contentY - scrollOffset;
        int labelX = menuX + 20;
        int inputX = labelX + LABEL_WIDTH + GAP;
        
        // Reposicionar EditBoxes según scroll
        for (StatEntry entry : statEntries) {
            // Label con color
            if (y + ENTRY_HEIGHT > contentY - 5 && y < contentY + contentVisibleHeight) {
                guiGraphics.drawString(this.font, entry.label, labelX, y + 5, 0xFFAAAAAA, false);
                
                // Mostrar valor calculado (si existe) junto al label
                if (entry.calculatedDisplay != null) {
                    int calcX = labelX + this.font.width(entry.label) + 8;
                    guiGraphics.drawString(this.font, "(" + entry.calculatedDisplay + ")", calcX, y + 5, 0xFF88FF88, false);
                }
                
                // Fondo del input
                guiGraphics.fill(inputX - 2, y - 2, inputX + INPUT_WIDTH + 2, y + 20, 0xFF111111);
                guiGraphics.fill(inputX - 2, y - 2, inputX + INPUT_WIDTH + 2, y - 1, 0xFF444444);
                guiGraphics.fill(inputX - 2, y + 19, inputX + INPUT_WIDTH + 2, y + 20, 0xFF444444);
                guiGraphics.fill(inputX - 2, y - 2, inputX - 1, y + 20, 0xFF444444);
                guiGraphics.fill(inputX + INPUT_WIDTH + 1, y - 2, inputX + INPUT_WIDTH + 2, y + 20, 0xFF444444);
                
                entry.editBox.setPosition(inputX, y - 2);
                entry.editBox.visible = true;
            } else {
                entry.editBox.visible = false;
            }
            y += ENTRY_HEIGHT;
        }
        
        // Scrollbar
        if (contentHeight > contentVisibleHeight) {
            int scrollBarX = menuX + MENU_WIDTH - SCROLL_BAR_WIDTH - 5;
            int scrollBarY = contentY;
            int scrollBarHeight = contentVisibleHeight;
            
            // Fondo scrollbar
            guiGraphics.fill(scrollBarX, scrollBarY, scrollBarX + SCROLL_BAR_WIDTH, scrollBarY + scrollBarHeight, 0xFF333333);
            
            // Thumb
            float visibleRatio = (float) contentVisibleHeight / contentHeight;
            int thumbHeight = Math.max(20, (int) (scrollBarHeight * visibleRatio));
            float scrollRatio = (float) scrollOffset / (contentHeight - contentVisibleHeight);
            int thumbY = scrollBarY + (int) ((scrollBarHeight - thumbHeight) * scrollRatio);
            
            guiGraphics.fill(scrollBarX + 1, thumbY, scrollBarX + SCROLL_BAR_WIDTH - 1, thumbY + thumbHeight, 0xFF888888);
        }
        
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
    
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double deltaX, double deltaY) {
        if (contentHeight > MENU_HEIGHT - START_Y - 60) {
            scrollOffset = (int) Math.max(0, Math.min(contentHeight - (MENU_HEIGHT - START_Y - 60), scrollOffset - deltaY * 20));
            repositionEntries(this.width / 2 - MENU_WIDTH / 2, this.height / 2 - MENU_HEIGHT / 2);
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, deltaX, deltaY);
    }
    
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (contentHeight > MENU_HEIGHT - START_Y - 60) {
            // Verificar si se arrastra en la scrollbar
            int menuX = this.width / 2 - MENU_WIDTH / 2;
            int menuY = this.height / 2 - MENU_HEIGHT / 2;
            int contentY = menuY + START_Y;
            int scrollBarX = menuX + MENU_WIDTH - SCROLL_BAR_WIDTH - 5;
            int scrollBarY = contentY;
            int scrollBarHeight = MENU_HEIGHT - START_Y - 60;
            
            if (mouseX >= scrollBarX && mouseX <= scrollBarX + SCROLL_BAR_WIDTH && 
                mouseY >= scrollBarY && mouseY <= scrollBarY + scrollBarHeight) {
                float visibleRatio = (float) scrollBarHeight / contentHeight;
                int thumbHeight = Math.max(20, (int) (scrollBarHeight * visibleRatio));
                float dragRatio = (float) (dragY / (scrollBarHeight - thumbHeight));
                scrollOffset = (int) Math.max(0, Math.min(contentHeight - scrollBarHeight, scrollOffset + dragRatio * (contentHeight - scrollBarHeight)));
                repositionEntries(this.width / 2 - MENU_WIDTH / 2, this.height / 2 - MENU_HEIGHT / 2);
                return true;
            }
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }
    
    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(lastScreen);
    }
    
    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // No llamar a super.renderBackground() para evitar el blur
        guiGraphics.fill(0, 0, this.width, this.height, 0xAA000000);
    }
    
    @Override
    public boolean isPauseScreen() {
        return false;
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Primero dejar que los widgets (EditBoxes, botones) manejen el click
        boolean handled = super.mouseClicked(mouseX, mouseY, button);
        return handled;
    }
    
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Escape SIEMPRE cierra la pantalla (prioridad máxima)
        if (keyCode == 256) { // Escape key
            onClose();
            return true;
        }
        
        // Primero dejar que los widgets (EditBoxes) manejen las teclas
        boolean handled = super.keyPressed(keyCode, scanCode, modifiers);
        return handled;
    }
    
    // Clase interna para cada entrada de stat
    private static class StatEntry {
        final String label;
        final EditBox editBox;
        final StatSetter setter;
        final String calculatedDisplay; // Valor calculado para mostrar (read-only)
        
        StatEntry(String label, float value, StatSetter setter, String calculatedDisplay) {
            this.label = label;
            this.setter = setter;
            this.calculatedDisplay = calculatedDisplay;
            this.editBox = new EditBox(Minecraft.getInstance().font, 0, 0, 100, 20, Component.literal(label));
            this.editBox.setValue(String.format(Locale.US, "%.2f", value));
            // Filtro muy permisivo - acepta tanto punto como coma decimal
            this.editBox.setFilter(s -> s.matches("[-.,]?\\d*[.,]?\\d*") || s.isEmpty() || s.equals("-") || s.equals(".") || s.equals(",") || s.equals("-."));
            this.editBox.setMaxLength(10);
            this.editBox.setTextColor(0xFFFFFFFF);
            this.editBox.setTextColorUneditable(0xFF888888);
        }
        
        void apply() {
            try {
                String text = editBox.getValue().trim().replace(',', '.'); // Normalizar coma a punto
                if (text.isEmpty() || text.equals("-") || text.equals(".") || text.equals("-.")) {
                    return; // No aplicar valores incompletos
                }
                float value = Float.parseFloat(text);
                setter.set(value);
            } catch (NumberFormatException ignored) {}
        }
    }
    
    @FunctionalInterface
    private interface StatSetter {
        void set(float value);
    }
}