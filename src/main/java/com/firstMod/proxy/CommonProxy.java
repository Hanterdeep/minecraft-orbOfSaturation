package com.firstMod.proxy;

import com.firstMod.handlers.ItemHandler;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        ItemHandler.init();
        ItemHandler.register();
    }
    public void init(FMLInitializationEvent e) {}
    public void postInit(FMLPostInitializationEvent e) {}
    public void serverStarting(FMLServerStartingEvent e) {}
    public void serverStopping(FMLServerStoppingEvent e) {}

}
