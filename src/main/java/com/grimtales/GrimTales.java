package com.grimtales;

import com.grimtales.block.ModBlocks;
import com.grimtales.datagen.ModWorldGenerator;
import com.grimtales.item.ModItemGroups;
import com.grimtales.item.ModItems;
import com.grimtales.item.custom.CebbitePickaxeItem;
import com.grimtales.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.fabric.api.event.server.ServerStopCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.grimtales.item.custom.CebbitePickaxeItem.slimes;

public class GrimTales implements ModInitializer {
    public static final String MOD_ID = "grimtales";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    static List<SlimeEntity> allSlimes = CebbitePickaxeItem.slimes;;
    private static void removeAllSlimes() {
        for (LivingEntity slime : new ArrayList<>(allSlimes)) {
            slime.remove(Entity.RemovalReason.DISCARDED);
        }
        allSlimes.clear();
    }

    @Override
    public void onInitialize() {
        List<SlimeEntity> allSlimes1 = CebbitePickaxeItem.slimes;
        ServerTickEvents.END_SERVER_TICK.register((world) -> {
            for (int i = allSlimes1.size() - 1; i >= 0; i--) {
                LivingEntity slime = allSlimes1.get(i);
                int age = slime.age;
                if (age == 200) {
                    slimes.remove(i);
                    slime.remove(Entity.RemovalReason.DISCARDED);
                }
            }

        });
        ServerStartCallback.EVENT.register((world)->
        {       removeAllSlimes();
        });
        ServerStopCallback.EVENT.register((world) ->
                {
                    removeAllSlimes();
                });
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            BlockState state = world.getBlockState(pos);
            if (state.isOf(Blocks.IRON_ORE) ||
                    state.isOf(Blocks.COAL_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_COAL_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_IRON_ORE) ||
                    state.isOf(Blocks.COPPER_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_COPPER_ORE) ||
                    state.isOf(Blocks.GOLD_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_GOLD_ORE) ||
                    state.isOf(Blocks.DIAMOND_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE) ||
                    state.isOf(Blocks.EMERALD_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_EMERALD_ORE) ||
                    state.isOf(Blocks.LAPIS_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_LAPIS_ORE) ||
                    state.isOf(Blocks.REDSTONE_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_REDSTONE_ORE) ||
                    state.isOf(ModBlocks.CEBBITE_ORE) ||
                    state.isOf(ModBlocks.DEEPSLATE_CEBBITE_ORE) ||
                    state.isOf(ModBlocks.SCULK_CEBBITE_ORE)){
                removeAllSlimes();
            }
            return ActionResult.PASS;
        });

        ModItemGroups.registerItemGroups();
        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
        ModWorldGeneration.generateModWorldGen();
    }
}