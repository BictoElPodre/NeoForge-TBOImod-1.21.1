package net.bictoelpodre.tboimod.items;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.items.custom.AcidTearsItems;
import net.bictoelpodre.tboimod.items.custom.BombItem;
import net.minecraft.world.item.Item;
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
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SULFUR = ITEMS.register("sulfur",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BREAKFAST = ITEMS.register("breakfast",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SULFURDUST = ITEMS.register("sulfurdust",
            () -> new AcidTearsItems(new Item.Properties(),25,25));
    public static final DeferredItem<Item> SULFURROCK = ITEMS.register("sulfurrock",
            () -> new AcidTearsItems(new Item.Properties(),50,50));
    public static final DeferredItem<Item> BOMB = ITEMS.register("bomb",
            () -> new BombItem(new Item.Properties().stacksTo(100)));
    public static final DeferredItem<Item> SULFURICACID = ITEMS.register("sulfuricacid",
            () -> new AcidTearsItems(new Item.Properties(),100,100));
    public static final DeferredItem<Item> SULFURICDUST = ITEMS.register("sulfuricdust",
            () -> new AcidTearsItems(new Item.Properties(), 200, 200));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
