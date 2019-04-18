package com.shadowprince345.thefirearmy.objects.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityFireBlacksmithFurnace extends TileEntity implements ITickable, ICapabilityProvider {

    public ItemStackHandler inventory = new ItemStackHandler(11);
    public boolean isBurning = false;

    public int totalProgress = 0;
    public int progress = 0;

    public int totalBurnTime = 0;
    public int currentBurnTime = 0;
    public int fuelLevel = 0;
    public int progressLevel = 0;

    @Override
    public void update() {
        if (world.isRemote) {
            return;
        }

        progressLevel = (int)(progress / (double)totalProgress * 100f);
        fuelLevel = (int)(currentBurnTime/ (double)totalBurnTime * 100f);
        totalProgress = 0;

        ItemStack output = inventory.getStackInSlot(10);
        boolean hasOutput = !output.isEmpty();

        if (hasOutput && output.getCount() >= output.getMaxStackSize()) {
            return;
        }


        ItemStack input = inventory.getStackInSlot(1);
        FireBlacksmithFurnaceRecipe recipe = FireBlacksmithFurnaceRecipes.INSTANCE.getRecipe(input);

        if (recipe == null) {
            return;
        }

        if (hasOutput && (recipe.output.isEmpty() || recipe.output.getItem() != output.getItem() || recipe.output.getMetadata() != output.getMetadata())) {
            return;
        }

        totalProgress = recipe.time > 0 ? recipe.time : 1;

        progress++;

        if (progress >= totalProgress) {
            progress = 0;
            input.shrink(1);
            inventory.setStackInSlot(1, input);
            if (hasOutput) {
                output.grow(1);
                inventory.setStackInSlot(10, output);
            } else {
                inventory.setStackInSlot(10, recipe.output);
            }
        }
        markDirty();
    }

//    @Override
//    public void update() {
//        if (world.isRemote) return;
//
//        fuelLevel = (int) (currentBurnTime/(double)totalBurnTime * 100f);
//        progressLevel = (int)(progress / (double) totalProgress * 100f);
//        isBurning = currentBurnTime > 0;
//        BlockFireBlacksmithFurnace.setBurning(isBurning, world, pos);
//
//        totalProgress = 0;
//
//        ItemStack[] craftingMatrix = new ItemStack[9];
//        for (int i = 0; i < 9; i++)
//            craftingMatrix[i] = inventory.getStackInSlot(i + 1);
//
//        ItemStack output = inventory.getStackInSlot(10);
//        boolean hasOutput = !output.isEmpty();
//
//        if (hasOutput && output.getCount() >= output.getMaxStackSize()){
//            progress = 0;
//            return;
//        }
//
//
//        FireBlacksmithFurnaceRecipe recipe = FireBlacksmithFurnaceRecipes.INSTANCE.getRecipe(craftingMatrix);
//
//        ItemStack fuel = inventory.getStackInSlot(0);
//        if(!fuel.isEmpty())
//            if(TileEntityFurnace.isItemFuel(fuel) && (currentBurnTime <= 0 || currentBurnTime < (recipe != null ? recipe.cost: 0)))
//            {
//                totalBurnTime = TileEntityFurnace.getItemBurnTime(fuel);
//                currentBurnTime += totalBurnTime;
//                fuel.shrink(1);
//                inventory.setStackInSlot(0, fuel);
//                isBurning = true;
//            }
//
//        if (recipe == null) {
//            progress = 0;
//            return;
//        }
//
//            boolean flag = true;
//        for (int i = 0; i < 9; i++) {
//            Item item = craftingMatrix[i].getItem();
//            int meta = !craftingMatrix[i].getHasSubtypes() && craftingMatrix[i].isItemStackDamageable() ? 0 : craftingMatrix[i].getMetadata();
//            if (recipe.inputs.get(i).getItem() != item && recipe.inputs.get(i).getMetadata() == meta)
//                flag = false;
//        }
//
//        if (hasOutput && (recipe.output.isEmpty() || !flag)){
//            progress = 0;
//            return;
//        }
//
//        totalProgress = recipe.time > 0 ? recipe.time : 300;
//
//        if(!isBurning && currentBurnTime < recipe.cost){
//            progress = 0;
//            return;
//        }
//
//        progress++;
//
//        if(progress >=  totalProgress) {
//
//            progress = 0;
//            for (int i = 0; i < 9; i++) {
//                craftingMatrix[i].shrink(1);
//                inventory.setStackInSlot(i + 1, craftingMatrix[i]);
//            }
//
//            if(hasOutput){
//                output.grow(1);
//                inventory.setStackInSlot(10, output);
//            } else {
//                inventory.setStackInSlot(10, recipe.output);
//            }
//            currentBurnTime -= recipe.cost;
//        }
//
//        markDirty();
//    }

    public static boolean isItemFuel(ItemStack stack){
        return TileEntityFurnace.isItemFuel(stack);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        currentBurnTime = nbt.getInteger("current");
        totalBurnTime = nbt.getInteger("total");
        isBurning = nbt.getBoolean("burning");
        progress = nbt.getInteger("progress");

        NBTTagCompound itemsTag = new NBTTagCompound();
        itemsTag.setTag("Items", nbt.getTagList("inventory", Constants.NBT.TAG_COMPOUND));
        inventory.deserializeNBT(itemsTag);

        super.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setBoolean("burning", isBurning);
        if(currentBurnTime > 0)
            nbt.setInteger("current", currentBurnTime);
        if(totalBurnTime > 0)
            nbt.setInteger("total", totalBurnTime);
        if(progress > 0)
            nbt.setInteger("progress", progress);
        nbt.setTag("inventory", inventory.serializeNBT().getTag("Items"));
        return super.writeToNBT(nbt);
    }

    @Override
    public void onLoad() {
        if(world.isRemote)
            world.tickableTileEntities.remove(this);

        validate();
    }

    @Override
    public void markDirty() {
        if(world != null)
            world.markChunkDirty(pos, this);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return true;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T) inventory;
        return super.getCapability(capability, facing);
    }
}
