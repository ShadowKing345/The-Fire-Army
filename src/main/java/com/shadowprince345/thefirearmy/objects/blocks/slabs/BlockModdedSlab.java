package com.shadowprince345.thefirearmy.objects.blocks.slabs;

import net.minecraft.block.material.Material;

public class BlockModdedSlab {
    private BlockSlabBase slab;
    private BlockDoubleSlabBase doubleSlab;

    public BlockModdedSlab(String registryName, Material material) {
        slab = new BlockSlabBase(registryName, material);
        doubleSlab = new BlockDoubleSlabBase(registryName, material, slab);
    }

    public BlockModdedSlab(String registryName, Material material, float hardness) {
        this(registryName, material);

        slab.setHardness(hardness);
        doubleSlab.setHardness(hardness * 2);
    }

    public BlockSlabBase getSlab() {
        return slab;
    }

    public BlockDoubleSlabBase getDoubleSlab() {
        return doubleSlab;
    }
}
