package com.shadowprince345.thefirearmy.lib;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.VanillaRecipeTypes;

import java.util.List;

public class Utils {
    public static FurnaceRecipe getFurnaceRecipe(ItemStack input, World world){
        List<FurnaceRecipe> recipes = world.getRecipeManager().getRecipes(VanillaRecipeTypes.SMELTING);
        for(FurnaceRecipe recipe : recipes){
            if(recipe.getIngredients().get(0).getMatchingStacks()[0].getItem() == input.getItem())
                return recipe;
        }
        return null;
    }
}
