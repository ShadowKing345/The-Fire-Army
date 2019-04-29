package com.shadowprince345.thefirearmy.intergration.jei;

import com.shadowprince345.thefirearmy.api.recipe.IFBBRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import java.util.List;

public class FBBRecipeWrapper extends BlankRecipeWrapper {
    public final IFBBRecipe recipe;

    public FBBRecipeWrapper(IFBBRecipe recipe){
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        List<List<ItemStack>> list = TFAJEIPlugin.jeiHelpers.getStackHelper().expandRecipeItemStackInputs(recipe.getInputs());
        ingredients.setInputLists(VanillaTypes.ITEM, list);
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutput());
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return null;
    }

    @Override
    public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
        return false;
    }

    public static class Factory implements IRecipeWrapperFactory<IFBBRecipe>{

        @Override
        public IRecipeWrapper getRecipeWrapper(IFBBRecipe recipe) {
            return new FBBRecipeWrapper(recipe);
        }
    }
}
