package com.shadowprince345.thefirearmy.init;

import com.shadowprince345.thefirearmy.objects.items.ItemDev;
import com.shadowprince345.thefirearmy.utils.RegistryEventHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class Items {

    public static Item itemDev = RegistryEventHandler.withName(new ItemDev(), "item_dev");
    public static Item itemGoldPlate = RegistryEventHandler.withName(new Item(), "gold_plate");
    public static Item itemIronPlate = RegistryEventHandler.withName(new Item(), "iron_plate");
    public static Item itemFireFlowerSeed = RegistryEventHandler.withName(new ItemSeeds(Blocks.blockFireFlowerCrop, net.minecraft.init.Blocks.FARMLAND), "fire_flower_seed");

}
