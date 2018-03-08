package com.pwn9.ResPwn;

import java.util.HashMap;
import java.util.List;
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

	//TODO: We'll set inventories here - this function not yet implemented
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
	
	// returns an itemstack for setting armor 
	public static ItemStack SetArmors(String itemBase, Map<String, Object> itemEnchants, String itemColor, String itemName, List<String> itemLore) {
		
		ItemStack getItem = new ItemStack(Material.getMaterial(itemBase));
		
		// set enchantments
		if (itemEnchants.get("use").equals(true)) {
			Map<Enchantment, Integer> itemEnchant = new HashMap<Enchantment, Integer>();
			for (String key : itemEnchants.keySet()) 
			{
				if (!key.equals("use")) 
				{
					itemEnchant.put(Enchantment.getByName(key), (Integer) itemEnchants.get(key));
				}
			}
			getItem.addEnchantments(itemEnchant);
		}
		
		// Set lore and display name item meta
		if(getItem.hasItemMeta()) 
		{
			// create item meta variable
			ItemMeta im = getItem.getItemMeta();
			// if it's leather we can set the color
			if((im instanceof LeatherArmorMeta) && (itemColor != "none")) 
			{
				((LeatherArmorMeta) im).setColor(Color.fromRGB(Integer.decode(itemColor)));
			}
			// set item meta display name
			im.setDisplayName(itemName);
			// set item meta lore
			im.setLore(itemLore);
			// put updated item meta on item
			getItem.setItemMeta(im);				
		}
			
		return getItem;
			
	}
	
	// We'll set armors here
	public static void ResArmor(Player p, World w) 
	{
		
		/* 
		 * TO-DO: Checks and balances, on badly configured items from the config.yml, 
		 * right now this sort of lets the server admin be stupid and break the plugin
		 * it they don't set stuff up right, but then again, that's their problem ain't 
		 * it? I mean, the config.yml is commented and documented.
		 */
		
		// Check to see if plugin is even enabled in this world, if not, quit now yo.
		if (!ResPwn.isEnabledIn(w.getName())) return; 
		
		// Check perms for this setting
		if (!p.hasPermission("respwn.armor")) return;		
			
		// Get players inventory - it should be empty (ish)
		PlayerInventory pi = p.getInventory();
		
		// Set the armors with the SetArmors routine
		
		// Boots
		if (ResPwn.respawnBootsUse) {
			pi.setBoots(Inventory.SetArmors(ResPwn.respawnBoots, ResPwn.respawnBootsEnchants, ResPwn.respawnBootsColor, ResPwn.respawnBootsName, ResPwn.respawnBootsLore));
		}
		
		// Helm
		if (ResPwn.respawnHelmUse) {
			pi.setHelmet(Inventory.SetArmors(ResPwn.respawnHelm, ResPwn.respawnHelmEnchants, ResPwn.respawnHelmColor, ResPwn.respawnHelmName, ResPwn.respawnHelmLore));
		}

		// Leggings (I'm calling them pants)
		if (ResPwn.respawnPantsUse) {
			pi.setLeggings(Inventory.SetArmors(ResPwn.respawnPants, ResPwn.respawnPantsEnchants, ResPwn.respawnPantsColor, ResPwn.respawnPantsName, ResPwn.respawnPantsLore));
		}
		
		// Chestplate
		if (ResPwn.respawnPlateUse) {
			pi.setChestplate(Inventory.SetArmors(ResPwn.respawnPlate, ResPwn.respawnPlateEnchants, ResPwn.respawnPlateColor, ResPwn.respawnPlateName, ResPwn.respawnPlateLore));
		}
		
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
		
		// Check config to see if wield is even enabled
		if (!ResPwn.respawnWieldUse) return;
			
		// Get players inventory - it should be empty (ish)
		PlayerInventory pi = p.getInventory();
		
		// Item in hand (I'm calling it wield)
		ItemStack getwield = new ItemStack(Material.getMaterial(ResPwn.respawnWield));
		
		// Get the enchants
		if (ResPwn.respawnWieldEnchants.get("use").equals(true)) {
			Map<Enchantment, Integer> wieldEnchants = new HashMap<Enchantment, Integer>();
			for (String key : ResPwn.respawnWieldEnchants.keySet()) 
			{
				if (!key.equals("use"))  
				{
					wieldEnchants.put(Enchantment.getByName(key), (Integer) ResPwn.respawnWieldEnchants.get(key));
				}
			}
			// Now set the enchants
			getwield.addEnchantments(wieldEnchants);
		}
		
		// Set lore and display name item meta
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
		pi.setItemInMainHand(getwield);

		// we could set the offhand now too..
		//p.getInventory().setItemInOffHand(something);
	}
	
	// We'll set off hand wielded item here
	public static void ResOffhand(Player p, World w) 
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
		if (!p.hasPermission("respwn.offhand")) return;	
		
		// Check config to see if wield is even enabled
		if (!ResPwn.respawnOffhandUse) return;
			
		// Get players inventory - it should be empty (ish)
		PlayerInventory pi = p.getInventory();
		
		// Item in hand (I'm calling it wield)
		ItemStack getwield = new ItemStack(Material.getMaterial(ResPwn.respawnOffhand));
		
		// Get the enchants
		if (ResPwn.respawnOffhandEnchants.get("use").equals(true)) {
			Map<Enchantment, Integer> wieldEnchants = new HashMap<Enchantment, Integer>();
			for (String key : ResPwn.respawnOffhandEnchants.keySet()) 
			{
				if (!key.equals("use")) 
				{
					wieldEnchants.put(Enchantment.getByName(key), (Integer) ResPwn.respawnOffhandEnchants.get(key));
				}
			}
			// Now set the enchants
			getwield.addEnchantments(wieldEnchants);
		}
		
		// Set lore and display name item meta
		if(getwield.hasItemMeta()) 
		{
			// create item meta variable
			ItemMeta im = getwield.getItemMeta();
			// set item meta display name
			im.setDisplayName(ResPwn.respawnOffhandName);
			// set item meta lore
			im.setLore(ResPwn.respawnOffhandLore);
			// put updated item meta on item
			getwield.setItemMeta(im);
		}
		
		// Give player an off hand item
		pi.setItemInOffHand(getwield);
	}
	
}