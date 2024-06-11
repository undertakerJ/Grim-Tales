package com.grimtales;

import com.grimtales.block.ModBlocks;
import com.grimtales.item.ModItemGroups;
import com.grimtales.item.ModItems;
import com.grimtales.item.custom.CebbitePickaxeItem;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.grimtales.item.custom.CebbitePickaxeItem.slimes;

public class GrimTales implements ModInitializer {
    public static final String MOD_ID = "grimtales";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        List<SlimeEntity> allSlimes = CebbitePickaxeItem.slimes;
        ServerTickEvents.END_SERVER_TICK.register((world) -> {
            for (int i = allSlimes.size() - 1; i >= 0; i--) {
                LivingEntity slime = allSlimes.get(i);
                int age = slime.age;
                if (age == 200) {
                    slimes.remove(i);
                    slime.remove(Entity.RemovalReason.DISCARDED);
                }
            }

        });




        ModItemGroups.registerItemGroups();
        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
    }
}