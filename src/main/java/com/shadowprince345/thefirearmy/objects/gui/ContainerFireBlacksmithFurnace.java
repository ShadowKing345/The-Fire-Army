package com.shadowprince345.thefirearmy.objects.gui;

import com.shadowprince345.thefirearmy.objects.tiles.TileEntityFireBlacksmithFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerFireBlacksmithFurnace extends Container {
    private ItemStackHandler handler;
    public final TileEntityFireBlacksmithFurnace furnace;
    public final InventoryPlayer inventoryPlayer;
    public int fuelLevel = -1;
    public int progressLevel = -1;

    public ContainerFireBlacksmithFurnace(InventoryPlayer inventoryPlayer, TileEntityFireBlacksmithFurnace furnace) {
        this.furnace = furnace;
        this.inventoryPlayer = inventoryPlayer;

        handler = (ItemStackHandler) furnace.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        addSlotToContainer(new SlotItemHandler(handler, 10, 130, 31));
        addSlotToContainer(new SlotItemHandler(handler, 0, 17, 31){
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return TileEntityFireBlacksmithFurnace.isItemFuel(stack);
        }});

        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 3; i++)
                addSlotToContainer(new SlotItemHandler(handler, i + j * 3 + 1, i * 18 + 54 + i, j * 18 + 12 + j));

        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 9; i++)
                addSlotToContainer(new Slot(this.inventoryPlayer, i + j * 9 + 9, 18 * i + 8, (18 * j) + 78));

        for (int i = 0; i < 9; i++)
            addSlotToContainer(new Slot(this.inventoryPlayer, i, 18 * i + 8, 136));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        Slot slot = inventorySlots.get(index);

        if(slot != null && slot.getHasStack()){
            ItemStack stack = slot.getStack();
            ItemStack oldStack = stack;

            if(index < 10) {
                if (!mergeItemStack(stack, 2, inventorySlots.size(), true))
                    return ItemStack.EMPTY;
            }else if(!mergeItemStack(stack, 0, 10, false))
                return ItemStack.EMPTY;

            if (stack.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            return oldStack;
        }

        return ItemStack.EMPTY;
    }

    private void listenerHelper(IContainerListener listener) {
        listener.sendWindowProperty(this, 0, furnace.fuelLevel);
        listener.sendWindowProperty(this, 1, furnace.progressLevel);
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listenerHelper(listener);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        if(!furnace.getWorld().isRemote)
        for (IContainerListener listener : listeners) {
            listenerHelper(listener);
        }

    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        if(id == 0)
            fuelLevel = data;
        if(id == 1)
            progressLevel = data;
        else
            super.updateProgressBar(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }
}
