//package com.shadowprince345.thefirearmy.intergration.jei;
//
//import com.shadowprince345.thefirearmy.TheFireArmy;
//import com.shadowprince345.thefirearmy.client.gui.GuiFBB;
//import mezz.jei.api.IGuiHelper;
//import mezz.jei.api.gui.IDrawable;
//import mezz.jei.api.gui.IGuiItemStackGroup;
//import mezz.jei.api.gui.IRecipeLayout;
//import mezz.jei.api.ingredients.IIngredients;
//import mezz.jei.api.recipe.IRecipeCategory;
//import net.minecraft.client.resources.I18n;
//
//public class FBBRecipeCategory implements IRecipeCategory<FBBRecipeWrapper> {
//    private final IDrawable background;
//    private final String localizedName;
//    private int xSize = 128;
//    private int ySize = 66;
//
//    public FBBRecipeCategory(IGuiHelper helper) {
//        background = helper.createDrawable(GuiFBB.texture, 12,6, xSize, ySize);
//        localizedName = I18n.format("gui." + TheFireArmy.getModId() + ".fireblacksmithbench.name");
//    }
//
//    @Override
//    public String getUid() {
//        return RecipeCategoriesUid.fbb;
//    }
//
//    @Override
//    public String getTitle() {
//        return localizedName;
//    }
//
//    @Override
//    public String getModName() {
//        return TheFireArmy.getModId();
//    }
//
//    @Override
//    public IDrawable getBackground() {
//        return background;
//    }
//
//    @Override
//    public void setRecipe(IRecipeLayout iRecipeLayout, FBBRecipeWrapper wrapper, IIngredients iIngredients) {
//        try{
//            IGuiItemStackGroup stackGroup = iRecipeLayout.getItemStacks();
//            stackGroup.init(0, false, 105, 24);
//
//            for(int i = 0; i < 3; i ++){
//                for(int j = 0; j < 3; j++) {
//                    stackGroup.init(i * 3 + j + 1, true, j * 19 + 28, i * 19 + 5);
//                }
//            }
//
//            stackGroup.set(iIngredients);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
