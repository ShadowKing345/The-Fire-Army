package com.shadowprince345.thefirearmy.utils;

import com.shadowprince345.thefirearmy.objects.blocks.Blocks;
import com.shadowprince345.thefirearmy.objects.items.Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class Registry {

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(Blocks.getBlockList().toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(Items.getItemList().toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void RegisterModels(ModelRegistryEvent event) {
        for (Item item : Items.getItemList())
            if (item instanceof IHasModel) ((IHasModel) item).registerModels();

        for (Block block : Blocks.getBlockList())
            if (block instanceof IHasModel) ((IHasModel) block).registerModels();
    }
}
