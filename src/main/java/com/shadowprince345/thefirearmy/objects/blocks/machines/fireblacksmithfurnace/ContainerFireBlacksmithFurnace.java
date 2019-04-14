package com.shadowprince345.thefirearmy.objects.blocks.machines.fireblacksmithfurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerFireBlacksmithFurnace extends Container {

    private TileEntityFireBlacksmithFurnace furnace;
    private InventoryPlayer player;
    private int currentFuleLevel = -1;

    public ContainerFireBlacksmithFurnace(InventoryPlayer player, TileEntityFireBlacksmithFurnace furnace) {
        this.furnace = furnace;
        this.player = player;

        IItemHandler handler = furnace.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        addSlotToContainer(new FuelSlotItemHandler(handler, 0, 17, 31));

        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 3; i++)
                addSlotToContainer(new SlotItemHandler(handler, i + j * 3 + 1, i * 18 + 54 + i, j * 18 + 12 + j));

        addSlotToContainer(new OutputSlotItemHandler(handler, 10, 130, 31));

        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 9; i++)
                addSlotToContainer(new Slot(this.player, i + j * 9 + 9, 18 * i + 8, (18 * j) + 78));

        for (int i = 0; i < 9; i++)
            addSlotToContainer(new Slot(this.player, i, 18 * i + 8, 136));
    }

    private void listenerHelper(IContainerListener listener) {
        listener.sendWindowProperty(this, 0, Math.min(255, furnace.currentBurnTime <= 0 ? 0 : furnace.currentBurnTime * 255 / furnace.burnTime));
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
            currentFuleLevel = data;
        else
            super.updateProgressBar(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }

    private class FuelSlotItemHandler extends SlotItemHandler {

        public FuelSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
            super(itemHandler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return TileEntityFireBlacksmithFurnace.isItemFuel(stack);
        }
    }

    private class OutputSlotItemHandler extends SlotItemHandler {
        public OutputSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
            super(itemHandler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return false;
        }
    }
}
