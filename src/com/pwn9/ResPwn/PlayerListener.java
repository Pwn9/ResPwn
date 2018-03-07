package com.pwn9.ResPwn;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerListener implements Listener 
{

	public PlayerListener(ResPwn plugin) 
	{
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);    
	}	
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e)
	{	
		// Get the player, so we can send the player to the sub-functions
		Player p = e.getPlayer();
		
		// Get the world of the event so we can send to the sub-functions
		World w = e.getRespawnLocation().getWorld();
		
		// Check to see if plugin is even enabled in this world, if not, quit now yo.
		if (!ResPwn.isEnabledIn(w.getName())) return; 
		
		// Check if player will get shielded on respawn
		Shield.doShield(p, w);
		
		// Check if player will get tp blocked on respawn
		Shield.doTpShield(p, w);

		// Check if player will get tp blocked on respawn
		Shield.doCommandShield(p, w);
		
		// Do health based respawn stuff
		Health.setResHealth(p, w);
		Health.setResHunger(p, w);
		
		// Armor & Inventory Stuff
		if (!Shield.isShielded(e)) 
		{
			Boolean sendMsg = false;
			
			// can i has some armor?
			if (p.hasPermission("respwn.armor")) 
			{
				Inventory.ResArmor(p, w);
				sendMsg = true;
			}
			
			// can i has a sword?
			if (p.hasPermission("respwn.wield")) 
			{
				Inventory.ResWield(p, w);	
				sendMsg = true;
			}
			
			// can i has off hand?
			if (p.hasPermission("respwn.offhand")) 
			{
				Inventory.ResOffhand(p, w);	
				sendMsg = true;
			}
			
			if (sendMsg)
			{
				String puuid = p.getUniqueId().toString() + ".ArmorTime";
				Long pat = ResPwn.calcTimer((long) ResPwn.armorDelay);
				ResPwn.players.getConfig().set(puuid, pat);
				ResPwn.players.saveConfig();
			}
		}
		
		// Add potions here?
		if (p.hasPermission("respwn.potions"))
		{
			PotionTask task = new PotionTask(p);
			ResPwn.instance.getServer().getScheduler().runTaskLater(ResPwn.instance, task, 10L);
		}
			
	}
	
	// Handle damage events, ignore cancelled for efficiency
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent e) 
	{
		// Check is player is shielded 
		if (Shield.isShielded(e)) e.setCancelled(true);  
	}
	
	// Handle teleport events, ignore cancelled for efficiency
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void OnTeleport(PlayerTeleportEvent e) 
	{
		// Check is player is shielded 
		if (Shield.isShielded(e)) e.setCancelled(true);	
	}

	// Handle command events, ignore cancelled for efficiency
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void OnCommand(PlayerCommandPreprocessEvent e) 
	{
		// Check is player is shielded 
		if (Shield.isShielded(e)) e.setCancelled(true);
	}	
}
