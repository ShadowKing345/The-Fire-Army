package com.shadowprince345.thefirearmy.intergration.jei;

import com.shadowprince345.thefirearmy.api.recipe.IFBBRecipe;
import com.shadowprince345.thefirearmy.init.Blocks;
import com.shadowprince345.thefirearmy.lib.FBBRecipesManager;
import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class TFAJEIPlugin implements IModPlugin {
    public static IJeiHelpers jeiHelpers;
    public static IJeiRuntime jeiRuntime;

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new FBBRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        jeiHelpers = registry.getJeiHelpers();

        registry.handleRecipes(IFBBRecipe.class, FBBRecipeWrapper::new, RecipeCategoriesUid.FBB);

        registry.addRecipes(FBBRecipesManager.instance.getRecipes(), RecipeCategoriesUid.FBB);
        registry.addRecipeCategoryCraftingItem(new ItemStack(Blocks.blockFireBlacksmithFurnace),RecipeCategoriesUid.FBB);
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        this.jeiRuntime = jeiRuntime;
    }
}
