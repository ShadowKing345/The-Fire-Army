package com.shadowprince345.thefirearmy.init;

import com.shadowprince345.thefirearmy.EventHandler;
import com.shadowprince345.thefirearmy.items.FireSword;
import com.shadowprince345.thefirearmy.items.ItemDev;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.common.util.EnumHelper;

public class Items {
    public static final Item.ToolMaterial FIRE_WOOD_MATRTIAL = EnumHelper.addToolMaterial("FIRE_WOOD",0,80, 2.0F, 0.5F, 15);

    public static Item itemDev = EventHandler.withName(new ItemDev(), "item_dev");
    public static Item itemGoldPlate = EventHandler.withName(new Item(), "gold_plate");
    public static Item itemIronPlate = EventHandler.withName(new Item(), "iron_plate");
    public static Item itemFireFlowerSeed = EventHandler.withName(new ItemSeeds(Blocks.blockFireFlowerCrop, net.minecraft.init.Blocks.FARMLAND), "fire_flower_seed");
    public static Item fireSword = new FireSword();
}
