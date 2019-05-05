package com.shadowprince345.thefirearmy.client;

import com.shadowprince345.thefirearmy.CommonProxy;
import com.shadowprince345.thefirearmy.blocks.tiles.TEFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.blocks.tiles.TEGrinder;
import com.shadowprince345.thefirearmy.client.tesr.RenderGrinder;
import com.shadowprince345.thefirearmy.client.tesr.RendererFireBlackSmithFurnace;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void preInit(){
        super.preInit();

        ClientRegistry.bindTileEntitySpecialRenderer(TEFireBlacksmithFurnace.class, new RendererFireBlackSmithFurnace());
        ClientRegistry.bindTileEntitySpecialRenderer(TEGrinder.class, new RenderGrinder());
    }
}
