package com.shadowprince345.thefirearmy.objects.creativetab;

import com.shadowprince345.thefirearmy.init.Blocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {

    public CreativeTab() {
        super("firearmytab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Blocks.blockFireFlower);
    }
}
