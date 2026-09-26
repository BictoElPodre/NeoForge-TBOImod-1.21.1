package net.bictoelpodre.tboimod.screen;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.capability.CharacterCapability;
import net.bictoelpodre.tboimod.character.ModCharacter;
import net.bictoelpodre.tboimod.character.ModCharacters;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.bictoelpodre.tboimod.network.CharacterSelectHandler;
import net.bictoelpodre.tboimod.network.CharacterSelectHandler.CharacterSelectPacket;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

public class CharacterSelectionScreen extends Screen {
    private static final int CHARACTER_SIZE = 64;
    private static final int SPACING = 20;
    private static final int CHARS_PER_PAGE = 3; // 3 characters per page
    private static final int ARROW_BUTTON_SIZE = 32;

    private final List<ModCharacter> characters;
    private ModCharacter selectedCharacter;
    private int currentPage = 0;

    private Button leftArrowButton;
    private Button rightArrowButton;

    private static final String CHARACTER_NBT_KEY = "tboi_selected_character";

    public CharacterSelectionScreen() {
        super(Component.literal("Character Selection"));
        this.characters = ModCharacters.getUnlockedCharacters(getPlayer());
    }

    private Player getPlayer() {
        return Minecraft.getInstance().player;
    }

    private int getTotalPages() {
        return (int) Math.ceil((double) characters.size() / CHARS_PER_PAGE);
    }

    private int getPageStartIndex() {
        return currentPage * CHARS_PER_PAGE;
    }

    private int getPageEndIndex() {
        return Math.min(getPageStartIndex() + CHARS_PER_PAGE, characters.size());
    }

    private void updateArrowButtons() {
        if (leftArrowButton != null) {
            leftArrowButton.visible = currentPage > 0;
        }
        if (rightArrowButton != null) {
            rightArrowButton.visible = currentPage < getTotalPages() - 1;
        }
    }

    @Override
    protected void init() {
        super.init();
        
        // Left arrow button
        leftArrowButton = this.addRenderableWidget(Button.builder(
            Component.literal("<"),
            button -> {
                if (currentPage > 0) {
                    currentPage--;
                    updateArrowButtons();
                }
            }
        ).bounds(20, this.height / 2 - ARROW_BUTTON_SIZE / 2, ARROW_BUTTON_SIZE, ARROW_BUTTON_SIZE).build());

        // Right arrow button
        rightArrowButton = this.addRenderableWidget(Button.builder(
            Component.literal(">"),
            button -> {
                if (currentPage < getTotalPages() - 1) {
                    currentPage++;
                    updateArrowButtons();
                }
            }
        ).bounds(this.width - 20 - ARROW_BUTTON_SIZE, this.height / 2 - ARROW_BUTTON_SIZE / 2, ARROW_BUTTON_SIZE, ARROW_BUTTON_SIZE).build());

        // Back button (only button at bottom)
        this.addRenderableWidget(Button.builder(
            Component.translatable("gui.back"),
            button -> onClose()
        ).bounds(this.width / 2 - 100, this.height - 35, 200, 20).build());

        // Initial arrow visibility
        updateArrowButtons();

        // Load current selection from player capability
        var player = getPlayer();
        var attachment = player.getData(CharacterCapability.CHARACTER_STATS);
        if (attachment != null) {
            String savedId = attachment.getCharacterId();
            if (!savedId.isEmpty()) {
                this.selectedCharacter = ModCharacters.getById(savedId);
            } else {
                this.selectedCharacter = characters.isEmpty() ? ModCharacters.ISAAC_CHARACTER : characters.get(0);
            }
        } else {
            this.selectedCharacter = characters.isEmpty() ? ModCharacters.ISAAC_CHARACTER : characters.get(0);
        }
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Override to disable default blur - render solid dark background instead
        guiGraphics.fill(0, 0, this.width, this.height, 0xCC000000);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Title
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);

        // Page indicator
        String pageText = "Page " + (currentPage + 1) + " of " + getTotalPages();
        guiGraphics.drawCenteredString(this.font, pageText, this.width / 2, 40, 0xAAAAAA);

