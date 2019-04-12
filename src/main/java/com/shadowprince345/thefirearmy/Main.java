package com.shadowprince345.thefirearmy;

import com.shadowprince345.thefirearmy.objects.blocks.Blocks;
import com.shadowprince345.thefirearmy.objects.creativetab.Tabs;
import com.shadowprince345.thefirearmy.objects.items.Items;
import com.shadowprince345.thefirearmy.proxy.CommonProxy;
import com.shadowprince345.thefirearmy.utils.References;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import static com.shadowprince345.thefirearmy.Main.*;

@Mod(modid = MOD_ID, name = NAME, version = VERSION)
public class Main {
    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = References.CLIENT_SIDE, serverSide = References.COMMON_SIDE)
    public static CommonProxy proxy;

    public static Logger logger;
    static final String MOD_ID = "firearmy";
    static final String NAME = "The Fire Army";
    static final String VERSION = "1.0";

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent e){
        logger = e.getModLog();
        Tabs.init();
        Blocks.init();
        Items.init();
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent e){
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
