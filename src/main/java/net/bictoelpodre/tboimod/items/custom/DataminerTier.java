package net.bictoelpodre.tboimod.items.custom;

import net.bictoelpodre.tboimod.utils.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class DataminerTier {
    public static final Tier DATAMINER = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_DATAMINER,
            4, 2f, 0f,0, () -> Ingredient.of(Items.BEDROCK));
}
