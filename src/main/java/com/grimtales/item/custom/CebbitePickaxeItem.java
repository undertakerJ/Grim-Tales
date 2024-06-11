package com.grimtales.item.custom;

import com.grimtales.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;


public class CebbitePickaxeItem extends PickaxeItem {
    public static final List<SlimeEntity> slimes = new ArrayList<>();

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos posClicked = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        int searchRadius = 8;
        for (int x = -searchRadius; x <= searchRadius; x++) {
            for (int y = -searchRadius; y <= searchRadius; y++) {
                for (int z = -searchRadius; z <= searchRadius; z++) {
                    BlockPos blockPos = posClicked.add(x, y, z);
                    BlockState state = context.getWorld().getBlockState(blockPos);
                    if (isValuableBlock(state)) {
                        highLightValuableBlocks(blockPos, player, state.getBlock());
                        if (!world.isClient) {
                            SlimeEntity slime = new SlimeEntity(EntityType.SLIME, world);
                            slime.setPosition(blockPos.getX() + 0.5, blockPos.getY() + 0.25, blockPos.getZ() + 0.5);
                            slime.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 15000000, 0, false, false, false));
                            slime.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 15000000, 0, false, false, false));
                            slime.setSilent(true);
                            slime.setAiDisabled(true);
                            slime.setNoGravity(true);
                            slime.setInvulnerable(true);
                            slime.setNoGravity(true);
                            slime.setSize(1, false);
                            slime.disableExperienceDropping();
                            slime.age = 0;
                            slime.setBodyYaw(0f);
                            slime.setHeadYaw(0f);
                            slime.setYaw(0f);
                            //slime.setGlowing(true);
                            Team team = getTeamColorBlock(world, state);
                            world.getScoreboard().addPlayerToTeam(slime.getEntityName(), team);
                            slimes.add(slime);
                            world.spawnEntity(slime);
                        }
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }


    private void highLightValuableBlocks(BlockPos blockPos, PlayerEntity player, Block block) {
        if (player.getWorld().isClient) {
            BlockState state = player.getWorld().getBlockState(blockPos);
            if (isValuableBlock(state)) {
                spawnParticleEffect(player.getWorld(), blockPos);
            }
        }
    }


    private void spawnParticleEffect(World world, BlockPos blockPos) {
        if (world.isClient) {
            world.addImportantParticle(ParticleTypes.SOUL_FIRE_FLAME, blockPos.getX() + 0.5, blockPos.getY() + 1.0, blockPos.getZ() + 0.5, 0, 0.05, 0);
        }
    }

    private static Team getTeamColorBlock(World world, BlockState state) {
        Team team = null;
        String name = null;
        Formatting color = null;

        if (state.isOf(Blocks.IRON_ORE) || state.isOf(Blocks.DEEPSLATE_IRON_ORE)) {
            name = "Iron";
            color = Formatting.DARK_GRAY;
        } else if (state.isOf(Blocks.COAL_ORE) || state.isOf(Blocks.DEEPSLATE_COAL_ORE)) {
            name = "Coal";
            color = Formatting.BLACK;
        } else if (state.isOf(Blocks.COPPER_ORE) || state.isOf(Blocks.DEEPSLATE_COPPER_ORE)) {
            name = "Copper";
            color = Formatting.byColorIndex(160);
        } else if (state.isOf(Blocks.LAPIS_ORE) || state.isOf(Blocks.DEEPSLATE_LAPIS_ORE)) {
            name = "Lapis";
            color = Formatting.DARK_BLUE;
        } else if (state.isOf(Blocks.GOLD_ORE) || state.isOf(Blocks.DEEPSLATE_GOLD_ORE)) {
            name = "Gold";
            color = Formatting.YELLOW;
        } else if (state.isOf(Blocks.DIAMOND_ORE) || state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)) {
            name = "Diamond";
            color = Formatting.AQUA;
        } else if (state.isOf(Blocks.EMERALD_ORE) || state.isOf(Blocks.DEEPSLATE_EMERALD_ORE)) {
            name = "Emerald";
            color = Formatting.DARK_GREEN;
        } else if (state.isOf(ModBlocks.CEBBITE_ORE) ||
                state.isOf(ModBlocks.DEEPSLATE_CEBBITE_ORE) ||
                state.isOf(ModBlocks.SCULK_CEBBITE_ORE)) {
            name = "Cebbite";
            color = Formatting.DARK_PURPLE;
        }

        team = world.getScoreboard().getPlayerTeam(name);
        if (team == null && name != null) {
            team = world.getScoreboard().addTeam(name);
            team.setColor(color);
        }

        return team;
    }

    private boolean isValuableBlock(BlockState state) {
        return state.isOf(Blocks.IRON_ORE) ||
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
                state.isOf(ModBlocks.SCULK_CEBBITE_ORE);
    }

    public CebbitePickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
