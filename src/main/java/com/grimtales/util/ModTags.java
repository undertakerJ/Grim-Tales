package com.grimtales.util;

import com.grimtales.GrimTales;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> CEBBITE_PICKAXE_HIGHLIGHT_BLOCKS =
                createTag("cebbite_pickaxe_highlight_blocks");

        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(GrimTales.MOD_ID, name));
        }
    }
    public static class Items {
        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, new Identifier(GrimTales.MOD_ID, name));
        }
    }
}
