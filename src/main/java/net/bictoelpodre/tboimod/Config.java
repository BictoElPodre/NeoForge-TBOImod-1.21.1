package net.bictoelpodre.tboimod;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = TheBindingOfIsaacMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    private static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    // a list of strings that are treated as resource locations for items
    private static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);

    // Character stat config fields
    public static ModConfigSpec.DoubleValue ISAAC_DAMAGE;
    public static ModConfigSpec.DoubleValue ISAAC_TEAR_RATE;
    public static ModConfigSpec.DoubleValue ISAAC_RANGE;
    public static ModConfigSpec.DoubleValue ISAAC_SHOT_SPEED;
    public static ModConfigSpec.DoubleValue ISAAC_SPEED;
    public static ModConfigSpec.DoubleValue ISAAC_LUCK;
    public static ModConfigSpec.IntValue ISAAC_MAX_HEALTH;

    public static ModConfigSpec.DoubleValue MAGDALENE_DAMAGE;
    public static ModConfigSpec.DoubleValue MAGDALENE_TEAR_RATE;
    public static ModConfigSpec.DoubleValue MAGDALENE_RANGE;
    public static ModConfigSpec.DoubleValue MAGDALENE_SHOT_SPEED;
    public static ModConfigSpec.DoubleValue MAGDALENE_SPEED;
    public static ModConfigSpec.DoubleValue MAGDALENE_LUCK;
    public static ModConfigSpec.IntValue MAGDALENE_MAX_HEALTH;

    public static ModConfigSpec.DoubleValue CAIN_DAMAGE;
    public static ModConfigSpec.DoubleValue CAIN_TEAR_RATE;
    public static ModConfigSpec.DoubleValue CAIN_RANGE;
    public static ModConfigSpec.DoubleValue CAIN_SHOT_SPEED;
    public static ModConfigSpec.DoubleValue CAIN_SPEED;
    public static ModConfigSpec.DoubleValue CAIN_LUCK;
    public static ModConfigSpec.IntValue CAIN_MAX_HEALTH;

    public static ModConfigSpec.DoubleValue JUDAS_DAMAGE;
    public static ModConfigSpec.DoubleValue JUDAS_TEAR_RATE;
    public static ModConfigSpec.DoubleValue JUDAS_RANGE;
    public static ModConfigSpec.DoubleValue JUDAS_SHOT_SPEED;
    public static ModConfigSpec.DoubleValue JUDAS_SPEED;
    public static ModConfigSpec.DoubleValue JUDAS_LUCK;
    public static ModConfigSpec.IntValue JUDAS_MAX_HEALTH;

    // Character stat configurations
    static {
        BUILDER.push("character_stats");

        // Isaac stats
        BUILDER.push("isaac");
        ISAAC_DAMAGE = BUILDER
                .comment("Isaac's base damage")
                .defineInRange("damage", 3.5, 0.0, 100.0);
        ISAAC_TEAR_RATE = BUILDER
                .comment("Isaac's base tear rate (tears per second)")
                .defineInRange("tearRate", 6.0, 0.1, 30.0);
        ISAAC_RANGE = BUILDER
                .comment("Isaac's base range")
                .defineInRange("range", 6.5, 0.5, 20.0);
        ISAAC_SHOT_SPEED = BUILDER
                .comment("Isaac's base shot speed")
                .defineInRange("shotSpeed", 1.0, 0.1, 5.0);
        ISAAC_SPEED = BUILDER
                .comment("Isaac's base speed")
                .defineInRange("speed", 1.0, 0.1, 3.0);
        ISAAC_LUCK = BUILDER
                .comment("Isaac's base luck")
                .defineInRange("luck", 0.0, -5.0, 10.0);
        ISAAC_MAX_HEALTH = BUILDER
                .comment("Isaac's max health (in half-hearts)")
                .defineInRange("maxHealth", 6, 1, 24);
        BUILDER.pop();

        // Magdalene stats
        BUILDER.push("magdalene");
        MAGDALENE_DAMAGE = BUILDER
                .comment("Magdalene's base damage")
                .defineInRange("damage", 3.5, 0.0, 100.0);
        MAGDALENE_TEAR_RATE = BUILDER
                .comment("Magdalene's base tear rate (tears per second)")
                .defineInRange("tearRate", 5.5, 0.1, 30.0);
        MAGDALENE_RANGE = BUILDER
                .comment("Magdalene's base range")
                .defineInRange("range", 6.5, 0.5, 20.0);
        MAGDALENE_SHOT_SPEED = BUILDER
                .comment("Magdalene's base shot speed")
                .defineInRange("shotSpeed", 0.9, 0.1, 5.0);
        MAGDALENE_SPEED = BUILDER
                .comment("Magdalene's base speed")
                .defineInRange("speed", 0.9, 0.1, 3.0);
        MAGDALENE_LUCK = BUILDER
                .comment("Magdalene's base luck")
                .defineInRange("luck", 0.0, -5.0, 10.0);
        MAGDALENE_MAX_HEALTH = BUILDER
                .comment("Magdalene's max health (in half-hearts)")
                .defineInRange("maxHealth", 8, 1, 24);
        BUILDER.pop();

        // Cain stats
        BUILDER.push("cain");
        CAIN_DAMAGE = BUILDER
                .comment("Cain's base damage")
                .defineInRange("damage", 3.5, 0.0, 100.0);
        CAIN_TEAR_RATE = BUILDER
                .comment("Cain's base tear rate (tears per second)")
                .defineInRange("tearRate", 6.0, 0.1, 30.0);
        CAIN_RANGE = BUILDER
                .comment("Cain's base range")
                .defineInRange("range", 6.5, 0.5, 20.0);
        CAIN_SHOT_SPEED = BUILDER
                .comment("Cain's base shot speed")
                .defineInRange("shotSpeed", 1.1, 0.1, 5.0);
        CAIN_SPEED = BUILDER
                .comment("Cain's base speed")
                .defineInRange("speed", 1.2, 0.1, 3.0);
        CAIN_LUCK = BUILDER
                .comment("Cain's base luck")
                .defineInRange("luck", 1.0, -5.0, 10.0);
        CAIN_MAX_HEALTH = BUILDER
                .comment("Cain's max health (in half-hearts)")
                .defineInRange("maxHealth", 4, 1, 24);
        BUILDER.pop();

        // Judas stats
        BUILDER.push("judas");
        JUDAS_DAMAGE = BUILDER
                .comment("Judas's base damage")
                .defineInRange("damage", 5.25, 0.0, 100.0);
        JUDAS_TEAR_RATE = BUILDER
                .comment("Judas's base tear rate (tears per second)")
                .defineInRange("tearRate", 6.0, 0.1, 30.0);
        JUDAS_RANGE = BUILDER
                .comment("Judas's base range")
                .defineInRange("range", 6.5, 0.5, 20.0);
        JUDAS_SHOT_SPEED = BUILDER
                .comment("Judas's base shot speed")
                .defineInRange("shotSpeed", 1.0, 0.1, 5.0);
        JUDAS_SPEED = BUILDER
                .comment("Judas's base speed")
                .defineInRange("speed", 1.0, 0.1, 3.0);
        JUDAS_LUCK = BUILDER
                .comment("Judas's base luck")
                .defineInRange("luck", 0.0, -5.0, 10.0);
        JUDAS_MAX_HEALTH = BUILDER
                .comment("Judas's max health (in half-hearts)")
                .defineInRange("maxHealth", 2, 1, 24);
        BUILDER.pop();

        BUILDER.pop(); // character_stats
    }

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean logDirtBlock;
    public static int magicNumber;
    public static String magicNumberIntroduction;
    public static Set<Item> items;

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        logDirtBlock = LOG_DIRT_BLOCK.get();
        magicNumber = MAGIC_NUMBER.get();
        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();

        // convert the list of strings into a set of items
        items = ITEM_STRINGS.get().stream()
                .map(itemName -> BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemName)))
                .collect(Collectors.toSet());
    }
}
