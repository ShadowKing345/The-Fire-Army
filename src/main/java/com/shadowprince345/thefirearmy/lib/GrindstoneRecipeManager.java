//package com.shadowprince345.thefirearmy.lib;
//
//import com.shadowprince345.thefirearmy.api.recipe.IGrindstoneRecipe;
//import com.shadowprince345.thefirearmy.init.Items;
//import net.minecraft.init.Blocks;
//import net.minecraft.item.ItemStack;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class GrindstoneRecipeManager {
//    public static GrindstoneRecipeManager instance = new GrindstoneRecipeManager();
//    private List<IGrindstoneRecipe> THE_LIST = new ArrayList<>();
//
//    public void add(ItemStack input, ItemStack output, int cost, int time){
//        add(new GrindstoneRecipe(input, output, cost, time));
//    }
//
//    public List<IGrindstoneRecipe> getRecipes(){
//        return THE_LIST;
//    }
//
//    public void add(IGrindstoneRecipe recipe){
//        THE_LIST.add(recipe);
//    }
//
//    public IGrindstoneRecipe findRecipe(ItemStack input){
//        for(IGrindstoneRecipe recipe: THE_LIST)
//            if(recipe.matches(input))
//                return recipe;
//
//        return null;
//    }
//
//    public void loadDefault(){
//        add(new ItemStack(Blocks.IRON_ORE), new ItemStack(Items.itemIronDust, 2), 2, 100);
//        add(new ItemStack(Blocks.GOLD_ORE), new ItemStack(Items.itemGoldDust, 2), 2, 100);
//        add(new ItemStack(Blocks.STONE, 1, 0), new ItemStack(Blocks.COBBLESTONE), 1, 200);
//        add(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.GRAVEL, 2), 1, 200);
//        add(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.SAND, 2), 1, 100);
//    }
//}
