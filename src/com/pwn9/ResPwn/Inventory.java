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
		// Check to see if plugin is even enabled in this world, if not, quit now yo.
		if (!ResPwn.isEnabledIn(w.getName())) return; 
		
		// Check perms for this setting
		if (!p.hasPermission("respwn.health")) return;		
		
		// And should check the configs
		
		// Get players inventory - it should be empty (ish)
		PlayerInventory pi = p.getInventory();
		
	}
	
	// We'll set armors here
	public static void ResArmor(Player p, World w) 
	{
		// Check to see if plugin is even enabled in this world, if not, quit now yo.
		if (!ResPwn.isEnabledIn(w.getName())) return; 
		
		// Check perms for this setting
		if (!p.hasPermission("respwn.health")) return;		
			
		// Get players inventory - it should be empty (ish)
		PlayerInventory pi = p.getInventory();
		
		// Boots
		ItemStack getboots = new ItemStack(Material.getMaterial(ResPwn.respawnBoots));
		//Map<Enchantment, Integer> bootEnchants = new HashMap<Enchantment, Integer>();
		//bootEnchants.put(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		//getboots.addEnchantments(bootEnchants);
		
		// Helmet
		ItemStack gethelmet = new ItemStack(Material.getMaterial(ResPwn.respawnHelm));
		//Map<Enchantment, Integer> helmetEnchants = new HashMap<Enchantment, Integer>();
		//helmetEnchants.put(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		//gethelmet.addEnchantments(helmetEnchants);

		// Leggings (I'm calling them pants)
		ItemStack getpants = new ItemStack(Material.getMaterial(ResPwn.respawnPants));
		//Map<Enchantment, Integer> pantsEnchants = new HashMap<Enchantment, Integer>();
		//helmetEnchants.put(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		//getpants.addEnchantments(pantsEnchants);
		
		// Chestplate
		ItemStack getplate = new ItemStack(Material.getMaterial(ResPwn.respawnPlate));
		//Map<Enchantment, Integer> plateEnchants = new HashMap<Enchantment, Integer>();
		//helmetEnchants.put(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		//getplate.addEnchantments(plateEnchants);
		
		// Item in hand (I'm calling it wield)
		ItemStack getwield = new ItemStack(Material.getMaterial(ResPwn.respawnWield));
		//Map<Enchantment, Integer> wieldEnchants = new HashMap<Enchantment, Integer>();
		//helmetEnchants.put(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		//getwield.addEnchantments(wieldEnchants);
		
		// Give player the items
		pi.setBoots(getboots);
		pi.setHelmet(gethelmet);
		pi.setLeggings(getpants);
		pi.setChestplate(getplate);
		pi.setItemInHand(getwield);	

	}
	
}