package com.pwn9.ResPwn;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Shield
{
	
	public static void doShield(Player p, World w)
	{
		
		// We should check to see if plugin is enabled first.
		if (!ResPwn.isEnabledIn(w.getName())) return; 
	
		// Check is player has permission to use shield
		if (!p.hasPermission("respwn.shield")) return;
			
		// Add this respawned player to the respawn timer
		ResPwn.respawnShieldTimes.put(p.getName(), ResPwn.calcTimer(ResPwn.respawnShieldTimer));	
		
		// Log respawn event
		if (ResPwn.logEnabled) 
		{
			ResPwn.logToFile("Player " + p.getDisplayName() + " respawn combat timer activated: " + ResPwn.respawnShieldTimer);
		}
		
		// Send them a message
		String msg = ChatColor.GOLD + "Combat shield activated for " + ChatColor.RED + ResPwn.respawnShieldTimer / 1000 + ChatColor.GOLD + " seconds.";
		
		ResPwn.pwnMessage(p, msg);
		
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
            			String msg = ChatColor.GOLD + victim.getName() + " has respawn shield for " + ChatColor.RED + (respTime / 1000 - currTime / 1000) + ChatColor.GOLD + " seconds.";
            			ResPwn.pwnMessage(attacker, msg, "shield");
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
	            	String msg = ChatColor.GOLD + "Shield on, no attacking for " + ChatColor.RED + (respTime / 1000 - currTime / 1000) + ChatColor.GOLD + " seconds.";
	            	ResPwn.pwnMessage(attacker, msg, "shield");
	            	attackerShield = true;
	            } 
	            // If attacker is shielded AND clearonAttack is true, remove the shield and let them know
	            else if (respTime > currTime)
	            {
	                if(ResPwn.clearOnAttack) 
	                {
	                	ResPwn.respawnShieldTimes.remove(attacker.getName());
	                	String msg = ChatColor.GOLD + "Your shield has been removed due to attacking!";
	                	ResPwn.pwnMessage(attacker, msg, "shield");
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

	public static void doTpShield(Player p, World w) 
	{
		
		// We should check to see if plugin is enabled first.
		if (!ResPwn.isEnabledIn(w.getName())) return; 

		// Check is player has permission to use shield
		if (!p.hasPermission("respwn.tpshield")) return;
		
		// Add this respawned player to the respawn timer
		ResPwn.respawnTpShieldTimes.put(p.getName(), ResPwn.calcTimer(ResPwn.respawnTpTimer));	
		
		// Log respawn event
		if (ResPwn.logEnabled) 
		{
			ResPwn.logToFile("Player " + p.getDisplayName() + " respawn teleport timer activated: " + ResPwn.respawnTpTimer);
		}
		
		// Send them a message 
		String msg = ChatColor.GOLD + "Teleport shield activated for " + ChatColor.RED + ResPwn.respawnTpTimer / 1000 + ChatColor.GOLD + " seconds.";
		ResPwn.pwnMessage(p, msg);
	}

	public static boolean isShielded(PlayerTeleportEvent e) 
	{
		
		// Get the event world
		World w = e.getFrom().getWorld();
		
		// We should check to see if plugin is enabled first.
		if (!ResPwn.isEnabledIn(w.getName())) return false; 
		
		// Get the player
		Player p = e.getPlayer();
		
		// Check if player is in the timer
		if(ResPwn.respawnTpShieldTimes.containsKey(p.getName()))
        {

            Long respTime = ResPwn.respawnTpShieldTimes.get(p.getName());
            Long currTime = System.currentTimeMillis();
            
            if(respTime > currTime) 
            {
            	// Player is still shielded
            	String msg = ChatColor.GOLD + "Cannot teleport for " + ChatColor.RED + (respTime / 1000 - currTime / 1000) + ChatColor.GOLD + " seconds.";
            	ResPwn.pwnMessage(p, msg, "tpShield");
            	return true;
            } 
            else 
            {
            	ResPwn.respawnTpShieldTimes.remove(p.getName());
            	return false;
            }
        }
		else 
		{
			return false;
		}
		
	}

	public static void doCommandShield(Player p, World w) 
	{
		
		// We should check to see if plugin is enabled first.
		if (!ResPwn.isEnabledIn(w.getName())) return; 
	
		// Check is player has permission to use shield
		if (!p.hasPermission("respwn.cmdshield")) return;
			
		// Add this respawned player to the respawn timer
		ResPwn.respawnCommandShieldTimes.put(p.getName(), ResPwn.calcTimer(ResPwn.respawnCommandTimer));	
		
		// Log respawn event
		if (ResPwn.logEnabled) 
		{
			ResPwn.logToFile("Player " + p.getDisplayName() + " respawn command timer activated: " + ResPwn.respawnCommandTimer);
		}
		
		// Send them a message 
		String msg = ChatColor.GOLD + "Command shield activated for " + ChatColor.RED + ResPwn.respawnCommandTimer / 1000 + ChatColor.GOLD + " seconds.";
		ResPwn.pwnMessage(p, msg);
		
	}
	
	public static boolean isShielded(PlayerCommandPreprocessEvent e) 
	{
		
		// Get the event world
		World w = e.getPlayer().getWorld();
		
		// We should check to see if plugin is enabled first.
		if (!ResPwn.isEnabledIn(w.getName())) return false; 
		
		// Get the player
		Player p = e.getPlayer();
		
		// Check if player is in the timer
		if(ResPwn.respawnCommandShieldTimes.containsKey(p.getName()))
        {

            Long respTime = ResPwn.respawnCommandShieldTimes.get(p.getName());
            Long currTime = System.currentTimeMillis();
            
            if (respTime > currTime) 
            {
            	// Player is still shielded
            	String msg = ChatColor.GOLD + "Cannot use commands for " + ChatColor.RED + (respTime / 1000 - currTime / 1000) + ChatColor.GOLD + " seconds.";
            	ResPwn.pwnMessage(p, msg, "commandShield");
            	return true;
            } 
            else 
            {
            	ResPwn.respawnCommandShieldTimes.remove(p.getName());
            	return false;
            }
        }
		else 
		{
			return false;
		}
	}

	// Armor delay 
	public static boolean isShielded(PlayerRespawnEvent e) 
	{
		Player p = e.getPlayer();
		
		if (ResPwn.players.getConfig().contains(p.getUniqueId().toString()))
		{
			Long respTime = ResPwn.players.getConfig().getLong(p.getUniqueId().toString()+".ArmorTime");
            Long currTime = System.currentTimeMillis();
            
            if (respTime > currTime) 
            {
            	// Player is still shielded and can't get armor
            	String timeLeft = ResPwn.getStrTime(respTime - currTime);
            	String msg = ChatColor.GOLD + "Armor kit delay remaining: " + ChatColor.RED + timeLeft + ChatColor.GOLD + ".";
            	ResPwn.pwnMessage(p, msg, "armorDelay");
            	return true;	
            }
            else 
            {
            	return false;
            }
		}
		else 
		{
			return false;
		}
		
	}
	
}