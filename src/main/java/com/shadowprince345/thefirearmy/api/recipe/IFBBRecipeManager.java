package com.shadowprince345.thefirearmy.api.recipe;

import com.shadowprince345.thefirearmy.api.gui.ItemHandlerCrafting;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

/**
 * @author shadowking345
 */
public interface IFBBRecipeManager {

    /**
     * The method called during the IRecipe register event phase..
     * Should be used to load all default recipes.
     * @param event
     */
    @SubscribeEvent
    void event(RegistryEvent.Register<IRecipe> event);

    void add(IFBBRecipe recipe);
    public void remove(IFBBRecipe recipe);
    public List<IFBBRecipe> getRecipes();

    /**
     * Returns a {@link IFBBRecipe} given a condition is true.
     * @param craftingMatrix The crafting matrix or grid to be used.
     * @return {@link IFBBRecipe}
     */
    public IFBBRecipe findRecipe(ItemHandlerCrafting craftingMatrix);
}
