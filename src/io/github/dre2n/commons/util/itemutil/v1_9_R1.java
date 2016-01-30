package io.github.dre2n.commons.util.itemutil;

import io.github.dre2n.commons.util.itemutil.ItemUtil.Slot;
import io.github.dre2n.commons.util.NumberUtil;
import net.minecraft.server.v1_9_R1.NBTTagByte;
import net.minecraft.server.v1_9_R1.NBTTagCompound;
import net.minecraft.server.v1_9_R1.NBTTagDouble;
import net.minecraft.server.v1_9_R1.NBTTagInt;
import net.minecraft.server.v1_9_R1.NBTTagList;
import net.minecraft.server.v1_9_R1.NBTTagString;

import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

class v1_9_R1 {
	
	static ItemStack setAttribute(ItemStack itemStack, String attributeName, double value) {
		net.minecraft.server.v1_9_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
		
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
	
	static ItemStack setAttribute(ItemStack itemStack, String attributeName, double value, Slot slot) {
		net.minecraft.server.v1_9_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
		
		NBTTagCompound compound = nmsStack.getTag();
		if (compound == null) {
			compound = new NBTTagCompound();
			nmsStack.setTag(compound);
			compound = nmsStack.getTag();
		}
		NBTTagList modifiers = new NBTTagList();
		
		NBTTagCompound attribute = new NBTTagCompound();
		attribute.set("AttributeName", new NBTTagString(attributeName));
		attribute.set("Name", new NBTTagString("IXL-" + attributeName));
		attribute.set("Amount", new NBTTagDouble(value));
		attribute.set("Operation", new NBTTagByte((byte) 0));
		attribute.set("UUIDLeast", new NBTTagInt(NumberUtil.generateRandomInt(1, 50000)));
		attribute.set("UUIDMost", new NBTTagInt(NumberUtil.generateRandomInt(50001, 100000)));
		attribute.set("Slot", new NBTTagString(slot.name()));
		
		modifiers.add(attribute);
		
		compound.set("AttributeModifiers", modifiers);
		nmsStack.setTag(compound);
		
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
	
	static ItemStack setSkullOwner(ItemStack itemStack, String id, String textureValue) {
		net.minecraft.server.v1_9_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
		
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
		net.minecraft.server.v1_9_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
		
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
