package com.natamus.guicompass.util;

import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Util {
	public static boolean isCompass(ItemStack itemStack) {
		Item item = itemStack.getItem();
		return item.toString().endsWith("compass") || item instanceof CompassItem;
	}
}
