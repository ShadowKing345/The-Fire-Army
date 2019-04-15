package com.shadowprince345.thefirearmy.init;

import com.shadowprince345.thefirearmy.objects.items.ItemDev;
import com.shadowprince345.thefirearmy.utils.RegistryEventHandler;
import net.minecraft.item.Item;

public class Items {

    public static Item itemDev = RegistryEventHandler.withName(new ItemDev(), "item_dev");

}
