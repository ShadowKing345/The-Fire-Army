package com.shadowprince345.thefirearmy.blocks;

import com.shadowprince345.thefirearmy.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Particles;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockFireFlower extends BlockBush implements IShearable{

    public BlockFireFlower() {
        super(Block.Properties.create(Material.GLASS).lightValue(3).sound(SoundType.GLASS).doesNotBlockMovement());
    }

    @Override
    public IItemProvider getItemDropped(IBlockState p_199769_1_, World p_199769_2_, BlockPos p_199769_3_, int p_199769_4_) {
        return net.minecraft.init.Items.BLAZE_POWDER;
    }

    @Override
    public void onEntityCollision(IBlockState state, World world, BlockPos pos, Entity entity) {
        entity.setFire(5);
    }

    @Override
    public void animateTick(IBlockState state, World world, BlockPos pos, Random rand) {
        if(rand.nextBoolean())
            world.spawnParticle(Particles.FLAME, pos.getX() + rand.nextFloat(), pos.getY()  + rand.nextFloat(), pos.getZ()  + rand.nextFloat(), 0,rand.nextFloat()/20,0);
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IWorld world, BlockPos pos, int fortune) {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(Blocks.blockFireFlower));
        return list;
    }
}
