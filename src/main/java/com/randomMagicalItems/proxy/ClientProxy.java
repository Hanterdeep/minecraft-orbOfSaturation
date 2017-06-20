package com.randomMagicalItems.proxy;

import com.randomMagicalItems.handlers.ItemHandler;
import net.minecraftforge.fml.common.event.*;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        ItemHandler.registerRenders();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

    @Override
    public void serverStarting(FMLServerStartingEvent e) {
        super.serverStarting(e);
    }

    @Override
    public void serverStopping(FMLServerStoppingEvent e) {
        super.serverStopping(e);
    }

}
