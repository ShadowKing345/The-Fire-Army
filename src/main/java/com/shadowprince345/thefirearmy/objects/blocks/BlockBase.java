package com.shadowprince345.thefirearmy.objects.blocks;

import com.shadowprince345.thefirearmy.Main;
import com.shadowprince345.thefirearmy.objects.items.Items;
import com.shadowprince345.thefirearmy.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {
    public BlockBase(String registryName, Material materialIn) {
        super(materialIn);
        setRegistryName(registryName);
        setUnlocalizedName(registryName);
        setCreativeTab(CreativeTabs.TRANSPORTATION);

        Blocks.getBlockList().add(this);
        Items.getItemList().add(new ItemBlock(this).setRegistryName(getRegistryName()).setUnlocalizedName(getUnlocalizedName()));
    }


    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
