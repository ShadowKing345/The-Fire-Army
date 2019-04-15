package com.shadowprince345.thefirearmy.init;

import com.shadowprince345.thefirearmy.objects.blocks.BlockDev;
import com.shadowprince345.thefirearmy.objects.blocks.BlockFireFlower;
import com.shadowprince345.thefirearmy.objects.blocks.firetree.BlockFireLeaf;
import com.shadowprince345.thefirearmy.objects.blocks.firetree.BlockFireLog;
import com.shadowprince345.thefirearmy.objects.blocks.firetree.BlockFireSapling;
import com.shadowprince345.thefirearmy.objects.blocks.machines.BlockFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.objects.blocks.slabs.BlockModdedSlab;
import com.shadowprince345.thefirearmy.utils.RegistryEventHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Blocks {

    public static Block blockDev = RegistryEventHandler.withName(new BlockDev(), "block_dev");
    public static Block blockFireFlower = RegistryEventHandler.withName(new BlockFireFlower(), "fire_flower");
    public static Block blockFireLeaf = RegistryEventHandler.withName(new BlockFireLeaf(), "fire_leaf").setHardness(1f);
    public static Block blockFireLog = RegistryEventHandler.withName(new BlockFireLog(), "fire_log");
    public static Block blockFireSapling = RegistryEventHandler.withName(new BlockFireSapling(), "fire_sapling");
    public static Block blockFireBlacksmithFurnace = RegistryEventHandler.withName(new BlockFireBlacksmithFurnace(), "fire_blacksmith_furnace");
    public static Block blockFirePlank = RegistryEventHandler.withName(new Block(Material.WOOD).setHardness(0.3f), "fire_plank");
    public static BlockModdedSlab blockFireSlab = new BlockModdedSlab("fire_slab", Material.WOOD, 0.3f);
}
