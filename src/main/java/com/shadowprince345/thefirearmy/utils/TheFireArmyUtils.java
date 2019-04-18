package com.shadowprince345.thefirearmy.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;

public class TheFireArmyUtils {

    //Based on LavtvianModder Project EX fix
    public static ItemStack fixOutput(ItemStack stack){
        if(stack.isEmpty()) return ItemStack.EMPTY;

        ItemStack stack1 = ItemHandlerHelper.copyStackWithSize(stack, 1);
        if(!stack1.getHasSubtypes() && stack1.isItemStackDamageable())
            stack1.setItemDamage(0);

        return stack1;
    }
}
