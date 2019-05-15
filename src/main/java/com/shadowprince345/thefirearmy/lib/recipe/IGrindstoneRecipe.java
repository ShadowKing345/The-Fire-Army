package com.shadowprince345.thefirearmy.lib.recipe;

import net.minecraft.item.ItemStack;

public interface IGrindstoneRecipe {
    ItemStack getInput();
    ItemStack getOutput(ItemStack input);
    int getCost();
    int getTime();
    boolean matches(ItemStack input);
}
