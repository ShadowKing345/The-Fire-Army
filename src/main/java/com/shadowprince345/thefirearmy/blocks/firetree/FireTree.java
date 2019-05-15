package com.shadowprince345.thefirearmy.blocks.firetree;

import com.shadowprince345.thefirearmy.init.Blocks;
import net.minecraft.block.trees.AbstractTree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class FireTree extends AbstractTree {
    @Nullable
    @Override
    protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
        return new TreeFeature(true, 4, Blocks.blockFireLog.getDefaultState(), Blocks.blockFireLeaf.getDefaultState(), false);
    }
}
