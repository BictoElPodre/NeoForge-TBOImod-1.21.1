package net.bictoelpodre.tboimod.datagen;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.block.ModBlocks;
import net.bictoelpodre.tboimod.utils.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TheBindingOfIsaacMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BASEMENTFLOOR.get())
                .add(ModBlocks.BASEMENTROCK.get())
                .add(ModBlocks.SULFURBLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SULFURBLOCK.get());

        tag(BlockTags.FENCES).add(ModBlocks.BASEMENTFLOOR_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.BASEMENTFLOOR_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.BASEMENTFLOOR_WALL.get());

        tag(ModTags.Blocks.NEEDS_DATA_MINER)
                .add(Blocks.BEDROCK)
                .addTags(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_DATAMINER)
                .addTags(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .remove(ModTags.Blocks.NEEDS_DATA_MINER);

    }
}
