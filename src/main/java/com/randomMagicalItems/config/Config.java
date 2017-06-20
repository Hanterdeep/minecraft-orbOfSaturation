package com.randomMagicalItems.config;

import com.randomMagicalItems.main.RandomMagicalItems;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {

    protected static final String CONFIG_DIR = "config/hanterdeep/" + RandomMagicalItems.MOD_ID + ".conf";
    protected static Configuration conf;

    protected static int orbOfSaturationMaxCapacity;

    public static int getOrbOfSaturationMaxCapacity() {
        return orbOfSaturationMaxCapacity;
    }

    static {
        File cFile = new File(CONFIG_DIR);
        conf = new Configuration(cFile);
        conf.addCustomCategoryComment("OrbOfSaturation", "Config for Orb of Saturation");
        orbOfSaturationMaxCapacity = conf.getInt("capacity", "OrbOfSaturation", 100, 50, 5000, "Max capacity of Orb of Saturation");
        conf.save();
    }



}
