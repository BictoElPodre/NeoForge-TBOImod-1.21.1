package net.bictoelpodre.tboimod.datagen;

import net.bictoelpodre.tboimod.block.ModBlocks;
import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;


import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SULFURBLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModedItems.SULFURROCK.get())
                .unlockedBy("has_sulfurrock",has(ModedItems.SULFURROCK))
                .save(recipeOutput, "thebindingofisaacmod:sulfurblockfromsulfurrock");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModedItems.SULFURROCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S',ModedItems.SULFURDUST)
                .unlockedBy("has_sulfurdust",has(ModedItems.SULFURDUST))
                .save(recipeOutput, "thebindingofisaacmod:sulfurrockformsulfurdust");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModedItems.SULFURROCK.get(), 9)
                .requires(ModBlocks.SULFURBLOCK.get())
                .unlockedBy("has_sulfurblock",has(ModBlocks.SULFURBLOCK))
                .save(recipeOutput, "thebindingofisaacmod:sulfurrockfromsulfurblock");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModedItems.SULFURDUST.get(), 9)
                .requires(ModedItems.SULFURROCK.get())
                .unlockedBy("has_sulfurblock",has(ModedItems.SULFURROCK))
                .save(recipeOutput, "thebindingofisaacmod:sulfurdustfromsulfurrock");

    }
}
