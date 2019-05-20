package com.shadowprince345.thefirearmy.lib.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemHandlerCrafting extends ItemStackHandler {

    private final Container container;
    private final ItemStackHandler handler;

    public ItemHandlerCrafting(Container container, ItemStackHandler handler) {
        this.container = container;
        this.handler = handler;
    }

    @Override
    public int getSlots() {
        return handler.getSlots();
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return handler.getStackInSlot(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return handler.insertItem(slot, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack result = handler.extractItem(slot, amount, simulate);

        if(!result.isEmpty())
            container.onCraftMatrixChanged(null);

        return result;
    }

    @Override
    public int getSlotLimit(int slot) {
        return handler.getSlotLimit(slot);
    }


    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        handler.setStackInSlot(slot, stack);

        if(!stack.isEmpty())
            container.onCraftMatrixChanged(new IInventory() {
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

                @Override
                public int getSizeInventory() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public ItemStack getStackInSlot(int i) {
                    return null;
                }

                @Override
                public ItemStack decrStackSize(int i, int i1) {
                    return null;
                }

                @Override
                public ItemStack removeStackFromSlot(int i) {
                    return null;
                }

                @Override
                public void setInventorySlotContents(int i, ItemStack itemStack) {

                }

                @Override
                public int getInventoryStackLimit() {
                    return 0;
                }

                @Override
                public void markDirty() {

                }

                @Override
                public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
                    return false;
                }

                @Override
                public void openInventory(EntityPlayer entityPlayer) {

                }

                @Override
                public void closeInventory(EntityPlayer entityPlayer) {

                }

                @Override
                public boolean isItemValidForSlot(int i, ItemStack itemStack) {
                    return false;
                }

                @Override
                public int getField(int i) {
                    return 0;
                }

                @Override
                public void setField(int i, int i1) {

                }

                @Override
                public int getFieldCount() {
                    return 0;
                }

                @Override
                public void clear() {

                }
            });

    }
}
