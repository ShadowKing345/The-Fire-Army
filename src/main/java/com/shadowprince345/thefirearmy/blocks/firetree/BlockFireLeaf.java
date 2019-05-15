package com.shadowprince345.thefirearmy.blocks.firetree;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockFireLeaf extends BlockLeaves{

    public BlockFireLeaf() {
        super(Block.Properties.create(Material.GLASS).needsRandomTick().hardnessAndResistance(1f).lightValue(15).sound(SoundType.GLASS));
        this.setDefaultState(this.stateContainer.getBaseState().with(DISTANCE, 7).with(PERSISTENT, Boolean.TRUE));
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public boolean isFireSource(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public boolean isFlammable(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face) {
        return true;
    }

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
        if(worldIn.rand.nextInt(chance) == 0)
            spawnAsEntity(worldIn, pos, new ItemStack(net.minecraft.init.Items.BLAZE_POWDER));
    }
}
