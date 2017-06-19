package com.firstMod.handlers;

import com.firstMod.config.Config;
import com.firstMod.items.BoneWand;
import com.firstMod.items.OrbOfSaturation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {

    public static List<Item> items = new ArrayList<Item>();

    public static void init() {
        Item boneWand = new BoneWand("bone_wand", CreativeTabs.TOOLS);
        items.add(boneWand);
        Item orbOfSaturation = new OrbOfSaturation("orb_of_saturation", CreativeTabs.TOOLS, Config.getOrbOfSaturationMaxCapacity());
        items.add(orbOfSaturation);
        GameRegistry.addShapedRecipe(new ItemStack(orbOfSaturation), "BCB", "CDC", "BCB", 'B', Items.BREAD, 'C', Item.getByNameOrId("chest"), 'D', Items.DIAMOND);
    }

    public static void addCraftings() {

    }

    public static void register() {
        items.forEach(GameRegistry::register);
//        GameRegistry.register(boneWand);
    }

    public static void registerRenders() {
        items.forEach(ItemHandler::registerRender);
//        registerRender(boneWand);
    }

    public static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
