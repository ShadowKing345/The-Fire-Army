package com.shadowprince345.thefirearmy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.List;

public class BlockFloorDrum extends Block {
    public BlockFloorDrum() {
        super(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD));
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z) {
        if(worldIn.isRemote) return true;

        AxisAlignedBB area = new AxisAlignedBB(pos.subtract(new Vec3i(10,5,10)), pos.add(new Vec3i(10,5,10)));
        List<Entity> entities = worldIn.getEntitiesWithinAABB(Entity.class, area);

        for(Entity entity: entities){
            if(entity instanceof EntityLiving)
                entity.attackEntityFrom(DamageSource.MAGIC, 3f);
            if(entity instanceof EntityPlayer && entity != playerIn)
                entity.attackEntityFrom(DamageSource.MAGIC, 6f);
        }

        return true;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}
