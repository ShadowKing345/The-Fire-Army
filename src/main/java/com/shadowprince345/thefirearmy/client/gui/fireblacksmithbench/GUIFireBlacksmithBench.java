package com.shadowprince345.thefirearmy.client.gui.fireblacksmithbench;

import com.shadowprince345.thefirearmy.TheFireArmy;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GUIFireBlacksmithBench extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(TheFireArmy.getModId(), "textures/gui/gui_fire_blacksmith_bench.png");
    private ContainerFireBlacksmithBench container;

    public GUIFireBlacksmithBench(ContainerFireBlacksmithBench c) {
        super(c);
        container = c;

        xSize=176;
        ySize=160;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int fuelPixels = 13 - Math.min((int) (container.fuelLevel / 100f * 13f), 13);
        drawTexturedModalRect(guiLeft + 17 , guiTop + 33 + fuelPixels, 176, fuelPixels, 16, 13 - fuelPixels);
    }
}
