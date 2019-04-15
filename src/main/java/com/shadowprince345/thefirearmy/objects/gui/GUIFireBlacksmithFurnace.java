package com.shadowprince345.thefirearmy.objects.gui;

import com.shadowprince345.thefirearmy.TheFireArmy;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GUIFireBlacksmithFurnace extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(TheFireArmy.getModId() + ":textures/gui/gui_fire_blacksmith_furnace.png");
    private ContainerFireBlacksmithFurnace container;

    public GUIFireBlacksmithFurnace(ContainerFireBlacksmithFurnace c) {
        super(c);
        container = c;

        xSize=176;
        ySize=160;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int pixels = (int) (container.currentFuelLevel / 100f * 16f);
        drawTexturedModalRect(guiLeft + 17, guiTop + 32 - pixels, 176, 16 - pixels, 16, 16);
    }
}
