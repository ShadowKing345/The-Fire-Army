package com.shadowprince345.thefirearmy.utils;

import com.shadowprince345.thefirearmy.objects.blocks.machines.fireblacksmithfurnace.ContainerFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.objects.blocks.machines.fireblacksmithfurnace.TileEntityFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.objects.gui.GUIFireBlacksmithFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == References.GUI_FIRE_BLACKSMITH_FURNACE) {
            return new ContainerFireBlacksmithFurnace(player.inventory, (TileEntityFireBlacksmithFurnace) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == References.GUI_FIRE_BLACKSMITH_FURNACE) {
            return new GUIFireBlacksmithFurnace(player.inventory, (TileEntityFireBlacksmithFurnace) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}
