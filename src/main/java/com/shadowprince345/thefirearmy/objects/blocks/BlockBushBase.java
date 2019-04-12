package com.shadowprince345.thefirearmy.objects.blocks;

import com.shadowprince345.thefirearmy.Main;
import com.shadowprince345.thefirearmy.objects.creativetab.Tabs;
import com.shadowprince345.thefirearmy.objects.items.Items;
import com.shadowprince345.thefirearmy.utils.IHasModel;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBushBase extends BlockBush implements IHasModel {

    public BlockBushBase(String registryName) {
        setRegistryName(registryName);
        setUnlocalizedName(registryName);
        setCreativeTab(Tabs.tab);

        setSoundType(SoundType.GROUND);

        Blocks.getBlockList().add(this);
        Items.getItemList().add(new ItemBlock(this).setRegistryName(registryName).setUnlocalizedName(registryName));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
