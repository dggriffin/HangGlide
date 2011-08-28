package me.salamijack.HangGlide;

//import org.bukkit.BlockFace;

import org.bukkit.ChatColor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.NoteBlock;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.material.MaterialData;



public class HangGlideBlockListener extends BlockListener{
	
    private final HangGlide plugin;

    public HangGlideBlockListener(final HangGlide plugin) {
        this.plugin = plugin;
    }


	
}
