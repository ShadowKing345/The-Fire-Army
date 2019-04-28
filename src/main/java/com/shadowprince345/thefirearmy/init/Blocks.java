package com.shadowprince345.thefirearmy.init;

import com.shadowprince345.thefirearmy.RegistryEventHandler;
import com.shadowprince345.thefirearmy.blocks.BlockDev;
import com.shadowprince345.thefirearmy.blocks.BlockFireFlower;
import com.shadowprince345.thefirearmy.blocks.BlockFireFlowerCrop;
import com.shadowprince345.thefirearmy.blocks.BlockFloorDrum;
import com.shadowprince345.thefirearmy.blocks.firetree.BlockFireLeaf;
import com.shadowprince345.thefirearmy.blocks.firetree.BlockFireLog;
import com.shadowprince345.thefirearmy.blocks.firetree.BlockFireSapling;
import com.shadowprince345.thefirearmy.blocks.machines.BlockFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.blocks.slabs.BlockModdedSlab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Blocks {

    public static Block blockDev = RegistryEventHandler.withName(new BlockDev(), "block_dev");
    public static BlockFireFlower blockFireFlower = (BlockFireFlower) RegistryEventHandler.withName(new BlockFireFlower(), "fire_flower");
    public static BlockFireLeaf blockFireLeaf = (BlockFireLeaf) RegistryEventHandler.withName(new BlockFireLeaf(), "fire_leaf").setHardness(1f);
    public static BlockFireLog blockFireLog = (BlockFireLog) RegistryEventHandler.withName(new BlockFireLog(), "fire_log");
    public static BlockFireSapling blockFireSapling = (BlockFireSapling) RegistryEventHandler.withName(new BlockFireSapling(), "fire_sapling");
    public static BlockFireBlacksmithFurnace blockFireBlacksmithFurnace = (BlockFireBlacksmithFurnace) RegistryEventHandler.withName(new BlockFireBlacksmithFurnace(), "fire_blacksmith_furnace");
    public static Block blockFirePlank = RegistryEventHandler.withName(new Block(Material.WOOD).setHardness(0.3f), "fire_plank");
    public static BlockModdedSlab blockFireSlab = new BlockModdedSlab("fire_slab", Material.WOOD, 0.3f);
    public static BlockFloorDrum blockFloorDrum = (BlockFloorDrum) RegistryEventHandler.withName(new BlockFloorDrum(),"floor_drum");
    public static BlockFireFlowerCrop blockFireFlowerCrop = new BlockFireFlowerCrop();
}
