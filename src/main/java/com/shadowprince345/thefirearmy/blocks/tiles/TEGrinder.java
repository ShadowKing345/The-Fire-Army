package com.shadowprince345.thefirearmy.blocks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;

public class TEGrinder extends TileEntity implements ITickable {
    public ItemStackHandler inventory = new ItemStackHandler(2);
    public boolean isGrinding = false;
    public int totalProgress = 0;
    public int currentProgress = 0;

    @Override
    public void update() {

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        totalProgress = nbt.getInteger("total");
        currentProgress = nbt.getInteger("current");
        NBTTagCompound tag = new NBTTagCompound();
        tag.setTag("Items", nbt.getCompoundTag("inventory"));
        inventory.deserializeNBT(tag);
        super.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        if(totalProgress > 0)
            nbt.setInteger("total", totalProgress);
        if(currentProgress > 0)
            nbt.setInteger("current", currentProgress);
        nbt.setTag("inventory", inventory.serializeNBT().getTag("Items"));
        return super.writeToNBT(nbt);
    }
}
