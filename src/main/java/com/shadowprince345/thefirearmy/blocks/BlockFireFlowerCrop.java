//package com.shadowprince345.thefirearmy.blocks;
//
//import com.shadowprince345.thefirearmy.init.Blocks;
//import com.shadowprince345.thefirearmy.init.Items;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockCrops;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.Material;
//import net.minecraft.item.Item;
//
//public class BlockFireFlowerCrop extends BlockCrops {
//
//    public BlockFireFlowerCrop() {
//        super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().needsRandomTick().sound(SoundType.GLASS));
//        setRegistryName("fire_flower_crop");
//    }
//
////    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
////        if(rand.nextBoolean() && stateIn.getValue(this.AGE) == 7)
////            worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + rand.nextFloat(), pos.getY()  + rand.nextFloat(), pos.getZ()  + rand.nextFloat(), 0,rand.nextFloat()/20,0);
////    }
//
//    protected Item getSeed() {
//        return Items.itemFireFlowerSeed;
//    }
//
//    protected Item getCrop() {
//        return Item.getItemFromBlock(Blocks.blockFireFlower);
//    }
//}
