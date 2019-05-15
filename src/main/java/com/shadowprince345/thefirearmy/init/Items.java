package com.shadowprince345.thefirearmy.init;

import com.shadowprince345.thefirearmy.EventHandler;
import com.shadowprince345.thefirearmy.items.FireSword;
import com.shadowprince345.thefirearmy.items.ItemDev;
import com.shadowprince345.thefirearmy.items.materials.Materials;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSeeds;

public class Items {

    public static Item itemDev = new ItemDev(new Item.Properties());
    public static Item itemGoldPlate = EventHandler.withName(new Item(new Item.Properties().group(ItemGroups.tab)), "gold_plate");
    public static Item itemIronPlate = EventHandler.withName(new Item(new Item.Properties().group(ItemGroups.tab)), "iron_plate");
    public static Item itemFireFlowerSeed = EventHandler.withName(new ItemSeeds(Blocks.blockFireFlowerCrop, new Item.Properties().group(ItemGroups.tab)), "fire_flower_seed");
    public static Item fireSword = new FireSword();
    public static Item itemIronDust = EventHandler.withName(new Item(new Item.Properties().group(ItemGroups.tab)), "iron_dust");
    public static Item itemGoldDust = EventHandler.withName(new Item(new Item.Properties().group(ItemGroups.tab)), "gold_dust");

    public static ItemArmor armorPlatedIronHelmet = (ItemArmor) EventHandler.withName(new ItemArmor(Materials.armorPlatedIron, EntityEquipmentSlot.HEAD, new Item.Properties().group(ItemGroups.tab)), "plated_iron_helmet");
    public static ItemArmor armorPlatedIronChestPlate = (ItemArmor) EventHandler.withName(new ItemArmor(Materials.armorPlatedIron, EntityEquipmentSlot.CHEST, new Item.Properties().group(ItemGroups.tab)), "plated_iron_chestplate");
    public static ItemArmor armorPlatedIronLeggings = (ItemArmor) EventHandler.withName(new ItemArmor(Materials.armorPlatedIron, EntityEquipmentSlot.LEGS, new Item.Properties().group(ItemGroups.tab)), "plated_iron_leggings");
    public static ItemArmor armorPlatedIronBoots = (ItemArmor) EventHandler.withName(new ItemArmor(Materials.armorPlatedIron, EntityEquipmentSlot.FEET, new Item.Properties().group(ItemGroups.tab)), "plated_iron_boots");
}
