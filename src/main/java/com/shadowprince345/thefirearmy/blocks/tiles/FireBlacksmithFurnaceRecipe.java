package com.shadowprince345.thefirearmy.blocks.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemHandlerHelper;

public class FireBlacksmithFurnaceRecipe {
    NonNullList<ItemStack> inputs = NonNullList.withSize(9, ItemStack.EMPTY);
    ItemStack output = ItemStack.EMPTY;
    int cost = 0;

    public NonNullList<ItemStack> getInputs() {
        return inputs;
    }

    public void setInputs(NonNullList<ItemStack> inputs) {
        this.inputs = inputs;
    }

    public ItemStack getOutput() {
        return ItemHandlerHelper.copyStackWithSize(output, output.getCount());
    }

    public void setOutput(ItemStack output) {
        this.output = output;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
