package com.shadowprince345.thefirearmy.objects.tiles;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.List;

public class FireBlacksmithFurnaceRecipes {
    public static final FireBlacksmithFurnaceRecipes INSTANCE = new FireBlacksmithFurnaceRecipes();

    public static final List<FireBlacksmithFurnaceRecipe> LIST = new ArrayList<>();

    public void add(ItemStack[] array, ItemStack output, int cost, int time){
        FireBlacksmithFurnaceRecipe recipe = new FireBlacksmithFurnaceRecipe();
        for(int i = 0; i < array.length; i++) {
            recipe.inputs.set(i, array[i]);
            if(i > 9) break;
        }

        recipe.output = output;
        recipe.cost = cost;
        recipe.time = time;

        LIST.add(recipe);
    }

    public boolean hasOutput(ItemStack[] inputs){
        return getRecipe(inputs) != null;
    }

    public FireBlacksmithFurnaceRecipe getRecipe(ItemStack[] inputs) {
        NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);

        for(int i = 0; i < inputs.length; i++) {
            items.set(i, inputs[i]);
            if(i > 9) break;
        }

        for (FireBlacksmithFurnaceRecipe recipe : LIST) {
            boolean flag = true;

            for(int i = 0; i < 9; i++) {
                Item item = items.get(i).getItem();
                int meta = !items.get(i).getHasSubtypes() && items.get(i).isItemStackDamageable() ? 0 : items.get(i).getMetadata();
                if (recipe.inputs.get(i).getItem() != item && recipe.inputs.get(i).getMetadata() == meta)
                    flag = false;
            }
            if(flag)
                return recipe;
        }

        return null;
    }

    public FireBlacksmithFurnaceRecipe getRecipe(ItemStack inputs) {

        for (FireBlacksmithFurnaceRecipe recipe : LIST) {

            Item item = inputs.getItem();
            int meta = !inputs.getHasSubtypes() && inputs.isItemStackDamageable() ? 0 : inputs.getMetadata();

            if (recipe.inputs.get(0).getItem() == item && recipe.inputs.get(0).getMetadata() == meta)
                return recipe;
        }

        return null;
    }

    public void addDefaultRecipes() {
        add(new ItemStack[]{
                        new ItemStack(Items.IRON_INGOT), new ItemStack(Items.IRON_INGOT)
                }
                , new ItemStack(Items.GOLD_INGOT), 20, 300);
    }
}
