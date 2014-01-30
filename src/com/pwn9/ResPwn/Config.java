package com.pwn9.ResPwn;

public class Config extends ResPwn
{

	// Load up any values from the config file
	public static void LoadConfig()
	{

		// Get enabled worlds
		ResPwn.enabledWorlds = instance.getConfig().getStringList("enabled_worlds");		
		
		// Get logging enabled
		ResPwn.logEnabled = instance.getConfig().getBoolean("debug_log");
		
		// Respawn timer config setting, default 20
		ResPwn.respawnShieldTimer = instance.getConfig().getInt("respawn_shield_timer", 20) * 1000;

		// Respawn tptimer config setting, default 20
		ResPwn.respawnTpTimer = instance.getConfig().getInt("respawn_tpshield_timer", 20) * 1000;
		
		// Clear on attack
		ResPwn.clearOnAttack = instance.getConfig().getBoolean("clear_on_attack");
		
		// Player health & hunger
		ResPwn.respawnHealth =  instance.getConfig().getInt("respawn_health", 20);
		ResPwn.respawnHunger =  instance.getConfig().getInt("respawn_hunger", 20);
		
		// Armor
		ResPwn.respawnBoots = instance.getConfig().getString("respawn_armor.boots.type", "none");
		ResPwn.respawnBootsEnchants = instance.getConfig().getConfigurationSection("respawn_armor.boots.enchants").getValues(false);
		ResPwn.respawnBootsName = instance.getConfig().getString("respawn_armor.boots.name");
		ResPwn.respawnBootsLore = instance.getConfig().getStringList("respawn_armor.boots.lore");
		ResPwn.respawnBootsColor = instance.getConfig().getString("respawn_armor.boots.color", "none");
		
		ResPwn.respawnHelm = instance.getConfig().getString("respawn_armor.helm.type", "none");
		ResPwn.respawnHelmEnchants = instance.getConfig().getConfigurationSection("respawn_armor.helm.enchants").getValues(false);
		ResPwn.respawnHelmName = instance.getConfig().getString("respawn_armor.helm.name");
		ResPwn.respawnHelmLore = instance.getConfig().getStringList("respawn_armor.helm.lore");
		ResPwn.respawnHelmColor = instance.getConfig().getString("respawn_armor.helm.color", "none");
		
		ResPwn.respawnPants = instance.getConfig().getString("respawn_armor.pants.type", "none");
		ResPwn.respawnPantsEnchants = instance.getConfig().getConfigurationSection("respawn_armor.pants.enchants").getValues(false);
		ResPwn.respawnPantsName = instance.getConfig().getString("respawn_armor.pants.name");		
		ResPwn.respawnPantsLore = instance.getConfig().getStringList("respawn_armor.pants.lore");
		ResPwn.respawnPantsColor = instance.getConfig().getString("respawn_armor.pants.color", "none");
		
		ResPwn.respawnPlate = instance.getConfig().getString("respawn_armor.plate.type", "none");
		ResPwn.respawnPlateEnchants = instance.getConfig().getConfigurationSection("respawn_armor.plate.enchants").getValues(false);
		ResPwn.respawnPlateName = instance.getConfig().getString("respawn_armor.plate.name");
		ResPwn.respawnPlateLore = instance.getConfig().getStringList("respawn_armor.plate.lore");
		ResPwn.respawnPlateColor = instance.getConfig().getString("respawn_armor.plate.color", "none");
		
		// Item in hand
		ResPwn.respawnWield = instance.getConfig().getString("respawn_wield.item", "none");
		ResPwn.respawnWieldEnchants = instance.getConfig().getConfigurationSection("respawn_wield.enchants").getValues(false);
		ResPwn.respawnWieldName = instance.getConfig().getString("respawn_wield.name");
		ResPwn.respawnWieldLore = instance.getConfig().getStringList("respawn_wield.lore");
	}
	
}