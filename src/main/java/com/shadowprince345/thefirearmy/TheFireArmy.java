package com.shadowprince345.thefirearmy;

import com.shadowprince345.thefirearmy.blocks.tiles.TEFireBlacksmithFurnace;
import com.shadowprince345.thefirearmy.blocks.tiles.TEGrindstone;
import com.shadowprince345.thefirearmy.client.GuiHandler;
import com.shadowprince345.thefirearmy.client.ter.RenderFireBlackSmithFurnace;
import com.shadowprince345.thefirearmy.client.ter.RenderGrindstone;
import com.shadowprince345.thefirearmy.lib.recipe.FARecipeSerializers;
import com.shadowprince345.thefirearmy.lib.recipe.FARecipeTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.shadowprince345.thefirearmy.TheFireArmy.MOD_ID;

@Mod(MOD_ID)
public class TheFireArmy {
    public static Logger LOGGER = LogManager.getLogger();
    static final String MOD_ID = "firearmy";

    public TheFireArmy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(final FMLCommonSetupEvent event) {
        FARecipeSerializers.init();
        FARecipeTypes.init();
    }

    public void doClientStuff(final FMLClientSetupEvent event){
        ClientRegistry.bindTileEntitySpecialRenderer(TEFireBlacksmithFurnace.class, new RenderFireBlackSmithFurnace());
        ClientRegistry.bindTileEntitySpecialRenderer(TEGrindstone.class, new RenderGrindstone());
    }

    public static String getModId() {
        return MOD_ID;
    }
}
