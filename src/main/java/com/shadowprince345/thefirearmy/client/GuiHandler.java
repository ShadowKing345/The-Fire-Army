package com.shadowprince345.thefirearmy.client;

import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.blocks.tiles.TEFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.blocks.tiles.TEFireFurnace;
import com.shadowprince345.thefirearmy.client.gui.GuiFBB;
import com.shadowprince345.thefirearmy.client.gui.GuiFBF;
import com.shadowprince345.thefirearmy.client.gui.GuiFireFurnace;
import com.shadowprince345.thefirearmy.inventory.ContainerFBB;
import com.shadowprince345.thefirearmy.inventory.ContainerFBF;
import com.shadowprince345.thefirearmy.inventory.ContainerFireFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.function.Function;
import java.util.function.Supplier;

public class GuiHandler implements Supplier<Function<FMLPlayMessages.OpenContainer, GuiScreen>> {

    @Override
    public Function<FMLPlayMessages.OpenContainer, GuiScreen> get() {
            return (openContainer) -> {
                ResourceLocation location = openContainer.getId();
                String[] strings = location.toString().split(":");
                if(strings[0].equals(TheFireArmy.getModId())) {
                    EntityPlayerSP player = Minecraft.getInstance().player;
                    TileEntity te = player.world.getTileEntity(openContainer.getAdditionalData().readBlockPos());

                    switch (strings[1]){
                        case "fire_furnace":
                            if(te instanceof TEFireFurnace)
                                return new GuiFireFurnace(new ContainerFireFurnace(player.inventory, (TEFireFurnace) te));
                        case "fire_blacksmith_furnace":
                            if(te instanceof TEFireBlacksmithFurnace)
                                return new GuiFBF(new ContainerFBF(player.inventory, (TEFireBlacksmithFurnace) te));
                        case "fire_blacksmith_bench":
                            if(te instanceof TEFireBlacksmithFurnace)
                                return new GuiFBB(new ContainerFBB(player.inventory, (TEFireBlacksmithFurnace) te));
                    }
                }
                return null;
            };
    }
}
