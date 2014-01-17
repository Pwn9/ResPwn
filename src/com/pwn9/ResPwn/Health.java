package com.pwn9.ResPwn;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class Health 
{

	// Set player respawn health levels
	public static void setResHealth(final Player p, World w)
	{
		
		// Check to see if plugin is even enabled in this world, if not, quit now yo.
		if (!ResPwn.isEnabledIn(w.getName())) return; 
		
		// Check perms for this setting
		if (!p.hasPermission("respwn.health")) return;
		
		if (ResPwn.respawnHealth < 20)
		{
			ResPwn.instance.getServer().getScheduler().scheduleSyncDelayedTask(ResPwn.instance, new Runnable() {
				public void run() 
				{
					p.setHealth(ResPwn.respawnHealth);	
					
					if (ResPwn.logEnabled) 
					{
						ResPwn.logToFile("Init ResPwn Health for player: " + p.getName() + " at level: " + ResPwn.respawnHealth);
					}
				}
			}, 10L);
		}
		
	}

	// Might as well do hunger here too
	public static void setResHunger(final Player p, World w)
	{
		
		// Check to see if plugin is even enabled in this world, if not, quit now yo.
		if (!ResPwn.isEnabledIn(w.getName())) return; 
		
		// Check perms for this setting
		if (!p.hasPermission("respwn.hunger")) return;
		
		if (ResPwn.respawnHunger < 20)
		{
			ResPwn.instance.getServer().getScheduler().scheduleSyncDelayedTask(ResPwn.instance, new Runnable() {
				public void run() 
				{
					p.setFoodLevel(ResPwn.respawnHunger);
					
					if (ResPwn.logEnabled) 
					{
						ResPwn.logToFile("ResPwn Hunger for player: " + p.getName() + " at level: " + ResPwn.respawnHunger);
					}
				}
			}, 10L);			
		}	
		
	}
}