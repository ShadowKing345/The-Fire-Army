package com.shadowprince345.thefirearmy.blocks;

import com.shadowprince345.thefirearmy.init.Blocks;
import com.shadowprince345.thefirearmy.init.Items;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFireFlowerCrop extends BlockCrops {

    public BlockFireFlowerCrop() {
        setUnlocalizedName("fire_flower_crop");
        setRegistryName("fire_flower_crop");
        setSoundType(SoundType.GLASS);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if(state.getValue(this.AGE) == 7)
            entityIn.setFire(5);
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if(rand.nextBoolean() && stateIn.getValue(this.AGE) == 7)
            worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + rand.nextFloat(), pos.getY()  + rand.nextFloat(), pos.getZ()  + rand.nextFloat(), 0,rand.nextFloat()/20,0);
    }

    @Override
    protected Item getSeed() {
        return Items.itemFireFlowerSeed;
    }

    @Override
    protected Item getCrop() {
        return Item.getItemFromBlock(Blocks.blockFireFlower);
    }
}
