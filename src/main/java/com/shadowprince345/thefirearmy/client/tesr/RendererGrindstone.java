package com.shadowprince345.thefirearmy.client.tesr;

import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.blocks.machines.BlockGrindstone;
import com.shadowprince345.thefirearmy.blocks.tiles.TEGrindstone;
import com.shadowprince345.thefirearmy.client.models.ModelGrinder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.animation.FastTESR;

public class RendererGrindstone extends FastTESR<TEGrindstone> {
    private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(TheFireArmy.getModId(), "textures/models/grinding_stone.png");

    @Override
    public void renderTileEntityFast(TEGrindstone te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer)  {
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);

        IBlockState state = te.getWorld().getBlockState(te.getPos());
        int rotation;

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
            GlStateManager.color(1.0f,1.0f,1.0f, 1);

        switch (state.getValue(BlockGrindstone.FACING)) {
            default:
            case NORTH:
                rotation = 0;
                break;
            case SOUTH:
                rotation = 180;
                break;
            case WEST:
                rotation = 90;
                break;
            case EAST:
                rotation = -90;
                break;
        }

        GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
        GlStateManager.rotate(rotation, 0, 1, 0);
        GlStateManager.scale(1.0F, -1.0F, -1.0F);

        if(state.getValue(BlockGrindstone.ACTIVE)) {
            te.rotation += partialTicks / 3;
            model.stone.rotateAngleX = te.rotation;
        }
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

        EntityItem itemEntity = new EntityItem(Minecraft.getMinecraft().world, 0,0,0, ItemStack.EMPTY);
        itemEntity.hoverStart = 0;

        float xOffset = 0.3125f;
        float zOffset = 1.03125f;
        float yOffset = 0.4375f;

        for(int i = 0; i < te.inventory.getSlots(); i++) {
            ItemStack stack = te.inventory.getStackInSlot(i);
            if(stack.isEmpty())
                continue;
            itemEntity.setItem(stack);
            GlStateManager.pushMatrix();
            {
                switch (state.getValue(BlockGrindstone.FACING)) {
                    case NORTH:
                    default:
                        GlStateManager.translate(x + (1 - xOffset) - (0.375f * i), y + yOffset, z + (1 - zOffset));
                        break;
                    case SOUTH:
                        GlStateManager.translate(x + xOffset + (0.375 * i), y + yOffset, z + zOffset);
                        break;
                    case EAST:
                        GlStateManager.translate(x + zOffset , y + yOffset, z + (1 - xOffset) - (0.375 * i));
                        break;
                    case WEST:
                        GlStateManager.translate(x + (1f - zOffset), y + yOffset, z + xOffset + (0.375f * i));
                        break;
                }
                GlStateManager.scale(0.45,0.45,0.45);
                Minecraft.getMinecraft().getRenderManager().renderEntity(itemEntity, 0,-0.0625,0, 0, 0, false);
            }
            GlStateManager.popMatrix();
        }
    }
}
