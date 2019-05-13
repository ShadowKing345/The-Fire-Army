//package com.shadowprince345.thefirearmy.blocks.firetree;
//
//import com.shadowprince345.thefirearmy.init.Blocks;
//import net.minecraft.block.BlockBush;
//import net.minecraft.block.IGrowable;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.properties.IProperty;
//import net.minecraft.block.properties.PropertyInteger;
//import net.minecraft.block.state.BlockStateContainer;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraft.world.gen.feature.WorldGenTrees;
//import net.minecraft.world.gen.feature.WorldGenerator;
//import net.minecraftforge.event.terraingen.TerrainGen;
//
//import java.util.Random;
//
//public class BlockFireSapling extends BlockBush implements IGrowable{
//    private static PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
//
//    public BlockFireSapling() {
//        this.setDefaultState(getBlockState().getBaseState().withProperty(STAGE, 0));
//        setSoundType(SoundType.GLASS);
//    }
//
//    @Override
//    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
//        if(worldIn.isRemote){
//            super.updateTick(worldIn, pos, state, rand);
//
//            if(!worldIn.isAreaLoaded(pos, 1)) return;
//            this.grow(worldIn, rand, pos, state);
//        }
//    }
//
//    @Override
//    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
//        return true;
//    }
//
//    @Override
//    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
//        return true;
//    }
//
//    @Override
//    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
//        if(state.getValue(STAGE) == 0)
//            worldIn.setBlockState(pos, state.cycleProperty(STAGE));
//        else
//            generateTree(worldIn, rand, pos, state);
//    }
//
//    public void generateTree(World worldIn, Random rand, BlockPos pos, IBlockState state){
//        if(!TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
//        WorldGenerator worldgenerator = new WorldGenTrees(true, 5, Blocks.blockFireLog.getDefaultState(), Blocks.blockFireLeaf.getDefaultState(), false);
//        boolean flag = false;
//        int i = 0;
//        int j = 0;
//
//        if (flag)
//        {
//            worldIn.setBlockToAir(pos.add(i, 0, j));
//            worldIn.setBlockToAir(pos.add(i + 1, 0, j));
//            worldIn.setBlockToAir(pos.add(i, 0, j + 1));
//            worldIn.setBlockToAir(pos.add(i + 1, 0, j + 1));
//        }
//        else
//        {
//            worldIn.setBlockToAir(pos);
//        }
//
//        if (!worldgenerator.generate(worldIn, rand, pos.add(i, 0, j)))
//        {
//            if (flag)
//            {
//                worldIn.setBlockState(pos.add(i, 0, j), state, 4);
//                worldIn.setBlockState(pos.add(i + 1, 0, j), state, 4);
//                worldIn.setBlockState(pos.add(i, 0, j + 1), state, 4);
//                worldIn.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
//            }
//            else
//            {
//                worldIn.setBlockState(pos, state, 4);
//            }
//        }
//
//    }
//
//    @Override
//    public IBlockState getStateFromMeta(int meta) {
//        return this.getDefaultState().withProperty(STAGE, Integer.valueOf((meta) >> 3));
//    }
//
//    @Override
//    public int getMetaFromState(IBlockState state) {
//        int i = 0;
//        i = i | state.getValue(STAGE) << 3;
//        return i;
//    }
//
//    @Override
//    protected BlockStateContainer createBlockState() {
//        return new BlockStateContainer(this, new IProperty[] {STAGE});
//    }
//}
