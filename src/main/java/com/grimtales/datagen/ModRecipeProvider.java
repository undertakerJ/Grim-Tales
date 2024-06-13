package com.grimtales.datagen;

import com.grimtales.block.ModBlocks;
import com.grimtales.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> CEBBITE_INGOT_SMELTABLE = List.of(ModItems.RAW_CEBBITE_ORE,
            ModBlocks.CEBBITE_ORE,
            ModBlocks.DEEPSLATE_CEBBITE_ORE,
            ModBlocks.SCULK_CEBBITE_ORE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, CEBBITE_INGOT_SMELTABLE, RecipeCategory.MISC, ModItems.CEBBITE_INGOT,
                0.7f, 200, "cebbite_ingot");
        offerBlasting(exporter, CEBBITE_INGOT_SMELTABLE, RecipeCategory.MISC, ModItems.CEBBITE_INGOT,
                0.7f, 100, "cebbite_ingot");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.CEBBITE_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.CEBBITE_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CEBBITE_PICKAXE, 1)
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .input('#', ModItems.CEBBITE_INGOT)
                .input('I', Items.STICK)
                .criterion(hasItem(ModItems.CEBBITE_INGOT), conditionsFromItem(ModItems.CEBBITE_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CEBBITE_PICKAXE)));

    }
}
