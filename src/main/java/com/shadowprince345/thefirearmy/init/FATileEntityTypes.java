package com.shadowprince345.thefirearmy.init;

import com.shadowprince345.thefirearmy.blocks.tiles.TEFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.blocks.tiles.TEFireFurnace;
import com.shadowprince345.thefirearmy.blocks.tiles.TEGrindstone;
import net.minecraft.tileentity.TileEntityType;

public class FATileEntityTypes {

    public static TileEntityType<TEFireBlacksmithFurnace> TileEntityFireBlacksmithFurnace;
    public static TileEntityType<TEFireFurnace> TileEntityFireFurnace;
    public static TileEntityType<TEGrindstone> TileEntityGrindstone;

    public static void init(){
        TileEntityFireBlacksmithFurnace = TileEntityType.register("fire_blacksmith_furnace", TileEntityType.Builder.create(TEFireBlacksmithFurnace::new));
        TileEntityFireFurnace = TileEntityType.register("fire_furnace", TileEntityType.Builder.create(TEFireFurnace::new));
        TileEntityGrindstone = TileEntityType.register("grindstone", TileEntityType.Builder.create(TEGrindstone::new));
    }
}
