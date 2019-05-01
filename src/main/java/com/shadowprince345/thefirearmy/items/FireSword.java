package com.shadowprince345.thefirearmy.items;

import com.shadowprince345.thefirearmy.creativetab.Tabs;
import com.shadowprince345.thefirearmy.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import java.util.Random;

public class FireSword extends ItemSword {
    public FireSword() {
        super(Items.FIRE_WOOD_MATRTIAL);

        setRegistryName("fire_wood_sword");
        setUnlocalizedName("fire_wood_sword");
        setCreativeTab(Tabs.tab);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        Random random = worldIn.rand;
        int i = Math.abs(random.nextInt());
        if(1000 > i) entityIn.setFire(3600);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        Random random = attacker.world.rand;
        int i = random.nextInt(100);
        if(i < 46) target.setFire(20);
        return super.hitEntity(stack, target, attacker);
    }
}
