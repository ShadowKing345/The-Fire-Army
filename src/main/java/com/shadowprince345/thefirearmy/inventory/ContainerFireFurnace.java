package com.shadowprince345.thefirearmy.inventory;

import com.shadowprince345.thefirearmy.blocks.tiles.TileEntityFireFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerFireFurnace extends Container {
    private ItemStackHandler furnaceHandler;
    public TileEntityFireFurnace furnace;
    public InventoryPlayer inventoryPlayer;
    public int fuelLevel = -1;
    public int progressLevel = -1;

    public ContainerFireFurnace(InventoryPlayer inventoryPlayer, TileEntityFireFurnace furnace){
        this.inventoryPlayer = inventoryPlayer;
        this.furnace = furnace;
        furnaceHandler = furnace.inventory;

        addSlotToContainer(new SlotItemHandler(furnaceHandler, 0, 67, 50){
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return TileEntityFurnace.isItemFuel(stack);
            }});
        addSlotToContainer(new SlotItemHandler(furnaceHandler, 1, 67, 11));
        addSlotToContainer(new SlotItemHandler(furnaceHandler, 2, 105, 30){
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return FurnaceRecipes.instance().getSmeltingResult(furnaceHandler.getStackInSlot(1)) != ItemStack.EMPTY;
            }});

        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 9; i++)
                addSlotToContainer(new Slot(this.inventoryPlayer, i + j * 9 + 9, 18 * i + 8, (18 * j) + 78));

        for (int i = 0; i < 9; i++)
            addSlotToContainer(new Slot(this.inventoryPlayer, i, 18 * i + 8, 136));
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

    @SideOnly(Side.CLIENT)
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
