//package com.shadowprince345.thefirearmy.intergration.jei;
//
//import com.shadowprince345.thefirearmy.lib.recipe.fbb.IFBBRecipe;
//import com.shadowprince345.thefirearmy.lib.recipe.grindstone.IGrindstoneRecipe;
//import com.shadowprince345.thefirearmy.client.gui.GuiFBB;
//import com.shadowprince345.thefirearmy.client.gui.GuiFBF;
//import com.shadowprince345.thefirearmy.client.gui.GuiFireFurnace;
//import com.shadowprince345.thefirearmy.init.Blocks;
//import com.shadowprince345.thefirearmy.lib.recipe.fbb.FBBRecipesManager;
//import com.shadowprince345.thefirearmy.lib.recipe.grindstone.GrindstoneRecipeManager;
//import mezz.jei.api.*;
//import mezz.jei.api.recipe.IRecipeCategoryRegistration;
//import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
//import net.minecraft.item.ItemStack;
//
//@JEIPlugin
//public class TFAJEIPlugin implements IModPlugin {
//    public static IJeiHelpers jeiHelpers;
//    public static IJeiRuntime jeiRuntime;
//
//    @Override
//    public void registerCategories(IRecipeCategoryRegistration registry) {
//        registry.addRecipeCategories(new FBBRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
//        registry.addRecipeCategories(new GrindstoneRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
//    }
//
//    @Override
//    public void register(IModRegistry registry) {
//        jeiHelpers = registry.getJeiHelpers();
//
//        registry.handleRecipes(IFBBRecipe.class, FBBRecipeWrapper::new, RecipeCategoriesUid.fbb);
//        registry.handleRecipes(IGrindstoneRecipe.class, GrindstoneRecipeWrapper::new, RecipeCategoriesUid.GRINDSTONE);
//
//        registry.addRecipeClickArea(GuiFBB.class, 99, 33, 15, 13, RecipeCategoriesUid.fbb);
//        registry.addRecipeClickArea(GuiFBF.class, 85, 32, 15, 13, VanillaRecipeCategoryUid.SMELTING);
//        registry.addRecipeClickArea(GuiFireFurnace.class, 85, 32, 15, 13, VanillaRecipeCategoryUid.SMELTING);
//
//        registry.addRecipes(FBBRecipesManager.instance.getRecipes(), RecipeCategoriesUid.fbb);
//        registry.addRecipes(GrindstoneRecipeManager.instance.getRecipes(), RecipeCategoriesUid.GRINDSTONE);
//
//        registry.addRecipeCategoryCraftingItem(new ItemStack(Blocks.blockFireBlacksmithFurnace), RecipeCategoriesUid.fbb);
//        registry.addRecipeCategoryCraftingItem(new ItemStack(Blocks.blockFireBlacksmithFurnace), VanillaRecipeCategoryUid.SMELTING);
//        registry.addRecipeCategoryCraftingItem(new ItemStack(Blocks.blockFireFurnace), VanillaRecipeCategoryUid.SMELTING);
//        registry.addRecipeCategoryCraftingItem(new ItemStack(Blocks.blockGrindstone), RecipeCategoriesUid.GRINDSTONE);
//    }
//
//    @Override
//    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
//        this.jeiRuntime = jeiRuntime;
//    }
//}
