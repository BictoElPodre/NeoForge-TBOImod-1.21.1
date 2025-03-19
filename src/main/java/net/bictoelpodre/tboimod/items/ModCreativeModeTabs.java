package net.bictoelpodre.tboimod.items;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheBindingOfIsaacMod.MOD_ID);

    public static final Supplier<CreativeModeTab> TBOIPASSIVEITEMSTAB = CREATIVE_MODE_TAB.register("tboipassiveitemstab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModedItems.MOMSLIPSTICK.get()))
                    .title(Component.translatable("creativetab.thebindingofisaacmod.tboipassiveitemstab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModedItems.MOMSLIPSTICK);
                        output.accept(ModedItems.ABAROFSOAP);

                    }).build());

    public static final Supplier<CreativeModeTab> TBOIFLOORSTAB = CREATIVE_MODE_TAB.register("tboifloorstab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BASEMENTFLOOR.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "tboipassiveitemstab"))
                    .title(Component.translatable("creativetab.thebindingofisaacmod.tboifloorstab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.BASEMENTFLOOR);

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
