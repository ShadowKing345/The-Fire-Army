//package com.shadowprince345.thefirearmy.client.gui;
//
//import com.shadowprince345.thefirearmy.TheFireArmy;
//import com.shadowprince345.thefirearmy.inventory.ContainerFireFurnace;
//import net.minecraft.client.gui.inventory.GuiContainer;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.util.ResourceLocation;
//
//public class GuiFireFurnace extends GuiContainer {
//    private static final ResourceLocation texture = new ResourceLocation(TheFireArmy.getModId(), "textures/gui/gui_fire_blacksmith_furnace.png");
//    private ContainerFireFurnace container;
//
//    public GuiFireFurnace(ContainerFireFurnace c) {
//        super(c);
//        this.container = c;
//
//        xSize=176;
//        ySize=160;
//    }
//
//    @Override
//    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
//        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
//    }
//
//    @Override
//    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//        super.drawScreen(mouseX, mouseY, partialTicks);
//        renderHoveredToolTip(mouseX, mouseY);
//    }
//
//    @Override
//    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
//        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
//        mc.getTextureManager().bindTexture(texture);
//        drawTexturedModalRect(guiLeft, guiTop, 0,0, xSize, ySize);
//
//        int fuelPixels = 13 - Math.min((int) (container.fuelLevel / 100f * 13f), 13);
//        drawTexturedModalRect(guiLeft + 67, guiTop + 32 + fuelPixels, 176, fuelPixels, 16, 13 - fuelPixels);
//        int progressPixels = (int) (container.progressLevel / 100f * 16f);
//        drawTexturedModalRect(guiLeft + 85, guiTop + 32, 176, 13, progressPixels, 12);
//    }
//}
