package net.bictoelpodre.tboimod.datagen;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.DoorBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStatesProvider extends BlockStateProvider {
    public ModBlockStatesProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TheBindingOfIsaacMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BASEMENTFLOOR);
        blockWithItem(ModBlocks.BASEMENTROCK);

        stairsBlock(ModBlocks.BASEMENTFLOOR_STAIRS.get(), blockTexture(ModBlocks.BASEMENTFLOOR.get()));
        slabBlock(ModBlocks.BASEMENTFLOOR_SLAB.get(), blockTexture(ModBlocks.BASEMENTFLOOR.get()), blockTexture(ModBlocks.BASEMENTFLOOR.get()));
        buttonBlock(ModBlocks.BASEMENTFLOOR_BUTTON.get(), blockTexture(ModBlocks.BASEMENTFLOOR.get()));
        fenceGateBlock(ModBlocks.BASEMENTFLOOR_FENCE_GATE.get(), blockTexture(ModBlocks.BASEMENTFLOOR.get()));
        pressurePlateBlock(ModBlocks.BASEMENTFLOOR_PRESSURE_PLATE.get(), blockTexture(ModBlocks.BASEMENTFLOOR.get()));
        fenceBlock(ModBlocks.BASEMENTFLOOR_FENCE.get(), blockTexture(ModBlocks.BASEMENTFLOOR.get()));
        wallBlock(ModBlocks.BASEMENTFLOOR_WALL.get(), blockTexture(ModBlocks.BASEMENTFLOOR.get()));
        doorBlockWithRenderType(ModBlocks.BASEMENTFLOOR_DOOR.get(), modLoc("block/basementfloor_door_bottom"), modLoc("block/basementfloor_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.BASEMENTFLOOR_TRAPDOOR.get(), modLoc("block/basementfloor_trapdoor"), true, "cutout");

        blockItem(ModBlocks.BASEMENTFLOOR_STAIRS);
        blockItem(ModBlocks.BASEMENTFLOOR_SLAB);
        blockItem(ModBlocks.BASEMENTFLOOR_BUTTON);
        blockItem(ModBlocks.BASEMENTFLOOR_FENCE_GATE);
        blockItem(ModBlocks.BASEMENTFLOOR_TRAPDOOR, "_bottom");
        blockItem(ModBlocks.BASEMENTFLOOR_PRESSURE_PLATE);
    }

    private void doorBlock(DoorBlock block, ResourceLocation bottom, ResourceLocation top, String renderType) {

    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("thebindingofisaacmod:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("thebindingofisaacmod:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
