package com.shadowprince345.thefirearmy.init;

import com.shadowprince345.thefirearmy.items.FireSword;
import com.shadowprince345.thefirearmy.items.ItemDev;
import net.minecraft.item.Item;

public class Items {
//    public static final ItemArmor.ArmorMaterial PLATED_IRON = EnumHelper.addArmorMaterial("plated_iron_material",TheFireArmy.getModId() + ":plated_iron_armor",20, new int[]{3,7,6,3}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0f);

    public static Item itemDev = new ItemDev(new Item.Properties());
//    public static Item itemGoldPlate = EventHandler.withName(new Item(), "gold_plate");
//    public static Item itemIronPlate = EventHandler.withName(new Item(), "iron_plate");
//    public static Item itemFireFlowerSeed = EventHandler.withName(new ItemSeeds(Blocks.blockFireFlowerCrop, net.minecraft.init.Blocks.FARMLAND), "fire_flower_seed");
    public static Item fireSword = new FireSword();
//    public static Item itemIronDust = EventHandler.withName(new Item(), "iron_dust");
//    public static Item itemGoldDust = EventHandler.withName(new Item(), "gold_dust");
//
//    public static ItemArmor armorPlatedIronHelmet = (ItemArmor) EventHandler.withName(new ItemArmor(PLATED_IRON, 1, EntityEquipmentSlot.HEAD), "plated_iron_helmet");
//    public static ItemArmor armorPlatedIronChestPlate = (ItemArmor) EventHandler.withName(new ItemArmor(PLATED_IRON, 1, EntityEquipmentSlot.CHEST), "plated_iron_chestplate");
//    public static ItemArmor armorPlatedIronLeggings = (ItemArmor) EventHandler.withName(new ItemArmor(PLATED_IRON, 1, EntityEquipmentSlot.LEGS), "plated_iron_leggings");
//    public static ItemArmor armorPlatedIronBoots = (ItemArmor) EventHandler.withName(new ItemArmor(PLATED_IRON, 1, EntityEquipmentSlot.FEET), "plated_iron_boots");
}
