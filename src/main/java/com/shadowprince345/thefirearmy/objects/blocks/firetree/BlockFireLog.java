package com.shadowprince345.thefirearmy.objects.blocks.firetree;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFireLog extends BlockLog {
    public BlockFireLog() {
        setDefaultState(this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y));
        setHardness(12.5f);
        setHarvestLevel("axe", 2);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getStateFromMeta(meta).withProperty(AXIS, facing.getAxis());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AXIS);
    }

    @Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return true;
    }

}
