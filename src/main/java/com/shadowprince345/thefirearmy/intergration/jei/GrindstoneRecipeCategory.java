package com.shadowprince345.thefirearmy.intergration.jei;

import com.shadowprince345.thefirearmy.TheFireArmy;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GrindstoneRecipeCategory implements IRecipeCategory<GrindstoneRecipeWrapper> {
    private final IDrawable background;
    private final String localizedName;
    private int xSize = 64;
    private int ySize = 48;

    public GrindstoneRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(new ResourceLocation(TheFireArmy.getModId(), "textures/gui/gui_grindstone.png"), 0, 0, xSize, ySize);
        localizedName = I18n.format("gui." + TheFireArmy.getModId() + ".grindstone.name");
    }

    @Override
    public String getUid() {
        return RecipeCategoriesUid.GRINDSTONE;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public String getModName() {
        return TheFireArmy.getModId();
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, GrindstoneRecipeWrapper wrapper, IIngredients iIngredients) {
        try{
            IGuiItemStackGroup group = iRecipeLayout.getItemStacks();
            group.init(0, false, 42, 16);
            group.init(1, true, 4, 16);
            group.set(iIngredients);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
