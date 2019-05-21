package com.shadowprince345.thefirearmy.lib.recipe;

import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.lib.recipe.fbb.IFBBRecipe;
import com.shadowprince345.thefirearmy.lib.recipe.grindstone.IGrindstoneRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.RecipeType;

public class FARecipeTypes {
    public static RecipeType<IFBBRecipe> FIRE_BLACKSMITH_FURNACE;
    public static RecipeType<IGrindstoneRecipe> GRINDSTONE;

    public static void init() {
        FIRE_BLACKSMITH_FURNACE = RecipeType.get(new ResourceLocation(TheFireArmy.getModId(),"fire_blacksmith_furnace"), IFBBRecipe.class);
        GRINDSTONE = RecipeType.get(new ResourceLocation(TheFireArmy.getModId(),"grindstone"), IGrindstoneRecipe.class);
    }
}
