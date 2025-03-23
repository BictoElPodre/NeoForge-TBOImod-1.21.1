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
                        output.accept(ModedItems.SULFURICACID);
                        output.accept(ModedItems.BREAKFAST);
                        output.accept(ModedItems.ROTTENMEAT);
                        output.accept(ModedItems.FIREMIND);

                    }).build());

    public static final Supplier<CreativeModeTab> TBOIACTIVEITEMSTAB = CREATIVE_MODE_TAB.register("tboiactiveitemstab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModedItems.SULFUR.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "tboipassiveitemstab"))
                    .title(Component.translatable("creativetab.thebindingofisaacmod.tboiactiveitemstab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModedItems.SULFUR);

                    }).build());

    public static final Supplier<CreativeModeTab> TBOIFLOORSTAB = CREATIVE_MODE_TAB.register("tboifloorstab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BASEMENTFLOOR.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "tboiactiveitemstab"))
                    .title(Component.translatable("creativetab.thebindingofisaacmod.tboifloorstab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.BASEMENTFLOOR);
                        output.accept(ModBlocks.BASEMENTROCK);

                    }).build());

    public static final Supplier<CreativeModeTab> TBOIPICKUPSTAB = CREATIVE_MODE_TAB.register("tboipickupstab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModedItems.BOMB.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "tboifloorstab"))
                    .title(Component.translatable("creativetab.thebindingofisaacmod.tboipickupstab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModedItems.BOMB);

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
