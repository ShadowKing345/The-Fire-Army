package com.shadowprince345.thefirearmy.objects.blocks;

import com.shadowprince345.thefirearmy.objects.blocks.firetree.BlockFireLeaf;
import com.shadowprince345.thefirearmy.objects.blocks.firetree.BlockFireLog;
import com.shadowprince345.thefirearmy.objects.blocks.firetree.BlockFireSapling;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Blocks {
    private static List<Block> blockList = new ArrayList<>();

    public static BlockDev blockDev;
    public static BlockFireFlower blockFireFlower;
    public static BlockFireLeaf blockFireLeaf;
    public static BlockFireLog blockFireLog;
    public static BlockFireSapling blockFireSapling;

    public static void init(){
        blockDev = new BlockDev("block_dev");
        blockFireFlower = new BlockFireFlower();
        blockFireLeaf = new BlockFireLeaf();
        blockFireLog = new BlockFireLog();
        blockFireSapling = new BlockFireSapling();
    }

    public static List<Block> getBlockList() {
        return blockList;
    }
}
