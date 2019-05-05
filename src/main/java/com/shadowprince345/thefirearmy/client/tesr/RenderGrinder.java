package com.shadowprince345.thefirearmy.client.tesr;

import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.blocks.tiles.TEGrinder;
import com.shadowprince345.thefirearmy.client.models.ModelGrinder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderGrinder extends TileEntitySpecialRenderer<TEGrinder> {
    private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(TheFireArmy.getModId(), "textures/models/grinding_stone.png");

    private static float test = 0;

    @Override
    public void render(TEGrinder te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);

        ModelGrinder model = new ModelGrinder();


        if (destroyStage >= 0)
        {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        bindTexture(TEXTURE_NORMAL);

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();

        if(destroyStage < 0)
            GlStateManager.color(1.0f,1.0f,1.0f, alpha);

        GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
        GlStateManager.scale(1.0F, -1.0F, -1.0F);

//        test += partialTicks * 3;

        model.stone.rotateAngleX = (float) Math.toRadians(test);

        model.renderAll();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }
}