        // Render characters centered horizontally
        int startIdx = getPageStartIndex();
        int endIdx = getPageEndIndex();
        int count = endIdx - startIdx;
        
        // Calculate total width of character row
        int totalWidth = count * CHARACTER_SIZE + (count - 1) * SPACING;
        int startX = (this.width - totalWidth) / 2;
        int startY = (this.height - CHARACTER_SIZE) / 2 - 20; // Center vertically with offset for title
        
        for (int i = startIdx; i < endIdx; i++) {
            int pageIndex = i - startIdx;
            
            int x = startX + pageIndex * (CHARACTER_SIZE + SPACING);
            int y = startY;
            
            ModCharacter character = characters.get(i);
            boolean isSelected = character == selectedCharacter;
            boolean isHovered = mouseX >= x && mouseX <= x + CHARACTER_SIZE && 
                               mouseY >= y && mouseY <= y + CHARACTER_SIZE;

            // Background
            int bgColor = isSelected ? 0xFF55FF55 : (isHovered ? 0xFFFFFFFF : 0xFFAAAAAA);
            guiGraphics.fill(x - 2, y - 2, x + CHARACTER_SIZE + 2, y + CHARACTER_SIZE + 2, bgColor);
            guiGraphics.fill(x, y, x + CHARACTER_SIZE, y + CHARACTER_SIZE, isSelected ? 0xFF00AA00 : 0xFF333333);

            // Character portrait
            TextureManager textureManager = Minecraft.getInstance().getTextureManager();
            ResourceLocation texture = character.portraitTexture;
            try {
                textureManager.getTexture(texture);
                guiGraphics.blit(texture, x, y, 0, 0, CHARACTER_SIZE, CHARACTER_SIZE, CHARACTER_SIZE, CHARACTER_SIZE);
            } catch (Exception e) {
                guiGraphics.drawCenteredString(this.font, "?", x + CHARACTER_SIZE / 2, y + CHARACTER_SIZE / 2 - 4, 0xFFFFFF);
            }

            // Character name (below portrait)
            guiGraphics.drawCenteredString(this.font, character.displayName, x + CHARACTER_SIZE / 2, y + CHARACTER_SIZE + 5, 0xFFFFFF);

            // Stats preview on hover
            if (isHovered) {
                renderTooltip(guiGraphics, mouseX, mouseY, character);
            }
        }

        // Render buttons and tooltips (from parent)
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    private void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, ModCharacter character) {
        List<Component> tooltip = List.of(
            Component.literal("Damage: " + character.baseDamage),
            Component.literal("Tears: " + character.baseTearRate),
            Component.literal("Range: " + character.baseRange),
            Component.literal("Speed: " + character.baseSpeed),
            Component.literal("Luck: " + character.baseLuck),
            Component.literal("Health: " + (character.maxHealth / 2) + " hearts"),
            character.maxSoulHearts > 0 ? Component.literal("Soul Hearts: " + (character.maxSoulHearts / 2)) : Component.empty(),
            character.maxBlackHearts > 0 ? Component.literal("Black Hearts: " + (character.maxBlackHearts / 2)) : Component.empty()
        );
        
        guiGraphics.renderComponentTooltip(this.font, tooltip, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int startIdx = getPageStartIndex();
            int endIdx = getPageEndIndex();
            int count = endIdx - startIdx;
            
            int totalWidth = count * CHARACTER_SIZE + (count - 1) * SPACING;
            int startX = (this.width - totalWidth) / 2;
            int startY = (this.height - CHARACTER_SIZE) / 2 - 20;
            
            for (int i = startIdx; i < endIdx; i++) {
                int pageIndex = i - startIdx;
                
                int x = startX + pageIndex * (CHARACTER_SIZE + SPACING);
                int y = startY;
                
                if (mouseX >= x && mouseX <= x + CHARACTER_SIZE && 
                    mouseY >= y && mouseY <= y + CHARACTER_SIZE) {
                    this.selectedCharacter = characters.get(i);
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}