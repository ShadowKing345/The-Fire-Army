package com.shadowprince345.thefirearmy.init;

import com.shadowprince345.thefirearmy.blocks.tiles.TEFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.blocks.tiles.TEFireFurnace;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityTypes {

    public static TileEntityType<TEFireBlacksmithFurnace> TileEntityFireBlacksmithFurnace;
    public static TileEntityType<TEFireFurnace> TileEntityFireFurnace;

    public static void init(){
        TileEntityFireBlacksmithFurnace = TileEntityType.register("fire_blacksmtih_furnace", TileEntityType.Builder.create(TEFireBlacksmithFurnace::new));
        TileEntityFireFurnace = TileEntityType.register("fire_furnace", TileEntityType.Builder.create(TEFireFurnace::new));
    }
}
