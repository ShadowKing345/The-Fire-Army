package com.shadowprince345.thefirearmy.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.List;

public interface IFireBlacksmithBenchRecipe {
    List<ItemStack> getInputs();
    ItemStack getOutput(NonNullList<ItemStack> inputs);
    default int getCost(){
        return 0;
    }
}
