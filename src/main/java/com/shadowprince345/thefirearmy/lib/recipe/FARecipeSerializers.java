package com.shadowprince345.thefirearmy.lib.recipe;

import com.shadowprince345.thefirearmy.lib.recipe.fbb.ShapedFBBRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.RecipeSerializers;

public class FARecipeSerializers {
    public static IRecipeSerializer<ShapedFBBRecipe> FBB;

    public static void init() {
       FBB = RecipeSerializers.register(new ShapedFBBRecipe.Serializer());
    }
}
