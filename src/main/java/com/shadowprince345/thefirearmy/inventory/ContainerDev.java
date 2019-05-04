package com.shadowprince345.thefirearmy.inventory;

import com.shadowprince345.thefirearmy.blocks.tiles.TEDev;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerDev extends Container {

    public final InventoryPlayer inventoryPlayer;
    public final TEDev furnace;

    public ContainerDev(InventoryPlayer inventoryPlayer, TEDev furnace) {
        this.inventoryPlayer = inventoryPlayer;
        this.furnace = furnace;

        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 9; i++)
                addSlotToContainer(new Slot(this.inventoryPlayer, i + j * 9 + 9, 18 * i + 8, (18 * j) + 78));

        for (int i = 0; i < 9; i++)
            addSlotToContainer(new Slot(this.inventoryPlayer, i, 18 * i + 8, 136));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }
}
