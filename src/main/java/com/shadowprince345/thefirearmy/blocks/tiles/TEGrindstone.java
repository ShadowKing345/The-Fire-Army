//package com.shadowprince345.thefirearmy.blocks.tiles;
//
//import com.shadowprince345.thefirearmy.api.recipe.IGrindstoneRecipe;
//import com.shadowprince345.thefirearmy.blocks.machines.BlockGrindstone;
//import com.shadowprince345.thefirearmy.lib.GrindstoneRecipeManager;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.network.NetworkManager;
//import net.minecraft.network.play.server.SPacketUpdateTileEntity;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.ITickable;
//import net.minecraftforge.common.util.Constants;
//import net.minecraftforge.items.ItemHandlerHelper;
//import net.minecraftforge.items.ItemStackHandler;
//
//import javax.annotation.Nullable;
//
//public class TEGrindstone extends TileEntity implements ITickable {
//    public ItemStackHandler inventory = new ItemStackHandler(2);
//    public int totalProgress = 0;
//    public int currentProgress = 0;
//    public float rotation = .0f;
//
//    @Override
//    public void update() {
//        if(world.isRemote) return;
//        BlockGrindstone.setACTIVE(totalProgress > currentProgress, world, pos);
//
//        totalProgress = 0;
//
//        ItemStack output = inventory.getStackInSlot(1);
//        boolean hasOutput = !output.isEmpty();
//
//        if (hasOutput && output.getCount() >= output.getMaxStackSize()) {
//            currentProgress = 0;
//            return;
//        }
//
//        IGrindstoneRecipe recipe = GrindstoneRecipeManager.instance.findRecipe(inventory.getStackInSlot(0));
//        if(recipe == null) {
//            currentProgress = 0;
//            return;
//        }
//
//        totalProgress = recipe.getCost() * recipe.getTime();
//
//        if(output.getCount() + recipe.getOutput(null).getCount() > output.getMaxStackSize())
//            return;
//
//        currentProgress++;
//
//        if(currentProgress >= totalProgress) {
//            ItemStack input = inventory.getStackInSlot(0);
//            ItemStack recipeOutput = recipe.getOutput(input);
//
//            if(hasOutput){
//                output.grow(recipeOutput.getCount());
//                inventory.setStackInSlot(1, output);
//            } else {
//                inventory.setStackInSlot(1, ItemHandlerHelper.copyStackWithSize(recipeOutput, recipeOutput.getCount()));
//            }
//
//            input.shrink(recipe.getInput().getCount());
//            inventory.setStackInSlot(0, input);
//
//            currentProgress = 0;
//        }
//
//        markDirty();
//    }
//
//    @Nullable
//    @Override
//    public SPacketUpdateTileEntity getUpdatePacket() {
//        NBTTagCompound nbt = getUpdateTag();
//        nbt.removeTag("total");
//        nbt.removeTag("current");
//        return new SPacketUpdateTileEntity(pos, 0, nbt);
//    }
//
//    @Override
//    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
//        NBTTagCompound tag = new NBTTagCompound();
//        tag.setTag("Items", pkt.getNbtCompound().getTagList("inventory", Constants.NBT.TAG_COMPOUND));
//        inventory.deserializeNBT(tag);
//    }
//
//    @Override
//    public NBTTagCompound getUpdateTag() {
//        return writeToNBT(new NBTTagCompound());
//    }
//
//    @Override
//    public void readFromNBT(NBTTagCompound nbt) {
//        totalProgress = nbt.getInteger("total");
//        currentProgress = nbt.getInteger("current");
//        NBTTagCompound tag = new NBTTagCompound();
//        tag.setTag("Items", nbt.getTagList("inventory", Constants.NBT.TAG_COMPOUND));
//        inventory.deserializeNBT(tag);
//        super.readFromNBT(nbt);
//    }
//
//    @Override
//    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
//        if(totalProgress > 0)
//            nbt.setInteger("total", totalProgress);
//        if(currentProgress > 0)
//            nbt.setInteger("current", currentProgress);
//        nbt.setTag("inventory", inventory.serializeNBT().getTag("Items"));
//        return super.writeToNBT(nbt);
//    }
//
//    @Override
//    public void onLoad() {
//        if(world.isRemote)
//            world.tickableTileEntities.remove(this);
//        validate();
//    }
//
//    @Override
//    public void markDirty() {
//        if (world != null)
//            world.markChunkDirty(pos, this);
//    }
//
//    @Override
//    public boolean hasFastRenderer() {
//        return true;
//    }
//}
