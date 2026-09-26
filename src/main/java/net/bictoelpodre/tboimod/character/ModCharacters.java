package net.bictoelpodre.tboimod.character;

import net.bictoelpodre.tboimod.Config;
import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class ModCharacters {
    // Character IDs
    public static final String ISAAC = "isaac";
    public static final String MAGDALENE = "magdalene";
    public static final String CAIN = "cain";
    public static final String JUDAS = "judas";
    public static final String BLUE_BABY = "blue_baby";
    public static final String EVE = "eve";
    public static final String SAMSON = "samson";
    public static final String AZAZEL = "azazel";
    public static final String LAZARUS = "lazarus";
    public static final String EDEN = "eden";
    public static final String LOST = "lost";
    public static final String LILITH = "lilith";
    public static final String KEEPER = "keeper";
    public static final String APOLLYON = "apollyon";
    public static final String THE_FORGOTTEN = "the_forgotten";
    public static final String BETHANY = "bethany";
    public static final String JACOB_ESAU = "jacob_esau";

    // Character instances
    public static ModCharacter ISAAC_CHARACTER;
    public static ModCharacter MAGDALENE_CHARACTER;
    public static ModCharacter CAIN_CHARACTER;
    public static ModCharacter JUDAS_CHARACTER;
    public static ModCharacter BLUE_BABY_CHARACTER;
    public static ModCharacter EVE_CHARACTER;
    public static ModCharacter SAMSON_CHARACTER;
    public static ModCharacter AZAZEL_CHARACTER;
    public static ModCharacter LAZARUS_CHARACTER;
    public static ModCharacter EDEN_CHARACTER;
    public static ModCharacter LOST_CHARACTER;
    public static ModCharacter LILITH_CHARACTER;
    public static ModCharacter KEEPER_CHARACTER;
    public static ModCharacter APOLLYON_CHARACTER;
    public static ModCharacter THE_FORGOTTEN_CHARACTER;
    public static ModCharacter BETHANY_CHARACTER;
    public static ModCharacter JACOB_ESAU_CHARACTER;

    public static void init() {
        // ISAAC - Balanced starter
        ISAAC_CHARACTER = new ModCharacter.Builder(
            ISAAC, "Isaac",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/isaac.png")
        )
            .baseDamage(Config.ISAAC_DAMAGE.get().floatValue())
            .baseTearRate(Config.ISAAC_TEAR_RATE.get().floatValue())
            .baseRange(Config.ISAAC_RANGE.get().floatValue())
            .baseShotSpeed(Config.ISAAC_SHOT_SPEED.get().floatValue())
            .baseSpeed(Config.ISAAC_SPEED.get().floatValue())
            .baseLuck(Config.ISAAC_LUCK.get().floatValue())
            .maxHealth(Config.ISAAC_MAX_HEALTH.get())
            .startingItems(List.of(new ItemStack(ModedItems.TEARS.get())))
            .build();

        // MAGDALENE - High health, low damage/speed
        MAGDALENE_CHARACTER = new ModCharacter.Builder(
            MAGDALENE, "Magdalene",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/magdalene.png")
        )
            .baseDamage(Config.MAGDALENE_DAMAGE.get().floatValue())
            .baseTearRate(Config.MAGDALENE_TEAR_RATE.get().floatValue())
            .baseRange(Config.MAGDALENE_RANGE.get().floatValue())
            .baseShotSpeed(Config.MAGDALENE_SHOT_SPEED.get().floatValue())
            .baseSpeed(Config.MAGDALENE_SPEED.get().floatValue())
            .baseLuck(Config.MAGDALENE_LUCK.get().floatValue())
            .maxHealth(Config.MAGDALENE_MAX_HEALTH.get())
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get()),
                new ItemStack(ModedItems.BREAKFAST.get()) // Yum Heart equivalent
            ))
            .build();

        // CAIN - High luck, speed, one eye
        CAIN_CHARACTER = new ModCharacter.Builder(
            CAIN, "Cain",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/cain.png")
        )
            .baseDamage(Config.CAIN_DAMAGE.get().floatValue())
            .baseTearRate(Config.CAIN_TEAR_RATE.get().floatValue())
            .baseRange(Config.CAIN_RANGE.get().floatValue())
            .baseShotSpeed(Config.CAIN_SHOT_SPEED.get().floatValue())
            .baseSpeed(Config.CAIN_SPEED.get().floatValue())
            .baseLuck(Config.CAIN_LUCK.get().floatValue())
            .maxHealth(Config.CAIN_MAX_HEALTH.get())
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get()),
                new ItemStack(ModedItems.ROTTENMEAT.get()) // Lucky Foot equivalent
            ))
            .build();

        // JUDAS - High damage, low health
        JUDAS_CHARACTER = new ModCharacter.Builder(
            JUDAS, "Judas",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/judas.png")
        )
            .baseDamage(Config.JUDAS_DAMAGE.get().floatValue())
            .baseTearRate(Config.JUDAS_TEAR_RATE.get().floatValue())
            .baseRange(Config.JUDAS_RANGE.get().floatValue())
            .baseShotSpeed(Config.JUDAS_SHOT_SPEED.get().floatValue())
            .baseSpeed(Config.JUDAS_SPEED.get().floatValue())
            .baseLuck(Config.JUDAS_LUCK.get().floatValue())
            .maxHealth(Config.JUDAS_MAX_HEALTH.get())
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get()),
                new ItemStack(ModedItems.MOMSESSENCE.get()) // Book of Belial equivalent
            ))
            .build();

        // BLUE BABY (???) - Soul hearts only, flies
        BLUE_BABY_CHARACTER = new ModCharacter.Builder(
            BLUE_BABY, "???",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/blue_baby.png")
        )
            .baseDamage(3.5f)
            .baseTearRate(6f)
            .baseRange(6.5f)
            .baseShotSpeed(1f)
            .baseSpeed(1f)
            .baseLuck(0f)
            .maxHealth(0)
            .maxSoulHearts(6) // 3 soul hearts
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get()),
                new ItemStack(ModedItems.MOMSESSENCE.get()) // Poop equivalent
            ))
            .build();

        // EVE - Low stats, Whore of Babylon, Dead Bird
        EVE_CHARACTER = new ModCharacter.Builder(
            EVE, "Eve",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/eve.png")
        )
            .baseDamage(3.5f)
            .baseTearRate(5f)
            .baseRange(6.5f)
            .baseShotSpeed(0.9f)
            .baseSpeed(0.9f)
            .baseLuck(0f)
            .maxHealth(4)
            .maxSoulHearts(2)
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get()),
                new ItemStack(ModedItems.ROTTENMEAT.get()) // Dead Bird equivalent
            ))
            .build();

        // SAMSON - Bloody Lust, high damage when hurt
        SAMSON_CHARACTER = new ModCharacter.Builder(
            SAMSON, "Samson",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/samson.png")
        )
            .baseDamage(3.5f)
            .baseTearRate(6f)
            .baseRange(6.5f)
            .baseShotSpeed(1f)
            .baseSpeed(1f)
            .baseLuck(0f)
            .maxHealth(6)
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get()),
                new ItemStack(ModedItems.MOMSESSENCE.get()) // Bloody Lust equivalent
            ))
            .build();

        // AZAZEL - Short range, Brimstone, flight, no red hearts
        AZAZEL_CHARACTER = new ModCharacter.Builder(
            AZAZEL, "Azazel",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/azazel.png")
        )
            .baseDamage(3.5f)
            .baseTearRate(4f) // Slower for Brimstone charge
            .baseRange(3f) // Very short range
            .baseShotSpeed(1f)
            .baseSpeed(1f)
            .baseLuck(0f)
            .maxHealth(0)
            .maxBlackHearts(6) // 3 black hearts
            .startingItems(List.of(
                new ItemStack(ModedItems.FIREMIND.get()), // Brimstone equivalent
                new ItemStack(ModedItems.MOMSLIPSTICK.get()) // Flight
            ))
            .build();

        // LAZARUS - Two lives, random pill
        LAZARUS_CHARACTER = new ModCharacter.Builder(
            LAZARUS, "Lazarus",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/lazarus.png")
        )
            .baseDamage(3.5f)
            .baseTearRate(6f)
            .baseRange(6.5f)
            .baseShotSpeed(1f)
            .baseSpeed(1f)
            .baseLuck(0f)
            .maxHealth(6)
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get()),
                new ItemStack(ModedItems.ROTTENMEAT.get()) // Random pill
            ))
            .build();

        // EDEN - Random stats/items
        EDEN_CHARACTER = new ModCharacter.Builder(
            EDEN, "Eden",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/eden.png")
        )
            .baseDamage(3.5f)
            .baseTearRate(6f)
            .baseRange(6.5f)
            .baseShotSpeed(1f)
            .baseSpeed(1f)
            .baseLuck(0f)
            .maxHealth(6)
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get())
            ))
            .build();

        // THE LOST - One hit death, flight, spectral, holy mantle
        LOST_CHARACTER = new ModCharacter.Builder(
            LOST, "The Lost",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/lost.png")
        )
            .baseDamage(3.5f)
            .baseTearRate(6f)
            .baseRange(6.5f)
            .baseShotSpeed(1f)
            .baseSpeed(1f)
            .baseLuck(0f)
            .maxHealth(0)
            .maxSoulHearts(0)
            .maxBlackHearts(0)
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get()),
                new ItemStack(ModedItems.MOMSWIG.get()), // Holy Mantle
                new ItemStack(ModedItems.MOMSLIPSTICK.get()) // Flight
            ))
            .build();

        // LILITH - No tears, familiar (Incubus), friend box
        LILITH_CHARACTER = new ModCharacter.Builder(
            LILITH, "Lilith",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/lilith.png")
        )
            .baseDamage(0f) // No direct tears
            .baseTearRate(0f)
            .baseRange(6.5f)
            .baseShotSpeed(1f)
            .baseSpeed(1f)
            .baseLuck(0f)
            .maxHealth(4)
            .maxSoulHearts(2)
            .startingItems(List.of(
                new ItemStack(ModedItems.MOMSESSENCE.get()), // Incubus familiar
                new ItemStack(ModedItems.MOMSLIPSTICK.get()) // Friend Box
            ))
            .build();

        // KEEPER - Coins as health, triple shot, wooden nickel
        KEEPER_CHARACTER = new ModCharacter.Builder(
            KEEPER, "Keeper",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/keeper.png")
        )
            .baseDamage(3.5f)
            .baseTearRate(6f)
            .baseRange(6.5f)
            .baseShotSpeed(1f)
            .baseSpeed(1f)
            .baseLuck(0f)
            .maxHealth(0)
            .maxBlackHearts(4) // 2 coin hearts
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get()),
                new ItemStack(ModedItems.MOMSESSENCE.get()) // Wooden Nickel
            ))
            .build();

        // APOLLYON - Void, active item absorption
        APOLLYON_CHARACTER = new ModCharacter.Builder(
            APOLLYON, "Apollyon",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/apollyon.png")
        )
            .baseDamage(3.5f)
            .baseTearRate(6f)
            .baseRange(6.5f)
            .baseShotSpeed(1f)
            .baseSpeed(1f)
            .baseLuck(0f)
            .maxHealth(6)
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get()),
                new ItemStack(ModedItems.MOMSESSENCE.get()) // Void
            ))
            .build();

        // THE FORGOTTEN - Bone club, spectral, charge attack
        THE_FORGOTTEN_CHARACTER = new ModCharacter.Builder(
            THE_FORGOTTEN, "The Forgotten",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/forgotten.png")
        )
            .baseDamage(3.5f)
            .baseTearRate(5f)
            .baseRange(4f)
            .baseShotSpeed(1f)
            .baseSpeed(1f)
            .baseLuck(0f)
            .maxHealth(0)
            .maxBlackHearts(4)
            .startingItems(List.of(
                new ItemStack(ModedItems.DATAMINER.get()), // Bone Club equivalent
                new ItemStack(ModedItems.MOMSESSENCE.get()) // Soul
            ))
            .build();

        // BETHANY - Book of Virtues, soul charge
        BETHANY_CHARACTER = new ModCharacter.Builder(
            BETHANY, "Bethany",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/bethany.png")
        )
            .baseDamage(3.5f)
            .baseTearRate(6f)
            .baseRange(6.5f)
            .baseShotSpeed(1f)
            .baseSpeed(1f)
            .baseLuck(0f)
            .maxHealth(4)
            .maxSoulHearts(4)
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get()),
                new ItemStack(ModedItems.MOMSESSENCE.get()) // Book of Virtues
            ))
            .build();

        // JACOB & ESAU - Two characters
        JACOB_ESAU_CHARACTER = new ModCharacter.Builder(
            JACOB_ESAU, "Jacob & Esau",
            ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "textures/gui/characters/jacob_esau.png")
        )
            .baseDamage(3.5f)
            .baseTearRate(6f)
            .baseRange(6.5f)
            .baseShotSpeed(1f)
            .baseSpeed(1f)
            .baseLuck(0f)
            .maxHealth(6)
            .startingItems(List.of(
                new ItemStack(ModedItems.TEARS.get()),
                new ItemStack(ModedItems.TEARS.get()) // Two sets
            ))
            .build();
    }

    public static ModCharacter getById(String id) {
        return switch (id) {
            case ISAAC -> ISAAC_CHARACTER;
            case MAGDALENE -> MAGDALENE_CHARACTER;
            case CAIN -> CAIN_CHARACTER;
            case JUDAS -> JUDAS_CHARACTER;
            case BLUE_BABY -> BLUE_BABY_CHARACTER;
            case EVE -> EVE_CHARACTER;
            case SAMSON -> SAMSON_CHARACTER;
            case AZAZEL -> AZAZEL_CHARACTER;
            case LAZARUS -> LAZARUS_CHARACTER;
            case EDEN -> EDEN_CHARACTER;
            case LOST -> LOST_CHARACTER;
            case LILITH -> LILITH_CHARACTER;
            case KEEPER -> KEEPER_CHARACTER;
            case APOLLYON -> APOLLYON_CHARACTER;
            case THE_FORGOTTEN -> THE_FORGOTTEN_CHARACTER;
            case BETHANY -> BETHANY_CHARACTER;
            case JACOB_ESAU -> JACOB_ESAU_CHARACTER;
            default -> ISAAC_CHARACTER;
        };
    }

    public static List<ModCharacter> getAllCharacters() {
        return List.of(
            ISAAC_CHARACTER, MAGDALENE_CHARACTER, CAIN_CHARACTER, JUDAS_CHARACTER,
            BLUE_BABY_CHARACTER, EVE_CHARACTER, SAMSON_CHARACTER, AZAZEL_CHARACTER,
            LAZARUS_CHARACTER, EDEN_CHARACTER, LOST_CHARACTER, LILITH_CHARACTER,
            KEEPER_CHARACTER, APOLLYON_CHARACTER, THE_FORGOTTEN_CHARACTER,
            BETHANY_CHARACTER, JACOB_ESAU_CHARACTER
        );
    }

    public static List<ModCharacter> getUnlockedCharacters(net.minecraft.world.entity.player.Player player) {
        // For now, return all. Later check unlock conditions.
        return getAllCharacters();
    }
}