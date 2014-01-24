package com.pwn9.ResPwn;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Inventory
{

	// We'll set inventories here
	public static void ResInventory(Player p, World w) 
	{
		// Before we do anything, should check permissions
		
		
		// And should check the configs
		
		
		// Get players inventory - it should be empty (ish)
		PlayerInventory pi = p.getInventory();
		
		
	}
	
	// We'll set armors here
	public static void ResArmor(Player p, World w) 
	{
		// Before we do anything, should check permissions
		
		
		// And should check the configs
		
		
		// Get players inventory - it should be empty (ish)
		PlayerInventory pi = p.getInventory();
		
		// Helmet
		ItemStack gethelmet = new ItemStack(Material.IRON_HELMET);
		Map<Enchantment, Integer> helmetEnchants = new HashMap<Enchantment, Integer>();
		helmetEnchants.put(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		gethelmet.addEnchantments(helmetEnchants);
		
		// Boots
		ItemStack getboots = new ItemStack(Material.IRON_BOOTS);
		
		// Leggings (I'm calling them pants)
		ItemStack getpants = new ItemStack(Material.IRON_LEGGINGS);
		
		// Chestplate
		ItemStack getplate = new ItemStack(Material.IRON_CHESTPLATE);
		
		// Item in hand (I'm calling it wield)
		ItemStack getwield = new ItemStack(Material.IRON_SWORD);
		
		
		// Give player the items
		pi.setHelmet(gethelmet);
		pi.setBoots(getboots);
		pi.setLeggings(getpants);
		pi.setChestplate(getplate);
		pi.setItemInHand(getwield);	
		 
	}
	
}