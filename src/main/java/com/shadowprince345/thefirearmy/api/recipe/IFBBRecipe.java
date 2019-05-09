package com.shadowprince345.thefirearmy.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.List;

/**
 * @author shadowprince345
 */
public interface IFBBRecipe {

    /**
     * Returns the inputs stored in the recipe.
     * @return {@link List<ItemStack>} inputs
     */
    List<ItemStack> getInputs();

    /**
     * Returns the output of the recipe.
     * Note: Please return a copy of the output.
     * @return {@link ItemStack} output
     */
    ItemStack getOutput();

    /**
     * Returns the cost to create said item.
     * Default 0.
     * @return {@link Integer} cost.
     */
    default int getCost(){
        return 0;
    }

    /**
     * Checks if the given inputs matches the recipe's inputs.
     * @param inputs external inputs
     * @return {@link Boolean} condition based on if the inputs matches the outputs.
     */
    boolean matches(NonNullList<ItemStack> inputs);
}
