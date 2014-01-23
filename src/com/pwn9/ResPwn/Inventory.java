package com.pwn9.ResPwn;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Inventory
{

	// We'll set inventories here
	public static void ResInventory(Player p, World w) 
	{
		
	}
	
	// We'll set armors here
	public static void ResArmor(Player p, World w) 
	{

		//example
		
		PlayerInventory pi = p.getInventory();
		
		ItemStack gethelmet = new ItemStack(Material.IRON_HELMET);
		ItemStack getboots = new ItemStack(Material.IRON_BOOTS);
		
		ItemStack getsword = new ItemStack(Material.IRON_SWORD);
		
		pi.setHelmet(gethelmet);
		pi.setBoots(getboots);
		
		pi.setItemInHand(getsword);
		
		 
	}
	
}