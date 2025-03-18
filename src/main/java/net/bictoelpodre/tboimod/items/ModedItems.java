package net.bictoelpodre.tboimod.items;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
