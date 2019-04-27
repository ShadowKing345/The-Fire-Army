package com.shadowprince345.thefirearmy.objects.tiles;

import com.shadowprince345.thefirearmy.TheFireArmy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class FireBlacksmithFurnaceRecipes {
    public static final FireBlacksmithFurnaceRecipes INSTANCE = new FireBlacksmithFurnaceRecipes();

    public static final List<FireBlacksmithFurnaceRecipe> LIST = new ArrayList<>();

    public void addDefaultRecipes() {
        add("minecraft:iron_ingot, minecraft:iron_ingot",
                new ItemStack(com.shadowprince345.thefirearmy.init.Items.itemIronPlate),
                10);

        add("minecraft:gold_ingot, minecraft:gold_ingot",
                new ItemStack(com.shadowprince345.thefirearmy.init.Items.itemGoldPlate),
                10);

        add("firearmy:fire_plank,minecraft:leather,firearmy:fire_plank," +
                "firearmy:gold_plate, firearmy:fire_flower, firearmy:gold_plate," +
                "firearmy:fire_plank, firearmy:fire_plank, firearmy:fire_plank",
                new ItemStack(com.shadowprince345.thefirearmy.init.Blocks.blockFloorDrum), 100);

        add("firearmy:fire_flower", new ItemStack(com.shadowprince345.thefirearmy.init.Items.itemFireFlowerSeed), 200);
    }

    public boolean isOutput(ItemStack output){
        return getOutputList(output) != null;
    }

    public List<FireBlacksmithFurnaceRecipe> getOutputList(ItemStack output){
        List<FireBlacksmithFurnaceRecipe> result = new ArrayList<>();

        for(FireBlacksmithFurnaceRecipe recipe: LIST){
            Item item = output.getItem();
            int meta = !output.getHasSubtypes() && output.isItemStackDamageable() ? 0 : output.getMetadata();
            if (recipe.output.getItem() == item && recipe.output.getMetadata() == meta)
                result.add(recipe);
        }

        return result.isEmpty() ? null : result;
    }

    private void add(String inputs, ItemStack output, int cost){
        FireBlacksmithFurnaceRecipe recipe = new FireBlacksmithFurnaceRecipe();
        inputs = inputs.replace(" ", "");
        String[] s = inputs.split(",");

        for(int i  = 0 ; i < s.length; i ++) {
            Item input = Item.REGISTRY.getObject(new ResourceLocation(s[i]));

            if(input == null){
                TheFireArmy.logger.error("Unknown item '" + s[i] + "'. Ignoring Recipe");
                return;
            }else{
                recipe.inputs.set(i, new ItemStack(input));
            }

            if(i >= 8)
                break;
        }

        recipe.output = output;
        recipe.cost = cost;

        if(LIST.contains(recipe)){
            TheFireArmy.logger.warn("Recipe already exists. Ignoring creation of repeats");
            return;
        }
        LIST.add(recipe);
    }

    public void add(ItemStack[] array, ItemStack output, int cost){
        FireBlacksmithFurnaceRecipe recipe = new FireBlacksmithFurnaceRecipe();
        for(int i = 0; i < array.length; i++) {
            recipe.inputs.set(i, array[i]);
            if(i > 9) break;
        }

        recipe.output = output;
        recipe.cost = cost;


        if(LIST.contains(recipe)){
            TheFireArmy.logger.warn("Recipe already exists. Ignoring creation of repeats");
            return;
        }
        LIST.add(recipe);
    }

    public void add(ItemStack input, ItemStack output, int cost, int time){
        FireBlacksmithFurnaceRecipe recipe = new FireBlacksmithFurnaceRecipe();

        recipe.inputs.set(0, input);
        recipe.output = output;
        recipe.cost = cost;

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
}
