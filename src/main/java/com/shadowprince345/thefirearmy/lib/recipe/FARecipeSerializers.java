package com.shadowprince345.thefirearmy.lib.recipe;

import com.shadowprince345.thefirearmy.lib.recipe.fbb.ShapedFBBRecipe;
import com.shadowprince345.thefirearmy.lib.recipe.grindstone.GrindstoneRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.RecipeSerializers;

public class FARecipeSerializers {
    public static IRecipeSerializer<ShapedFBBRecipe> FBB;
    public static IRecipeSerializer<GrindstoneRecipe> GRINDSTONE;

    public static void init() {
       FBB = RecipeSerializers.register(new ShapedFBBRecipe.Serializer());
       GRINDSTONE = RecipeSerializers.register(new GrindstoneRecipe.Serializer());
    }
}
