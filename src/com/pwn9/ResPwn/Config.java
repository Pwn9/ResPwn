package com.pwn9.ResPwn;

public class Config 
{

	// Load up any values from the config file
	public static void LoadConfig()
	{

		// Get enabled worlds
		ResPwn.enabledWorlds = ResPwn.instance.getConfig().getStringList("enabled_worlds");		
		
		// Get logging enabled
		ResPwn.logEnabled = ResPwn.instance.getConfig().getBoolean("debug_log");
		
		// Get prefix enabled
		ResPwn.prefixEnabled = ResPwn.instance.getConfig().getBoolean("prefix_enabled");
		
		// Get prefix stuffs
		ResPwn.prefixMsg = ResPwn.instance.getConfig().getString("prefix_msg", "ResPwn");
		ResPwn.prefixMsgColor = ResPwn.instance.getConfig().getString("prefix_msg_color", "GOLD");
		ResPwn.prefixWrapFront = ResPwn.instance.getConfig().getString("prefix_wrap_front", "[");
		ResPwn.prefixWrapBack = ResPwn.instance.getConfig().getString("prefix_wrap_back", "]");
		ResPwn.prefixWrapColor = ResPwn.instance.getConfig().getString("prefix_wrap_color", "RED");
		
		// Respawn timer config setting, default 20
		ResPwn.respawnShieldTimer = ResPwn.instance.getConfig().getInt("respawn_shield_timer", 20) * 1000;

		// Respawn tptimer config setting, default 20
		ResPwn.respawnTpTimer = ResPwn.instance.getConfig().getInt("respawn_tpshield_timer", 20) * 1000;

		// Respawn command timer config setting, default 20
		ResPwn.respawnCommandTimer = ResPwn.instance.getConfig().getInt("respawn_cmdshield_timer", 20) * 1000;
		
		// Clear on attack
		ResPwn.clearOnAttack = ResPwn.instance.getConfig().getBoolean("clear_on_attack");
		
		// Player health & hunger
		ResPwn.respawnHealth = ResPwn.instance.getConfig().getInt("respawn_health", 20);
		ResPwn.respawnHunger = ResPwn.instance.getConfig().getInt("respawn_hunger", 20);
		
		// Armor delay
		ResPwn.armorDelay = ResPwn.instance.getConfig().getInt("armor_delay", 600) * 1000;
		
		// Inventory Booleans
		ResPwn.respawnBootsUse = ResPwn.instance.getConfig().getBoolean("respawn_armor.boots.use");
		ResPwn.respawnHelmUse = ResPwn.instance.getConfig().getBoolean("respawn_armor.helm.use");
		ResPwn.respawnPantsUse = ResPwn.instance.getConfig().getBoolean("respawn_armor.pants.use");
		ResPwn.respawnPlateUse = ResPwn.instance.getConfig().getBoolean("respawn_armor.plate.use");
		ResPwn.respawnWieldUse = ResPwn.instance.getConfig().getBoolean("respawn_wield.use");
		
		// Armor
		ResPwn.respawnBoots = ResPwn.instance.getConfig().getString("respawn_armor.boots.type", "none");
		ResPwn.respawnBootsEnchants = ResPwn.instance.getConfig().getConfigurationSection("respawn_armor.boots.enchants").getValues(false);
		ResPwn.respawnBootsName = ResPwn.instance.getConfig().getString("respawn_armor.boots.name");
		ResPwn.respawnBootsLore = ResPwn.instance.getConfig().getStringList("respawn_armor.boots.lore");
		ResPwn.respawnBootsColor = ResPwn.instance.getConfig().getString("respawn_armor.boots.color", "none");
		
		ResPwn.respawnHelm = ResPwn.instance.getConfig().getString("respawn_armor.helm.type", "none");
		ResPwn.respawnHelmEnchants = ResPwn.instance.getConfig().getConfigurationSection("respawn_armor.helm.enchants").getValues(false);
		ResPwn.respawnHelmName = ResPwn.instance.getConfig().getString("respawn_armor.helm.name");
		ResPwn.respawnHelmLore = ResPwn.instance.getConfig().getStringList("respawn_armor.helm.lore");
		ResPwn.respawnHelmColor = ResPwn.instance.getConfig().getString("respawn_armor.helm.color", "none");
		
		ResPwn.respawnPants = ResPwn.instance.getConfig().getString("respawn_armor.pants.type", "none");
		ResPwn.respawnPantsEnchants = ResPwn.instance.getConfig().getConfigurationSection("respawn_armor.pants.enchants").getValues(false);
		ResPwn.respawnPantsName = ResPwn.instance.getConfig().getString("respawn_armor.pants.name");		
		ResPwn.respawnPantsLore = ResPwn.instance.getConfig().getStringList("respawn_armor.pants.lore");
		ResPwn.respawnPantsColor = ResPwn.instance.getConfig().getString("respawn_armor.pants.color", "none");
		
		ResPwn.respawnPlate = ResPwn.instance.getConfig().getString("respawn_armor.plate.type", "none");
		ResPwn.respawnPlateEnchants = ResPwn.instance.getConfig().getConfigurationSection("respawn_armor.plate.enchants").getValues(false);
		ResPwn.respawnPlateName = ResPwn.instance.getConfig().getString("respawn_armor.plate.name");
		ResPwn.respawnPlateLore = ResPwn.instance.getConfig().getStringList("respawn_armor.plate.lore");
		ResPwn.respawnPlateColor = ResPwn.instance.getConfig().getString("respawn_armor.plate.color", "none");
		
		// Item in hand
		ResPwn.respawnWield = ResPwn.instance.getConfig().getString("respawn_wield.item", "none");
		ResPwn.respawnWieldEnchants = ResPwn.instance.getConfig().getConfigurationSection("respawn_wield.enchants").getValues(false);
		ResPwn.respawnWieldName = ResPwn.instance.getConfig().getString("respawn_wield.name");
		ResPwn.respawnWieldLore = ResPwn.instance.getConfig().getStringList("respawn_wield.lore");
		
		// Get balances config
		ResPwn.players.saveDefaultConfig();
	}
	
}