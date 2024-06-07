package com.grimtales.item;

import com.grimtales.GrimTales;
import com.grimtales.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup GRIMTALES_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(GrimTales.MOD_ID, "grimtales_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.grimtales_group"))
                    .icon(() -> new ItemStack(ModItems.CEBBITE_INGOT)).entries((displayContext, entries) -> {
                        //ITEMS
                        entries.add(ModItems.CEBBITE_INGOT);
                        //BLOCKS
                        entries.add(ModBlocks.CEBBITE_BLOCK);

                    }).build());
    public static void registerItemGroups(){
        GrimTales.LOGGER.info("Registering Item Groups for " + GrimTales.MOD_ID);
    }
}
