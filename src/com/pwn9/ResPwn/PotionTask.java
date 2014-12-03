package com.pwn9.ResPwn;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionTask implements Runnable
{
    private Player player;
   
    public PotionTask(Player player) {
        this.player = player;
    }
       
    public void run() 
    {           
    	String PotionList = "";
    	
	    for(String key : ResPwn.instance.getConfig().getConfigurationSection("respawn_potions").getKeys(false))
	    {
	    	Integer duration = ResPwn.instance.getConfig().getInt("respawn_potions."+key+".duration");
	    	Integer amplifier = ResPwn.instance.getConfig().getInt("respawn_potions."+key+".amplifier");
	    	player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(key), duration, amplifier));
	    	ResPwn.logToFile("Added potion "+ key + " to player "+ player.getDisplayName().toString() + " for duration: " + duration + " and amp: " + amplifier);
	    	PotionList += key + ", ";
		}
	    ResPwn.pwnMessage(player, ChatColor.GOLD + "Potions Active: " + ChatColor.GREEN + PotionList, "potion");
	}
    
}
