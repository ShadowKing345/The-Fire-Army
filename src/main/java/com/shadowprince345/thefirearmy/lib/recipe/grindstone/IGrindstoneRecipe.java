package com.shadowprince345.thefirearmy.lib.recipe.grindstone;

import net.minecraft.item.crafting.IRecipe;

public interface IGrindstoneRecipe extends IRecipe {
    int getCost();
    int getTime();
}
