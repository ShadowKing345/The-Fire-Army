package com.shadowprince345.thefirearmy.items;

import com.shadowprince345.thefirearmy.init.ItemGroups;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;

import java.util.Random;

public class FireSword extends ItemSword {
    public FireSword() {
        super(new FIRE_WOOD_MATERIAL(), 3, -2.4f, new Item.Properties().group(ItemGroups.tab));

        setRegistryName("fire_wood_sword");
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity player, int inventorySlot, boolean isSelected) {
        Random random = world.rand;
        int i = Math.abs(random.nextInt());
        if(1000 > i) player.setFire(3600);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        Random random = attacker.world.rand;
        int i = random.nextInt(100);
        if(i < 46) target.setFire(20);
        return super.hitEntity(stack, target, attacker);
    }

    public static class FIRE_WOOD_MATERIAL implements IItemTier {

        @Override
        public int getMaxUses() {
            return 80;
        }

        @Override
        public float getEfficiency() {
            return 2.0f;
        }

        @Override
        public float getAttackDamage() {
            return 1.0f;
        }

        @Override
        public int getHarvestLevel() {
            return 0;
        }

        @Override
        public int getEnchantability() {
            return 15;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.EMPTY;
        }
    }
}
