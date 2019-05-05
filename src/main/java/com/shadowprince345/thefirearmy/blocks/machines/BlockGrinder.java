package com.shadowprince345.thefirearmy.blocks.machines;

import com.shadowprince345.thefirearmy.blocks.tiles.TEGrinder;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockGrinder extends Block {

    public BlockGrinder() {
        super(Material.GROUND);
        setSoundType(SoundType.STONE);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TEGrinder();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add("The Class name is BlockGrinder");
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
