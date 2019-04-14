package com.shadowprince345.thefirearmy.objects.blocks.slabs;

import com.shadowprince345.thefirearmy.objects.creativetab.Tabs;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSlabBase extends BlockSlab{

    public BlockSlabBase(String registryName, Material material) {
        super(material);

        setRegistryName(registryName);
        setUnlocalizedName(registryName);
        setCreativeTab(Tabs.tab);

        IBlockState defaultState = this.blockState.getBaseState();
        if(!isDouble())
            defaultState.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        setDefaultState(defaultState);
        useNeighborBrightness = true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, HALF);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        if(isDouble())
            return getDefaultState().withProperty(HALF, EnumBlockHalf.values()[meta % EnumBlockHalf.values().length]);
        return this.getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        if(isDouble()) return 0;

        return state.getValue(HALF).ordinal() + 1;
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return super.getUnlocalizedName();
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return HALF;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return EnumBlockHalf.BOTTOM;
    }
}
