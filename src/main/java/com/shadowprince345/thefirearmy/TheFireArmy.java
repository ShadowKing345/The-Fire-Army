package com.shadowprince345.thefirearmy;

import com.shadowprince345.thefirearmy.blocks.tiles.FireBlacksmithFurnaceRecipes;
import com.shadowprince345.thefirearmy.creativetab.Tabs;
import com.shadowprince345.thefirearmy.gui.GuiHandler;
import com.shadowprince345.thefirearmy.proxy.ServerProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;

import static com.shadowprince345.thefirearmy.TheFireArmy.*;

@Mod(modid = MOD_ID, name = NAME, version = VERSION)
public class TheFireArmy {
    @Mod.Instance
    public static TheFireArmy instance;

    @SidedProxy(clientSide = References.CLIENT_SIDE, serverSide = References.SERVER_SIDE)
    public static ServerProxy proxy;

    public static Logger logger;
    static final String MOD_ID = "firearmy";
    static final String NAME = "The Fire Army";
    static final String VERSION = "1.0";

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent e){
        logger = e.getModLog();
        Tabs.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent e){
        RegistryEventHandler.registerOreDic();
        FireBlacksmithFurnaceRecipes.INSTANCE.addDefaultRecipes();
    }

    public static void postInit(FMLPostInitializationEvent e){
    }

    public static String getModId() {
        return MOD_ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getVERSION() {
        return VERSION;
    }
}
