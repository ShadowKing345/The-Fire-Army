package com.shadowprince345.thefirearmy.blocks;

import com.shadowprince345.thefirearmy.blocks.tiles.TEDev;
import com.shadowprince345.thefirearmy.client.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockDev extends Block {
    public BlockDev() {
        super(Material.GLASS);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote) return !playerIn.isSneaking();

        GuiHandler.open(playerIn, GuiHandler.GUI_DEV, pos.getX(), pos.getY(), pos.getZ());

        return !playerIn.isSneaking();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TEDev();
    }
}
