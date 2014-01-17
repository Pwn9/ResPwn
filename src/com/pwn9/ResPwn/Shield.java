package com.pwn9.ResPwn;

import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Shield 
{
	
	public static void doShield(Player p, World w)
	{
		
		// We should check to see if plugin is enabled first.
		if (!ResPwn.isEnabledIn(w.getName())) return; 

		/*** We should also check to see what other config settings are set before adding player to the timer ***/
		
		// some code will go here...

		/*** Check against the player for more settings before adding player to the timer ***/
		
		// Check is player has permission to use shield
		if (!p.hasPermission("respwn.shield")) return;
		
		/*** OK, everything is good lets add player to the timer ***/
		
		// Add this respawned player to the respawn timer
		ResPwn.respawnShieldTimes.put(p.getName(), ResPwn.calcTimer(ResPwn.respawnShieldTimer));	
		
		// Log respawn event
		if (ResPwn.logEnabled) 
		{
			ResPwn.logToFile("Player " + p.getDisplayName() + " respawn timer activated: " + ResPwn.respawnShieldTimer);
		}
		
		// Send them a message 
		p.sendMessage("�cRespawn shield activated for �6" + ResPwn.respawnShieldTimer / 1000 + "�c seconds.");
		
	}
	
	public static boolean isShielded(EntityDamageByEntityEvent e)
	{
		
		// Get the event world
		World w = e.getDamager().getWorld();
		
		// We should check to see if plugin is enabled first.
		if (!ResPwn.isEnabledIn(w.getName())) return false; 

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
			// attacker is NOT a player - do we want to do anything with this knowledge?
		}
		
		// Check if damage is done TO a player
		if(e.getEntity() instanceof Player) 
		{
			victim = (Player)e.getEntity();
		}
		else 
		{
			// victim is NOT a player - do we want to do anything with this knowledge?
		}

		boolean victimShield = false;
		boolean attackerShield = false;
		
		// 1. Check if the VICTIM is a PLAYER in the respawn timer
		if (victim instanceof Player)
		{
			if(ResPwn.respawnShieldTimes.containsKey(victim.getName()))
            {

                Long respTime = ResPwn.respawnShieldTimes.get(victim.getName());
                Long currTime = System.currentTimeMillis();
                
                if(respTime > currTime) 
                {
                	// Shield the victim from damage if they are in shielded mode
                	victimShield = true;
                	
                	// If the attacker is a player, tell them their victim is shielded right now
                	if (attacker instanceof Player)
            		{
            			attacker.sendMessage("�c" + victim.getName() + " has respawn shield for �6" + (respTime / 1000 - currTime / 1000) + "�c seconds.");
            		}	
                }            	
            }			
		}

		// 2. Check if ATTACKER is in the respawn timer
		if (attacker instanceof Player)
		{
	        if(ResPwn.respawnShieldTimes.containsKey(attacker.getName())) 
	        {
	        	
	            Long respTime = ResPwn.respawnShieldTimes.get(attacker.getName());
	            Long currTime = System.currentTimeMillis();
	             
	            // If attacker is shielded AND clearonAttack is false then keep them shielded and give a warning
	            if((respTime > currTime) && (!ResPwn.clearOnAttack))
	            {
	            	attacker.sendMessage("�cRespawn shield on, no attacking for �6" + (respTime / 1000 - currTime / 1000) + "�c seconds.");
	            	attackerShield = true;
	            } 
	            // If attacker is shielded AND clearonAttack is true, remove the shield and let them know
	            else if (respTime > currTime)
	            {
	                if(ResPwn.clearOnAttack) 
	                {
	                	ResPwn.respawnShieldTimes.remove(attacker.getName());
	                	attacker.sendMessage("�cYour respawn shield has been removed due to attacking!");
	                }
	            }
	            // Shield time has run out, remove the shield, no need to say anything here
	            else 
	            {
	            	ResPwn.respawnShieldTimes.remove(attacker.getName());
	            }
	        }			
		}

        // 3. Tell the event listener if the event should be cancelled or not
        if (attackerShield && victimShield) 
        {
        	return true;
        }
        else if (victimShield)
        {
        	return true;
        }
        else if (attackerShield)
        {
        	return true;
        }
        else 
        {
        	return false;
        }
				
	}
	
}