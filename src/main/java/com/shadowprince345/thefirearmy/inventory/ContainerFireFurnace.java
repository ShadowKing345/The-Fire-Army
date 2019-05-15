package com.shadowprince345.thefirearmy.inventory;

import com.shadowprince345.thefirearmy.blocks.tiles.TEFireFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerFireFurnace extends Container {
    private ItemStackHandler furnaceHandler;
    public TEFireFurnace furnace;
    public InventoryPlayer inventoryPlayer;
    public int fuelLevel = -1;
    public int progressLevel = -1;

    public ContainerFireFurnace(InventoryPlayer inventoryPlayer, TEFireFurnace furnace){
        this.inventoryPlayer = inventoryPlayer;
        this.furnace = furnace;
        furnaceHandler = furnace.inventory;

        addSlot(new SlotItemHandler(furnaceHandler, 2, 67, 50){
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return TileEntityFurnace.isItemFuel(stack);
            }});
        addSlot(new SlotItemHandler(furnaceHandler, 0, 67, 11));
        addSlot(new SlotItemHandler(furnaceHandler, 1, 105, 30){
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }});

        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 9; i++)
                addSlot(new Slot(this.inventoryPlayer, i + j * 9 + 9, 18 * i + 8, (18 * j) + 78));

        for (int i = 0; i < 9; i++)
            addSlot(new Slot(this.inventoryPlayer, i, 18 * i + 8, 136));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        Slot slot = inventorySlots.get(index);
        if(slot != null) {
            ItemStack stack = slot.getStack();
            ItemStack oldStack = stack.copy();

            if(index < 3){
                if(!mergeItemStack(stack, 3, inventorySlots.size(), true))
                    return ItemStack.EMPTY;
            }
            else if(!mergeItemStack(stack, 0, 3, false)){
                return ItemStack.EMPTY;
            }

            if(stack.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            return oldStack;
        }

        return ItemStack.EMPTY;
    }

    private void listenerHelper(IContainerListener listener) {
        listener.sendWindowProperty(this, 0, (int) (furnace.currentBurnTime/(double)furnace.totalBurnTime * 100f) & 255);
        listener.sendWindowProperty(this, 1, (int) (furnace.progress/(double)furnace.totalProgress * 100f) & 255);
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

    @OnlyIn(Dist.CLIENT)
    public void updateProgressBar(int id, int data) {
        if(id == 0)
            fuelLevel = data & 255;
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
