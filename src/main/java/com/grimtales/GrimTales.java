package com.grimtales;

import com.grimtales.block.ModBlocks;
import com.grimtales.item.ModItemGroups;
import com.grimtales.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrimTales implements ModInitializer {
	public static final String MOD_ID = "grimtales";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
	}
}