package com.shadowprince345.thefirearmy.objects.blocks;

import com.shadowprince345.thefirearmy.objects.creativetab.Tabs;
import net.minecraft.block.material.Material;

public class BlockDev extends BlockBase {
    public BlockDev(String registryName) {
        super(registryName,Material.GLASS);
        setCreativeTab(Tabs.tab);
    }
}
