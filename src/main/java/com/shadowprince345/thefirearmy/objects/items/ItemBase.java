package com.shadowprince345.thefirearmy.objects.items;

import com.shadowprince345.thefirearmy.Main;
import com.shadowprince345.thefirearmy.utils.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
    public ItemBase(String registryName) {
        setRegistryName(registryName);
        setUnlocalizedName(registryName);
        setCreativeTab(CreativeTabs.TRANSPORTATION);

        Items.getItemList().add(this);
    }


    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
