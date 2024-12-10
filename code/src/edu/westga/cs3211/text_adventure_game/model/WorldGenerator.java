package edu.westga.cs3211.text_adventure_game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * World Generator Class to randomize the room locations
 * 
 * @author Shawn Bretthauer, MoriaEl, kg00281
 * 
 * @version Fall 2024
 */
public class WorldGenerator {

	private FileReader fileReader;
	private HashMap<String, Location> gameLocations;
    private Location[][] worldGrid;
	
    /**
     * WordGenerator Cponstructor
     * 
     * @param gameLocations gameLocations
     */
    public WorldGenerator(HashMap<String, Location> gameLocations) {
    	this.fileReader = new FileReader();
        this.gameLocations = this.fileReader.getLocations();
        
        this.worldGrid = new Location[3][3];
        this.generateWorld();
    }
	
    /**
     * Randomized the connected rooms
     */
    public void generateWorld() {
        List<Location> locations = new ArrayList<>(this.gameLocations.values());
        Collections.shuffle(locations);

        int index = 0;
        for (int i = 0; i < this.worldGrid.length; i++) {
            for (int j = 0; j < this.worldGrid[i].length; j++) {
                if (index < locations.size()) {
                	this.worldGrid[i][j] = locations.get(index);
                    index++;
                } else {
                	this.worldGrid[i][j] = null;
                }
            }
        }

        this.setConnectedRooms();
    }
    
    //TODO: Set up if statement due to inline conditionals cause checkstyle problem
//    private void setConnectedRooms() {
//		for (int i = 0; i < this.worldGrid.length; i++) {
//			for (int j = 0; j < this.worldGrid[i].length; j++) {
//				Location currentLocation = this.worldGrid[i][j];
//		        if (currentLocation != null) {
//		            String[] connectedRooms = new String[4]; 
//		            connectedRooms[0] = (i > 0 && this.worldGrid[i - 1][j] != null) ? this.worldGrid[i - 1][j].getRoomName() : "";
//		            connectedRooms[1] = (j < this.worldGrid[i].length - 1 && this.worldGrid[i][j + 1] != null) ? this.worldGrid[i][j + 1].getRoomName() : "";
//		            connectedRooms[2] = (i < this.worldGrid.length - 1 && this.worldGrid[i + 1][j] != null) ? this.worldGrid[i + 1][j].getRoomName() : "";
//		            connectedRooms[3] = (j > 0 && this.worldGrid[i][j - 1] != null) ? this.worldGrid[i][j - 1].getRoomName() : "";
//		                currentLocation.setConnectedRooms(connectedRooms);
//	            }
//	        }
//	    }
//	}
    
    private void setConnectedRooms() {
        for (int i = 0; i < this.worldGrid.length; i++) {
            for (int j = 0; j < this.worldGrid[i].length; j++) {
                Location currentLocation = this.worldGrid[i][j];
                if (currentLocation != null) {
                    String[] connectedRooms = new String[4];

                    if (i > 0 && this.worldGrid[i - 1][j] != null) {
                        connectedRooms[0] = this.worldGrid[i - 1][j].getRoomName();
                    } else {
                        connectedRooms[0] = "";
                    }

                    if (j < this.worldGrid[i].length - 1 && this.worldGrid[i][j + 1] != null) {
                        connectedRooms[1] = this.worldGrid[i][j + 1].getRoomName();
                    } else {
                        connectedRooms[1] = "";
                    }

                    if (i < this.worldGrid.length - 1 && this.worldGrid[i + 1][j] != null) {
                        connectedRooms[2] = this.worldGrid[i + 1][j].getRoomName();
                    } else {
                        connectedRooms[2] = "";
                    }

                    if (j > 0 && this.worldGrid[i][j - 1] != null) {
                        connectedRooms[3] = this.worldGrid[i][j - 1].getRoomName();
                    } else {
                        connectedRooms[3] = "";
                    }

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
        for (Location[] row : this.worldGrid) {
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
    
    /**
     * Gets the game locations
     * 
     * @return gameLocations
     */
    public HashMap<String, Location> getGameHazards() {
        return this.gameLocations;
    }
}
