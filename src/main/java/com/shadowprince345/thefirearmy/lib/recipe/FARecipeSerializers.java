package com.shadowprince345.thefirearmy.lib.recipe;

import com.shadowprince345.thefirearmy.lib.recipe.fbb.ShapedFBBRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.RecipeSerializers;

public class FARecipeSerializers {
    public static final IRecipeSerializer<ShapedFBBRecipe> FBB = RecipeSerializers.register(new ShapedFBBRecipe.Serializer());
}
