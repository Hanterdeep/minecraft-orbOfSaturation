package com.firstMod.items;

import com.firstMod.main.RandomMagicalItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class OrbOfSaturation extends Item {
    protected int capacity;

    public OrbOfSaturation(String name, CreativeTabs tab, int capacity) {
        super();
//        RandomMagicalItems.LOGGER.info("Creating " + this.getClass().getSimpleName() + " with unlocalized and registry name: " + name);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);
        setMaxStackSize(1);
        this.capacity = capacity;
    }

    public int getSaturationCounter(ItemStack stack) throws NullPointerException {
        NBTTagCompound tag = getNBTShareTag(stack);
        if (tag == null) {
            setSaturationCounter(stack, 0);
            return 0;
        }
        return tag.getInteger("saturationCounter");
    }

    public void setSaturationCounter(ItemStack stack, int counter) throws NullPointerException {
        NBTTagCompound tag = stack.getItem().getNBTShareTag(stack);
        if (tag == null) tag = new NBTTagCompound();
        tag.setInteger("saturationCounter", counter);
        stack.setTagCompound(tag);
    }

    public void incrementSaturationCounter(ItemStack stack, int inc) throws NullPointerException {
        int tmp = getSaturationCounter(stack) + inc;
        tmp = (tmp > capacity) ? capacity : tmp;
        setSaturationCounter(stack, tmp);
    }
    public int getCapacity() {
        return capacity;
    }

    // true if orb full and food doesn't consumed
    public boolean chargeOrb(ItemStack stack, int points) {
        if (getSaturationCounter(stack) >= capacity) return true;

        incrementSaturationCounter(stack, points);

        return false;
    }

    //    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        EntityPlayer player = (EntityPlayer) entityIn;
        ItemStack ownStack = null;
        for(ItemStack s: player.inventory.mainInventory) {
            if (s != null && s.getItem() == this) {
                try {
                    getSaturationCounter(s);
                    ownStack = s;
                    break;
                }
                catch (NullPointerException e) {
                    RandomMagicalItems.LOGGER.catching(e);
                }
            }
        }
        if(ownStack != null) {

            if (getSaturationCounter(ownStack) > 0 && player.getFoodStats().getFoodLevel() < 20) {
                int diff = 20 - player.getFoodStats().getFoodLevel();
                int regen = (diff < getSaturationCounter(ownStack)) ? diff : getSaturationCounter(ownStack);
                incrementSaturationCounter(ownStack, -regen);
                player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + regen);
            }
        }
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        InventoryPlayer inv = playerIn.inventory;

        if (inv.getCurrentItem().getItem() instanceof OrbOfSaturation) {
            ItemStack ownStack = inv.getCurrentItem();
            OrbOfSaturation orb = (OrbOfSaturation) inv.getCurrentItem().getItem();
            for (int i = 0; i < inv.getSizeInventory() && orb.getSaturationCounter(ownStack) < capacity; i++) {
                ItemStack stack = inv.getStackInSlot(i);
                if (stack.getItem() instanceof ItemFood) {
                    int taken = 0;

                    for (; taken < stack.getCount() && !orb.chargeOrb(ownStack, ((ItemFood) stack.getItem()).getHealAmount(stack)); taken++)
                        ;
                    inv.setInventorySlotContents(i, stack);
                    playerIn.inventory.clearMatchingItems(stack.getItem(), -1, taken, null);
                }

            }
            playerIn.sendStatusMessage(new TextComponentString("Current orb charge: " + getSaturationCounter(ownStack) + "/" + capacity), false);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
