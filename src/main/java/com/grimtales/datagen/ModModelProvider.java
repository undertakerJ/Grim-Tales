package com.grimtales.datagen;

import com.grimtales.block.ModBlocks;
import com.grimtales.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CEBBITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CEBBITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SCULK_CEBBITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_CEBBITE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CEBBITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_CEBBITE_ORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CEBBITE_PICKAXE, Models.HANDHELD);

    }
}
