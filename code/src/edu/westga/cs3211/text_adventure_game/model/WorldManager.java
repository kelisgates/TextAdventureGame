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
	
	private HashMap<String, Location> gameLocations;

	/**
	 * World Manager constructor.
	 */
	public WorldManager() {
		this.worldGenerator = new WorldGenerator(this.gameLocations);
		
		this.gameLocations = this.worldGenerator.getGameLocations();
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
		this.worldGenerator.generateWorld(true);
	}

	/**
	 * Retrieves all game locations.
	 * 
	 * @return gameLocations
	 */
	public HashMap<String, Location> getGameLocations() {
		return this.worldGenerator.getGameLocations();
	}
	
	/**
	 * Prints the game world as a matrix.
	 */
	public void printWorldGrid() {
	    System.out.println("Game World Map:");
	    Location[][] worldGrid = this.worldGenerator.getWorldGrid();

	    for (int row = 0; row < worldGrid.length; row++) {
	        for (int col = 0; col < worldGrid[row].length; col++) {
	            if (worldGrid[row][col] != null) {
	                System.out.print(worldGrid[row][col].getRoomName() + "\t");
	            } else {
	                System.out.print("Empty\t");
	            }
	        }
	        System.out.println();
	    }
	}
	
}
