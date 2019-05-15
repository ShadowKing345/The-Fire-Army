package com.shadowprince345.thefirearmy.blocks.tiles;

import com.shadowprince345.thefirearmy.blocks.machines.BlockFireFurnace;
import com.shadowprince345.thefirearmy.init.TileEntityTypes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipe;
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

public class TEFireFurnace extends TileEntity implements IInventory, IRecipeHolder, ITickable{
    public ItemStackHandler inventory = new ItemStackHandler(3){
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
    public FurnaceRecipe recipe;

    @Override
    public void tick() {
        if (world.isRemote) return;

        isBurning = currentBurnTime > 0;
        BlockFireFurnace.setBurning(isBurning, world, pos);

        totalProgress = 0;

        ItemStack fuel = inventory.getStackInSlot(2);
        if (!fuel.isEmpty()) {
            if (TileEntityFurnace.isItemFuel(fuel) && currentBurnTime <= 0) {
                totalBurnTime = currentBurnTime = getItemBurnTime(fuel);
                if (fuel.getItem() instanceof ItemBucket)
                    world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.BUCKET)));
                fuel.shrink(1);
                inventory.setStackInSlot(2, fuel);
            }
        }

        ItemStack output = inventory.getStackInSlot(1);
        boolean hasOutput = !output.isEmpty();

        if (hasOutput && output.getCount() >= output.getMaxStackSize())
            return;

        recipe = world.getRecipeManager().getRecipe(this, world, VanillaRecipeTypes.SMELTING);
        if (recipe == null) {
            progress = 0;
            return;
        }

        if (hasOutput && (recipe.getRecipeOutput().isEmpty() || recipe.getRecipeOutput().getItem() != output.getItem()))
            return;

        totalProgress = recipe.getCookingTime();

        if (!isBurning) {
            progress = 0;
            return;
        }

        progress++;
        currentBurnTime--;

        if (progress >= totalProgress) {
            ItemStack input = inventory.getStackInSlot(0);
            input.shrink(recipe.getIngredients().get(0).getMatchingStacks()[0].getCount());
            inventory.setStackInSlot(0, input);

            if (hasOutput) {
                output.grow(1);
                inventory.setStackInSlot(1, output);
            } else {
                inventory.setStackInSlot(1, ItemHandlerHelper.copyStackWithSize(recipe.getRecipeOutput(), recipe.getRecipeOutput().getCount()));
            }

            progress = 0;
        }

        markDirty();
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

    @Override
    public void read(NBTTagCompound nbt) {
        currentBurnTime = nbt.getInt("current");
        totalBurnTime = nbt.getInt("total");
        isBurning = nbt.getBoolean("burning");
        progress = nbt.getInt("progress");

        NBTTagCompound itemsTag = new NBTTagCompound();
        itemsTag.setTag("Items", nbt.getList("inventory", Constants.NBT.TAG_COMPOUND));
        inventory.deserializeNBT(itemsTag);
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
        nbt.setTag("inventory", inventory.serializeNBT().getTag("Items"));
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
        return inventory.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < inventory.getSlots(); i++)
            if(!inventory.getStackInSlot(i).isEmpty())
                return false;
        return true;
    }

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

    public TEFireFurnace(){
        super(TileEntityTypes.TileEntityFireFurnace);
    }

    @Override
    public void setRecipeUsed(@Nullable IRecipe iRecipe) {
        this.recipe = (FurnaceRecipe) iRecipe;
    }

    @Nullable
    @Override
    public IRecipe getRecipeUsed() {
        return recipe;
    }

    @Override
    public ITextComponent getName() {
        return new TextComponentTranslation("fire_furnace");
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

//    @Nullable
//    @Override
//    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
//        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? new LazyOptional<IItemHandler>(){
//            @Override
//            public int getSlots() {
//                return inventory.getSlots();
//            }
//
//            @Nonnull
//            @Override
//            public ItemStack getStackInSlot(int slot) {
//                return inventory.getStackInSlot(slot);
//            }
//
//            @Nonnull
//            @Override
//            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
//                ItemStack result = stack;
//                switch (slot) {
//                    case 0:
//                        if(TileEntityFurnace.isItemFuel(stack))
//                            result = inventory.insertItem(slot, stack, simulate);
//                        break;
//                    case 1:
//                        result = inventory.insertItem(slot, stack, simulate);
//                        default:
//                }
//                return result;
//            }
//
//            @Nonnull
//            @Override
//            public ItemStack extractItem(int slot, int amount, boolean simulate) {
//                return slot == 2 ? inventory.extractItem(slot, amount, simulate): ItemStack.EMPTY;
//            }
//
//            @Override
//            public int getSlotLimit(int slot) {
//                return inventory.getSlotLimit(slot);
//            }
//
//            @Override
//            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
//                return inventory.isItemValid(slot, stack);
//            }
//        }: null;
//    }
}
