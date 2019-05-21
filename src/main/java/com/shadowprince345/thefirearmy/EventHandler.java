package com.shadowprince345.thefirearmy;

import com.shadowprince345.thefirearmy.init.Blocks;
import com.shadowprince345.thefirearmy.init.FATileEntityTypes;
import com.shadowprince345.thefirearmy.init.ItemGroups;
import com.shadowprince345.thefirearmy.init.Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {

    public static Block withName(Block block, String registryName){
        block.setRegistryName(registryName);
        return block;
    }

    public static Item withName(Item item, String registryName){
        item.setRegistryName(registryName);
        return item;
    }

    private static ItemBlock withName(Block block){
        ItemBlock result = new ItemBlock(block, new Item.Properties().group(ItemGroups.tab));
        result.setRegistryName(block.getRegistryName());
        return result;
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> blockEvent) {
        IForgeRegistry<Block> registryBlock = blockEvent.getRegistry();

        registryBlock.register(Blocks.blockDev);
        registryBlock.register(Blocks.blockFireFlower);
        registryBlock.register(Blocks.blockFireLeaf);
        registryBlock.register(Blocks.blockFireLog);
        registryBlock.register(Blocks.blockFireSapling);
        registryBlock.register(Blocks.blockFireBlacksmithFurnace);
        registryBlock.register(Blocks.blockFirePlank);
        registryBlock.register(Blocks.blockFireSlab);
        registryBlock.register(Blocks.blockFloorDrum);
        registryBlock.register(Blocks.blockFireFlowerCrop);
        registryBlock.register(Blocks.blockFireFurnace);
        registryBlock.register(Blocks.blockGrindstone);
    }

    @SubscribeEvent
    public static void onTileEntityRegister(RegistryEvent.Register<TileEntityType<?>> tileEntityEvent){
        IForgeRegistry<TileEntityType<?>> registry = tileEntityEvent.getRegistry();
        FATileEntityTypes.init();
        registry.register(FATileEntityTypes.TileEntityFireBlacksmithFurnace);
        registry.register(FATileEntityTypes.TileEntityFireFurnace);
        registry.register(FATileEntityTypes.TileEntityGrindstone);
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        //ItemBlocks
        registry.register(withName(Blocks.blockDev));
        registry.register(withName(Blocks.blockFireFlower));
        registry.register(withName(Blocks.blockFireLeaf));
        registry.register(withName(Blocks.blockFireLog));
        registry.register(withName(Blocks.blockFireSapling));
        registry.register(withName(Blocks.blockFireBlacksmithFurnace));
        registry.register(withName(Blocks.blockFirePlank));
        registry.register(withName(Blocks.blockFireSlab));
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

        registry.register(Items.armorPlatedIronHelmet);
        registry.register(Items.armorPlatedIronChestPlate);
        registry.register(Items.armorPlatedIronLeggings);
        registry.register(Items.armorPlatedIronBoots);
    }

//    public static void registerOreDic(){
//        OreDictionary.registerOre("plankWood", Blocks.blockFirePlank);
//        OreDictionary.registerOre("logWood", Blocks.blockFireLog);
//        OreDictionary.registerOre("treeSapling", Blocks.blockFireSapling);
//        OreDictionary.registerOre("treeLeaves", Blocks.blockFireLeaf);
//        OreDictionary.registerOre("slabWood", Blocks.blockFireSlab.getSlab());
//    }
//
    @SubscribeEvent
    public static void registerFuel(FurnaceFuelBurnTimeEvent event){
        ItemStack stack = event.getItemStack();
        if(stack.getItem().equals(new ItemBlock(Blocks.blockFireFlower, new Item.Properties())))
            event.setBurnTime(40000);
        if(stack.getItem().equals(new ItemBlock(Blocks.blockFireLog, new Item.Properties())) ||
                stack.getItem().equals(new ItemBlock(Blocks.blockFireLeaf, new Item.Properties())) ||
                stack.getItem().equals(new ItemBlock(Blocks.blockFirePlank, new Item.Properties())))
            event.setBurnTime(600);
        if(stack.getItem().equals(new ItemBlock(Blocks.blockFireSlab, new Item.Properties())))
            event.setBurnTime(200);
    }

//    @SubscribeEvent
//    public static void registerRecipes(RegistryEvent.Register<IRecipe> event){
//        FBBRecipesManager.instance.loadDefault();
//        GrindstoneRecipeManager.instance.loadDefault();
//        FurnaceRecipes.instance().addSmelting(Items.itemIronDust, new ItemStack(net.minecraft.init.Items.IRON_INGOT), 0.7F);
//        FurnaceRecipes.instance().addSmelting(Items.itemGoldDust, new ItemStack(net.minecraft.init.Items.GOLD_INGOT), 1.0F);
//    }
}
