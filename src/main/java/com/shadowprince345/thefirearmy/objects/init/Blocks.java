package com.shadowprince345.thefirearmy.objects.init;

import com.shadowprince345.thefirearmy.objects.blocks.BlockDev;
import com.shadowprince345.thefirearmy.objects.blocks.BlockFireFlower;
import com.shadowprince345.thefirearmy.objects.blocks.firetree.BlockFireLeaf;
import com.shadowprince345.thefirearmy.objects.blocks.firetree.BlockFireLog;
import com.shadowprince345.thefirearmy.objects.blocks.firetree.BlockFireSapling;
import com.shadowprince345.thefirearmy.objects.blocks.machines.fireblacksmithfurnace.BlockFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.objects.blocks.slabs.BlockModdedSlab;
import com.shadowprince345.thefirearmy.utils.RegistryEventHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Blocks {

    public static Block blockDev = RegistryEventHandler.withName(new BlockDev(), "block_dev");
    public static Block blockFireFlower = RegistryEventHandler.withName(new BlockFireFlower(), "block_fire_flower");
    public static Block blockFireLeaf = RegistryEventHandler.withName(new BlockFireLeaf(), "block_fire_leaf").setHardness(1f);
    public static Block blockFireLog = RegistryEventHandler.withName(new BlockFireLog(), "block_fire_log");
    public static Block blockFireSapling = RegistryEventHandler.withName(new BlockFireSapling(), "block_fire_sapling");
    public static Block blockFireBlacksmithFurnace = RegistryEventHandler.withName(new BlockFireBlacksmithFurnace(), "block_fire_blacksmith_furnace");
    public static Block blockFirePlank = RegistryEventHandler.withName(new Block(Material.WOOD).setHardness(0.3f), "block_fire_plank");
    public static BlockModdedSlab blockFireSlab = new BlockModdedSlab("block_fire_slab", Material.WOOD, 0.3f);
}
