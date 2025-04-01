package net.bictoelpodre.tboimod.items;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.items.custom.*;
import net.bictoelpodre.tboimod.utils.ModItemProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModedItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TheBindingOfIsaacMod.MOD_ID);

    public static final DeferredItem<Item> ABAROFSOAP = ITEMS.register("abarofsoap",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MOMSLIPSTICK = ITEMS.register("momslipstick",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FIREMIND = ITEMS.register("firemind",
            () -> new FuelItems(new Item.Properties(),Integer.MAX_VALUE));
    public static final DeferredItem<Item> BREAKFAST = ITEMS.register("breakfast",
            () -> new FoodIsDrink(new Item.Properties().food(ModFoodProperties.BREAKFAST)));
    public static final DeferredItem<Item> ROTTENMEAT = ITEMS.register("rottenmeat",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROTTENMEAT)));
    public static final DeferredItem<Item> SULFURICACID = ITEMS.register("sulfuricacid",
            () -> new AcidTearsItems(new Item.Properties(),100,100));
    public static final DeferredItem<Item> SULFUR = ITEMS.register("sulfur",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<ArmorItem> MOMSBRA = ITEMS.register("momsbra",
            () -> new ModArmorItem(ModArmorMaterials.MOMS_ESSENCE_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final DeferredItem<ArmorItem> MOMSUNDERWEAR = ITEMS.register("momsunderwear",
            () -> new ArmorItem(ModArmorMaterials.MOMS_ESSENCE_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final DeferredItem<ArmorItem> MOMSWIG = ITEMS.register("momswig",
            () -> new ArmorItem(ModArmorMaterials.MOMS_ESSENCE_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties()));
    public static final DeferredItem<ArmorItem> MOMSHEELS = ITEMS.register("momsheels",
            () -> new ArmorItem(ModArmorMaterials.MOMS_ESSENCE_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties()));

    public static final DeferredItem<Item> SULFURDUST = ITEMS.register("sulfurdust",
            () -> new AcidTearsItems(new Item.Properties(),25,25));
    public static final DeferredItem<Item> SULFURROCK = ITEMS.register("sulfurrock",
            () -> new AcidTearsItems(new Item.Properties(),50,50));
    public static final DeferredItem<Item> SULFURICDUST = ITEMS.register("sulfuricdust",
            () -> new AcidTearsItems(new Item.Properties(), 200, 200));
    public static final DeferredItem<Item> TEARS = ITEMS.register("tears",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MOMSESSENCE = ITEMS.register("momsessence",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BOMB = ITEMS.register("bomb",
            () -> new BombItem(new Item.Properties().stacksTo(100)));

    public static final DeferredItem<PickaxeItem> DATAMINER = ITEMS.register("dataminer",
            () -> new PickaxeItem(DataminerTier.DATAMINER, new Item.Properties().stacksTo(1)
                    .attributes(PickaxeItem.createAttributes(DataminerTier.DATAMINER, 0.5f, -6))));

    public static final DeferredItem<GlassCannonItem> GLASS_CANNON = ITEMS.register("glass_cannon",
            () -> new GlassCannonItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
