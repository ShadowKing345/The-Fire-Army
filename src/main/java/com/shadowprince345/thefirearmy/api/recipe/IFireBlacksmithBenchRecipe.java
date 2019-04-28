package com.shadowprince345.thefirearmy.api.recipe;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface IFireBlacksmithBenchRecipe {
    List<ItemStack> getInputs();
    ItemStack getOutput();
}
