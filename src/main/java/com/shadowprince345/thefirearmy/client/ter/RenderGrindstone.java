package com.shadowprince345.thefirearmy.client.ter;

import com.shadowprince345.thefirearmy.TheFireArmy;
import com.shadowprince345.thefirearmy.blocks.machines.BlockGrindstone;
import com.shadowprince345.thefirearmy.blocks.tiles.TEGrindstone;
import com.shadowprince345.thefirearmy.client.models.ModelGrinder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderGrindstone extends TileEntityRenderer<TEGrindstone> {
    private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(TheFireArmy.getModId(), "textures/model/grinding_stone.png");

    @Override
    public void render(TEGrindstone te, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.enableDepthTest();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);

        IBlockState state = te.getWorld().getBlockState(te.getPos());
        int rotation;

        switch (state.get(BlockGrindstone.FACING)) {
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

        ModelGrinder model = new ModelGrinder();

        bindTexture(TEXTURE_NORMAL);

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();

        GlStateManager.translated(x + 0.5, y + 0.5, z + 0.5);
        GlStateManager.rotatef(rotation, 0, 1, 0);
        GlStateManager.scalef(1.0F, -1.0F, -1.0F);

        if(te.isActive) {
            te.rotation += partialTicks / 3;
            model.stone.rotateAngleX = te.rotation;
        }
        model.renderAll();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        EntityItem itemEntity = new EntityItem(Minecraft.getInstance().world, 0,0,0, ItemStack.EMPTY);
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
                switch (state.get(BlockGrindstone.FACING)) {
                    case NORTH:
                    default:
                        GlStateManager.translated(x + (1 - xOffset) - (0.375f * i), y + yOffset, z + (1 - zOffset));
                        break;
                    case SOUTH:
                        GlStateManager.translated(x + xOffset + (0.375 * i), y + yOffset, z + zOffset);
                        break;
                    case EAST:
                        GlStateManager.translated(x + zOffset , y + yOffset, z + (1 - xOffset) - (0.375 * i));
                        break;
                    case WEST:
                        GlStateManager.translated(x + (1f - zOffset), y + yOffset, z + xOffset + (0.375f * i));
                        break;
                }
                GlStateManager.scalef(0.45f,0.45f,0.45f);
                Minecraft.getInstance().getRenderManager().renderEntity(itemEntity, 0,-0.0625,0, 0, 0, false);
            }
            GlStateManager.popMatrix();
        }
    }
}
