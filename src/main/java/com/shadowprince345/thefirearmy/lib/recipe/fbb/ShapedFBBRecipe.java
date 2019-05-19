package com.shadowprince345.thefirearmy.lib.recipe.fbb;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.lib.recipe.FARecipeSerializers;
import com.shadowprince345.thefirearmy.lib.recipe.FARecipeTypes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.RecipeType;

import java.util.Map;
import java.util.Set;

public class ShapedFBBRecipe implements IFBBRecipe {
    static int MAX_H = 3;
    static int MAX_W = 3;
    NonNullList<Ingredient> inputs;
    ItemStack output;
    int cost;
    ResourceLocation id;

    public ShapedFBBRecipe(ResourceLocation id, NonNullList<Ingredient> inputs, ItemStack output, int cost) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
        this.cost = cost;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        for(int i  = 0; i <= inv.getWidth() - MAX_W; i++) {
            for (int j = 0; j <= inv.getHeight() - MAX_H; j++) {
                if (checkMatch(inv, i, j, true))
                    return true;
                if (checkMatch(inv, i, j, false))
                    return true;
            }
        }
        return false;
    }

    private boolean checkMatch(IInventory craftingInventory, int I, int J, boolean flag){
        for(int i = 0; i < craftingInventory.getWidth(); i++) {
            for (int j = 0; j < craftingInventory.getHeight(); j++) {
                int k = i - I;
                int l = j - J;
                Ingredient ingredient = Ingredient.EMPTY;
                if(k >= 0 && l >= 0 && k < MAX_W && l < MAX_H){
                    if(flag)
                        ingredient = inputs.get(MAX_W - k - 1 + l * MAX_W);
                    else
                        ingredient = inputs.get(k + l * MAX_W);
                }

                if(!ingredient.test(craftingInventory.getStackInSlot(i + j * craftingInventory.getWidth())))
                    return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return output;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width == MAX_W && height == MAX_H;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return FARecipeSerializers.FBB;
    }

    @Override
    public RecipeType<? extends IRecipe> getType() {
        return FARecipeTypes.FIRE_BLACKSMITH_FURNACE;
    }

    private static Map<String, Ingredient> deserializeKey(JsonObject json){
        Map<String, Ingredient> map = Maps.newHashMap();

        for(Map.Entry<String, JsonElement> entry : json.entrySet()){
            if(entry.getKey().length() != 1)
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character long).");

            if(entry.getKey().equals(" "))
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved word.");

            map.put(entry.getKey(), Ingredient.fromJson(entry.getValue()));
        }

        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    private static String[] shrink(String ... toShrink){
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for(int i1 = 0; i1 < toShrink.length; ++i1){
            String s = toShrink[i1];
            i = Math.min(i, firstNonSpace(s));
            int j1 = lastNonSpace(s);
            j = Math.max(j, j1);
            if(j1 < 0) {
                if (k == i1) {
                    k++;
                }

                ++l;
            }else {
                l = 0;
            }
        }

        if(toShrink.length == l){
            return new String[0];
        }else {
            String[] aString = new String[toShrink.length - l - k];

            for (int k1 = 0; k1 < aString.length; ++k1)
                aString[k1] = toShrink[k1 + k].substring(i, j + 1);

            return aString;
        }
    }

    private static int firstNonSpace(String s){
        int i;
        for(i = 0; i < s.length() && s.charAt(i) == ' '; ++i);
        return i;
    }

    private static int lastNonSpace(String s){
        int i;
        for(i = s.length() - 1; i >= 0 && s.charAt(i) == ' '; --i);
        return i;
    }

    private static String[] patternFromJson(JsonArray json){
        String[] strings = new String[json.size()];
        if (strings.length > MAX_H)
            throw new JsonSyntaxException("Invalid pattern: too many rows, " + MAX_H + " is maximum.");
        else if (strings.length == 0)
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed.");
        else for (int i = 0; i < strings.length; i++){
                String s = JsonUtils.getString(json.get(i), "pattern[" + i + "]");
                if (s.length() > MAX_W)
                    throw new JsonSyntaxException("Invalid pattern: too many columns, " + MAX_W + " is maximum.");

                if (i > 0 && strings[0].length() != s.length())
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width.");

                strings[i] = s;
            }

        return strings;
    }

    private static NonNullList<Ingredient> deserializeIngredients(String[] pattern, Map<String, Ingredient> keys, int patternWidth, int patternHeigh) {
        NonNullList<Ingredient> nonNullList = NonNullList.withSize(patternWidth * patternHeigh, Ingredient.EMPTY);
        Set<String> set = Sets.newHashSet(keys.keySet());
        set.remove(" ");

        for (int i = 0; i < pattern.length; ++i) {
            for (int j = 0; j < pattern[i].length(); ++j){
                String s = pattern[i].substring(j, j + 2);
                Ingredient ingredient = keys.get(s);
                if (ingredient == null)
                    throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key.");

                set.remove(s);
                nonNullList.set(j + patternWidth * i, ingredient);
            }
        }

        if(!set.isEmpty())
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        else
            return nonNullList;
    }

    public static class Serializer implements IRecipeSerializer<ShapedFBBRecipe>{
        private static ResourceLocation NAME = new ResourceLocation(TheFireArmy.getModId(), "fire_blacksmith_furnace");
        @Override
        public ShapedFBBRecipe read(ResourceLocation recipeId, JsonObject json) {
            Map<String, Ingredient> map = ShapedFBBRecipe.deserializeKey(JsonUtils.getJsonObject(json, "key"));
            String[] aString = ShapedFBBRecipe.shrink(ShapedFBBRecipe.patternFromJson(JsonUtils.getJsonArray(json, "pattern")));
            int i = aString[0].length();
            int j = aString.length;
            NonNullList<Ingredient> nonNullList = ShapedFBBRecipe.deserializeIngredients(aString, map, i, j);
            ItemStack itemStack = ShapedRecipe.deserializeItem(JsonUtils.getJsonObject(json, "result"));
            int cost = JsonUtils.getInt(json, "cost");
            return new ShapedFBBRecipe(recipeId, nonNullList, itemStack, cost);
        }

        @Override
        public ShapedFBBRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> nonNullList = NonNullList.withSize(9, Ingredient.EMPTY);

            for(int k = 0; k <  nonNullList.size(); k++)
                nonNullList.set(k, Ingredient.fromBuffer(buffer));

            ItemStack itemStack = buffer.readItemStack();
            int cost = buffer.readVarInt();
            return new ShapedFBBRecipe(recipeId, nonNullList, itemStack, cost);
        }

        @Override
        public void write(PacketBuffer buffer, ShapedFBBRecipe recipe) {
            for(Ingredient i: recipe.inputs)
                i.writeToBuffer(buffer);

            buffer.writeItemStack(recipe.output);
            buffer.writeInt(recipe.cost);
        }

        @Override
        public ResourceLocation getName() {
            return NAME;
        }
    }
}
