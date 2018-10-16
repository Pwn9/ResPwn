package com.pwn9.ResPwn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.pwn9.ResPwn.ConfigAccessor;
import com.pwn9.ResPwn.MetricsLite;

public class ResPwn extends JavaPlugin 
{
	// For convenience, a reference to the instance of this plugin
    public static ResPwn instance;	
	
    // Get the datafolder
	public static File dataFolder;
	
	/*** Init configurable values ***/
	
	// Get enabled worlds
	public static List<String> enabledWorlds;
	
	// Get logging enabled
	public static Boolean logEnabled;
	
	// Get prefix configurations
	public static Boolean prefixEnabled;
	public static String prefixMsg;
	public static String prefixMsgColor;
	public static String prefixWrapFront;
	public static String prefixWrapBack;
	public static String prefixWrapColor;
	
	// Respawn timer config setting, default 20
	public static long respawnShieldTimer = 20000;
	
	// Respawn teleport timer config setting, default 20
	public static long respawnTpTimer = 20000;

	// Respawn command timer config setting, default 20
	public static long respawnCommandTimer = 20000;
	
	// Clear on attack - for shield timer
	public static Boolean clearOnAttack;
	
	// Player health & hunger
	public static int respawnHealth = 20;
	public static int respawnHunger = 20;
	
	// Armor delay timer config setting, default 600 (10 mins)
	public static long armorDelay = 600000;
	
	// Inventory Booleans
	public static Boolean respawnBootsUse;
	public static Boolean respawnHelmUse;
	public static Boolean respawnPantsUse;
	public static Boolean respawnPlateUse;	
	public static Boolean respawnWieldUse;	
	public static Boolean respawnOffhandUse;	
	// Armor
	public static String respawnBoots;
	public static String respawnHelm;
	public static String respawnPants;
	public static String respawnPlate;
	// Armor enchant maps
	public static Map<String, Object> respawnBootsEnchants = new HashMap<String, Object>();
	public static Map<String, Object> respawnHelmEnchants = new HashMap<String, Object>();
	public static Map<String, Object> respawnPantsEnchants = new HashMap<String, Object>();
	public static Map<String, Object> respawnPlateEnchants = new HashMap<String, Object>();
	// Armor names
	public static String respawnBootsName;
	public static String respawnHelmName;
	public static String respawnPantsName;
	public static String respawnPlateName;	
	// Armor lore
	public static List<String> respawnBootsLore;
	public static List<String> respawnHelmLore;
	public static List<String> respawnPantsLore;
	public static List<String> respawnPlateLore;
	// Armor colors (if leather)
	public static String respawnBootsColor;
	public static String respawnHelmColor;
	public static String respawnPantsColor;
	public static String respawnPlateColor;	
	
	// Item in main hand
	public static String respawnWield;
	public static Map<String, Object> respawnWieldEnchants = new HashMap<String, Object>();
	public static String respawnWieldName;
	public static List<String> respawnWieldLore;

	// Item in off hand
	public static String respawnOffhand;
	public static Map<String, Object> respawnOffhandEnchants = new HashMap<String, Object>();
	public static String respawnOffhandName;
	public static List<String> respawnOffhandLore;
	
	/*** End configurable values ***/
	
	/*** Init other mappings and variables ***/
	
	// Setup respawn shield time player lists. 
	public static HashMap<String, Long> respawnShieldTimes = new HashMap<String, Long>();
	
	// Setup respawn tpblock timer player lists.
	public static HashMap<String, Long> respawnTpShieldTimes = new HashMap<String, Long>();

	// Setup respawn tpblock timer player lists.
	public static HashMap<String, Long> respawnCommandShieldTimes = new HashMap<String, Long>();
	
 	// Last message map, designed to reduce sendmessage spam - player, lastmessagetime
	public static Map<UUID, Map<String, Long>> lastMessage = new HashMap<UUID, Map<String, Long>>();	
	
	/*** End other values ***/
	
	public static ConfigAccessor players;
	
	// Things to do when the plugin starts
	public void onEnable() 
	{
		// Create an instance of this, for some reason, I forget why.
		instance = this;
		 
		// Get the config.yml stuffs
		this.saveDefaultConfig();
		
		// Start Metrics
		MetricsLite metricslite = new MetricsLite(this);

		// Setup listeners
		new PlayerListener(this);
			
		// Get data folder
		ResPwn.dataFolder = getDataFolder();
		
		// Config accessor
		players = new ConfigAccessor(this, "players.yml");
		
		// Load Configurable Values
		Config.LoadConfig();

	}
	
	public void onDisable() 
	{
		players.saveConfig();
	}
	
	/*** Utility Section - Stuff that does stuff ***/
	
