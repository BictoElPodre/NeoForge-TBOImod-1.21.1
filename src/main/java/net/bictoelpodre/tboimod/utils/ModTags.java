package net.bictoelpodre.tboimod.utils;

import net.bictoelpodre.tboimod.TheBindingOfIsaacMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTagKey(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> SULFURITEMS = createTagKey("sulfuritems");

        private static TagKey<Item> createTagKey(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(TheBindingOfIsaacMod.MOD_ID, name));
        }
    }
}
