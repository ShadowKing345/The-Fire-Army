package com.shadowprince345.thefirearmy.proxy;

import com.shadowprince345.thefirearmy.Main;
import com.shadowprince345.thefirearmy.objects.blocks.machines.fireblacksmithfurnace.TileEntityFireBlacksmithFurnace;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ServerProxy {

    public void registerItemRenderer(Item item, int meta, String id){}

    public void registerTileEntity(){
        GameRegistry.registerTileEntity(TileEntityFireBlacksmithFurnace.class, new ResourceLocation(Main.getModId() + ":tile_entity_fire_blacksmith_furnace"));
    }
}
