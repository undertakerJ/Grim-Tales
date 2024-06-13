package com.grimtales.datagen;

import com.grimtales.block.ModBlocks;
import com.grimtales.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Blocks.CEBBITE_PICKAXE_HIGHLIGHT_BLOCKS)
                .add(ModBlocks.CEBBITE_ORE)
                .add(ModBlocks.DEEPSLATE_CEBBITE_ORE)
                .add(ModBlocks.SCULK_CEBBITE_ORE)
                .forceAddTag(BlockTags.GOLD_ORES)
                .forceAddTag(BlockTags.COAL_ORES)
                .forceAddTag(BlockTags.COPPER_ORES)
                .forceAddTag(BlockTags.DIAMOND_ORES)
                .forceAddTag(BlockTags.EMERALD_ORES)
                .forceAddTag(BlockTags.REDSTONE_ORES)
                .forceAddTag(BlockTags.LAPIS_ORES)
                .forceAddTag(BlockTags.IRON_ORES);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.SCULK_CEBBITE_ORE)
                .add(ModBlocks.CEBBITE_ORE)
                .add(ModBlocks.CEBBITE_BLOCK)
                .add(ModBlocks.DEEPSLATE_CEBBITE_ORE);
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.CEBBITE_BLOCK)
        ;
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_4")))
                .add(ModBlocks.CEBBITE_ORE)
                .add(ModBlocks.DEEPSLATE_CEBBITE_ORE)
                .add(ModBlocks.SCULK_CEBBITE_ORE);
    }
}
