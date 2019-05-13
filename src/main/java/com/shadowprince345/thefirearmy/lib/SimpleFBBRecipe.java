//package com.shadowprince345.thefirearmy.lib;
//
//import com.shadowprince345.thefirearmy.api.recipe.IFBBRecipe;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.NonNullList;
//import net.minecraftforge.items.ItemHandlerHelper;
//
//public class SimpleFBBRecipe implements IFBBRecipe {
//    NonNullList<ItemStack> inputs;
//    ItemStack output;
//    int cost;
//
//    public SimpleFBBRecipe(NonNullList<ItemStack> inputs, ItemStack output, int cost) {
//        this.inputs = inputs;
//        this.output = output;
//        this.cost = cost;
//    }
//
//    public NonNullList<ItemStack> getInputs() {
//        return inputs;
//    }
//
//    @Override
//    public ItemStack getOutput() {
//        return ItemHandlerHelper.copyStackWithSize(output, output.getCount());
//    }
//
//    public int getCost() {
//        return cost;
//    }
//
//    @Override
//    public boolean matches(NonNullList<ItemStack> inputs) {
//
//        for(int i  = 0; i < 9; i++){
//            if(!ItemStack.areItemsEqual(inputs.get(i), this.inputs.get(i)))
//                return false;
//        }
//        return true;
//    }
//}
