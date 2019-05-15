package com.shadowprince345.thefirearmy;

import com.shadowprince345.thefirearmy.client.GuiHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
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
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(final FMLCommonSetupEvent event) {
//        proxy.setup();
    }
//
//    @Mod.EventHandler
//    public static void init(FMLInitializationEvent e){
//        EventHandler.registerOreDic();
//    }

    public static String getModId() {
        return MOD_ID;
    }
}
