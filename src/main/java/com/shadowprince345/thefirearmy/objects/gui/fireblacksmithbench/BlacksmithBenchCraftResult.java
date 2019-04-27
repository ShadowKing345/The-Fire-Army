package com.shadowprince345.thefirearmy.objects.gui.fireblacksmithbench;

import com.shadowprince345.thefirearmy.objects.tiles.FireBlacksmithFurnaceRecipe;
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
