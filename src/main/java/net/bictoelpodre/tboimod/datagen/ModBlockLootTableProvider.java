package net.bictoelpodre.tboimod.datagen;

import net.bictoelpodre.tboimod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super (Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.BASEMENTFLOOR.get());
        dropSelf(ModBlocks.CELLAR_FLOOR.get());
        dropSelf(ModBlocks.BASEMENTFLOOR.get());
        dropSelf(ModBlocks.BASEMENTFLOOR.get());
        dropSelf(ModBlocks.BASEMENTFLOOR.get());
        dropSelf(ModBlocks.BASEMENTFLOOR_STAIRS.get());
        dropSelf(ModBlocks.BASEMENTFLOOR_BUTTON.get());
        dropSelf(ModBlocks.BASEMENTFLOOR_FENCE.get());
        dropSelf(ModBlocks.BASEMENTFLOOR_FENCE_GATE.get());
        dropSelf(ModBlocks.BASEMENTFLOOR_WALL.get());
        dropSelf(ModBlocks.BASEMENTFLOOR_TRAPDOOR.get());
        dropSelf(ModBlocks.BASEMENTFLOOR_PRESSURE_PLATE.get());
        add(ModBlocks.BASEMENTFLOOR_DOOR.get(),
                block -> createDoorTable(ModBlocks.BASEMENTFLOOR_DOOR.get()));
        add(ModBlocks.BASEMENTFLOOR_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.BASEMENTFLOOR_SLAB.get()));
        dropSelf(ModBlocks.BASEMENTROCK.get());
        dropSelf(ModBlocks.SULFURBLOCK.get());


    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
