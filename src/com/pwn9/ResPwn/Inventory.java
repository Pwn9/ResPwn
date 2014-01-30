package com.pwn9.ResPwn;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Inventory extends ResPwn
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
		/*
		 * TO-DO: Shorten this up some, seems like I could probably do a loop for each armor
		 * piece and set up the item stack that way, it would be less code needed and potentially 
		 * scalable if I ever set this up to do per permission armor sets on respawn.
		 */
		
		/* 
		 * TO-DO: Checks and balances, on badly configured items from the config.yml, 
		 * right now this sort of lets the server admin be stupid and break the plugin
		 * it they don't set stuff up right, but then again, that's there problem ain't 
		 * it? I mean, the config.yml is commented and documented.
		 */
		
		// Check to see if plugin is even enabled in this world, if not, quit now yo.
		if (!ResPwn.isEnabledIn(w.getName())) return; 
		
		// Check perms for this setting
		if (!p.hasPermission("respwn.armor")) return;		
			
		// Get players inventory - it should be empty (ish)
		PlayerInventory pi = p.getInventory();
		
		// Boots
		ItemStack getboots = new ItemStack(Material.getMaterial(ResPwn.respawnBoots));
		Map<Enchantment, Integer> bootEnchants = new HashMap<Enchantment, Integer>();
		for (String key : ResPwn.respawnBootsEnchants.keySet()) 
		{
			bootEnchants.put(Enchantment.getByName(key), (Integer) ResPwn.respawnBootsEnchants.get(key));
		}
		getboots.addEnchantments(bootEnchants);
		// Set lore and displayname item meta
		if(getboots.hasItemMeta()) 
		{
			// create item meta variable
			ItemMeta im = getboots.getItemMeta();
			// is it leather?
			if((im instanceof LeatherArmorMeta) && (ResPwn.respawnBootsColor != "none")) 
			{
				((LeatherArmorMeta) im).setColor(Color.fromRGB(Integer.decode(ResPwn.respawnBootsColor)));
			}
			// set item meta display name
			im.setDisplayName(ResPwn.respawnBootsName);
			// set item meta lore
			im.setLore(ResPwn.respawnBootsLore);
			// put updated item meta on item
			getboots.setItemMeta(im);				
		}		
		
		// Helmet
		ItemStack gethelmet = new ItemStack(Material.getMaterial(ResPwn.respawnHelm));
		Map<Enchantment, Integer> helmEnchants = new HashMap<Enchantment, Integer>();
		for (String key : ResPwn.respawnHelmEnchants.keySet()) 
		{
			helmEnchants.put(Enchantment.getByName(key), (Integer) ResPwn.respawnHelmEnchants.get(key));
		}
		gethelmet.addEnchantments(helmEnchants);
		// Set lore and displayname item meta
		if(gethelmet.hasItemMeta()) 
		{
			// create item meta variable
			ItemMeta im = gethelmet.getItemMeta();
			// is it leather?
			if((im instanceof LeatherArmorMeta) && (ResPwn.respawnHelmColor != "none")) 
			{
				((LeatherArmorMeta) im).setColor(Color.fromRGB(Integer.decode(ResPwn.respawnHelmColor)));
			}
			// set item meta display name
			im.setDisplayName(ResPwn.respawnHelmName);
			// set item meta lore
			im.setLore(ResPwn.respawnHelmLore);
			// put updated item meta on item
			gethelmet.setItemMeta(im);					
		}		

		// Leggings (I'm calling them pants)
		ItemStack getpants = new ItemStack(Material.getMaterial(ResPwn.respawnPants));
		Map<Enchantment, Integer> pantsEnchants = new HashMap<Enchantment, Integer>();
		for (String key : ResPwn.respawnPantsEnchants.keySet()) 
		{
			pantsEnchants.put(Enchantment.getByName(key), (Integer) ResPwn.respawnPantsEnchants.get(key));
		}
		getpants.addEnchantments(pantsEnchants);
		// Set lore and displayname item meta
		if(getpants.hasItemMeta()) 
		{
			// create item meta variable
			ItemMeta im = getpants.getItemMeta();
			// is it leather?
			if((im instanceof LeatherArmorMeta) && (ResPwn.respawnPantsColor != "none")) 
			{
				((LeatherArmorMeta) im).setColor(Color.fromRGB(Integer.decode(ResPwn.respawnPantsColor)));
			}
			// set item meta display name
			im.setDisplayName(ResPwn.respawnPantsName);
			// set item meta lore
			im.setLore(ResPwn.respawnPantsLore);
			// put updated item meta on item
			getpants.setItemMeta(im);						
		}	
		
		// Chestplate
		ItemStack getplate = new ItemStack(Material.getMaterial(ResPwn.respawnPlate));
		Map<Enchantment, Integer> plateEnchants = new HashMap<Enchantment, Integer>();
		for (String key : ResPwn.respawnPlateEnchants.keySet()) 
		{
			plateEnchants.put(Enchantment.getByName(key), (Integer) ResPwn.respawnPlateEnchants.get(key));
		}
		getplate.addEnchantments(plateEnchants);
		// Set lore and displayname item meta
		if(getplate.hasItemMeta()) 
		{
			// create item meta variable
			ItemMeta im = getplate.getItemMeta();
			// is it leather?
			if((im instanceof LeatherArmorMeta) && (ResPwn.respawnPlateColor != "none")) 
			{
				((LeatherArmorMeta) im).setColor(Color.fromRGB(Integer.decode(ResPwn.respawnPlateColor)));
			}
			// set item meta display name
			im.setDisplayName(ResPwn.respawnPlateName);
			// set item meta lore
			im.setLore(ResPwn.respawnPlateLore);
			// put updated item meta on item
			getplate.setItemMeta(im);						
		}		
		
		
		// Give player the items we've seteup
		pi.setBoots(getboots);
		pi.setHelmet(gethelmet);
		pi.setLeggings(getpants);
		pi.setChestplate(getplate);
	}

	// We'll set wielded item here
	public static void ResWield(Player p, World w) 
	{
		
		/* 
		 * TO-DO: Checks and balances, on badly configured items from the config.yml, 
		 * right now this sort of lets the server admin be stupid and break the plugin
		 * it they don't set stuff up right, but then again, that's there problem ain't 
		 * it? I mean, the config.yml is commented and documented.
		 */
		
		// Check to see if plugin is even enabled in this world, if not, quit now yo.
		if (!ResPwn.isEnabledIn(w.getName())) return; 
		
		// Check perms for this setting
		if (!p.hasPermission("respwn.wield")) return;		
			
		// Get players inventory - it should be empty (ish)
		PlayerInventory pi = p.getInventory();
		
		// Item in hand (I'm calling it wield)
		ItemStack getwield = new ItemStack(Material.getMaterial(ResPwn.respawnWield));
		
		// Get the enchants
		Map<Enchantment, Integer> wieldEnchants = new HashMap<Enchantment, Integer>();
		for (String key : ResPwn.respawnWieldEnchants.keySet()) 
		{
			wieldEnchants.put(Enchantment.getByName(key), (Integer) ResPwn.respawnWieldEnchants.get(key));
		}
		// Now set the enchants
		getwield.addEnchantments(wieldEnchants);
		
		// Set lore and displayname item meta
		if(getwield.hasItemMeta()) 
		{
			// create item meta variable
			ItemMeta im = getwield.getItemMeta();
			// set item meta display name
			im.setDisplayName(ResPwn.respawnWieldName);
			// set item meta lore
			im.setLore(ResPwn.respawnWieldLore);
			// put updated item meta on item
			getwield.setItemMeta(im);
		}
		
		// Set active slot to slot 0, in case we want to give them other items in the hotbar someday
		pi.setHeldItemSlot(0);
		// Give player a wielded item
		pi.setItemInHand(getwield);	
	}
}