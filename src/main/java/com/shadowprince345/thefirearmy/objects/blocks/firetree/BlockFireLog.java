package com.shadowprince345.thefirearmy.objects.blocks.firetree;

import com.shadowprince345.thefirearmy.Main;
import com.shadowprince345.thefirearmy.objects.blocks.Blocks;
import com.shadowprince345.thefirearmy.objects.creativetab.Tabs;
import com.shadowprince345.thefirearmy.objects.items.Items;
import com.shadowprince345.thefirearmy.utils.IHasModel;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFireLog extends BlockLog implements IHasModel {
    public BlockFireLog() {
        String registryName = "block_fire_log";

        setRegistryName(registryName);
        setUnlocalizedName(registryName);
        setCreativeTab(Tabs.tab);

        setDefaultState(this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y));
        setHardness(12.5f);
        setHarvestLevel("axe", 2);

        Blocks.getBlockList().add(this);
        Items.getItemList().add(new ItemBlock(this).setRegistryName(registryName).setUnlocalizedName(registryName));
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getStateFromMeta(meta).withProperty(AXIS, facing.getAxis());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AXIS);
    }

    @Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return true;
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
