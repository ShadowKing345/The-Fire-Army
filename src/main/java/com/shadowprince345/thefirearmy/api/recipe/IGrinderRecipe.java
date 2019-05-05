package com.shadowprince345.thefirearmy.api.recipe;

import net.minecraft.item.ItemStack;

public interface IGrinderRecipe {
    ItemStack getInput();
    ItemStack getOutput(ItemStack input);
    boolean matches(ItemStack input);
}
