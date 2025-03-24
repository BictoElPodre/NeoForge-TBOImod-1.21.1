package net.bictoelpodre.tboimod.block;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.block.custom.SulfurBlock;
import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS  =
            DeferredRegister.createBlocks(TheBindingOfIsaacMod.MOD_ID);

    public static final DeferredBlock<Block> BASEMENTFLOOR = registerBlock("basementfloor",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(-1F).requiresCorrectToolForDrops().sound(SoundType.MUD_BRICKS)));
    public static final DeferredBlock<Block> SULFURBLOCK = registerBlock("sulfurblock",
            () -> new SulfurBlock(BlockBehaviour.Properties.of()
                    .strength(1F).requiresCorrectToolForDrops().sound(SoundType.SAND)));
    public static final DeferredBlock<Block> BASEMENTROCK = registerBlock("basementrock",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(-1F).requiresCorrectToolForDrops().sound(SoundType.MUD_BRICKS)) {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.thebindingofisaacmod.basementrock.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModedItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
