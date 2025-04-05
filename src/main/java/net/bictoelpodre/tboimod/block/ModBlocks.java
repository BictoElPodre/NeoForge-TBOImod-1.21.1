package net.bictoelpodre.tboimod.block;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.block.custom.CellarFloor;
import net.bictoelpodre.tboimod.block.custom.LateralAngelicAltar;
import net.bictoelpodre.tboimod.block.custom.MainAngelicAltar;
import net.bictoelpodre.tboimod.block.custom.SulfurBlock;
import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
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
    public static final DeferredBlock<Block> CELLAR_FLOOR = registerBlock("cellar_floor",
            () -> new CellarFloor(BlockBehaviour.Properties.of()
                    .strength(1F).requiresCorrectToolForDrops().sound(SoundType.WOOD)));

    public static final DeferredBlock<StairBlock> BASEMENTFLOOR_STAIRS = registerBlock("basementfloor_stairs",
            () -> new StairBlock(ModBlocks.BASEMENTFLOOR.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(-1F).requiresCorrectToolForDrops().sound(SoundType.MUD_BRICKS)));
    public static final DeferredBlock<SlabBlock> BASEMENTFLOOR_SLAB = registerBlock("basementfloor_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(-1F).requiresCorrectToolForDrops().sound(SoundType.MUD_BRICKS)));
    public static final DeferredBlock<WallBlock> BASEMENTFLOOR_WALL = registerBlock("basementfloor_wall",
                () -> new WallBlock(BlockBehaviour.Properties.of().strength(-1F).requiresCorrectToolForDrops().sound(SoundType.MUD_BRICKS)));
    public static final DeferredBlock<PressurePlateBlock> BASEMENTFLOOR_PRESSURE_PLATE = registerBlock("basementfloor_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.of().strength(-1F).requiresCorrectToolForDrops().sound(SoundType.MUD_BRICKS)));
    public static final DeferredBlock<ButtonBlock> BASEMENTFLOOR_BUTTON = registerBlock("basementfloor_button",
            () -> new ButtonBlock(BlockSetType.STONE, 30, BlockBehaviour.Properties.of().strength(-1F).requiresCorrectToolForDrops().noCollission().sound(SoundType.MUD_BRICKS)));
    public static final DeferredBlock<FenceBlock> BASEMENTFLOOR_FENCE = registerBlock("basementfloor_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(-1F).requiresCorrectToolForDrops().sound(SoundType.MUD_BRICKS)));
    public static final DeferredBlock<FenceGateBlock> BASEMENTFLOOR_FENCE_GATE = registerBlock("basementfloor_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(-1F).requiresCorrectToolForDrops().sound(SoundType.MUD_BRICKS)));
    public static final DeferredBlock<DoorBlock> BASEMENTFLOOR_DOOR = registerBlock("basementfloor_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(-1F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.MUD_BRICKS)));
    public static final DeferredBlock<TrapDoorBlock> BASEMENTFLOOR_TRAPDOOR = registerBlock("basementfloor_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(-1F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.MUD_BRICKS)));

    public static final DeferredBlock<Block> SULFURBLOCK = registerBlock("sulfurblock",
            () -> new SulfurBlock(BlockBehaviour.Properties.of()
                    .strength(1F).requiresCorrectToolForDrops().sound(SoundType.SAND)));
    public static final DeferredBlock<Block> MAIN_ANGELIC_ALTAR = registerBlock("main_angelic_altar",
            () -> new MainAngelicAltar(BlockBehaviour.Properties.of().noOcclusion()
                    .strength(1F).requiresCorrectToolForDrops().sound(SoundType.SAND)));
    public static final DeferredBlock<Block> LATERAL_ANGELIC_ALTAR = registerBlock("lateral_angelic_altar",
            () -> new LateralAngelicAltar(BlockBehaviour.Properties.of().noOcclusion()
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
