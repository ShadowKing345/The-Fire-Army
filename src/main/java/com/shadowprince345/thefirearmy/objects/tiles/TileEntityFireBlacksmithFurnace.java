package com.shadowprince345.thefirearmy.objects.tiles;

import com.shadowprince345.thefirearmy.objects.blocks.machines.BlockFireBlacksmithFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityFireBlacksmithFurnace extends TileEntity implements ITickable, ICapabilityProvider {

    private ItemStackHandler handler;
    public String customName = "";

    public boolean isBurning = true;

    public int burnTime = 500;
    public int currentBurnTime = 0;
    public int fuelLevel = 0;

    public TileEntityFireBlacksmithFurnace() {
        customName = "";
        handler = new ItemStackHandler(11);
    }

    @Override
    public void update() {
        if (world.isRemote) return;

        if (isBurning) {
            --currentBurnTime;
            if (currentBurnTime <= 0)
                currentBurnTime = burnTime;
        }

        world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockFireBlacksmithFurnace.BURNING, isBurning));

        fuelLevel = (int) (currentBurnTime / (double) burnTime * 100f);

        markDirty();
    }

    public static boolean isItemFuel(ItemStack stack){
        return TileEntityFurnace.isItemFuel(stack);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("burnTime", burnTime);
        compound.setInteger("currentBurnTime", currentBurnTime);
        compound.setBoolean("isBurning", isBurning);
        compound.setTag("itemStackHandler", handler.serializeNBT().getTag("Items"));
        compound.setString("customName", customName);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        customName = compound.getString("customName");
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setTag("Items", compound.getCompoundTag("itemStackHandler"));
        handler.deserializeNBT(nbtTagCompound);
        isBurning = compound.getBoolean("isBurning");
        currentBurnTime = compound.getInteger("currentBurnTime");
        burnTime = compound.getInteger("burnTime");
    }

    @Override
    public void onLoad() {
        if(world.isRemote)
            world.tickableTileEntities.remove(this);

        validate();
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, 0, writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public void markDirty() {
        if(world != null)
            world.markChunkDirty(pos, this);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T) handler;
        return super.getCapability(capability, facing);
    }
}
