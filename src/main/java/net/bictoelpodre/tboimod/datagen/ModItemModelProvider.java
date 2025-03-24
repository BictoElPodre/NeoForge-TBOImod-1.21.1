package net.bictoelpodre.tboimod.datagen;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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
    }
}
