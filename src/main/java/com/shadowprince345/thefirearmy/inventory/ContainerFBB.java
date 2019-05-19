package com.shadowprince345.thefirearmy.inventory;

import com.shadowprince345.thefirearmy.blocks.tiles.TEFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.lib.gui.ItemHandlerCrafting;
import com.shadowprince345.thefirearmy.lib.gui.SlotCraftingItemHandler;
import com.shadowprince345.thefirearmy.lib.recipe.FARecipeTypes;
import com.shadowprince345.thefirearmy.lib.recipe.fbb.IFBBRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerFBB extends Container {
    public final TEFireBlacksmithFurnace furnace;
    private final ItemHandlerCrafting craftingMatrix;
    private final BlacksmithBenchCraftResult craftResult;
    public final InventoryPlayer inventoryPlayer;
    public int fuelLevel = -1;

    public ContainerFBB(InventoryPlayer inventoryPlayer, TEFireBlacksmithFurnace furnace) {
        this.inventoryPlayer = inventoryPlayer;
        this.craftingMatrix = new ItemHandlerCrafting(this, furnace.benchInventory);
        this.craftResult = new BlacksmithBenchCraftResult();
        this.furnace = furnace;

        addSlot(new SlotCraftingItemHandler(inventoryPlayer.player, craftingMatrix, craftResult, 0, 118, 31) {
            @Override
            protected void onCrafting(ItemStack stack) {
                super.onCrafting(stack);
                IFBBRecipe recipe = craftResult.getRecipe();

                if (recipe != null)
                    craftResult.setRecipe(recipe);
            }

            @Override
            public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
                IFBBRecipe recipe = craftResult.getRecipe();

                if(recipe != null && !thePlayer.world.isRemote) {
                    this.onCrafting(stack);
                    net.minecraftforge.common.ForgeHooks.setCraftingPlayer(inventoryPlayer.player);
                    NonNullList<ItemStack> nonNullList = NonNullList.withSize(9, ItemStack.EMPTY);
                    net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);

                    for (int i = 0; i < nonNullList.size(); i++) {
                        ItemStack craftingMatrixStack = craftingMatrix.getStackInSlot(i);
                        ItemStack itemStack = nonNullList.get(i);

                        if (!craftingMatrixStack.isEmpty()) {
                            craftingMatrix.extractItem(i, 1, false);
                            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) thePlayer;
                            entityPlayerMP.connection.sendPacket(new SPacketSetSlot(windowId, i + 1, craftingMatrix.getStackInSlot(i)));
                            craftingMatrixStack = craftingMatrix.getStackInSlot(i);
                        }

                        if (!itemStack.isEmpty()) {
                            if (craftingMatrixStack.isEmpty())
                                craftingMatrix.setStackInSlot(i, itemStack);
                            else if (ItemStack.areItemsEqual(craftingMatrixStack, itemStack) && ItemStack.areItemStackTagsEqual(craftingMatrixStack, itemStack)) {
                                itemStack.grow(craftingMatrixStack.getCount());
                                craftingMatrix.setStackInSlot(i, itemStack);
                            } else if (!inventoryPlayer.addItemStackToInventory(itemStack))
                                inventoryPlayer.player.dropItem(itemStack, false);
                        }
                    }

                    furnace.decreaseCraftingFuel();
                }

                return stack;
            }
        });

        for(int j = 0; j < 3; j++)
            for(int i = 0; i < 3; i++)
                addSlot(new SlotItemHandler(craftingMatrix, j * 3 + i, i * 18 + 41 + i, j * 18 + 12 + j));

        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 9; i++)
                addSlot(new Slot(this.inventoryPlayer, i + j * 9 + 9, 18 * i + 8, (18 * j) + 78));

        for (int i = 0; i < 9; i++)
            addSlot(new Slot(this.inventoryPlayer, i, 18 * i + 8, 136));

        onCraftMatrixChanged(furnace.FBB);
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        slotChangedCraftingGrid(inventoryPlayer.player.world, inventoryPlayer.player, craftResult);
    }

    private void slotChangedCraftingGrid(World world, EntityPlayer player, BlacksmithBenchCraftResult craftResult) {
        if (world.isRemote) return;

        EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
        ItemStack output = ItemStack.EMPTY;

        IFBBRecipe recipe = (IFBBRecipe) world.getRecipeManager().getRecipe(furnace.FBB, world, FARecipeTypes.FIRE_BLACKSMITH_FURNACE);

        if (recipe != null) {
            craftResult.setRecipe(recipe);
            furnace.setCraftingRecipe(recipe);
            output = recipe.getRecipeOutput();
        }

        craftResult.setStackInSlot(0, output);
        entityPlayerMP.connection.sendPacket(new SPacketSetSlot(this.windowId, 0, output));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        Slot slot = inventorySlots.get(index);
        if(slot != null) {
            ItemStack stack = slot.getStack();
            ItemStack oldStack = stack.copy();

            if(index == 0){
                stack.getItem().onCreated(stack, playerIn.world, playerIn);

                if (!this.mergeItemStack(stack, 10, inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(stack, oldStack);
            }
            else if(index < 10){
                if(!mergeItemStack(stack, 10, inventorySlots.size(), true))
                    return ItemStack.EMPTY;
            }
            else if(!mergeItemStack(stack, 0, 10, false)){
                return ItemStack.EMPTY;
            }

            if(stack.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if(stack.getCount() == oldStack.getCount())
                return ItemStack.EMPTY;

            ItemStack itemStack = slot.onTake(playerIn, stack);

            if(index == 0)
                playerIn.dropItem(itemStack, false);

            return oldStack;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }

    private void listenerHelper(IContainerListener listener) {
        listener.sendWindowProperty(this, 0, (int) (furnace.currentBurnTime/(double)furnace.totalBurnTime * 100f) & 255);
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listenerHelper(listener);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        if(!furnace.getWorld().isRemote)
            for (IContainerListener listener : listeners) {
                listenerHelper(listener);
            }

    }

    @OnlyIn(Dist.CLIENT)
    public void updateProgressBar(int id, int data) {
        if(id == 0)
            fuelLevel = data & 255;
        else
            super.updateProgressBar(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }
}
