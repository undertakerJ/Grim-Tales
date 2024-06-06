package com.grimmtales.item;

import com.grimmtales.GrimmTales;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item CEBBITE_INGOT = registerItem("cebbite_ingot", new Item(new FabricItemSettings()));

    private static void addItemsToIngridientTabItemGroup(FabricItemGroupEntries entries){
        entries.add(CEBBITE_INGOT);
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(GrimmTales.MOD_ID, name), item);

    }
    public static void registerModItems(){
        GrimmTales.LOGGER.info("Registering Mod Items for " + GrimmTales.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngridientTabItemGroup);
    }
}
