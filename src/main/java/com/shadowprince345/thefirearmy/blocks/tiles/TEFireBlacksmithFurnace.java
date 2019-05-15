package com.shadowprince345.thefirearmy.blocks.tiles;

import com.shadowprince345.thefirearmy.blocks.machines.BlockFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.init.TileEntityTypes;
import com.shadowprince345.thefirearmy.lib.recipe.IFBBRecipe;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.crafting.VanillaRecipeTypes;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TEFireBlacksmithFurnace extends TileEntity implements ITickable, IInventory {

    public ItemStackHandler benchInventory = new ItemStackHandler(9);
    public ItemStackHandler furnaceInventory = new ItemStackHandler(3){
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            if(slot == 2)
                return TileEntityFurnace.isItemFuel(stack);
            return true;
        }
    };
    public boolean isBurning = false;

    public int totalProgress = 0;
    public int progress = 0;

    public int totalBurnTime = 0;
    public int currentBurnTime = 0;

    public IFBBRecipe craftingRecipe = null;
    private FurnaceRecipe furnaceRecipe;

    public TEFireBlacksmithFurnace() {
        super(TileEntityTypes.TileEntityFireBlacksmithFurnace);
    }

    @Override
    public void tick() {
        if (world.isRemote) return;

        isBurning = currentBurnTime > 0;
        BlockFireBlacksmithFurnace.setBurning(isBurning, world, pos);

        furnaceRecipe = null;
        totalProgress = 0;

        furnaceRecipe = world.getRecipeManager().getRecipe(this, world, VanillaRecipeTypes.SMELTING);

        ItemStack fuel = furnaceInventory.getStackInSlot(2);
        if (!fuel.isEmpty()) {
            if (TileEntityFurnace.isItemFuel(fuel) && currentBurnTime <= 0) {
                totalBurnTime = currentBurnTime = getItemBurnTime(fuel);
                if (fuel.getItem() instanceof ItemBucket)
                    world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.BUCKET)));
                fuel.shrink(1);
                furnaceInventory.setStackInSlot(2, fuel);
            }
        }

        furnaceUpdate();
    }

    private int getItemBurnTime(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        } else {
            Item item = stack.getItem();
            int ret = stack.getBurnTime();
            return net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack, ret == -1 ? TileEntityFurnace.getBurnTimes().getOrDefault(item, 0) : ret);
        }
    }

    private void furnaceUpdate() {
        ItemStack output = furnaceInventory.getStackInSlot(1);
        boolean hasOutput = !output.isEmpty();

        if (furnaceRecipe == null)
            return;

        if (hasOutput && output.getCount() >= output.getMaxStackSize())
            return;

        if (furnaceRecipe.getRecipeOutput() == ItemStack.EMPTY)
            return;

        if (hasOutput && (furnaceRecipe.getRecipeOutput().isEmpty() || furnaceRecipe.getRecipeOutput().getItem() != output.getItem()))
            return;

        totalProgress = 200;

        if (!isBurning) {
            progress = 0;
            return;
        }

        progress++;
        currentBurnTime--;

        if (progress >= totalProgress) {
            ItemStack input = furnaceInventory.getStackInSlot(0);
            input.shrink(1);
            furnaceInventory.setStackInSlot(0, input);

            if (hasOutput) {
                output.grow(1);
                furnaceInventory.setStackInSlot(1, output);
            } else {
                furnaceInventory.setStackInSlot(1, ItemHandlerHelper.copyStackWithSize(furnaceRecipe.getRecipeOutput(), furnaceRecipe.getRecipeOutput().getCount()));
            }

            progress = 0;
        }

        markDirty();
    }

    public void decreaseCraftingFuel() {
        currentBurnTime -= craftingRecipe != null ? craftingRecipe.getCost() : 0;
    }

    @Override
    public void read(NBTTagCompound nbt) {
        currentBurnTime = nbt.getInt("current");
        totalBurnTime = nbt.getInt("total");
        isBurning = nbt.getBoolean("burning");
        progress = nbt.getInt("progress");

        NBTTagCompound itemsTag = new NBTTagCompound();
        itemsTag.setTag("Items", nbt.getList("benchInventory", Constants.NBT.TAG_COMPOUND));
        benchInventory.deserializeNBT(itemsTag);
        itemsTag = new NBTTagCompound();
        itemsTag.setTag("Items", nbt.getList("inventory", Constants.NBT.TAG_COMPOUND));
        furnaceInventory.deserializeNBT(itemsTag);
        super.read(nbt);
    }

    @Override
    public NBTTagCompound write(NBTTagCompound nbt) {
        nbt.setBoolean("burning", isBurning);
        if (currentBurnTime > 0)
            nbt.setInt("current", currentBurnTime);
        if (totalBurnTime > 0)
            nbt.setInt("total", totalBurnTime);
        if (progress > 0)
            nbt.setInt("progress", progress);
        nbt.setTag("benchInventory", benchInventory.serializeNBT().getTag("Items"));
        nbt.setTag("inventory", furnaceInventory.serializeNBT().getTag("Items"));
        return super.write(nbt);
    }

    @Override
    public void onLoad() {
        if (world.isRemote)
            world.tickableTileEntities.remove(this);
        validate();
    }

    @Override
    public int getSizeInventory() {
        return furnaceInventory.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < furnaceInventory.getSlots(); i++)
            if(!furnaceInventory.getStackInSlot(i).isEmpty())
                return false;
        return true;
    }

    public ItemStack getStackInSlot(int i) {
        return furnaceInventory.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int i1) {
        return furnaceInventory.extractItem(i, i1, true);
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        return furnaceInventory.extractItem(i, 64, true);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        furnaceInventory.setStackInSlot(i, itemStack);
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
        return furnaceInventory.isItemValid(i, itemStack);
    }

    @Override
    public int getField(int i) {
        switch (i){
            case 0:
                return totalBurnTime;
            case 1:
                return currentBurnTime;
            case 2:
                return totalProgress;
            case 3:
                return progress;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int i, int i1) {
        switch (i){
            case 0:
                totalBurnTime = i1;
                break;
            case 1:
                currentBurnTime = i1;
                break;
            case 2:
                totalProgress = i1;
                break;
            case 3:
                progress = i1;
                break;
            default:
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public void clear() {

    }

    @Override
    public ITextComponent getName() {
        return new TextComponentTranslation("fire_blacksmith_furnace");
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

    public IFBBRecipe getCraftingRecipe() {
        return craftingRecipe;
    }

    public void setCraftingRecipe(IFBBRecipe craftingRecipe) {
        this.craftingRecipe = craftingRecipe;
    }

    @Override
    public boolean hasFastRenderer() {
        return true;
    }
}
