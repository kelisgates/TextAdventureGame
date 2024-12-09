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

	private WorldGenerator worldGenerator;
	private FileReader fileReader;
	
	private HashMap<String, Location> gameLocations;
//	private HashMap<String, Hazard> gameHazards;
//	private HashMap<String, NPC> gameNPCs;
//	private HashMap<String, Item> gameItems;

	/**
	 * World Manager constructor.
	 * 
	 * @param gameFiles the FileReader instance containing game data
	 */
	public WorldManager() {
		this.worldGenerator = new WorldGenerator(this.gameLocations);
		
		this.gameLocations = this.worldGenerator.getGameLocations();
//		this.gameHazards = this.worldGenerator;
//		this.gameNPCs = this.worldGenerator;
//		this.gameItems = this.worldGenerator;
		
	}

	/**
	 * Retrieves the starting location.
	 * 
	 * @return starting Location
	 */
	public Location getStartingLocation() {
		return this.worldGenerator.getStartingLocation();
	}

	/**
	 * Shuffles the world layout.
	 */
	public void shuffleWorld() {
		this.worldGenerator.generateWorld();
	}

	/**
	 * Retrieves all game locations.
	 * 
	 * @return gameLocations
	 */
	public HashMap<String, Location> getGameLocations() {
		return this.worldGenerator.getGameLocations();
	}
}
