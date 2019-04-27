package com.shadowprince345.thefirearmy.objects.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotCraftingItemHandler extends SlotItemHandler {

    private final EntityPlayer player;
    private final ItemHandlerCrafting craftMatrix;
    private final IItemHandler itemHandler;
    private int amountCrafted;

    public SlotCraftingItemHandler(EntityPlayer player, ItemHandlerCrafting craftingMatrix, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.player = player;
        this.craftMatrix = craftingMatrix;
        this.itemHandler = itemHandler;
    }

    @Override
    public void onSlotChange(@Nonnull ItemStack p_75220_1_, @Nonnull ItemStack p_75220_2_) {
        int i = p_75220_2_.getCount() - p_75220_1_.getCount();

        if(i > 0)
            this.onCrafting(p_75220_2_, i);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return false;
    }

    @Nonnull
    @Override
    public ItemStack decrStackSize(int amount) {
        if(getHasStack())
            amountCrafted += Math.min(amount, getStack().getCount());

        return super.decrStackSize(amount);
    }

    @Override
    protected void onCrafting(ItemStack stack, int amount) {
        amountCrafted += amount;
        onCrafting(stack);
    }

    @Override
    protected void onSwapCraft(int p_190900_1_) {
        amountCrafted += p_190900_1_;
    }

    @Override
    protected void onCrafting(ItemStack stack) {
        if(amountCrafted > 0)
            stack.onCrafting(player.world, player, amountCrafted);

        amountCrafted = 0;

    }

    @Override
    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
        for (int i = 0; i < 9; i++)
            craftMatrix.extractItem(i, 1, false);

        return stack;
    }
}
