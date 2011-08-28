package me.salamijack.HangGlide;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleListener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.util.Vector;

public class HangGlideVehicleListener extends VehicleListener{
	
	
	private final HangGlide plugin;
	private static final Logger log = Logger.getLogger("Minecraft");
    public HangGlideVehicleListener(HangGlide instance) 
    {
        plugin = instance;

        
        
        
    }
    
    
   public void onVehicleExit (VehicleExitEvent event)
   {
	   
   }
   
   public void onVehicleMove(VehicleMoveEvent event)
   {
	   if ( event.getVehicle() instanceof Chicken)
	   {
		   
		   Chicken chick = (Chicken) event.getVehicle();
		   Player player = (Player)chick.getPassenger();
		   player.sendMessage("Lol chicken moving");
		   
		   Location below = chick.getLocation();
	    	
	    	below.setY((below.getY())-1);
	    	
	    	Block blockBelow = below.getBlock();
	    	Snowball ride = player.throwSnowball();
    		
    		Vector projvelo = ride.getVelocity();
	    	chick.setVelocity(projvelo);
			
			if (blockBelow.getType() != Material.AIR)
			{
				chick.eject();
				chick.setHealth(0);
			}
		}
	    	
	    	
	    		
	    				
	    				
		   
		   
	   }
	   
   }


