package com.shadowprince345.thefirearmy.client.gui.dev;

import com.shadowprince345.thefirearmy.TheFireArmy;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GUIDev extends GuiContainer {

    public GUIDev(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(new ResourceLocation(TheFireArmy.getModId(), "textures/gui/gui_fire_blacksmith_bench.png"));
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
