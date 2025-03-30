package net.bictoelpodre.tboimod.datagen;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.block.ModBlocks;
import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TheBindingOfIsaacMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        basicItem(ModedItems.SULFURDUST.get());
        basicItem(ModedItems.SULFURROCK.get());
        basicItem(ModedItems.SULFURICDUST.get());

        basicItem(ModedItems.BOMB.get());

        basicItem(ModedItems.BREAKFAST.get());
        basicItem(ModedItems.ROTTENMEAT.get());
        basicItem(ModedItems.FIREMIND.get());
        basicItem(ModedItems.ABAROFSOAP.get());
        basicItem(ModedItems.MOMSLIPSTICK.get());
        basicItem(ModedItems.SULFURICACID.get());

        basicItem(ModedItems.SULFUR.get());

        buttonItem(ModBlocks.BASEMENTFLOOR_BUTTON, ModBlocks.BASEMENTFLOOR);
        fenceItem(ModBlocks.BASEMENTFLOOR_FENCE, ModBlocks.BASEMENTFLOOR);
        wallItem(ModBlocks.BASEMENTFLOOR_WALL, ModBlocks.BASEMENTFLOOR);

        basicItem(ModBlocks.BASEMENTFLOOR_DOOR.asItem());

        handhelditem(ModedItems.DATAMINER);
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private ItemModelBuilder handhelditem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
