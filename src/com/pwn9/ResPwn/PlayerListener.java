package com.pwn9.ResPwn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerListener implements Listener 
{

	public PlayerListener(ResPwn plugin) 
	{
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);    
	}	
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e)
	{	
		// Check if player will get shielded on respawn
		Shield.doShield(e);
		
		// Other respawn events go here...
	}
	
	// Handle damage events, ignore cancelled for efficiency
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent e) 
	{
		
		// Check is player is shielded 
		if (Shield.isShielded(e)) e.setCancelled(true);
        
	}
	
}
