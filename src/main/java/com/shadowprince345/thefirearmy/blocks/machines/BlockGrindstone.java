//package com.shadowprince345.thefirearmy.blocks.machines;
//
//import com.shadowprince345.thefirearmy.blocks.tiles.TEGrindstone;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockHorizontal;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.properties.PropertyBool;
//import net.minecraft.block.properties.PropertyDirection;
//import net.minecraft.block.state.BlockStateContainer;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.client.util.ITooltipFlag;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.EntityEquipmentSlot;
//import net.minecraft.inventory.InventoryHelper;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.EnumBlockRenderType;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.EnumHand;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.items.ItemHandlerHelper;
//
//import javax.annotation.Nullable;
//import java.util.List;
//
//public class BlockGrindstone extends Block {
//
//    public static PropertyBool ACTIVE = PropertyBool.create("active");
//    public static PropertyDirection FACING = BlockHorizontal.FACING;
//
//    public BlockGrindstone() {
//        super(Material.GROUND);
//        setSoundType(SoundType.STONE);
//        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false));
//    }
//
//    @Override
//    public EnumBlockRenderType getRenderType(IBlockState state) {
//        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
//    }
//
//    @Override
//    public boolean hasTileEntity(IBlockState state) {
//        return true;
//    }
//
//    @Nullable
//    @Override
//    public TileEntity createTileEntity(World world, IBlockState state) {
//        return new TEGrindstone();
//    }
//
//    @Override
//    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
//        tooltip.add("Please sharpen actual swords with it.");
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
//    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
//        TEGrindstone te = (TEGrindstone) worldIn.getTileEntity(pos);
//        for(int i = 0; i < te.inventory.getSlots(); i++)
//            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.inventory.getStackInSlot(i));
//    }
//
//    @Override
//    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
//        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(ACTIVE, false));
//    }
//
//    @Override
//    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//        if(worldIn.isRemote) return true;
//        TEGrindstone te = (TEGrindstone) worldIn.getTileEntity(pos);
//        ItemStack holding = playerIn.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
//        ItemStack input = te.inventory.getStackInSlot(0);
//        ItemStack output = te.inventory.getStackInSlot(1);
//
//        if(!playerIn.isSneaking()) {
//            if (!holding.isEmpty()) {
//                if (input.isEmpty()) {
//                    input = ItemHandlerHelper.copyStackWithSize(holding, holding.getCount());
//                    holding.shrink(holding.getCount());
//                } else if (ItemStack.areItemsEqual(input, holding) && input.getCount() <= input.getMaxStackSize()) {
//                    int amountFree = input.getMaxStackSize() - input.getCount();
//                    if (amountFree <= holding.getCount() && input.getCount() + holding.getCount() <= input.getMaxStackSize()) {
//                        input.grow(holding.getCount());
//                        holding.shrink(holding.getCount());
//                    } else {
//                        input.grow(amountFree);
//                        holding.shrink(amountFree);
//                    }
//                }
//            } else {
//                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), input);
//                input.shrink(input.getCount());
//            }
//            te.inventory.setStackInSlot(0, input);
//        }else {
//            if(!output.isEmpty()){
//                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), output);
//                output.shrink(output.getCount());
//                te.inventory.setStackInSlot(1, output);
//            }
//        }
//
//        return true;
//    }
//
//    @Override
//    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
//        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
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
//    protected BlockStateContainer createBlockState() {
//        return new BlockStateContainer(this, FACING, ACTIVE);
//    }
//
//    public static void setACTIVE(boolean value, World world, BlockPos pos){
//        IBlockState state = world.getBlockState(pos);
//        TileEntity tileEntity = world.getTileEntity(pos);
//
//        world.setBlockState(pos, state.withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVE, value), 3);
//
//        if(tileEntity != null){
//            tileEntity.validate();
//            world.setTileEntity(pos, tileEntity);
//        }
//    }
//}
