package com.shadowprince345.thefirearmy.utils;

import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.init.Blocks;
import com.shadowprince345.thefirearmy.init.Items;
import com.shadowprince345.thefirearmy.objects.creativetab.Tabs;
import com.shadowprince345.thefirearmy.objects.tiles.TileEntityFireBlacksmithFurnace;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class RegistryEventHandler {

    public static Block withName(Block block, String name){
        block.setRegistryName(name);
        block.setUnlocalizedName(name);
        block.setCreativeTab(Tabs.tab);
        return block;
    }

    public static Item withName(Item item, String name){
        item.setRegistryName(name);
        item.setUnlocalizedName(name);
        item.setCreativeTab(Tabs.tab);
        return item;
    }

    public static ItemBlock withName(Block item){
        ItemBlock itemBlock = new ItemBlock(item);
        itemBlock.setRegistryName(item.getRegistryName());
        itemBlock.setUnlocalizedName(item.getUnlocalizedName());
        return itemBlock;
    }


    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> blockEvent) {
        IForgeRegistry<Block> registryBlock = blockEvent.getRegistry();

        registryBlock.register(Blocks.blockFireFlower);
        registryBlock.register(Blocks.blockFireLeaf);
        registryBlock.register(Blocks.blockFireLog);
        registryBlock.register(Blocks.blockFireSapling);
        registryBlock.register(Blocks.blockDev);
        registryBlock.register(Blocks.blockFireBlacksmithFurnace);
        registryBlock.register(Blocks.blockFirePlank);
        registryBlock.register(Blocks.blockFireSlab.getSlab());
        registryBlock.register(Blocks.blockFireSlab.getDoubleSlab());

        GameRegistry.registerTileEntity(TileEntityFireBlacksmithFurnace.class, new ResourceLocation(TheFireArmy.getModId(),"block_fire_blacksmith_furnace"));
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        //ItemBlocks
        registry.register(withName(Blocks.blockFireFlower));
        registry.register(withName(Blocks.blockFireLeaf));
        registry.register(withName(Blocks.blockFireLog));
        registry.register(withName(Blocks.blockFireSapling));
        registry.register(withName(Blocks.blockDev));
        registry.register(withName(Blocks.blockFireBlacksmithFurnace));
        registry.register(withName(Blocks.blockFirePlank));
        registry.register(new ItemSlab(Blocks.blockFireSlab.getSlab(), Blocks.blockFireSlab.getSlab(), Blocks.blockFireSlab.getDoubleSlab()).setUnlocalizedName(Blocks.blockFireSlab.getSlab().getUnlocalizedName()).setRegistryName(Blocks.blockFireSlab.getSlab().getRegistryName()));
        registry.register(withName(Blocks.blockFireSlab.getDoubleSlab()));

        registry.register(Items.itemDev);
    }

    @SubscribeEvent
    public static void RegisterModels(ModelRegistryEvent event) {
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireFlower), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireLeaf), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireLog), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireSapling), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockDev), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireBlacksmithFurnace), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFirePlank), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireSlab.getSlab()), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireSlab.getDoubleSlab()), 0, "inventory");

        TheFireArmy.proxy.registerItemRenderer(Items.itemDev, 0, "inventory");
    }

    @SubscribeEvent
    public static void RegisterRecepies(RegistryEvent.Register<IRecipe> event){
    }
}
