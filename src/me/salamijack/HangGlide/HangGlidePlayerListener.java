package me.salamijack.HangGlide;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;





import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
//import org.bukkit.Player;
import org.bukkit.block.Block;
import org.bukkit.block.NoteBlock;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;



public class HangGlidePlayerListener extends PlayerListener
{
	

    private final HangGlide plugin;
    public Server server = null;
    
    public static Vector projvelo = null;
    //public Chicken chick = null;
    public static LinkedList<playerProfile> glidingPlayers = new LinkedList<playerProfile>();
    
    
 
    
   
    
    
    public HangGlidePlayerListener(HangGlide instance) 
    {
        plugin = instance;
   
        
    }
    
    public void onPlayerJoin(PlayerJoinEvent event)
    {
    	
    }
    
    public void onPlayerQuit(PlayerQuitEvent event)
    {
    	
    }
   
    public void onPlayerInteractEntity(	PlayerInteractEntityEvent event)
    {
    	Player player = event.getPlayer();
    	boolean found = false;
    	ItemStack is = player.getItemInHand();
    	
    	if( is.getType() == Material.SEEDS && event.getRightClicked() instanceof Chicken && player.isInsideVehicle() == false)
    	{
    		
    		for(int x = 0; x < glidingPlayers.size(); x++)
    		{
        		if (player.getName().equals(glidingPlayers.get(x).getPlayer().getName()))
    			{
        			player.sendMessage(ChatColor.RED + "You already caught a chicken!");
        			
        			
        			found = true;
        			
        			
                
                }
    		
    		
    		}	
    		
    		if(found == false)
    		{
    			
    			if(player.getItemInHand().getAmount() > 1)
	    		{
	    			player.getItemInHand().setAmount(player.getItemInHand().getAmount() -1);
	    		}
	    		else if (player.getItemInHand().getAmount() == 1)
	    		{
	    			player.setItemInHand(null);	 
	    		}
    			
    			
    			playerProfile me = new playerProfile(player);
    			
    			Chicken chick = (Chicken) event.getRightClicked();
    			
    			
    			chick.remove();
    			me.setHasChicken(true);
    			glidingPlayers.add(me);
    			player.sendMessage(ChatColor.GREEN + "You caught a chicken!");
    		}
    	}
    	
    	
    }

    
    
    public void onPlayerInteract(PlayerInteractEvent event)
    {
    
    	Player player = event.getPlayer();
    	Boolean found = false;
    	Action action = event.getAction();
    	Block target = event.getClickedBlock();
    	playerProfile me = null;
    	World current = player.getWorld();
    	ItemStack is = player.getItemInHand();
    	Location loc = player.getLocation();
    	Location locheck = player.getLocation();
    	
    
    	
  //if(HangGlidePermissions.getInstance().use(player) || HangGlidePermissions.getInstance().permissionsEnabled == false || player.hasPermission("hangglide.use"))
    
    
    	if( is.getType() == Material.FEATHER)
    	{
    		 if((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK))
    		    {
    		
    			 for(int x = 0; x < glidingPlayers.size(); x++)
    	    		{
    	        		if (player.getName().equals(glidingPlayers.get(x).getPlayer().getName()))
    	    			{
    	        			//player.sendMessage(ChatColor.RED + "You already caught a chicken!");
    	        			
    	        			me = glidingPlayers.get(x);
    	        			found = true;
    	        			
    	        			
    	                
    	                }
    	    		
    	    		
    	    		}	
    			 
    			 	if(found == false)
    			 	{
    			 		player.sendMessage(ChatColor.RED + "You don't have a chicken!");
    			 	}
    			 	else if (found == true)
    			 	{
    			 		 if(me.getChicken() == null)
    	    			 {
    	    				
    	    					if(player.getItemInHand().getAmount() > 1)
    	    		    		{
    	    		    			player.getItemInHand().setAmount(player.getItemInHand().getAmount() -1);
    	    		    		}
    	    		    		else if (player.getItemInHand().getAmount() == 1)
    	    		    		{
    	    		    			player.setItemInHand(null);	 
    	    		    		}
    	    		    			Snowball ride = player.throwSnowball();
    	    		    			// Vector velo = ride.getVelocity().multiply(.5);
    	    		    			projvelo = ride.getVelocity();
    	    		    			ride.remove();
    	    		
    	    		    			
    	    		    			Chicken chick = (Chicken) player.getWorld().spawnCreature(player.getLocation(), CreatureType.CHICKEN);
    	    		    			me.setChicken(chick);
    	    		    			chick.setVelocity(projvelo);
    	    		    			chick.setPassenger(player);
    	    		    			chick.setTarget(player);
    	    		    			
    	    		    			player.sendMessage(ChatColor.GREEN + "You fly down on your chicken!");
    	    				
    	    				 
    	    				 
    	    			 }
    	    			 
    	    			 else
    	    			 {
    	    				
    	    				 	
    	    				 	if(me.getBoostCount() < .4)
    	    				 	{
    	    				 		player.sendMessage(ChatColor.RED + "Your chicken is exhausted!");
    	    				 	}
    	    				 	else{
    	    				 		Chicken chick = me.getChicken();
    	    				 		
    	    		    			// Vector velo = ride.getVelocity().multiply(.5);
    	    		    			
    	    		    			
        	    				 	chick.setVelocity(projvelo.multiply(me.getBoostCount()));
        	    				 	chick.setTarget(player);
        	    				 	me.setBoostCount(me.getBoostCount()-.3);
        	    				 	
        	    				 	if(player.getItemInHand().getAmount() > 1)
        	    		    		{
        	    		    			player.getItemInHand().setAmount(player.getItemInHand().getAmount() -1);
        	    		    		}
        	    		    		else if (player.getItemInHand().getAmount() == 1)
        	    		    		{
        	    		    			player.setItemInHand(null);
        	    		    		}
    	    				 		
    	    				 	}
    	    				 	
    	    				 	
    	    					
    	    				
    	    			 }
    			 		
    			 		
    			 		
    			 		
    			 		
    			 	}
    			
    			
    			 
    			 
    			 	
    			 	
    		    }
    	}
    }
  

  
    
