package com.shadowprince345.thefirearmy.lib;

import com.shadowprince345.thefirearmy.api.recipe.IGrinderRecipe;
import net.minecraft.item.ItemStack;

public class GrinderRecipe implements IGrinderRecipe {
    ItemStack input = ItemStack.EMPTY;
    ItemStack output = ItemStack.EMPTY;

    @Override
    public ItemStack getInput() {
        return input;
    }

    @Override
    public ItemStack getOutput(ItemStack input) {
        return output;
    }

    @Override
    public boolean matches(ItemStack input) {
        return ItemStack.areItemsEqual(input, this.input);
    }
}
