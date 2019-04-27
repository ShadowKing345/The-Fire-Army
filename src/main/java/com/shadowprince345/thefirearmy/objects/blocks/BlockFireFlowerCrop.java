package com.shadowprince345.thefirearmy.objects.blocks;

import com.shadowprince345.thefirearmy.init.Blocks;
import com.shadowprince345.thefirearmy.init.Items;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;

public class BlockFireFlowerCrop extends BlockCrops {

    public BlockFireFlowerCrop() {
        setUnlocalizedName("fire_flower_crop");
        setRegistryName("fire_flower_crop");
        setSoundType(SoundType.GLASS);
    }

    @Override
    protected Item getSeed() {
        return Items.itemFireFlowerSeed;
    }

    @Override
    protected Item getCrop() {
        return Item.getItemFromBlock(Blocks.blockFireFlower);
    }
}
