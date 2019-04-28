package com.shadowprince345.thefirearmy.inventory;

import com.shadowprince345.thefirearmy.blocks.tiles.FireBlacksmithFurnaceRecipe;
import net.minecraftforge.items.ItemStackHandler;

public class BlacksmithBenchCraftResult extends ItemStackHandler {
    private FireBlacksmithFurnaceRecipe recipe;

    public FireBlacksmithFurnaceRecipe getRecipe() {
        return recipe;
    }

    public void setRecipe(FireBlacksmithFurnaceRecipe recipe) {
        this.recipe = recipe;
    }
}
