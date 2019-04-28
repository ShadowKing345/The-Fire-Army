package com.shadowprince345.thefirearmy.blocks.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileDev extends TileEntity implements ITickable, ICapabilityProvider {

    ItemStackHandler craftingMatrix = new ItemStackHandler(9);

    @Override
    public void update() {

    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return true;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return (T) craftingMatrix;
    }
}
