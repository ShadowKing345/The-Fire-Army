package com.shadowprince345.thefirearmy.client.gui;

import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.blocks.tiles.TileDev;
import com.shadowprince345.thefirearmy.blocks.tiles.TileEntityFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.inventory.ContainerDev;
import com.shadowprince345.thefirearmy.inventory.ContainerFireBlacksmithBench;
import com.shadowprince345.thefirearmy.inventory.ContainerFireBlacksmithFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    public static final int GUI_DEV = -1;
    public static final int GUI_FIRE_BLACKSMITH_BENCH = 0;
    public static final int GUI_FIRE_BLACKSMITH_FURNACE = 1;

    public static void open(EntityPlayer player, int id, int x, int y, int z){
        player.openGui(TheFireArmy.instance, id, player.world, x, y, z);
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_DEV:
                return new ContainerDev(player.inventory, (TileDev) world.getTileEntity(new BlockPos(x,y,z)));
            case GUI_FIRE_BLACKSMITH_BENCH:
                return new ContainerFireBlacksmithBench(player.inventory, (TileEntityFireBlacksmithFurnace) world.getTileEntity(new BlockPos(x, y, z)));
            case GUI_FIRE_BLACKSMITH_FURNACE:
                return new ContainerFireBlacksmithFurnace(player.inventory, (TileEntityFireBlacksmithFurnace) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_DEV:
                return new GUIDev(new ContainerDev(player.inventory, (TileDev) world.getTileEntity(new BlockPos(x,y,z))));
            case GUI_FIRE_BLACKSMITH_BENCH:
                return new GuiFBB(new ContainerFireBlacksmithBench(player.inventory, (TileEntityFireBlacksmithFurnace) world.getTileEntity(new BlockPos(x, y, z))));
            case GUI_FIRE_BLACKSMITH_FURNACE:
                return new GUIFireBlacksmithFurnace(new ContainerFireBlacksmithFurnace(player.inventory, (TileEntityFireBlacksmithFurnace) world.getTileEntity(new BlockPos(x, y, z))));
            default:
                return null;
        }
    }
}
