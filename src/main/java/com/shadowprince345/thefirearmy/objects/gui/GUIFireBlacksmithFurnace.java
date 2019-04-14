package com.shadowprince345.thefirearmy.objects.gui;

import com.shadowprince345.thefirearmy.Main;
import com.shadowprince345.thefirearmy.objects.blocks.machines.fireblacksmithfurnace.ContainerFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.objects.blocks.machines.fireblacksmithfurnace.TileEntityFireBlacksmithFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIFireBlacksmithFurnace extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.getModId() + ":textures/gui/gui_fire_blacksmith_furnace.png");
    private final InventoryPlayer player;
    private final TileEntityFireBlacksmithFurnace furnace;

    public GUIFireBlacksmithFurnace(InventoryPlayer player, TileEntityFireBlacksmithFurnace furnace) {
        super(new ContainerFireBlacksmithFurnace(player, furnace));
        xSize=176;
        ySize=160;

        this.player = player;
        this.furnace = furnace;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if(furnace.isBurning) {
            int pixels = (int) (furnace.currentBurnTime / furnace.burnTime * 16f);
            drawTexturedModalRect(guiLeft + 17, guiTop + 32 - pixels, 176, 16 - pixels, 16, 16);
        }
    }
}