	// Boolean to return if the plugin is enabled in a specific world
	public static boolean isEnabledIn(String world) 
	{
		return enabledWorlds.contains(world);
	}	
	
	// Boolean to check if player is in the respawn timer
	public boolean inShieldTimer(String player) 
	{
		  if(respawnShieldTimes.containsKey(player)) 
	      {
	    	  	return (respawnShieldTimes.get(player) < System.currentTimeMillis());
	      }
	      return true;
	}
	
	// Generic function to return the calculated time
	public static long calcTimer(Long time) 
	{
		return System.currentTimeMillis() + time;
	}
	
	// Special logging function
    public static void logToFile(String message) 
    {   
    	try 
    	{
		    if(!dataFolder.exists()) 
		    {
		    	dataFolder.mkdir();
		    }
		     
		    File saveTo = new File(dataFolder, "ResPwn.log");
		    if (!saveTo.exists())  
		    {
		    	saveTo.createNewFile();
		    }
		    
		    FileWriter fw = new FileWriter(saveTo, true);
		    PrintWriter pw = new PrintWriter(fw);
		    pw.println(getDate() +" "+ message);
		    pw.flush();
		    pw.close();
	    } 
	    catch (IOException e) 
	    {
	    	e.printStackTrace();
	    }
    }	
    
    // PwnMessaging System - antispam with message type
    public static void pwnMessage(Player p, String msg, String msgType)
    {
		if(ResPwn.lastMessage.containsKey(p.getUniqueId()))
        {
			Map<String, Long> lastSent = ResPwn.lastMessage.get(p.getUniqueId());
			if (lastSent.containsKey(msgType)) 
			{
	            Long lastTime = lastSent.get(msgType);
	            Long currTime = System.currentTimeMillis();
	            
	            if(currTime > lastTime) 
	            {
	            	lastSent.put(msgType, ResPwn.calcTimer((long) 5000));
					ResPwn.lastMessage.put(p.getUniqueId(), lastSent);
	            }
	            else 
	            {
	            	// Last message was sent too recently, block this message to avoid spammyness.
	            	return;
	            }				
			}
			else
			{
				HashMap<String, Long> msgMap = new HashMap<String, Long>();
	        	msgMap.put(msgType, ResPwn.calcTimer((long) 5000));
				ResPwn.lastMessage.put(p.getUniqueId(), msgMap);				
			}
        }
		else 
		{
			HashMap<String, Long> msgMap = new HashMap<String, Long>();
        	msgMap.put(msgType, ResPwn.calcTimer((long) 5000));
			ResPwn.lastMessage.put(p.getUniqueId(), msgMap);
		}

    	if (ResPwn.prefixEnabled)
    	{
    		p.sendMessage(ChatColor.valueOf(ResPwn.prefixWrapColor) + ResPwn.prefixWrapFront + ChatColor.valueOf(ResPwn.prefixMsgColor) + ResPwn.prefixMsg + ChatColor.valueOf(ResPwn.prefixWrapColor) + ResPwn.prefixWrapBack + msg);
    	}
    	else 
    	{
    		p.sendMessage(msg);
    	}
    }
    

    // PwnMessaging System - antispam bypass message
    public static void pwnMessage(Player p, String msg)
    {
    	if (ResPwn.prefixEnabled)
    	{
    		p.sendMessage(ChatColor.valueOf(ResPwn.prefixWrapColor) + ResPwn.prefixWrapFront + ChatColor.valueOf(ResPwn.prefixMsgColor) + ResPwn.prefixMsg + ChatColor.valueOf(ResPwn.prefixWrapColor) + ResPwn.prefixWrapBack + msg);
    	}
    	else 
    	{
    		p.sendMessage(msg);
    	}
    }
    
    // Date for logging
    public static String getDate() 
    {
		  String s;
		  Format formatter;
		  Date date = new Date(); 
		  formatter = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]");
		  s = formatter.format(date);
		  return s;
    }
    
    /**
     * Convert a millisecond duration to a string format
     * 
     * @param millis A duration to convert to a string form
     * @return A string of the form "X Days Y Hours Z Minutes A Seconds".
     */
    public static String getStrTime(long millis)
    {
        if(millis < 0)
        {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(64);
        if (days > 0)
        {
	        sb.append(days);
	        sb.append(" Days ");
        }
        if (hours > 0)
        {
	        sb.append(hours);
	        sb.append(" Hours ");
        }
        if (minutes > 0)
        {
	        sb.append(minutes);
	        sb.append(" Mins ");
        }
        if (seconds >= 0)
        {
	        sb.append(seconds);
	        sb.append(" Secs");
        }
        
        return(sb.toString());
    }    
	
}