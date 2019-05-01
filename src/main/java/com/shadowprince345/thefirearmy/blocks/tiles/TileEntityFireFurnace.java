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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityFireFurnace extends TileEntity implements ITickable, ICapabilityProvider {
    public ItemStackHandler inventory = new ItemStackHandler(3){
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

        ItemStack fuel = inventory.getStackInSlot(0);
        if (!fuel.isEmpty()) {
            if (TileEntityFurnace.isItemFuel(fuel) && currentBurnTime <= 0) {
                totalBurnTime = TileEntityFurnace.getItemBurnTime(fuel);
                currentBurnTime += totalBurnTime;
                if (fuel.getItem() instanceof ItemBucket)
                    world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.BUCKET)));
                fuel.shrink(1);
                inventory.setStackInSlot(0, fuel);
                isBurning = true;
            }
        }

        ItemStack output = inventory.getStackInSlot(2);
        boolean hasOutput = !output.isEmpty();

        if (hasOutput && output.getCount() >= output.getMaxStackSize())
            return;

        ItemStack recipe = FurnaceRecipes.instance().getSmeltingResult(inventory.getStackInSlot(1));
        if (recipe == ItemStack.EMPTY)
            return;

        if (hasOutput && (recipe.isEmpty() || recipe.getItem() != output.getItem() || recipe.getMetadata() != output.getMetadata()))
            return;

        totalProgress = 120;

        if (!isBurning) {
            progress = 0;
            return;
        }

        progress++;
        currentBurnTime--;

        if (progress >= totalProgress) {
            ItemStack input = inventory.getStackInSlot(1);
            input.shrink(1);
            inventory.setStackInSlot(1, input);

            if (hasOutput) {
                output.grow(1);
                inventory.setStackInSlot(2, output);
            } else {
                inventory.setStackInSlot(2, ItemHandlerHelper.copyStackWithSize(recipe, 1));
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
        itemsTag.setTag("Items", nbt.getTagList("inventory", Constants.NBT.TAG_COMPOUND));
        inventory.deserializeNBT(itemsTag);
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
        nbt.setTag("inventory", inventory.serializeNBT().getTag("Items"));
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

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return true;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return (T) inventory;
    }
}
