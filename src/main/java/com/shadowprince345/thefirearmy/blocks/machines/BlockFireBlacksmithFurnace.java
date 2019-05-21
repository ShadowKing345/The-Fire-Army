package com.shadowprince345.thefirearmy.blocks.machines;

import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.blocks.tiles.TEFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.inventory.ContainerFBB;
import com.shadowprince345.thefirearmy.inventory.ContainerFBF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Particles;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.*;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockFireBlacksmithFurnace extends Block {

    public static final DirectionProperty FACING = BlockHorizontal.HORIZONTAL_FACING;
    public static final BooleanProperty BURNING = BooleanProperty.create("burning");

    public BlockFireBlacksmithFurnace() {
        super(Block.Properties.create(Material.ROCK).sound(SoundType.METAL).hardnessAndResistance(1f));
        setDefaultState(stateContainer.getBaseState().with(FACING, EnumFacing.NORTH).with(BURNING, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> stateContainer) {
        stateContainer.add(FACING, BURNING);
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return !playerIn.isSneaking();

        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if (tileEntity instanceof TEFireBlacksmithFurnace) {
            if(facing == EnumFacing.UP)
                NetworkHooks.openGui((EntityPlayerMP) playerIn, new BlockFireBlacksmithFurnace.Bench(worldIn, pos), pos);
            else
                NetworkHooks.openGui((EntityPlayerMP) playerIn, new BlockFireBlacksmithFurnace.Furnace(worldIn, pos), pos);
        }
        return !playerIn.isSneaking();
    }


    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (!stateIn.get(BURNING)) return;

        EnumFacing facing = stateIn.get(FACING);
        double x = pos.getX() + 0.125;
        double z = pos.getZ() + 0.125;

        switch (facing) {
            case NORTH:
                z += 0.75f;
            case SOUTH:
                x += 0.375f;
                break;
            case WEST:
                x += 0.75f;
            case EAST:
                z += 0.375f;
                break;
            default:
        }

        worldIn.spawnParticle(Particles.LARGE_SMOKE, x, pos.getY() + 1.25, z, 0, 0, 0);
    }

    @Override
    public int getLightValue(IBlockState state, IWorldReader world, BlockPos pos) {
        return state.get(BURNING) ? 5 : 0;
    }

    @Override
    public VoxelShape getCollisionShape(IBlockState p_196268_1_, IBlockReader p_196268_2_, BlockPos p_196268_3_) {
        return Block.makeCuboidShape(0.0f, 0.0f, 0.0f, 16.0f, 16.0f, 16.0f);
    }

    @Override
    public VoxelShape getShape(IBlockState p_196247_1_, IBlockReader p_196247_2_, BlockPos p_196247_3_) {
        return Block.makeCuboidShape(0.0f,0.0f,0.0f, 16.0f,11.0f, 16.0f);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public IBlockState getStateForPlacement(IBlockState state, EnumFacing facing, IBlockState state2, IWorld world, BlockPos pos1, BlockPos pos2, EnumHand hand) {
        return getDefaultState().with(FACING, facing.getOpposite()).with(BURNING, state.get(BURNING));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, getDefaultState().with(FACING, placer.getHorizontalFacing().getOpposite()));
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TEFireBlacksmithFurnace){
            for(int i = 0; i < ((TEFireBlacksmithFurnace) te).furnaceInventory.getSlots(); i++)
                player.dropItem(((TEFireBlacksmithFurnace) te).furnaceInventory.getStackInSlot(i), false);
            for(int i = 0; i < ((TEFireBlacksmithFurnace) te).benchInventory.getSlots(); i++)
                player.dropItem(((TEFireBlacksmithFurnace) te).benchInventory.getStackInSlot(i), false);
        }
        super.onBlockHarvested(world, pos, state, player);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
        return new TEFireBlacksmithFurnace();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public static void setBurning(boolean value, World world, BlockPos pos){
        IBlockState state = world.getBlockState(pos);
        TileEntity tileEntity = world.getTileEntity(pos);

        world.setBlockState(pos, state.with(BURNING, value), 3);

        if(tileEntity != null){
            tileEntity.validate();
            world.setTileEntity(pos, tileEntity);
        }
    }

    public static class Furnace implements IInteractionObject {
        private final World world;
        private final BlockPos pos;

        public Furnace(World world, BlockPos pos) {
            this.world = world;
            this.pos = pos;
        }

        @Override
        public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {
            return new ContainerFBF(inventoryPlayer, (TEFireBlacksmithFurnace) world.getTileEntity(pos));
        }

        @Override
        public String getGuiID() {
            return TheFireArmy.getModId() + ":fire_blacksmith_furnace";
        }

        @Override
        public ITextComponent getName() {
            return null;
        }

        @Override
        public boolean hasCustomName() {
            return false;
        }

        @Nullable
        @Override
        public ITextComponent getCustomName() {
            return null;
        }
    }

    public static class Bench implements IInteractionObject {
        private final World world;
        private final BlockPos pos;

        public Bench(World world, BlockPos pos) {
            this.world = world;
            this.pos = pos;
        }

        @Override
        public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {
            return new ContainerFBB(inventoryPlayer, (TEFireBlacksmithFurnace) world.getTileEntity(pos));
        }

        @Override
        public String getGuiID() {
            return TheFireArmy.getModId() + ":fire_blacksmith_bench";
        }

        @Override
        public ITextComponent getName() {
            return null;
        }

        @Override
        public boolean hasCustomName() {
            return false;
        }

        @Nullable
        @Override
        public ITextComponent getCustomName() {
            return null;
        }
    }
}
