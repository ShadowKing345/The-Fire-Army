package com.shadowprince345.thefirearmy.blocks.machines;

import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.blocks.tiles.TEFireFurnace;
import com.shadowprince345.thefirearmy.init.Blocks;
import com.shadowprince345.thefirearmy.inventory.ContainerFireFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockFireFurnace extends Block {
    public static final DirectionProperty FACING = BlockHorizontal.HORIZONTAL_FACING;
    public static final BooleanProperty BURNING = BooleanProperty.create("burning");

    public BlockFireFurnace() {
        super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE));
        this.setDefaultState(this.getDefaultState().with(FACING, EnumFacing.NORTH).with(BURNING, false));
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z) {
        if(worldIn.isRemote) return !playerIn.isSneaking();

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity instanceof TEFireFurnace) {
            NetworkHooks.openGui((EntityPlayerMP) playerIn, new BlockFireFurnace.FireFurnace(worldIn, pos), pos);
        }

        return !playerIn.isSneaking();
    }

    @Override
    public int getLightValue(IBlockState state, IWorldReader world, BlockPos pos) {
        return state.get(BURNING) ? 5 : 0;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> container) {
        container.add(FACING, BURNING);
    }

    @Nullable
    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context) {
        return getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, getDefaultState().with(FACING, placer.getHorizontalFacing().getOpposite()));
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TEFireFurnace)
            for(int i = 0; i < ((TEFireFurnace) te).inventory.getSlots(); i++)
                player.dropItem(((TEFireFurnace) te).inventory.getStackInSlot(i), false);
        super.onBlockHarvested(world, pos, state, player);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
        return new TEFireFurnace();
    }

    public static void setBurning(boolean value, World world, BlockPos pos){
        IBlockState state = world.getBlockState(pos);
        TileEntity tileEntity = world.getTileEntity(pos);

        world.setBlockState(pos, state.with(FACING, state.get(FACING)).with(BURNING, value), 3);

        if(tileEntity != null){
            tileEntity.validate();
            world.setTileEntity(pos, tileEntity);
        }
    }

    public static class FireFurnace implements IInteractionObject{

        private final World world;
        private final BlockPos pos;

        public FireFurnace(World world, BlockPos pos) {
            this.world = world;
            this.pos = pos;
        }

        @Override
        public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {
            return new ContainerFireFurnace(inventoryPlayer, (TEFireFurnace) world.getTileEntity(pos));
        }

        @Override
        public String getGuiID() {
            return TheFireArmy.getModId() + ":fire_furnace";
        }

        @Override
        public ITextComponent getName() {
            return new TextComponentTranslation(Blocks.blockFireFurnace.getTranslationKey());
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
