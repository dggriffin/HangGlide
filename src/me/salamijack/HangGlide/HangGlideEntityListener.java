package me.salamijack.HangGlide;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.inventory.ItemStack;

public class HangGlideEntityListener extends EntityListener{
	
	private final HangGlide plugin;
	
	public HangGlideEntityListener(final HangGlide plugin)
	{
		this.plugin = plugin;
	}
	
	
	//Checks to make sure arrow is not currently flying
	
	 public void onProjectileHit( ProjectileHitEvent event)
	    {
	    	
	    }
	    
	
	
	public void onEntityDamage(EntityDamageEvent event)
	{
		
		
	}
	
	

}
