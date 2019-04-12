package com.shadowprince345.thefirearmy.objects.blocks.firetree;

import com.shadowprince345.thefirearmy.Main;
import com.shadowprince345.thefirearmy.objects.blocks.Blocks;
import com.shadowprince345.thefirearmy.objects.creativetab.Tabs;
import com.shadowprince345.thefirearmy.objects.items.Items;
import com.shadowprince345.thefirearmy.utils.IHasModel;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockFireLeaf extends BlockLeaves implements IHasModel {

    public BlockFireLeaf() {
        String registryName = "block_fire_leaf";

        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.TRUE).withProperty(DECAYABLE, Boolean.TRUE));

        setRegistryName(registryName);
        setUnlocalizedName(registryName);
        setSoundType(SoundType.GLASS);
        setLightLevel(1f);

        setCreativeTab(Tabs.tab);

        Blocks.getBlockList().add(this);
        Items.getItemList().add(new ItemBlock(this).setRegistryName(registryName).setUnlocalizedName(registryName));

    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(this));
        return list;
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 4) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if (!state.getValue(DECAYABLE)) {
            i |= 4;
        }

        if (!state.getValue(CHECK_DECAY)) {
            i |= 8;
        }

        return i;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(Blocks.blockFireSapling);
    }

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
        if(worldIn.rand.nextInt(chance) == 0)
            spawnAsEntity(worldIn, pos, new ItemStack(net.minecraft.init.Items.BLAZE_POWDER));
    }
}
