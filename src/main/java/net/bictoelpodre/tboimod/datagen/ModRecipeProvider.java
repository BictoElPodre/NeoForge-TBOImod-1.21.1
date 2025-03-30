package net.bictoelpodre.tboimod.datagen;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.bictoelpodre.tboimod.block.ModBlocks;
import net.bictoelpodre.tboimod.items.ModedItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.level.ItemLike;


import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModedItems.MOMSBRA.get())
                .pattern("M M")
                .pattern("MMM")
                .define('M', ModedItems.MOMSESSENCE.get())
                .unlockedBy("has_momsessence",has(ModedItems.MOMSESSENCE))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModedItems.MOMSHEELS.get())
                .pattern("M M")
                .pattern("M M")
                .pattern("M M")
                .define('M', ModedItems.MOMSESSENCE.get())
                .unlockedBy("has_momsessence",has(ModedItems.MOMSESSENCE))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModedItems.MOMSUNDERWEAR.get())
                .pattern("M M")
                .pattern(" M ")
                .define('M', ModedItems.MOMSESSENCE.get())
                .unlockedBy("has_momsessence",has(ModedItems.MOMSESSENCE))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModedItems.MOMSWIG.get())
                .pattern("MMM")
                .pattern("M M")
                .define('M', ModedItems.MOMSESSENCE.get())
                .unlockedBy("has_momsessence",has(ModedItems.MOMSESSENCE))
                .save(recipeOutput);

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

        stairBuilder(ModBlocks.BASEMENTFLOOR_STAIRS.get(), Ingredient.of(ModBlocks.BASEMENTFLOOR)).group("bismuth")
                .unlockedBy("has_bismuth", has(ModBlocks.BASEMENTFLOOR)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BASEMENTFLOOR_SLAB.get(), ModBlocks.BASEMENTFLOOR.get());

        buttonBuilder(ModBlocks.BASEMENTFLOOR_BUTTON.get(), Ingredient.of(ModBlocks.BASEMENTFLOOR.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModBlocks.BASEMENTFLOOR.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.BASEMENTFLOOR_PRESSURE_PLATE.get(), ModBlocks.BASEMENTFLOOR.get());

        fenceBuilder(ModBlocks.BASEMENTFLOOR_FENCE.get(), Ingredient.of(ModBlocks.BASEMENTFLOOR.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModBlocks.BASEMENTFLOOR.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.BASEMENTFLOOR_FENCE_GATE.get(), Ingredient.of(ModBlocks.BASEMENTFLOOR.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModBlocks.BASEMENTFLOOR.get())).save(recipeOutput);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BASEMENTFLOOR_WALL.get(), ModBlocks.BASEMENTFLOOR.get());

        doorBuilder(ModBlocks.BASEMENTFLOOR_DOOR.get(), Ingredient.of(ModBlocks.BASEMENTFLOOR.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModBlocks.BASEMENTFLOOR.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.BASEMENTFLOOR_TRAPDOOR.get(), Ingredient.of(ModBlocks.BASEMENTFLOOR.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModBlocks.BASEMENTFLOOR.get())).save(recipeOutput);
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, TheBindingOfIsaacMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
