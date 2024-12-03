package edu.westga.cs3211.text_adventure_game.model;

import java.util.HashMap;

/**
 * Text Adventure Game - World Manager
 * 
 * Handles the world loading and management.
 * 
 * @author Shawn Bretthauer
 * @version Fall 2024
 */
public class WorldManager {
	
	private final String startingLocation = "EntryRoom";
	private final String gameOverYouLoseText = "Looks like you lost all of your health. Why am I talking. You're dead, you can't hear me.\nRelaunch the game to play again.";
	
	private FileReader gameFiles;
	private WorldGenerator generatedWorld;
	private Player player;
	private Location playerLocation;
	
	private HashMap<String, Location> gameLocations;
	
	/**
	 * World Manager constructor
	 */
	public WorldManager() {
		this.gameFiles = new FileReader();
		this.generatedWorld = new WorldGenerator();
		this.player = new Player();
		
		this.initializedWorld();
	}
	
	private void initializedWorld() {
		this.generatedWorld.generateWorld();
		this.generatedWorld.getGameLocations();
	}
	
	private void removeItemFromRoomPlaceInPlayerInventory(Item item) {
		player.PutInInventory(location.RemoveItem(item));
	}
	
	private void removeItemFromInventoryPlaceInRoom(Item item) {
		playerLocation.placeItem(player.RemoveItem(item));
	}
}
