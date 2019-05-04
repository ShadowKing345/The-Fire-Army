package com.shadowprince345.thefirearmy.api.recipe;

import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowking345
 * Holds every recipe and manager of recipe.
 * Use with caution.
 */

public class FBBRecipeApi {
    private static List<IFBBRecipeManager> MANAGERS = new ArrayList<>();
    private static List<IFBBRecipe> THE_LIST = new ArrayList<>();

    /**
     * Adds a {@link IFBBRecipeManager} to the list.
     * Automatically registers it to the event bus.
     * @param manager {@link IFBBRecipeManager}
     */
    public static void addManager(IFBBRecipeManager manager){
        MANAGERS.add(manager);
        MinecraftForge.EVENT_BUS.register(manager);
    }

    /**
     * Removes a {@link IFBBRecipeManager} to the list.
     * Unknown effects of unregister.
     * @param manager {@link IFBBRecipeManager}
     */
    public static void removeManager(IFBBRecipeManager manager){
        MANAGERS.remove(manager);
        MinecraftForge.EVENT_BUS.unregister(manager);
    }

    /**
     * Returns the whole list
     * @return {@link List<IFBBRecipeManager>}
     */
    public static List<IFBBRecipeManager> getManagers(){
        return MANAGERS;
    }

    /**
     * Adds a recipe to the list
     * @param recipe {@link IFBBRecipe}
     */
    public static void addRecipe(IFBBRecipe recipe){
            THE_LIST.add(recipe);
    }

    /**
     * Removes a recipe to the list
     * @param recipe {@link IFBBRecipe}
     */
    public static void removeRecipe(IFBBRecipe recipe){
            THE_LIST.remove(recipe);
    }

    /**
     * Returns the whole list
     * @return {@link List<IFBBRecipe>}
     */
    public static List<IFBBRecipe> getRecipes(){
        return THE_LIST;
    }
}
