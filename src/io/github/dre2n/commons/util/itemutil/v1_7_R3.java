package io.github.dre2n.commons.util.itemutil;

import io.github.dre2n.commons.util.NumberUtil;
import net.minecraft.server.v1_7_R3.NBTTagByte;
import net.minecraft.server.v1_7_R3.NBTTagCompound;
import net.minecraft.server.v1_7_R3.NBTTagDouble;
import net.minecraft.server.v1_7_R3.NBTTagInt;
import net.minecraft.server.v1_7_R3.NBTTagList;
import net.minecraft.server.v1_7_R3.NBTTagString;

import org.bukkit.craftbukkit.v1_7_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

class v1_7_R3 {
	
	static ItemStack setAttribute(ItemStack itemStack, String attributeName, double value) {
		net.minecraft.server.v1_7_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
		
		NBTTagCompound compound = nmsStack.getTag();
		if (compound == null) {
			compound = new NBTTagCompound();
			nmsStack.setTag(compound);
			compound = nmsStack.getTag();
		}
		NBTTagList modifiers = compound.getList("AttributeModifiers", 10);
		
		NBTTagCompound attribute = new NBTTagCompound();
		attribute.set("AttributeName", new NBTTagString(attributeName));
		attribute.set("Name", new NBTTagString("IXL-" + attributeName));
		attribute.set("Amount", new NBTTagDouble(value));
		attribute.set("Operation", new NBTTagByte((byte) 0));
		attribute.set("UUIDLeast", new NBTTagInt(NumberUtil.generateRandomInt(1, 50000)));
		attribute.set("UUIDMost", new NBTTagInt(NumberUtil.generateRandomInt(50001, 100000)));
		
		modifiers.add(attribute);
		
		compound.set("AttributeModifiers", modifiers);
		nmsStack.setTag(compound);
		
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
	
	static ItemStack setSkullOwner(ItemStack itemStack, String id, String textureValue) {
		net.minecraft.server.v1_7_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
		
		NBTTagCompound compound = nmsStack.getTag();
		if (compound == null) {
			compound = new NBTTagCompound();
			nmsStack.setTag(compound);
			compound = nmsStack.getTag();
		}
		
		NBTTagCompound skullOwner = new NBTTagCompound();
		skullOwner.set("Id", new NBTTagString(id));
		NBTTagCompound properties = new NBTTagCompound();
		NBTTagList textures = new NBTTagList();
		NBTTagCompound value = new NBTTagCompound();
		value.set("Value", new NBTTagString(textureValue));
		textures.add(value);
		properties.set("textures", textures);
		skullOwner.set("Properties", properties);
		
		compound.set("SkullOwner", skullOwner);
		nmsStack.setTag(compound);
		
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
	
	static ItemStack setUnbreakable(ItemStack itemStack) {
		net.minecraft.server.v1_7_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
		
		NBTTagCompound compound = nmsStack.getTag();
		if (compound == null) {
			compound = new NBTTagCompound();
			nmsStack.setTag(compound);
			compound = nmsStack.getTag();
		}
		
		compound.set("Unbreakable", new NBTTagInt(1));
		
		nmsStack.setTag(compound);
		
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
	
}
