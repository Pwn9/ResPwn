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

	// We'll set inventories here - to do
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
		Map<Enchantment, Integer> bootEnchants = new HashMap<Enchantment, Integer>();
		for (String key : ResPwn.respawnBootsEnchants.keySet()) {
			bootEnchants.put(Enchantment.getByName(key), (Integer) ResPwn.respawnBootsEnchants.get(key));
		}
		getboots.addEnchantments(bootEnchants);
		
		// Helmet
		ItemStack gethelmet = new ItemStack(Material.getMaterial(ResPwn.respawnHelm));
		Map<Enchantment, Integer> helmEnchants = new HashMap<Enchantment, Integer>();
		for (String key : ResPwn.respawnHelmEnchants.keySet()) {
			helmEnchants.put(Enchantment.getByName(key), (Integer) ResPwn.respawnHelmEnchants.get(key));
		}
		gethelmet.addEnchantments(helmEnchants);

		// Leggings (I'm calling them pants)
		ItemStack getpants = new ItemStack(Material.getMaterial(ResPwn.respawnPants));
		Map<Enchantment, Integer> pantsEnchants = new HashMap<Enchantment, Integer>();
		for (String key : ResPwn.respawnPantsEnchants.keySet()) {
			pantsEnchants.put(Enchantment.getByName(key), (Integer) ResPwn.respawnPantsEnchants.get(key));
		}
		getpants.addEnchantments(pantsEnchants);
		
		// Chestplate
		ItemStack getplate = new ItemStack(Material.getMaterial(ResPwn.respawnPlate));
		Map<Enchantment, Integer> plateEnchants = new HashMap<Enchantment, Integer>();
		for (String key : ResPwn.respawnPlateEnchants.keySet()) {
			plateEnchants.put(Enchantment.getByName(key), (Integer) ResPwn.respawnPlateEnchants.get(key));
		}
		getplate.addEnchantments(plateEnchants);
		
		// Give player the items
		pi.setBoots(getboots);
		pi.setHelmet(gethelmet);
		pi.setLeggings(getpants);
		pi.setChestplate(getplate);
	}

	// We'll set wielded item here
	public static void ResWield(Player p, World w) 
	{
		// Check to see if plugin is even enabled in this world, if not, quit now yo.
		if (!ResPwn.isEnabledIn(w.getName())) return; 
		
		// Check perms for this setting
		if (!p.hasPermission("respwn.wield")) return;		
			
		// Get players inventory - it should be empty (ish)
		PlayerInventory pi = p.getInventory();
		
		// Item in hand (I'm calling it wield)
		ItemStack getwield = new ItemStack(Material.getMaterial(ResPwn.respawnWield));
		Map<Enchantment, Integer> wieldEnchants = new HashMap<Enchantment, Integer>();
		for (String key : ResPwn.respawnWieldEnchants.keySet()) {
			wieldEnchants.put(Enchantment.getByName(key), (Integer) ResPwn.respawnWieldEnchants.get(key));
		}
		getwield.addEnchantments(wieldEnchants);
		
		// Give player a wielded item
		pi.setItemInHand(getwield);	
	}
}