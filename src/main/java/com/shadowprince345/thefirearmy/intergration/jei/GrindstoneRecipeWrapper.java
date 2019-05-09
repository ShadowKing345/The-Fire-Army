package com.shadowprince345.thefirearmy.intergration.jei;

import com.shadowprince345.thefirearmy.api.recipe.IGrindstoneRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.List;

public class GrindstoneRecipeWrapper implements IRecipeWrapper {

    public final IGrindstoneRecipe recipe;

    public GrindstoneRecipeWrapper(IGrindstoneRecipe recipe){
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM, recipe.getInput());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutput(null));
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        minecraft.fontRenderer.drawString("Time", 4, 0, Color.darkGray.getRGB());
        minecraft.fontRenderer.drawString(Integer.toString(recipe.getTime()), 4, 8, Color.gray.getRGB());
        minecraft.fontRenderer.drawString("Cost", 43, 0, Color.darkGray.getRGB());
        minecraft.fontRenderer.drawString(Integer.toString(recipe.getCost()), 43, 8, Color.gray.getRGB());
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return null;
    }

    @Override
    public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
        return false;
    }

    public static class Factory implements IRecipeWrapperFactory<IGrindstoneRecipe> {

        @Override
        public IRecipeWrapper getRecipeWrapper(IGrindstoneRecipe recipe) {
            return new GrindstoneRecipeWrapper(recipe);
        }
    }
}