    public void onItemHeldChange (PlayerItemHeldEvent event)
    {
    	Player player = event.getPlayer();
    	playerProfile me = null;
    	boolean found = false;
    	
    	for(int x = 0; x < glidingPlayers.size(); x++)
		{
    		if (player.getName().equals(glidingPlayers.get(x).getPlayer().getName()))
			{
    			//player.sendMessage(ChatColor.RED + "You already caught a chicken!");
    			
    			me = glidingPlayers.get(x);
    			found = true;
    			
    			
            
            }
		
		
		}	
    	
    	if (found == true)
    	{
    		if(me.getChicken() != null)
        	{
    			Chicken chick = me.getChicken();
        		chick.eject();
        		chick.setHealth(0);
        		chick = null;
        		me.setChicken(null);
        		me.setHasChicken(false);
        		glidingPlayers.remove(me);
        		player.sendMessage(ChatColor.BLUE + "You chicken becomes uninterested and runs away.");
        	}
    	}
    	
			
    			
		
    }
    

    
    public void onPlayerMove (PlayerMoveEvent event)
    {
    	Player player = event.getPlayer();
    	ItemStack is = player.getItemInHand();
    	
    	Location below = player.getLocation();
    	
    	below.setY((below.getY())-1);
    	
    	Block blockBelow = below.getBlock();
    	
    	
    	
    	
    		if(player.isInsideVehicle() == true)
			{
    			if(player.getVehicle() instanceof Chicken)
    			{
    				Chicken chick = (Chicken) player.getVehicle();
    				chick.setVelocity(projvelo);
    			
    				if (blockBelow.getType() != Material.AIR)
    				{
    					chick.eject();
    					chick.setHealth(0);
    				}
    			}
    			
    			
    	
			}
    	
    		if(player.isInsideVehicle() == true)
    		{
    			player.setFallDistance(0);
    		}
    	}
    
   

    
    public static void waiting (double n){
        
        long t0, t1;

        t0 =  System.currentTimeMillis();

        do{
            t1 = System.currentTimeMillis();
        }
        while ((t1 - t0) < (n * 1000));
    }
}
 			
    			

	
//PLAYER PROFILE CLASS

class playerProfile
{
	
	
	public Player player;
	public boolean hasChicken;
	public Chicken myChicken = null;
	public double boostCount = 1;
	
	public playerProfile(Player name)
	{
		
		
		player = name;
	}
	
	public double getBoostCount()
	{
		return boostCount;
	}
	
	public void setBoostCount(double d)
	{
		boostCount = d;
	}

	public Chicken getChicken()
	{
		return myChicken;
	}
	
	public void setChicken(Chicken my)
	{
		myChicken = my;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	
	public void setHasChicken(boolean bol)
	{
		hasChicken = bol;
	}
	
	public boolean hasChicken()
	{
		return hasChicken;
	}
	
	
}
    		
    		
    	
			
	
