package com.shadowprince345.thefirearmy.objects.blocks.machines.fireblacksmithfurnace;

import com.shadowprince345.thefirearmy.Main;
import com.shadowprince345.thefirearmy.utils.References;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class BlockFireBlacksmithFurnace extends Block implements ITileEntityProvider {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool BURNING = PropertyBool.create("burning");

    public BlockFireBlacksmithFurnace() {
        super(Material.IRON);
        setSoundType(SoundType.METAL);

        setHardness(1f);
        setHarvestLevel("pickaxe", 1);

        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, false));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, BURNING);
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state.getValue(BURNING) ? 10 : 0;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote)
            playerIn.openGui(Main.instance, References.GUI_FIRE_BLACKSMITH_FURNACE, worldIn, pos.getX(), pos.getY(), pos.getZ());

        return !playerIn.isSneaking();
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta);
        if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
        return getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityFireBlacksmithFurnace();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        IItemHandler handler = worldIn.getTileEntity(pos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        for(int slot = 0; slot < handler.getSlots(); slot++)
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(slot));

        super.breakBlock(worldIn, pos, state);
    }
}
