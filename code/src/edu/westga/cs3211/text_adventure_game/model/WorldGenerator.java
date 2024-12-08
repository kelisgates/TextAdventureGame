package edu.westga.cs3211.text_adventure_game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class WorldGenerator {

    private FileReader gameFiles;
    private HashMap<String, Location> gameLocations;
    private Location[][] worldGrid;
	
    /**
     * WordGenerator Cponstructor
     * 
     * @param gameFiles files to read
     */
    public WorldGenerator(FileReader gameFiles) {
        this.gameFiles = gameFiles;
        this.gameLocations = gameFiles.getLocations();
        this.worldGrid = new Location[3][3];
        this.generateWorld();
    }
	
    public void generateWorld() {
        List<Location> locations = new ArrayList<>(this.gameLocations.values());
        Collections.shuffle(locations);

        int index = 0;
        for (int i = 0; i < worldGrid.length; i++) {
            for (int j = 0; j < worldGrid[i].length; j++) {
                if (index < locations.size()) {
                    worldGrid[i][j] = locations.get(index);
                    index++;
                } else {
                    worldGrid[i][j] = null;
                }
            }
        }

        this.setConnectedRooms();
    }
	
	 private void setConnectedRooms() {
	        for (int i = 0; i < worldGrid.length; i++) {
	            for (int j = 0; j < worldGrid[i].length; j++) {
	                Location currentLocation = worldGrid[i][j];
	                if (currentLocation != null) {
	                    String[] connectedRooms = new String[4]; 
	                    connectedRooms[0] = (i > 0 && worldGrid[i - 1][j] != null) ? worldGrid[i - 1][j].getRoomName() : "";
	                    connectedRooms[1] = (j < worldGrid[i].length - 1 && worldGrid[i][j + 1] != null) ? worldGrid[i][j + 1].getRoomName() : "";
	                    connectedRooms[2] = (i < worldGrid.length - 1 && worldGrid[i + 1][j] != null) ? worldGrid[i + 1][j].getRoomName() : "";
	                    connectedRooms[3] = (j > 0 && worldGrid[i][j - 1] != null) ? worldGrid[i][j - 1].getRoomName() : "";
	                    currentLocation.setConnectedRooms(connectedRooms);
	                }
	            }
	        }
	    }
	
	 /**
	     * Gets the starting location (EntryRoom)
	     * 
	     * @return starting location
	     */
	    public Location getStartingLocation() {
	        for (Location[] row : worldGrid) {
	            for (Location loc : row) {
	                if (loc != null && loc.getRoomName().equals("EntryRoom")) {
	                    return loc;
	                }
	            }
	        }
	        return null;
	    }

	    /**
	     * Gets the game locations
	     * 
	     * @return gameLocations
	     */
	    public HashMap<String, Location> getGameLocations() {
	        return this.gameLocations;
	    }
}
