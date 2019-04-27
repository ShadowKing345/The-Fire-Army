package com.shadowprince345.thefirearmy.objects.tiles.render;

import com.shadowprince345.thefirearmy.objects.tiles.TileEntityFireBlacksmithFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.items.ItemHandlerHelper;

public class RendererFireBlackSmithFurnace extends FastTESR<TileEntityFireBlacksmithFurnace> {

    @Override
    public void renderTileEntityFast(TileEntityFireBlacksmithFurnace te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        EntityItem itemEntity = new EntityItem(Minecraft.getMinecraft().world, 0,0,0, ItemStack.EMPTY);
        itemEntity.hoverStart = 0;

        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 3; k++) {

                itemEntity.setItem(ItemHandlerHelper.copyStackWithSize(te.benchInventory.getStackInSlot((k * 3) + i), 1));
                GlStateManager.pushMatrix();
                {
                    GlStateManager.translate(x + 0.3125f + (0.1875 * i), y + 0.51f, z + 0.3125f + (0.1875 * k));
                    GlStateManager.scale(0.45, 0.45, 0.45);
                    Minecraft.getMinecraft().getRenderManager().renderEntity(itemEntity, 0, 0, 0, 0f, 0, false);
                }
                GlStateManager.popMatrix();
            }
    }
}
