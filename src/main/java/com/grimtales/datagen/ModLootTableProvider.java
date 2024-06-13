package com.grimtales.datagen;

import com.grimtales.block.ModBlocks;
import com.grimtales.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.CEBBITE_BLOCK);

        addDrop(ModBlocks.CEBBITE_ORE, oreDrops(ModBlocks.CEBBITE_ORE, ModItems.RAW_CEBBITE_ORE));
        addDrop(ModBlocks.DEEPSLATE_CEBBITE_ORE, oreDrops(ModBlocks.DEEPSLATE_CEBBITE_ORE, ModItems.RAW_CEBBITE_ORE));
        addDrop(ModBlocks.SCULK_CEBBITE_ORE, oreDrops(ModBlocks.SCULK_CEBBITE_ORE, ModItems.RAW_CEBBITE_ORE));
    }
    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return BlockLootTableGenerator
                .dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this
                        .applyExplosionDecay(drop, ((LeafEntry.Builder) ItemEntry
                                .builder(item)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider.create(1.0f, 4.0f))))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
