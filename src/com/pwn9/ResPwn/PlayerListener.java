package com.pwn9.ResPwn;

import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerListener implements Listener 
{

	private final ResPwn plugin;
	
	public PlayerListener(ResPwn plugin) 
	{
	    plugin.getServer().getPluginManager().registerEvents(this, plugin);    
	    this.plugin = plugin;
	}	
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e)
	{	
		
		// Init world event is taking place in.
		World w = e.getRespawnLocation().getWorld();
		
		// We should check to see if plugin is enabled first.
		if (!ResPwn.isEnabledIn(w.getName())) return; 

		// We should also check to see what other config settings are set before adding player to the timer
		// some code will go here...
		
		// Init the player as Player p to save some typing later
		Player p = e.getPlayer();
			
		// Add this respawned player to the respawn timer
		plugin.respawnShieldTimes.put(p.getName(), plugin.calcTimer(ResPwn.respawnShieldTimer));	
		
		// Log respawn event
		ResPwn.logToFile("Player " + p.getDisplayName() + " respawn timer activated: " + ResPwn.respawnShieldTimer);
		
		// Send them a message 
		p.sendMessage("§cRespawn shield activated for §6" + ResPwn.respawnShieldTimer / 1000 + "§c seconds.");

	}
	
	// Handle damage events, ignore cancelled for efficiency
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent e) 
	{
		
		// Get the event world
		World w = e.getDamager().getWorld();
		
		// We should check to see if plugin is enabled first.
		if (!ResPwn.isEnabledIn(w.getName())) return; 

		// The attacker
		Player attacker = null;
		
		// The victim
		Player victim = null;
		
		// Determine who or what attacker is
		if(e.getDamager() instanceof Arrow) 
		{
			Arrow arrow = (Arrow)e.getDamager();
			if(arrow.getShooter() instanceof Player) 
			{
				attacker = (Player)arrow.getShooter();
			} 
		} 
		else if(e.getDamager() instanceof Player) 
		{
			attacker = (Player)e.getDamager();
		} 
		else 
		{
			// attacker is NOT a player
		}
		
		// Check if damage is done TO a player
		if(e.getEntity() instanceof Player) 
		{
			victim = (Player)e.getEntity();
		}
		else 
		{
			// victim is NOT a player
		}

		// 1. Check if ATTACKER is in the respawn timer
        if(plugin.respawnShieldTimes.containsKey(attacker.getName()) && (attacker instanceof Player)) 
        {
        	
            Long respTime = plugin.respawnShieldTimes.get(attacker.getName());
            Long currTime = System.currentTimeMillis();
                        
            if((respTime > currTime) && (!ResPwn.clearOnAttack))
            {
            	attacker.sendMessage("§cRespawn shield on, no attacking for §6" + (respTime / 1000 - currTime / 1000) + "§c seconds.");
            	e.setCancelled(true);
            } 
            else 
            {
                if(ResPwn.clearOnAttack) 
                {
                	plugin.respawnShieldTimes.remove(attacker.getName());
                	attacker.sendMessage("§cYour respawn shield has been removed due to attacking!");
                }
            }
        }
					
		// 2. Check if the VICTIM is in the respawn timer	
        if(plugin.respawnShieldTimes.containsKey(victim.getName()) && (victim instanceof Player)) 
        {

            Long respTime = plugin.respawnShieldTimes.get(victim.getName());
            Long currTime = System.currentTimeMillis();
            
            if(respTime > currTime) 
            {
        		
            	if (attacker instanceof Player)
        		{
        			attacker.sendMessage("§c" + victim.getName() + " has respawn shield for §6" + (respTime / 1000 - currTime / 1000) + "§c seconds.");
        		}
        		e.setCancelled(true);
        		
            } 
        }		        
        
	}
	
}
