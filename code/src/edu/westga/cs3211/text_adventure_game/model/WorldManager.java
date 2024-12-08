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
	private FileReader gameFiles;

	/**
     * World Manager constructor.
     * 
     * @param gameFiles the FileReader instance containing game data
     */
    public WorldManager(FileReader gameFiles) {
        this.gameFiles = gameFiles;
        this.worldGenerator = new WorldGenerator(gameFiles);
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
}
