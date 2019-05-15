package com.shadowprince345.thefirearmy.blocks.firetree;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockFireSapling extends BlockSapling implements IGrowable{

    public BlockFireSapling() {
        super(new FireTree(), Block.Properties.create(Material.GLASS).lightValue(1).sound(SoundType.GLASS));
        this.setDefaultState(this.stateContainer.getBaseState().with(STAGE, 0));
    }
}
