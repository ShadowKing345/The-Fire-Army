//package com.shadowprince345.thefirearmy.client;
//
//import com.shadowprince345.thefirearmy.TheFireArmy;
//import com.shadowprince345.thefirearmy.blocks.tiles.TEDev;
//import com.shadowprince345.thefirearmy.blocks.tiles.TEFireBlacksmithFurnace;
//import com.shadowprince345.thefirearmy.blocks.tiles.TEFireFurnace;
//import com.shadowprince345.thefirearmy.client.gui.GUIDev;
//import com.shadowprince345.thefirearmy.client.gui.GuiFBB;
//import com.shadowprince345.thefirearmy.client.gui.GuiFBF;
//import com.shadowprince345.thefirearmy.client.gui.GuiFireFurnace;
//import com.shadowprince345.thefirearmy.inventory.ContainerDev;
//import com.shadowprince345.thefirearmy.inventory.ContainerFBB;
//import com.shadowprince345.thefirearmy.inventory.ContainerFBF;
//import com.shadowprince345.thefirearmy.inventory.ContainerFireFurnace;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.common.network.IGuiHandler;
//
//import javax.annotation.Nullable;
//
//public class GuiHandler implements IGuiHandler {
//    public static final int GUI_DEV = -1;
//    public static final int GUI_FIRE_BLACKSMITH_BENCH = 0;
//    public static final int GUI_FIRE_BLACKSMITH_FURNACE = 1;
//    public static final int GUI_FIRE_FURNACE = 2;
//
//    public static void open(EntityPlayer player, int id, int x, int y, int z){
//        player.openGui(TheFireArmy.instance, id, player.world, x, y, z);
//    }
//
//    @Nullable
//    @Override
//    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//        switch (ID) {
//            case GUI_DEV:
//                return new ContainerDev(player.inventory, (TEDev) world.getTileEntity(new BlockPos(x,y,z)));
//            case GUI_FIRE_BLACKSMITH_BENCH:
//                return new ContainerFBB(player.inventory, (TEFireBlacksmithFurnace) world.getTileEntity(new BlockPos(x, y, z)));
//            case GUI_FIRE_BLACKSMITH_FURNACE:
//                return new ContainerFBF(player.inventory, (TEFireBlacksmithFurnace) world.getTileEntity(new BlockPos(x, y, z)));
//            case GUI_FIRE_FURNACE:
//                return new ContainerFireFurnace(player.inventory, (TEFireFurnace) world.getTileEntity(new BlockPos(x, y, z)));
//            default:
//                return null;
//        }
//    }
//
//    @Nullable
//    @Override
//    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//        switch (ID) {
//            case GUI_DEV:
//                return new GUIDev(new ContainerDev(player.inventory, (TEDev) world.getTileEntity(new BlockPos(x,y,z))));
//            case GUI_FIRE_BLACKSMITH_BENCH:
//                return new GuiFBB(new ContainerFBB(player.inventory, (TEFireBlacksmithFurnace) world.getTileEntity(new BlockPos(x, y, z))));
//            case GUI_FIRE_BLACKSMITH_FURNACE:
//                return new GuiFBF(new ContainerFBF(player.inventory, (TEFireBlacksmithFurnace) world.getTileEntity(new BlockPos(x, y, z))));
//            case GUI_FIRE_FURNACE:
//                return new GuiFireFurnace(new ContainerFireFurnace(player.inventory, (TEFireFurnace) world.getTileEntity(new BlockPos(x, y, z))));
//            default:
//                return null;
//        }
//    }
//}
