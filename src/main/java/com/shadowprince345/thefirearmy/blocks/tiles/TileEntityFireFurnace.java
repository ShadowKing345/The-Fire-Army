package com.shadowprince345.thefirearmy.blocks.tiles;

import com.shadowprince345.thefirearmy.blocks.machines.BlockFireFurnace;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TileEntityFireFurnace extends TileEntity implements ITickable {
    public ItemStackHandler furnaceInventory = new ItemStackHandler(3){
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            if(slot == 2)
                return FurnaceRecipes.instance().getSmeltingResult(this.getStackInSlot(1)) == stack;
            if(slot == 0)
                return TileEntityFurnace.isItemFuel(stack);
            return true;
        }
    };

    public boolean isBurning = false;

    public int totalProgress = 0;
    public int progress = 0;

    public int totalBurnTime = 0;
    public int currentBurnTime = 0;

    @Override
    public void update() {
        if (world.isRemote) return;

        isBurning = currentBurnTime > 0;
        BlockFireFurnace.setBurning(isBurning, world, pos);

        totalProgress = 0;

        ItemStack fuel = furnaceInventory.getStackInSlot(0);
        if (!fuel.isEmpty()) {
            if (TileEntityFurnace.isItemFuel(fuel) && currentBurnTime <= 0) {
                totalBurnTime = TileEntityFurnace.getItemBurnTime(fuel);
                currentBurnTime += totalBurnTime;
                if (fuel.getItem() instanceof ItemBucket)
                    world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.BUCKET)));
                fuel.shrink(1);
                furnaceInventory.setStackInSlot(0, fuel);
                isBurning = true;
            }
        }

        ItemStack output = furnaceInventory.getStackInSlot(2);
        boolean hasOutput = !output.isEmpty();

        if (hasOutput && output.getCount() >= output.getMaxStackSize())
            return;

        ItemStack recipe = FurnaceRecipes.instance().getSmeltingResult(furnaceInventory.getStackInSlot(1));
        if (recipe == ItemStack.EMPTY)
            return;

        if (hasOutput && (recipe.isEmpty() || recipe.getItem() != output.getItem() || recipe.getMetadata() != output.getMetadata()))
            return;

        totalProgress = 360;

        if (!isBurning) {
            progress = 0;
            return;
        }

        progress++;
        currentBurnTime--;

        if (progress >= totalProgress) {
            ItemStack input = furnaceInventory.getStackInSlot(1);
            input.shrink(1);
            furnaceInventory.setStackInSlot(1, input);

            if (hasOutput) {
                output.grow(2);
                furnaceInventory.setStackInSlot(2, output);
            } else {
                furnaceInventory.setStackInSlot(2, ItemHandlerHelper.copyStackWithSize(recipe, 2));
            }

            progress = 0;
        }

        markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        currentBurnTime = nbt.getInteger("current");
        totalBurnTime = nbt.getInteger("total");
        isBurning = nbt.getBoolean("burning");
        progress = nbt.getInteger("progress");

        NBTTagCompound itemsTag = new NBTTagCompound();
        itemsTag.setTag("Items", nbt.getTagList("benchInventory", Constants.NBT.TAG_COMPOUND));
        furnaceInventory.deserializeNBT(itemsTag);
        super.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setBoolean("burning", isBurning);
        if (currentBurnTime > 0)
            nbt.setInteger("current", currentBurnTime);
        if (totalBurnTime > 0)
            nbt.setInteger("total", totalBurnTime);
        if (progress > 0)
            nbt.setInteger("progress", progress);
        return super.writeToNBT(nbt);
    }

    @Override
    public void onLoad() {
        if (world.isRemote)
            world.tickableTileEntities.remove(this);

        validate();
    }

    @Override
    public void markDirty() {
        if (world != null)
            world.markChunkDirty(pos, this);
    }
}
