package com.shadowprince345.thefirearmy.lib;

import com.shadowprince345.thefirearmy.lib.recipe.IGrindstoneRecipe;
import net.minecraft.item.ItemStack;

public class GrindstoneRecipe implements IGrindstoneRecipe {
    ItemStack input;
    ItemStack output;
    int cost;
    int time;

    public GrindstoneRecipe(ItemStack input, ItemStack output, int cost, int time) {
        this.input = input;
        this.output = output;
        this.cost = cost <=  0 ? 1 : cost;
        this.time = time <=  0 ? 1 : time;
    }

    @Override
    public ItemStack getInput() {
        return input;
    }

    @Override
    public ItemStack getOutput(ItemStack input) {
        return output;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public boolean matches(ItemStack input) {
        return ItemStack.areItemsEqual(input, this.input);
    }
}
