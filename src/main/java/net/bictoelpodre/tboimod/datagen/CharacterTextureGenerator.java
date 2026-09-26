package net.bictoelpodre.tboimod.datagen;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.character.ModCharacter;
import net.bictoelpodre.tboimod.character.ModCharacters;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class CharacterTextureGenerator implements DataProvider {

    private final PackOutput packOutput;
    private final ExistingFileHelper existingFileHelper;

    public CharacterTextureGenerator(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        this.packOutput = packOutput;
        this.existingFileHelper = existingFileHelper;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        return CompletableFuture.runAsync(() -> {
            try {
                generateCharacterTextures();
            } catch (IOException e) {
                System.err.println("Failed to generate character textures: " + e.getMessage());
            }
        });
    }

    @Override
    public String getName() {
        return "Character Texture Generator";
    }

    private void generateCharacterTextures() throws IOException {
        // Initialize characters if not already done
        ModCharacters.init();
        
        Path outputDir = packOutput.getOutputFolder().resolve("assets/thebindingofisaacmod/textures/gui/characters");
        Files.createDirectories(outputDir);

        for (ModCharacter character : ModCharacters.getAllCharacters()) {
            Path filePath = outputDir.resolve(character.id + ".png");
            if (!Files.exists(filePath)) {
                createCharacterTexture(filePath, character);
            }
        }
    }

    private void createCharacterTexture(Path filePath, ModCharacter character) throws IOException {
        int size = 64;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Character-specific colors
        Color baseColor = getCharacterColor(character.id);
        Color accentColor = getAccentColor(character.id);

        // Background
        g2d.setColor(baseColor);
        g2d.fillRect(0, 0, size, size);

        // Draw character silhouette/initial
        g2d.setColor(accentColor);
        g2d.setFont(new Font("Arial", Font.BOLD, 28));
        String initial = character.id.substring(0, 1).toUpperCase();
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(initial);
        int textHeight = fm.getAscent();
        g2d.drawString(initial, (size - textWidth) / 2, (size + textHeight) / 2 - 4);

        // Border
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(1, 1, size - 2, size - 2);

        g2d.dispose();

        // Write PNG
        try (OutputStream out = Files.newOutputStream(filePath)) {
            javax.imageio.ImageIO.write(image, "PNG", out);
        }
    }

    private static Color getCharacterColor(String id) {
        return switch (id) {
            case "isaac" -> new Color(200, 220, 255);       // Light blue
            case "magdalene" -> new Color(255, 180, 200);   // Pink
            case "cain" -> new Color(255, 230, 120);        // Yellow
            case "judas" -> new Color(180, 120, 220);       // Purple
            case "blue_baby" -> new Color(100, 200, 255);   // Bright blue
            case "eve" -> new Color(120, 200, 120);         // Green
            case "samson" -> new Color(255, 120, 120);      // Red
            case "azazel" -> new Color(60, 40, 80);         // Dark purple
            case "lazarus" -> new Color(200, 180, 140);     // Tan
            case "eden" -> new Color(220, 220, 220);        // White/gray
            case "lost" -> new Color(180, 180, 255);        // Ghostly blue
            case "lilith" -> new Color(160, 100, 180);      // Dark pink
            case "keeper" -> new Color(200, 200, 100);      // Gold
            case "apollyon" -> new Color(100, 60, 120);     // Dark magenta
            case "the_forgotten" -> new Color(160, 140, 100); // Bone color
            case "bethany" -> new Color(255, 230, 255);     // Light pink
            case "jacob_esau" -> new Color(255, 160, 80);   // Orange
            default -> new Color(180, 180, 180);
        };
    }

    private static Color getAccentColor(String id) {
        return switch (id) {
            case "isaac" -> new Color(50, 100, 200);
            case "magdalene" -> new Color(200, 50, 100);
            case "cain" -> new Color(200, 180, 0);
            case "judas" -> new Color(120, 40, 180);
            case "blue_baby" -> new Color(0, 100, 200);
            case "eve" -> new Color(40, 150, 40);
            case "samson" -> new Color(200, 40, 40);
            case "azazel" -> new Color(200, 200, 255);
            case "lazarus" -> new Color(180, 120, 60);
            case "eden" -> new Color(80, 80, 80);
            case "lost" -> new Color(200, 200, 255);
            case "lilith" -> new Color(200, 50, 150);
            case "keeper" -> new Color(180, 160, 0);
            case "apollyon" -> new Color(200, 100, 200);
            case "the_forgotten" -> new Color(120, 100, 60);
            case "bethany" -> new Color(200, 150, 200);
            case "jacob_esau" -> new Color(200, 100, 40);
            default -> Color.BLACK;
        };
    }
}