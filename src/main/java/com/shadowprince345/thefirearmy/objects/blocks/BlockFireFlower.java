package com.shadowprince345.thefirearmy.objects.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockFireFlower extends BlockBushBase implements IShearable {

    public BlockFireFlower() {
        super("block_fire_flower");
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return net.minecraft.init.Items.BLAZE_POWDER;
    }


    @Override
    public boolean isShearable(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(Item.getItemFromBlock(Blocks.blockFireFlower)));
        return list;
    }
}
