package com.shadowprince345.thefirearmy.lib.recipe.fbb;

import net.minecraft.item.crafting.IRecipe;

/**
 * @author shadowprince345
 */
public interface IFBBRecipe extends IRecipe {

    /**
     * Returns the cost to create said item.
     * Default 0.
     * @return {@link Integer} cost.
     */
    default int getCost(){
        return 0;
    }
}
