package com.shadowprince345.thefirearmy.inventory;

import com.shadowprince345.thefirearmy.blocks.tiles.TileDev;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerDev extends Container {

    public ContainerDev(InventoryPlayer inventoryPlayer, TileDev furnace) {
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }
}
