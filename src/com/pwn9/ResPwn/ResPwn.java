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

import org.bukkit.plugin.java.JavaPlugin;

import com.pwn9.ResPwn.MetricsLite;

public class ResPwn extends JavaPlugin 
{
	//for convenience, a reference to the instance of this plugin
    public static ResPwn instance;	
	
	public static File dataFolder;
	
	/*** Init configurable values ***/
	
	// Get enabled worlds
	public static List<String> enabledWorlds;
	
	// Get logging enabled
	public static Boolean logEnabled;
	
	// Respawn timer config setting, default 20
	public static long respawnShieldTimer = 20000;
	
	// Clear on attack
	public static Boolean clearOnAttack;
	
	// Player health
	public static int respawnHealth = 20;
	
	// Player hunger
	public static int respawnHunger = 20;
	
	
	/*** End configurable values ***/
	
	// Setup respawn shield time player lists. 
	public static HashMap<String, Long> respawnShieldTimes = new HashMap<String, Long>();
	
	public void onEnable() 
	{
		instance = this;
		 
		this.saveDefaultConfig();
		
		// Start Metrics
		try 
		{
		    MetricsLite metricslite = new MetricsLite(this);
		    metricslite.start();
		} 
		catch (IOException e) 
		{
		    // Failed to submit the stats :-(
		}
	    
		// Setup listeners
		new PlayerListener(this);
			
		// Get data folder
		ResPwn.dataFolder = getDataFolder();
		
		/*** Configurable Values ***/
		
		// Get enabled worlds
		ResPwn.enabledWorlds = getConfig().getStringList("enabled_worlds");		
		
		// Get logging enabled
		ResPwn.logEnabled = getConfig().getBoolean("debug_log");
		
		// Respawn timer config setting, default 20
		ResPwn.respawnShieldTimer = getConfig().getInt("respawn_shield_timer", 20) * 1000;
		
		// Clear on attack
		ResPwn.clearOnAttack = getConfig().getBoolean("clear_on_attack");
		
		// Player health
		ResPwn.respawnHealth =  getConfig().getInt("respawn_health", 20);
		
		// Player hunger
		ResPwn.respawnHunger =  getConfig().getInt("respawn_hunger", 20);
	}
	
	public void onDisable() 
	{
		
	}
	
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
	
}