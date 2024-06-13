package com.grimtales.item;

import com.grimtales.GrimTales;
import com.grimtales.item.custom.CebbitePickaxeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item CEBBITE_INGOT = registerItem("cebbite_ingot", new Item(new FabricItemSettings()));
    public static final Item RAW_CEBBITE_ORE = registerItem("raw_cebbite_ore", new Item(new FabricItemSettings()));

    public static final Item CEBBITE_PICKAXE = registerItem("cebbite_pickaxe", new CebbitePickaxeItem(ToolMaterials.NETHERITE, 3, 1, new Item.Settings().maxDamage(-1)));






    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(GrimTales.MOD_ID, name), item);

    }
    public static void registerModItems(){
        GrimTales.LOGGER.info("Registering Mod Items for " + GrimTales.MOD_ID);

    }
}
