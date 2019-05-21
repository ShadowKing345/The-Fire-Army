package com.shadowprince345.thefirearmy.lib.recipe.grindstone;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.lib.recipe.FARecipeSerializers;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class GrindstoneRecipe implements IGrindstoneRecipe {
    final ResourceLocation id;
    final Ingredient input;
    final ItemStack output;
    int cost;
    int time;

    public GrindstoneRecipe(ResourceLocation id, Ingredient input, ItemStack output, int cost, int time) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.cost = cost <=  0 ? 1 : cost;
        this.time = time <=  0 ? 1 : time;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return input.test(inv.getStackInSlot(0));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.withSize(1, Ingredient.EMPTY);
        list.set(0, input);
        return list;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return getRecipeOutput();
    }

    @Override
    public boolean canFit(int width, int height) {
        return width == 1 && height == 1;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemHandlerHelper.copyStackWithSize(output, output.getCount());
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return FARecipeSerializers.GRINDSTONE;
    }

    public static class Serializer implements IRecipeSerializer<GrindstoneRecipe> {
        private static ResourceLocation NAME = new ResourceLocation(TheFireArmy.getModId(), "grindstone");
        @Override
        public GrindstoneRecipe read(ResourceLocation recipeId, JsonObject json) {
            Ingredient input = Ingredient.fromJson(JsonUtils.getJsonObject(json, "ingredient"));
            ItemStack output = ShapedRecipe.deserializeItem(JsonUtils.getJsonObject(json, "result"));
            if(!json.has("cost"))
                throw new JsonSyntaxException("Invalid json recipe. 'cost' field is missing.");
            int cost = JsonUtils.getInt(json, "cost");
            if(!json.has("time"))
                throw new JsonSyntaxException("Invalid json recipe. 'time' field is missing.");
            int time = JsonUtils.getInt(json, "time");
            return new GrindstoneRecipe(recipeId, input, output, cost, time);
        }

        @Override
        public GrindstoneRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            return new GrindstoneRecipe(recipeId, Ingredient.fromBuffer(buffer), buffer.readItemStack(), buffer.readVarInt(), buffer.readVarInt());
        }

        @Override
        public void write(PacketBuffer buffer, GrindstoneRecipe recipe) {
            recipe.input.writeToBuffer(buffer);
            buffer.writeItemStack(recipe.output);
            buffer.writeInt(recipe.cost);
            buffer.writeInt(recipe.time);
        }

        @Override
        public ResourceLocation getName() {
            return NAME;
        }
    }
}
