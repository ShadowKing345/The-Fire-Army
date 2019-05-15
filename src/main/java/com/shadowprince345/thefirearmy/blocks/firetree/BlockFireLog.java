package com.shadowprince345.thefirearmy.blocks.firetree;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockFireLog extends BlockLog {
    public BlockFireLog() {
        super(MaterialColor.RED, Block.Properties.create(Material.GLASS).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.GLASS));
        setDefaultState(this.getDefaultState().with(AXIS, EnumFacing.Axis.Y));
    }

    @Override
    public boolean isFireSource(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public boolean isFlammable(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face) {
        return true;
    }
}
