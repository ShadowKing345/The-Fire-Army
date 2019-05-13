//package com.shadowprince345.thefirearmy.blocks.slabs;
//
//import net.minecraft.block.material.Material;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.item.Item;
//
//import java.util.Random;
//
//public class BlockDoubleSlabBase extends BlockSlabBase {
//    private BlockSlabBase slabVarient;
//
//    public BlockDoubleSlabBase(String registryName, Material material, BlockSlabBase slab) {
//        super(registryName + "_double", material);
//        slabVarient = slab;
//    }
//
//    @Override
//    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
//        return Item.getItemFromBlock(slabVarient);
//    }
//
//    @Override
//    public boolean isDouble() {
//        return true;
//    }
//}
