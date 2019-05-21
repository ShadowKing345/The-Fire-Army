package com.shadowprince345.thefirearmy.blocks.tiles;

import com.shadowprince345.thefirearmy.init.FATileEntityTypes;
import com.shadowprince345.thefirearmy.lib.recipe.FARecipeTypes;
import com.shadowprince345.thefirearmy.lib.recipe.grindstone.IGrindstoneRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TEGrindstone extends TileEntity implements ITickable, IInventory {
    public ItemStackHandler inventory = new ItemStackHandler(2);
    public boolean isActive = false;
    public int totalProgress = 0;
    public int currentProgress = 0;
    public float rotation = .0f;

    public TEGrindstone(){
        super(FATileEntityTypes.TileEntityGrindstone);
    }

    @Override
    public void tick() {
        if(world.isRemote) return;
        isActive = totalProgress > currentProgress;

        totalProgress = 0;

        ItemStack output = inventory.getStackInSlot(1);
        boolean hasOutput = !output.isEmpty();

        if (hasOutput && output.getCount() >= output.getMaxStackSize()) {
            currentProgress = 0;
            return;
        }

        IGrindstoneRecipe recipe = world.getRecipeManager().getRecipe(this, world, FARecipeTypes.GRINDSTONE);
        if(recipe == null) {
            currentProgress = 0;
            return;
        }

        totalProgress = recipe.getCost() * recipe.getTime();

        if(output.getCount() + recipe.getRecipeOutput().getCount() > output.getMaxStackSize())
            return;

        currentProgress++;

        if(currentProgress >= totalProgress) {
            ItemStack input = inventory.getStackInSlot(0);
            ItemStack recipeOutput = recipe.getRecipeOutput();

            if(hasOutput){
                output.grow(recipeOutput.getCount());
                inventory.setStackInSlot(1, output);
            } else {
                inventory.setStackInSlot(1, ItemHandlerHelper.copyStackWithSize(recipeOutput, recipeOutput.getCount()));
            }

            input.shrink(recipe.getIngredients().get(0).getMatchingStacks()[0].getCount());
            inventory.setStackInSlot(0, input);

            currentProgress = 0;
        }

        markDirty();
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = getUpdateTag();
        nbt.removeTag("total");
        nbt.removeTag("current");
        return new SPacketUpdateTileEntity(pos, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        isActive = pkt.getNbtCompound().getBoolean("active");
        NBTTagCompound tag = new NBTTagCompound();
        tag.setTag("Items", pkt.getNbtCompound().getList("inventory", Constants.NBT.TAG_COMPOUND));
        inventory.deserializeNBT(tag);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return write(new NBTTagCompound());
    }

    @Override
    public void read(NBTTagCompound nbt) {
        isActive = nbt.getBoolean("active");
        totalProgress = nbt.getInt("total");
        currentProgress = nbt.getInt("current");
        NBTTagCompound tag = new NBTTagCompound();
        tag.setTag("Items", nbt.getList("inventory", Constants.NBT.TAG_COMPOUND));
        inventory.deserializeNBT(tag);
        super.read(nbt);
    }

    @Override
    public NBTTagCompound write(NBTTagCompound nbt) {
        nbt.setBoolean("active", isActive);
        if(totalProgress > 0)
            nbt.setInt("total", totalProgress);
        if(currentProgress > 0)
            nbt.setInt("current", currentProgress);
        nbt.setTag("inventory", inventory.serializeNBT().getTag("Items"));
        return super.write(nbt);
    }

    @Override
    public void onLoad() {
        if(world.isRemote)
            world.tickableTileEntities.remove(this);
        validate();
    }

    @Override
    public int getSizeInventory() {
        return inventory.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < inventory.getSlots(); i++)
            if(!inventory.getStackInSlot(i).isEmpty())
                return false;
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int i1) {
        return inventory.extractItem(i, i1, true);
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        return inventory.extractItem(i, 64, true);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        inventory.setStackInSlot(i, itemStack);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
        if (world != null)
            world.markChunkDirty(pos, this);
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer entityPlayer) {

    }

    @Override
    public void closeInventory(EntityPlayer entityPlayer) {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return inventory.isItemValid(i, itemStack);
    }

    @Override
    public int getField(int i) {
        switch (i) {
            case 0:
                return currentProgress;
            case 1:
                return totalProgress;
            default:
            return 0;
        }
    }

    @Override
    public void setField(int i, int i1) {
        switch (i) {
            case 0:
                currentProgress = i1;
                break;
            case 1:
                totalProgress = i1;
                break;
            default:
        }
    }

    @Override
    public int getFieldCount() {
        return 2;
    }

    @Override
    public void clear() {
        inventory.setStackInSlot(0, ItemStack.EMPTY);
        inventory.setStackInSlot(1, ItemStack.EMPTY);
    }

    @Override
    public ITextComponent getName() {
        return new TextComponentTranslation("grindstone");
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Nullable
    @Override
    public ITextComponent getCustomName() {
        return null;
    }
}
