package com.shadowprince345.thefirearmy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
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
        super(Material.WOOD);
        setSoundType(SoundType.WOOD);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
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
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}
