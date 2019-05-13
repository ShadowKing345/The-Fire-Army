package com.shadowprince345.thefirearmy.creativetab;

import com.shadowprince345.thefirearmy.init.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FAItemGroup extends ItemGroup {

    public FAItemGroup() {
        super( "firearmytab");
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(Blocks.blockDev);
    }

    @Override
    public void fill(NonNullList<ItemStack> items) {
        super.fill(items);
    }
}
