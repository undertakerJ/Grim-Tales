package com.grimtales.item.custom;

import com.grimtales.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.sound.Sound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.BlockEvent;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CebbitePickaxeItem extends PickaxeItem {

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()){
            BlockPos posClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;
            int x = posClicked.getX()+32;
            int z = posClicked.getZ()+32;

            for(int i = 0;i <= (posClicked.getY()+32); i++){
                BlockState state = context.getWorld().getBlockState(posClicked.add(x,i,z));

                if(isValuableBlock(state)){
                    highLightValuableBlocks(posClicked.add(x,i,z), player, state.getBlock());
                    foundBlock = true;
                    player.sendMessage(Text.literal("Found valuable blocks"));
                    break;

                }

            }

        }



        return ActionResult.SUCCESS;
    }
        private void highLightValuableBlocks(BlockPos blockPos, PlayerEntity player, Block block){
            if(player.getWorld().isClient){
                BlockState state = player.getWorld().getBlockState(blockPos);
                if (isValuableBlock(state)) {
                    spawnParticleEffect(player.getWorld(), blockPos);
                }

            }

        }
    private void spawnParticleEffect(World world, BlockPos blockPos) {
        // Choose your desired particle type
        world.playSound((PlayerEntity) world.getPlayers(),blockPos.getX(), blockPos.getY(),blockPos.getZ(),SoundEvents.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.BLOCKS, 1f,1f, 100);
        world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, blockPos.getX() + 0.5, blockPos.getY() + 1.0, blockPos.getZ() + 0.5, 4, 0.1, 4);

    }

        private boolean isValuableBlock(BlockState state){
            return state.isOf(Blocks.IRON_ORE) ||
                    state.isOf(Blocks.COAL_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_COAL_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_IRON_ORE) ||
                    state.isOf(Blocks.COPPER_ORE)  ||
                    state.isOf(Blocks.DEEPSLATE_COPPER_ORE) ||
                    state.isOf(Blocks.GOLD_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_GOLD_ORE) ||
                    state.isOf(Blocks.DIAMOND_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE) ||
                    state.isOf(Blocks.EMERALD_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_EMERALD_ORE) ||
                    state.isOf(ModBlocks.CEBBITE_ORE) ||
                    state.isOf(ModBlocks.DEEPSLATE_CEBBITE_ORE) ||
                    state.isOf(ModBlocks.SCULK_CEBBITE_ORE);
        }

    public CebbitePickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
