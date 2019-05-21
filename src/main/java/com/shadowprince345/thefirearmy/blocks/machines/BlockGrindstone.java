package com.shadowprince345.thefirearmy.blocks.machines;

import com.shadowprince345.thefirearmy.blocks.tiles.TEGrindstone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

public class BlockGrindstone extends Block {

    public static DirectionProperty FACING = BlockHorizontal.HORIZONTAL_FACING;

    public BlockGrindstone() {
        super(Block.Properties.create(Material.ROCK).sound(SoundType.WOOD));
        setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumFacing.NORTH));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> container) {
        container.add(FACING);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState p_149645_1_) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(IBlockState p_196244_1_, IBlockReader p_196244_2_, BlockPos p_196244_3_) {
        EnumFacing facing = p_196244_1_.get(FACING);
        switch (facing) {
            case WEST:
            case EAST:
                return Block.makeCuboidShape(0, 0, 3, 16, 12, 13);
            case NORTH:
            case SOUTH:
                return Block.makeCuboidShape(3, 0, 0, 13, 12, 16);
            default:
                return super.getShape(p_196244_1_, p_196244_2_, p_196244_3_);
        }
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
        return new TEGrindstone();
    }

    @Override
    public boolean isFullCube(IBlockState p_149686_1_) {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockReader p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public int getOpacity(IBlockState p_200011_1_, IBlockReader p_200011_2_, BlockPos p_200011_3_) {
        return 0;
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TEGrindstone)
            for(int i = 0; i < ((TEGrindstone) te).inventory.getSlots(); i++)
                player.dropItem(((TEGrindstone) te).inventory.getStackInSlot(i), false);
        super.onBlockHarvested(world, pos, state, player);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.with(FACING, placer.getHorizontalFacing().getOpposite()));
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote) return true;
        TEGrindstone te = (TEGrindstone) worldIn.getTileEntity(pos);
        ItemStack holding = playerIn.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
        ItemStack input = te.inventory.getStackInSlot(0);
        ItemStack output = te.inventory.getStackInSlot(1);

        //todo: Remake.
        if(!playerIn.isSneaking()) {
            if (!holding.isEmpty()) {
                if (input.isEmpty()) {
                    input = ItemHandlerHelper.copyStackWithSize(holding, holding.getCount());
                    holding.shrink(holding.getCount());
                } else if (ItemStack.areItemsEqual(input, holding) && input.getCount() <= input.getMaxStackSize()) {
                    int amountFree = input.getMaxStackSize() - input.getCount();
                    if (amountFree <= holding.getCount() && input.getCount() + holding.getCount() <= input.getMaxStackSize()) {
                        input.grow(holding.getCount());
                        holding.shrink(holding.getCount());
                    } else {
                        input.grow(amountFree);
                        holding.shrink(amountFree);
                    }
                }
            } else {
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), input);
                input.shrink(input.getCount());
            }
            te.inventory.setStackInSlot(0, input);
        }else {
            if(!output.isEmpty()){
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), output);
                output.shrink(output.getCount());
                te.inventory.setStackInSlot(1, output);
            }
        }

        return true;
    }

    @Override
    public IBlockState getStateForPlacement(IBlockState state, EnumFacing facing, IBlockState state2, IWorld world, BlockPos pos1, BlockPos pos2, EnumHand hand) {
        return getDefaultState().with(FACING, facing.getOpposite());
    }
}
