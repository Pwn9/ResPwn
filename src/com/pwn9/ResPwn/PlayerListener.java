package com.pwn9.ResPwn;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
		
		// Do health based respawn stuff
		Health.setResHealth(p, w);
		Health.setResHunger(p, w);
		
		// Armor & Inventory Stuff
		Inventory.ResArmor(p, w);
		Inventory.ResWield(p, w);
			
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
	
}
