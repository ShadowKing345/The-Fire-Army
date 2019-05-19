package com.shadowprince345.thefirearmy.lib.recipe;

import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.lib.recipe.fbb.IFBBRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.RecipeType;

public class FARecipeTypes {
    public static final RecipeType<IRecipe> FIRE_BLACKSMITH_FURNACE = RecipeType.get(new ResourceLocation(TheFireArmy.getModId(),"fire_blacksmith_furnace"), IFBBRecipe.class);
}
