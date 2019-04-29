package com.shadowprince345.thefirearmy.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.List;

public interface IFBBRecipe {
    List<ItemStack> getInputs();
    ItemStack getOutput();
    default int getCost(){
        return 0;
    }
    boolean matches(NonNullList<ItemStack> inputs);
}
