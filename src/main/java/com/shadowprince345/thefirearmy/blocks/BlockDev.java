package com.shadowprince345.thefirearmy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockDev extends Block {
    public BlockDev() {
        super(Block.Properties.create(Material.GROUND).sound(SoundType.STONE));
        setRegistryName("block_dev");
    }
}