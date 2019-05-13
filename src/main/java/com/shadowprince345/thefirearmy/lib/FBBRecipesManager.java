//package com.shadowprince345.thefirearmy.lib;
//
//import com.shadowprince345.thefirearmy.TheFireArmy;
//import com.shadowprince345.thefirearmy.api.gui.ItemHandlerCrafting;
//import com.shadowprince345.thefirearmy.api.recipe.FBBRecipeApi;
//import com.shadowprince345.thefirearmy.api.recipe.IFBBRecipe;
//import com.shadowprince345.thefirearmy.init.Blocks;
//import com.shadowprince345.thefirearmy.init.Items;
//import com.sun.istack.internal.NotNull;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.crafting.IRecipe;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.RegistryEvent;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FBBRecipesManager {
//    public static final FBBRecipesManager instance = new FBBRecipesManager();
//    private static List<IFBBRecipe> theList = new ArrayList<>();
//    private static FBBRecipeApi REGISTRY = null;
//
//    public static void initialise(){
//        REGISTRY = new FBBRecipeApi(instance);
//        MinecraftForge.EVENT_BUS.register(new FBBRecipesManager());
//    }
//
//    @SubscribeEvent
//    public void event(RegistryEvent.Register<IRecipe> event){
//        loadDefault();
//    }
//
//    public void add(IFBBRecipe recipe) {
//        theList.add(recipe);
//    }
//
//    public List<IFBBRecipe> getRecipes(){
//        return theList;
//    }
//
//    public void loadDefault(){
//        add("minecraft:iron_ingot, minecraft:iron_ingot",
//                new ItemStack(com.shadowprince345.thefirearmy.init.Items.itemIronPlate),
//                10);
//
//        add("minecraft:gold_ingot, minecraft:gold_ingot",
//                new ItemStack(com.shadowprince345.thefirearmy.init.Items.itemGoldPlate),
//                10);
//
//        add("firearmy:fire_plank,minecraft:leather,firearmy:fire_plank," +
//                        "firearmy:gold_plate, firearmy:fire_flower, firearmy:gold_plate," +
//                        "firearmy:fire_plank, firearmy:fire_plank, firearmy:fire_plank",
//                new ItemStack(com.shadowprince345.thefirearmy.init.Blocks.blockFloorDrum), 600);
//
//        add("firearmy:fire_flower", new ItemStack(com.shadowprince345.thefirearmy.init.Items.itemFireFlowerSeed), 200);
//
//        add("minecraft:air, firearmy:fire_plank, minecraft:air,"+
//                "minecraft:air, firearmy:fire_plank, minecraft:air," +
//                "minecraft:air, minecraft:blaze_rod, minecraft:air",
//                new ItemStack(Items.fireSword), 200);
//
//        add("firearmy:iron_plate, minecraft:stone, firearmy:iron_plate,"+
//                        "firearmy:iron_plate, firearmy:fire_flower, firearmy:iron_plate," +
//                        "firearmy:iron_plate, minecraft:stone, firearmy:iron_plate",
//                new ItemStack(Blocks.blockFireFurnace), 800);
//
//        add(new ItemStack(Blocks.blockGrindstone), 800,
//                ItemStack.EMPTY, new ItemStack(net.minecraft.init.Items.STICK), ItemStack.EMPTY,
//                ItemStack.EMPTY, new ItemStack(net.minecraft.init.Blocks.STONE, 1, 6), new ItemStack(net.minecraft.init.Blocks.SANDSTONE, 1, 0),
//                new ItemStack(net.minecraft.init.Blocks.PLANKS, 1, 0), ItemStack.EMPTY, new ItemStack(net.minecraft.init.Blocks.PLANKS, 1, 0));
//
//        add(new ItemStack(Items.armorPlatedIronHelmet), 1600,
//                new ItemStack(Items.itemIronPlate), new ItemStack(Items.itemIronPlate), new ItemStack(Items.itemIronPlate),
//                new ItemStack(Items.itemIronPlate), ItemStack.EMPTY, new ItemStack(Items.itemIronPlate),
//                ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY);
//
//        add(new ItemStack(Items.armorPlatedIronChestPlate), 1600,
//                new ItemStack(Items.itemIronPlate), ItemStack.EMPTY, new ItemStack(Items.itemIronPlate),
//                new ItemStack(Items.itemIronPlate), new ItemStack(Items.itemIronPlate), new ItemStack(Items.itemIronPlate),
//                new ItemStack(Items.itemIronPlate), new ItemStack(Items.itemIronPlate), new ItemStack(Items.itemIronPlate));
//
//        add(new ItemStack(Items.armorPlatedIronLeggings), 1600,
//                new ItemStack(Items.itemIronPlate), new ItemStack(Items.itemIronPlate), new ItemStack(Items.itemIronPlate),
//                new ItemStack(Items.itemIronPlate), ItemStack.EMPTY, new ItemStack(Items.itemIronPlate),
//                new ItemStack(Items.itemIronPlate), ItemStack.EMPTY, new ItemStack(Items.itemIronPlate));
//
//        add(new ItemStack(Items.armorPlatedIronBoots), 1600,
//                new ItemStack(Items.itemIronPlate), ItemStack.EMPTY, new ItemStack(Items.itemIronPlate),
//                new ItemStack(Items.itemIronPlate), ItemStack.EMPTY, new ItemStack(Items.itemIronPlate),
//                ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY);
//    }
//
//    @NotNull
//    private void add(String inputs, ItemStack output, int cost){
//        inputs = inputs.replace(" ", "");
//        String[] s = inputs.split(",");
//
//        NonNullList<ItemStack> ingredients = NonNullList.withSize(9, ItemStack.EMPTY);
//
//        for(int i = 0; i < s.length; i++){
//            if(i > 8) {
//                TheFireArmy.LOGGER.warn("Recipe for '" + output.getItem().getRegistryName() + "' has more then 9 items as ingredients. The recipe still works but the any ingredients after this will be ignored.");
//                break;
//            }
//
//            ItemStack stack = new ItemStack(Item.REGISTRY.getObject(new ResourceLocation(s[i])));
//            if(stack == null){
//                TheFireArmy.LOGGER.error("Unknown Item: '" + s + "'. Skipping recipe");
//                return;
//            }else{
//                if(stack.getItem() == net.minecraft.init.Items.AIR)
//                    ingredients.set(i, ItemStack.EMPTY);
//                else
//                    ingredients.set(i, stack);
//            }
//        }
//
//        add(new SimpleFBBRecipe(ingredients, output, cost));
//    }
//
//    public void add(ItemStack output, int cost, ItemStack... inputs){
//        NonNullList<ItemStack> ingredients = NonNullList.withSize(9, ItemStack.EMPTY);
//
//        for(int i = 0; i < inputs.length; i++){
//            if(i > 8) {
//                TheFireArmy.LOGGER.warn("Recipe for '" + output.getItem().getRegistryName() + "' has more then 9 items as ingredients. The recipe still works but the any ingredients after this will be ignored.");
//                break;
//            }
//
//            ingredients.set(i, inputs[i]);
//        }
//
//        add(new SimpleFBBRecipe(ingredients, output, cost));
//    }
//
//    public IFBBRecipe findRecipe(ItemHandlerCrafting craftingMatrix){
//        NonNullList<ItemStack> inputs = NonNullList.withSize(9,ItemStack.EMPTY);
//
//        for(int i = 0; i < craftingMatrix.getSlots(); i++){
//            if(i > 8)
//                break;
//
//            inputs.set(i, craftingMatrix.getStackInSlot(i));
//        }
//
//        for(IFBBRecipe recipe: theList){
//            if(recipe.matches(inputs))
//                return recipe;
//        }
//
//        return null;
//    }
//}