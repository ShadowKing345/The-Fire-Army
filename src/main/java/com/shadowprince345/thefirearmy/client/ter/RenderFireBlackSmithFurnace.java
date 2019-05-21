package com.shadowprince345.thefirearmy.client.ter;

import com.shadowprince345.thefirearmy.blocks.tiles.TEFireBlacksmithFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.animation.TileEntityRendererFast;
import net.minecraftforge.items.ItemHandlerHelper;

public class RenderFireBlackSmithFurnace extends TileEntityRendererFast<TEFireBlacksmithFurnace> {

    private static float partialTicks = 0;

    @Override
    public void renderTileEntityFast(TEFireBlacksmithFurnace te, double x, double y, double z, float partialTicks, int a, BufferBuilder buffer) {
        EntityItem itemEntity = new EntityItem(Minecraft.getInstance().world, 0,0,0, ItemStack.EMPTY);
        itemEntity.hoverStart = 0;

        RenderFireBlackSmithFurnace.partialTicks += partialTicks;

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                ItemStack itemStack = ItemHandlerHelper.copyStackWithSize(te.benchInventory.getStackInSlot((k * 3) + i), 1);
                if (itemStack.isEmpty())
                    continue;

                itemEntity.setItem(itemStack);
                GlStateManager.pushMatrix();
                {
                    GlStateManager.translated(x + 0.3125f + (0.1875f * i), y + 0.58f, z + 0.3125f + (0.1875 * k));
                    GlStateManager.scalef(0.45f, 0.45f, 0.45f);
                    Minecraft.getInstance().getRenderManager().renderEntity(itemEntity, 0, 0, 0, 0f, RenderFireBlackSmithFurnace.partialTicks, false);
                }
                GlStateManager.popMatrix();
            }
        }
    }
}
