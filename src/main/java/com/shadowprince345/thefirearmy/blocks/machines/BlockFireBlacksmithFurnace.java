//package com.shadowprince345.thefirearmy.blocks.machines;
//
//import com.shadowprince345.thefirearmy.blocks.tiles.TEFireBlacksmithFurnace;
//import com.shadowprince345.thefirearmy.client.GuiHandler;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockHorizontal;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.properties.PropertyBool;
//import net.minecraft.block.properties.PropertyDirection;
//import net.minecraft.block.state.BlockStateContainer;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.InventoryHelper;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.EnumHand;
//import net.minecraft.util.EnumParticleTypes;
//import net.minecraft.util.math.AxisAlignedBB;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.IBlockAccess;
//import net.minecraft.world.World;
//
//import javax.annotation.Nullable;
//import java.util.Random;
//
//public class BlockFireBlacksmithFurnace extends Block {
//
//    public static final PropertyDirection FACING = BlockHorizontal.FACING;
//    public static final PropertyBool BURNING = PropertyBool.create("burning");
//
//    public BlockFireBlacksmithFurnace() {
//        super(Material.IRON);
//        setSoundType(SoundType.METAL);
//
//        setHardness(1f);
//        setHarvestLevel("pickaxe", 1);
//
//        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, false));
//    }
//
//    @Override
//    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//        if (worldIn.isRemote) return !playerIn.isSneaking();
//
//        TileEntity tileEntity = worldIn.getTileEntity(pos);
//
//        if (tileEntity instanceof TEFireBlacksmithFurnace) {
//            if(facing == EnumFacing.UP)
//                GuiHandler.open(playerIn, GuiHandler.GUI_FIRE_BLACKSMITH_BENCH, pos.getX(), pos.getY(), pos.getZ());
//            else
//                GuiHandler.open(playerIn, GuiHandler.GUI_FIRE_BLACKSMITH_FURNACE, pos.getX(), pos.getY(), pos.getZ());
//        }
//        return !playerIn.isSneaking();
//    }
//
//    @Override
//    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
//        if(!stateIn.getValue(BURNING)) return;
//
//        EnumFacing facing = stateIn.getValue(FACING);
//        double x = pos.getX() + 0.125;
//        double z = pos.getZ() + 0.125;
//
//        switch (facing) {
//            case NORTH:
//                z += 0.75f;
//            case SOUTH:
//                x += 0.375f;
//                break;
//            case WEST:
//                x += 0.75f;
//            case EAST:
//                z += 0.375f;
//                break;
//            default:
//        }
//
//        worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x, pos.getY() + 1.25, z, 0,0,0);
//    }
//
//    @Override
//    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
//        return state.getValue(BURNING) ? 10 : 0;
//    }
//
//    @Nullable
//    @Override
//    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
//        return new AxisAlignedBB(0,0,0,1,1,1);
//    }
//
//    @Override
//    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
//        AxisAlignedBB boundingBox = new AxisAlignedBB(0,0,0,1,0.65,1);
//
//        return boundingBox;
//    }
//
//    @Override
//    public boolean isOpaqueCube(IBlockState state) {
//        return false;
//    }
//
//    @Override
//    public boolean isFullCube(IBlockState state) {
//        return false;
//    }
//
//    @Override
//    protected BlockStateContainer createBlockState() {
//        return new BlockStateContainer(this, FACING, BURNING);
//    }
//
//    @Override
//    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
//        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
//    }
//
//    @Override
//    public IBlockState getStateFromMeta(int meta) {
//        EnumFacing facing = EnumFacing.getFront(meta);
//        if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
//        return getDefaultState().withProperty(FACING, facing);
//    }
//
//    @Override
//    public int getMetaFromState(IBlockState state) {
//        return state.getValue(FACING).getIndex();
//    }
//
//    @Override
//    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
//        worldIn.setBlockState(pos, getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
//    }
//
//    @Nullable
//    @Override
//    public TileEntity createTileEntity(World world, IBlockState state) {
//        return new TEFireBlacksmithFurnace();
//    }
//
//    @Override
//    public boolean hasTileEntity(IBlockState state) {
//        return true;
//    }
//
//    @Override
//    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
//        if(worldIn.isRemote) return;
//
//        TEFireBlacksmithFurnace tileEntity = (TEFireBlacksmithFurnace) worldIn.getTileEntity(pos);
//        if(tileEntity == null) return;
//
//        for(int slot = 0; slot < tileEntity.benchInventory.getSlots(); slot++)
//            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntity.benchInventory.getStackInSlot(slot));
//
//        for(int slot = 0; slot < tileEntity.furnaceInventory.getSlots(); slot++)
//            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntity.furnaceInventory.getStackInSlot(slot));
//
//        super.breakBlock(worldIn, pos, state);
//    }
//
//    public static void setBurning(boolean value, World world, BlockPos pos){
//        IBlockState state = world.getBlockState(pos);
//        TileEntity tileEntity = world.getTileEntity(pos);
//
//        world.setBlockState(pos, state.withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, value), 3);
//
//        if(tileEntity != null){
//            tileEntity.validate();
//            world.setTileEntity(pos, tileEntity);
//        }
//    }
//}
