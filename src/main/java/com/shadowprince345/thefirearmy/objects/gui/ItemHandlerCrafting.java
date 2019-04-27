package com.shadowprince345.thefirearmy.objects.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

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
            container.onCraftMatrixChanged(new IInventory() {
                @Override
                public int getSizeInventory() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public ItemStack getStackInSlot(int index) {
                    return null;
                }

                @Override
                public ItemStack decrStackSize(int index, int count) {
                    return null;
                }

                @Override
                public ItemStack removeStackFromSlot(int index) {
                    return null;
                }

                @Override
                public void setInventorySlotContents(int index, ItemStack stack) {

                }

                @Override
                public int getInventoryStackLimit() {
                    return 0;
                }

                @Override
                public void markDirty() {

                }

                @Override
                public boolean isUsableByPlayer(EntityPlayer player) {
                    return false;
                }

                @Override
                public void openInventory(EntityPlayer player) {

                }

                @Override
                public void closeInventory(EntityPlayer player) {

                }

                @Override
                public boolean isItemValidForSlot(int index, ItemStack stack) {
                    return false;
                }

                @Override
                public int getField(int id) {
                    return 0;
                }

                @Override
                public void setField(int id, int value) {

                }

                @Override
                public int getFieldCount() {
                    return 0;
                }

                @Override
                public void clear() {

                }

                @Override
                public String getName() {
                    return null;
                }

                @Override
                public boolean hasCustomName() {
                    return false;
                }

                @Override
                public ITextComponent getDisplayName() {
                    return null;
                }
            });

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
                public int getSizeInventory() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public ItemStack getStackInSlot(int index) {
                    return null;
                }

                @Override
                public ItemStack decrStackSize(int index, int count) {
                    return null;
                }

                @Override
                public ItemStack removeStackFromSlot(int index) {
                    return null;
                }

                @Override
                public void setInventorySlotContents(int index, ItemStack stack) {

                }

                @Override
                public int getInventoryStackLimit() {
                    return 0;
                }

                @Override
                public void markDirty() {

                }

                @Override
                public boolean isUsableByPlayer(EntityPlayer player) {
                    return false;
                }

                @Override
                public void openInventory(EntityPlayer player) {

                }

                @Override
                public void closeInventory(EntityPlayer player) {

                }

                @Override
                public boolean isItemValidForSlot(int index, ItemStack stack) {
                    return false;
                }

                @Override
                public int getField(int id) {
                    return 0;
                }

                @Override
                public void setField(int id, int value) {

                }

                @Override
                public int getFieldCount() {
                    return 0;
                }

                @Override
                public void clear() {

                }

                @Override
                public String getName() {
                    return null;
                }

                @Override
                public boolean hasCustomName() {
                    return false;
                }

                @Override
                public ITextComponent getDisplayName() {
                    return null;
                }
            });

    }
}
