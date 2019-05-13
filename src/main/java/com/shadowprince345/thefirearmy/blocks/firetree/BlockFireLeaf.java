//package com.shadowprince345.thefirearmy.blocks.firetree;
//
//import com.shadowprince345.thefirearmy.init.ItemGroups;
//import com.shadowprince345.thefirearmy.init.Blocks;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockLeaves;
//import net.minecraft.block.BlockPlanks;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.state.BlockStateContainer;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.IBlockAccess;
//import net.minecraft.world.World;
//
//import javax.annotation.Nonnull;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class BlockFireLeaf extends BlockLeaves{
//
//    public BlockFireLeaf() {
//        super(Block.Properties.create(Material.GLASS).needsRandomTick().doesNotBlockMovement().hardnessAndResistance(1f).sound(SoundType.GLASS));
////        this.setDefaultState(getBaseState().withProperty(CHECK_DECAY, Boolean.TRUE).withProperty(DECAYABLE, Boolean.TRUE));
////        setCreativeTab(ItemGroups.tab);
//    }
//
//    @Override
//    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
//        if(worldIn.rand.nextInt(chance) == 0)
//            spawnAsEntity(worldIn, pos, new ItemStack(net.minecraft.init.Items.BLAZE_POWDER));
//    }
//}
