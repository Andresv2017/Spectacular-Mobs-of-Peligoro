package net.darkblade.smopmod.tags;


import net.darkblade.smopmod.SMOP;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> EGG_BLOCKS = tag("egg_blocks");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(SMOP.MOD_ID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(SMOP.MOD_ID, name));
        }

    }
}

