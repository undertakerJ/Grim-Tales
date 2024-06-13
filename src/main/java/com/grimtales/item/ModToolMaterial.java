package com.grimtales.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterial implements ToolMaterial {

    CEBBITE(5, 2365, 25, 11f, 2f,
                    () ->
        Ingredient.ofItems(ModItems.CEBBITE_INGOT));


    private final int miningLevel;

    private final int itemDurability;

    private final int enchatability;

    private final float miningSpeed;

    private final float attackDamage;

    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(int miningLevel, int itemDurability, int enchatability, float miningSpeed, float attackDamage, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.enchatability = enchatability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchatability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

}
