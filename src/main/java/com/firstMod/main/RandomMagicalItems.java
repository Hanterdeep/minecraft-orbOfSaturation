package com.firstMod.main;

import com.firstMod.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = RandomMagicalItems.MOD_ID, version = RandomMagicalItems.VERSION, name = RandomMagicalItems.NAME)
public class RandomMagicalItems {
    public static final String MOD_ID = "firstmod";
    public static final String NAME = "myFirstMod";
    public static final String VERSION = "1.0";
    public static final String CLIENT_PROXY = "com.firstMod.proxy.ClientProxy";
    public static final String COMMON_PROXY = "com.firstMod.proxy.CommonProxy";

    @SidedProxy(clientSide = RandomMagicalItems.CLIENT_PROXY, serverSide = RandomMagicalItems.COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.Instance
    public static RandomMagicalItems instance;

    public static final Logger LOGGER = LogManager.getLogger(RandomMagicalItems.MOD_ID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Starting pre-initialization...");
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("Starting initialization...");
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("Starting post-initialization...");
        proxy.postInit(event);
    }
}
