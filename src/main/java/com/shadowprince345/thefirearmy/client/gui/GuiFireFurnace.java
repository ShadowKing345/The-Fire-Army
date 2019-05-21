package com.shadowprince345.thefirearmy.client.gui;

import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.inventory.ContainerFireFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiFireFurnace extends GuiContainer implements IContainerListener {
    private static final ResourceLocation texture = new ResourceLocation(TheFireArmy.getModId(), "textures/gui/gui_fire_blacksmith_furnace.png");
    private ContainerFireFurnace container;

    public GuiFireFurnace(ContainerFireFurnace c) {
        super(c);
        this.container = c;

        xSize=176;
        ySize=160;
    }

    @Override
    public void render(int x, int y, float p_73863_3_) {
        super.render(x, y, p_73863_3_);
        this.renderHoveredToolTip(x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0,0, xSize, ySize);

        int fuelPixels = 13 - Math.min((int) (container.fuelLevel / 100f * 13f), 13);
        drawTexturedModalRect(guiLeft + 67, guiTop + 32 + fuelPixels, 176, fuelPixels, 16, 13 - fuelPixels);
        int progressPixels = (int) (container.progressLevel / 100f * 16f);
        drawTexturedModalRect(guiLeft + 85, guiTop + 32, 176, 13, progressPixels, 12);
    }

    @Override
    public void sendAllContents(Container container, NonNullList<ItemStack> nonNullList) {
        sendAllContents(container, nonNullList);
    }

    @Override
    public void sendSlotContents(Container container, int i, ItemStack itemStack) {
    }

    @Override
    public void sendWindowProperty(Container container, int i, int i1) {

    }

    @Override
    public void sendAllWindowProperties(Container container, IInventory iInventory) {

    }
}
