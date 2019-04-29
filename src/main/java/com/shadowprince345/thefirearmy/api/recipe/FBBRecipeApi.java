package com.shadowprince345.thefirearmy.api.recipe;

import com.shadowprince345.thefirearmy.lib.FBBRecipesManager;

import java.util.ArrayList;
import java.util.List;

public class FBBRecipeApi {
    public static FBBRecipesManager registry = null;

    public static void addRecipe(IFBBRecipe recipe){
        if(registry != null)
            registry.add(recipe);
    }
    public static void removeRecipe(IFBBRecipe recipe){
        if(registry != null)
            registry.remove(recipe);
    }
    public static List<IFBBRecipe> getRecipes(){
        if(registry != null) return registry.getRecipes();

        return new ArrayList<>();
    }
}
