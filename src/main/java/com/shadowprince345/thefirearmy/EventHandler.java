package com.shadowprince345.thefirearmy;

import com.shadowprince345.thefirearmy.blocks.tiles.TEDev;
import com.shadowprince345.thefirearmy.blocks.tiles.TEFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.blocks.tiles.TEFireFurnace;
import com.shadowprince345.thefirearmy.blocks.tiles.TEGrindstone;
import com.shadowprince345.thefirearmy.creativetab.Tabs;
import com.shadowprince345.thefirearmy.init.Blocks;
import com.shadowprince345.thefirearmy.init.Items;
import com.shadowprince345.thefirearmy.lib.FBBRecipesManager;
import com.shadowprince345.thefirearmy.lib.GrindstoneRecipeManager;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class EventHandler {

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
        registryBlock.register(Blocks.blockFloorDrum);
        registryBlock.register(Blocks.blockFireFlowerCrop);
        registryBlock.register(Blocks.blockFireFurnace);
        registryBlock.register(Blocks.blockGrindstone);

        GameRegistry.registerTileEntity(TEFireBlacksmithFurnace.class, new ResourceLocation(TheFireArmy.getModId(),"fire_blacksmith_furnace"));
        GameRegistry.registerTileEntity(TEDev.class, new ResourceLocation(TheFireArmy.getModId(),"block_dev"));
        GameRegistry.registerTileEntity(TEFireFurnace.class, new ResourceLocation(TheFireArmy.getModId(),"fire_furnace"));
        GameRegistry.registerTileEntity(TEGrindstone.class, new ResourceLocation(TheFireArmy.getModId(),"grindstone"));
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
        registry.register(withName(Blocks.blockFloorDrum));
        registry.register(withName(Blocks.blockFireFlowerCrop));
        registry.register(withName(Blocks.blockFireFurnace));
        registry.register(withName(Blocks.blockGrindstone));

        registry.register(Items.itemDev);
        registry.register(Items.itemGoldPlate);
        registry.register(Items.itemIronPlate);
        registry.register(Items.itemFireFlowerSeed);
        registry.register(Items.fireSword);
        registry.register(Items.itemIronDust);
        registry.register(Items.itemGoldDust);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireFlower), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireLeaf), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireLog), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireSapling), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockDev), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireBlacksmithFurnace), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFirePlank), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireSlab.getSlab()), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireSlab.getDoubleSlab()), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFloorDrum), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockFireFurnace), 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Item.getItemFromBlock(Blocks.blockGrindstone), 0, "inventory");

        TheFireArmy.proxy.registerItemRenderer(Items.itemDev, 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Items.itemGoldPlate, 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Items.itemIronPlate, 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Items.itemFireFlowerSeed, 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Items.fireSword, 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Items.itemIronDust, 0, "inventory");
        TheFireArmy.proxy.registerItemRenderer(Items.itemGoldDust, 0, "inventory");
    }

    public static void registerOreDic(){
        OreDictionary.registerOre("plankWood", Blocks.blockFirePlank);
        OreDictionary.registerOre("logWood", Blocks.blockFireLog);
        OreDictionary.registerOre("treeSapling", Blocks.blockFireSapling);
        OreDictionary.registerOre("treeLeaves", Blocks.blockFireLeaf);
        OreDictionary.registerOre("slabWood", Blocks.blockFireSlab.getSlab());
    }

    @SubscribeEvent
    public static void registerFuel(FurnaceFuelBurnTimeEvent event){
        ItemStack stack = event.getItemStack();
        if(stack.getItem() == Item.getItemFromBlock(Blocks.blockFireFlower))
            event.setBurnTime(40000);
        if(stack.getItem() == Item.getItemFromBlock(Blocks.blockFireLog) ||
                stack.getItem() == Item.getItemFromBlock(Blocks.blockFireLeaf) ||
                stack.getItem() == Item.getItemFromBlock(Blocks.blockFirePlank))
            event.setBurnTime(600);
        if(stack.getItem() == Item.getItemFromBlock(Blocks.blockFireSlab.getSlab()))
            event.setBurnTime(200);
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event){
        FBBRecipesManager.instance.loadDefault();
        GrindstoneRecipeManager.instance.loadDefault();
        FurnaceRecipes.instance().addSmelting(Items.itemIronDust, new ItemStack(net.minecraft.init.Items.IRON_INGOT), 0.7F);
        FurnaceRecipes.instance().addSmelting(Items.itemGoldDust, new ItemStack(net.minecraft.init.Items.GOLD_INGOT), 1.0F);
    }
}
