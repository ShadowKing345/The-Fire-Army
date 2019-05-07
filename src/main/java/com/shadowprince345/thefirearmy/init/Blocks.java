package com.shadowprince345.thefirearmy.init;

import com.shadowprince345.thefirearmy.EventHandler;
import com.shadowprince345.thefirearmy.blocks.BlockDev;
import com.shadowprince345.thefirearmy.blocks.BlockFireFlower;
import com.shadowprince345.thefirearmy.blocks.BlockFireFlowerCrop;
import com.shadowprince345.thefirearmy.blocks.BlockFloorDrum;
import com.shadowprince345.thefirearmy.blocks.firetree.BlockFireLeaf;
import com.shadowprince345.thefirearmy.blocks.firetree.BlockFireLog;
import com.shadowprince345.thefirearmy.blocks.firetree.BlockFireSapling;
import com.shadowprince345.thefirearmy.blocks.machines.BlockFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.blocks.machines.BlockFireFurnace;
import com.shadowprince345.thefirearmy.blocks.machines.BlockGrindstone;
import com.shadowprince345.thefirearmy.blocks.slabs.BlockModdedSlab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Blocks {

    public static Block blockDev = EventHandler.withName(new BlockDev(), "block_dev");
    public static BlockFireFlower blockFireFlower = (BlockFireFlower) EventHandler.withName(new BlockFireFlower(), "fire_flower");
    public static BlockFireLeaf blockFireLeaf = (BlockFireLeaf) EventHandler.withName(new BlockFireLeaf(), "fire_leaf").setHardness(1f);
    public static BlockFireLog blockFireLog = (BlockFireLog) EventHandler.withName(new BlockFireLog(), "fire_log");
    public static BlockFireSapling blockFireSapling = (BlockFireSapling) EventHandler.withName(new BlockFireSapling(), "fire_sapling");
    public static BlockFireBlacksmithFurnace blockFireBlacksmithFurnace = (BlockFireBlacksmithFurnace) EventHandler.withName(new BlockFireBlacksmithFurnace(), "fire_blacksmith_furnace");
    public static Block blockFirePlank = EventHandler.withName(new Block(Material.WOOD).setHardness(0.3f), "fire_plank");
    public static BlockModdedSlab blockFireSlab = new BlockModdedSlab("fire_slab", Material.WOOD, 0.3f);
    public static BlockFloorDrum blockFloorDrum = (BlockFloorDrum) EventHandler.withName(new BlockFloorDrum(),"floor_drum");
    public static BlockFireFlowerCrop blockFireFlowerCrop = new BlockFireFlowerCrop();
    public static BlockFireFurnace blockFireFurnace = (BlockFireFurnace) EventHandler.withName(new BlockFireFurnace(),"fire_furnace");
    public static BlockGrindstone blockGrindstone = (BlockGrindstone) EventHandler.withName(new BlockGrindstone(), "grindstone");
}
