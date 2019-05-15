package com.shadowprince345.thefirearmy.blocks;

import com.shadowprince345.thefirearmy.init.Blocks;
import com.shadowprince345.thefirearmy.init.Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Particles;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFireFlowerCrop extends BlockCrops {

    public BlockFireFlowerCrop() {
        super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().needsRandomTick().sound(SoundType.GLASS));
    }

    @Override
    public void onEntityCollision(IBlockState state, World world, BlockPos pos, Entity entity) {
        entity.setFire(5);
    }

    @Override
    public void animateTick(IBlockState state, World world, BlockPos pos, Random rand) {
        if(rand.nextBoolean() && state.get(AGE) == 7)
            world.spawnParticle(Particles.FLAME, pos.getX() + rand.nextFloat(), pos.getY()  + rand.nextFloat(), pos.getZ()  + rand.nextFloat(), 0,rand.nextFloat()/20,0);
    }

    protected Item getSeed() {
        return Items.itemFireFlowerSeed;
    }

    protected Item getCrop() {
        return Item.getItemFromBlock(Blocks.blockFireFlower);
    }
}
