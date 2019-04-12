package com.shadowprince345.thefirearmy.objects.items;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private static List<Item> itemList = new ArrayList<>();

    public static ItemDev itemDev;

    public static void init(){
        itemDev = new ItemDev("item_dev");
    }

    public static List<Item> getItemList() {
        return itemList;
    }
}
