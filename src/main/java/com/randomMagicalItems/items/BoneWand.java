package com.randomMagicalItems.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BoneWand extends Item {
    public BoneWand(String name, CreativeTabs tab) {
        super();
//        RandomMagicalItems.LOGGER.info("Creating BoneWand with unlocalized and registry name: " + name);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);
        setMaxStackSize(1);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {

        if (entity == null) return true;

        entity.changeDimension(1);

        return false;
    }
}
